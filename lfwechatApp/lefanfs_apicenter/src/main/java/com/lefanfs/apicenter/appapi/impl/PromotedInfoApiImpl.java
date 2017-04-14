package com.lefanfs.apicenter.appapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefanfs.apicenter.appapi.PromotedInfoApi;
import com.lefanfs.apicenter.dao.*;
import com.lefanfs.apicenter.model.*;
import com.lefanfs.apicenter.service.RedisService;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.apicenter.util.HttpClientUtils;
import com.lefanfs.apicenter.util.QrUtils;
import com.lefanfs.apicenter.util.SendMessageUntil;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/3/8.
 */
@Service
@ApiService(descript = "推广相关API")
public class PromotedInfoApiImpl  extends BaseServiceImpl implements PromotedInfoApi{
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private PromotedInfoMapper promotedInfoMapper;

    @Autowired
    private UserPromotedMapper userPromotedMapper;

    @Autowired
    private CommonAreaMapper commonAreaMapper;

    @Autowired
    private MessageInfoMapper messageInfoMapper;
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Value("${mpwx_appId}")
    private String mpwxAppId;

    @Value("${mpwx_secret}")
    private String mpwxSecret;

    @Value("${redis_prefix}")
    private String redisPrefix;
    /**
     * 用户推广认证信息查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "用户推广认证信息查询", value = "user-promoted-indetails", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse<UserPromoted> selectUserPromotedInfoByuserId(ApiRequest apiReq) {
       /*Long userId = apiReq.getLong("userId");
       apiReq.put("userId",userId);*/
        Long userId =apiReq.getCurrentUserId();
        apiReq.put("userId",userId);
       UserPromoted userPromoted = this.userPromotedMapper.selectUserPromotedByUserId(apiReq);
        if(userPromoted!=null){
            //贷款申请省市区转换
            String provinceId = userPromoted.getProvince();
            String city = userPromoted.getCity();
            String district = userPromoted.getDistrict();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String[] aredIdArray =new String[]{provinceId,city,district};
            paramMap.put("areaList",aredIdArray);
            List<CommonArea>  commonAreas = commonAreaMapper.selectAreaByAreaIdList(paramMap);
            for(CommonArea commonArea:commonAreas){
                if(commonArea.getAreaType()==1)
                    userPromoted.setProvince(commonArea.getAreaName());
                else if(commonArea.getAreaType()==2)
                    userPromoted.setCity(commonArea.getAreaName());
                else  if(commonArea.getAreaType()==3)
                    userPromoted.setDistrict(commonArea.getAreaName());
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,userPromoted);
    }

    /**
     * 提交我的推广信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "提交推广认证信息", value = "insert-user-promoted", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)"),
            @ApiParam(descript = "真实姓名(*)", name = "realName"), @ApiParam(descript = "推广电话(*)", name = "phone"),
            @ApiParam(descript = "职业(*)", name = "occupation"),@ApiParam(descript = "省(*)", name = "province"),@ApiParam(descript = "城市(*)", name = "city"),
            @ApiParam(descript = "区域(*)", name = "district"), @ApiParam(descript = "推广微信号", name = "wechatId"), @ApiParam(descript = "提交表单ID", name = "formId")})
    @Override
    public ApiResponse insertUserPromoted(ApiRequest apiReq) throws Exception{
        Long userId =apiReq.getCurrentUserId();
        String realName = apiReq.getString("realName");
        String phone = apiReq.getString("phone");
        Integer occupation = apiReq.getInt("occupation");
        String province = apiReq.getString("province");
        String city = apiReq.getString("city");
        String district = apiReq.getString("district");
        String formId = apiReq.getString("formId");
        //增加推广人信息
        UserPromoted userPromoted = new UserPromoted();
        userPromoted.setUserId(userId);
        userPromoted.setRealName(realName);
        userPromoted.setPhone(phone);
        userPromoted.setOccupation(occupation);
        userPromoted.setProvince(province);
        userPromoted.setCity(city);
        userPromoted.setDistrict(district);
        userPromoted.setState(1);//待审核
        if(!StringUtils.isEmpty(apiReq.get("wechatId"))){
            userPromoted.setWechatId(apiReq.getString("wechatId"));
        }
        int result = this.userPromotedMapper.insertSelective(userPromoted);
       //发送短信
        if(result<=0){
            return new ApiResponse(ApiMsgEnum.FAIL,0,userPromoted);
        }
        SendMessageUntil.sendSmsUserPromoted(phone);//
        //系统推送短信
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setTitle("乐凡推广大使申请消息");
        messageInfo.setConent("您好，感谢您申请成为乐凡推广大使，我们会在24小时内与您联系，如需其他咨询，可致电24小时服务电话4006303071，赠人玫瑰手留余香！");
        messageInfo.setSenderId(0);
        messageInfo.setSenderName("系统消息");
        messageInfo.setReceiverId(userId);
        messageInfo.setReceiverName(realName);
        messageInfo.setMessageType(1);
        messageInfo.setSendTime(new Date());
        messageInfo.setDeleteFlag(0);
        this.messageInfoMapper.insertSelective(messageInfo);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月DD日 hh:mm:ss");
        String dataStr = simpleDateFormat.format(messageInfo.getSendTime());
        //添加推送模板消息
        UserLogin userLogin=this.userLoginMapper.selectByPrimaryKey(userId);
        String openId=userLogin.getWechatId();
        String msgStr="您好，感谢您申请成为乐凡推广大使，我们会在24小时内与您联系，如需其他咨询，可致电24小时服务电话4006303071，赠人玫瑰手留余香！";
        sendModelMessage(msgStr,openId,formId,realName,dataStr);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,userPromoted);
    }
    /**
     * 推广认证信息修改
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "推广认证信息修改", value = "update-user-promoted", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)"),@ApiParam(descript = "用户推广认证信息标识ID(*)", name = "id"),
            @ApiParam(descript = "真实姓名(*)", name = "realName"), @ApiParam(descript = "推广电话(*)", name = "phone"),
            @ApiParam(descript = "职业(*)", name = "occupation"),@ApiParam(descript = "省(*)", name = "province"),@ApiParam(descript = "城市(*)", name = "city"),
            @ApiParam(descript = "区域(*)", name = "district"), @ApiParam(descript = "推广微信号", name = "wechatId"), @ApiParam(descript = "提交表单ID", name = "formId")})
    @Override
    public ApiResponse updateUserPromoted(ApiRequest apiReq)  throws Exception{
        Long id = apiReq.getLong("id");
        //增加推广人信息

        Long  userId = apiReq.getCurrentUserId();
        UserPromoted userPromoted = new UserPromoted();
        userPromoted.setId(id);
        userPromoted.setUserId(userId);
        if(!StringUtils.isEmpty(apiReq.get("realName"))){
            userPromoted.setRealName(apiReq.getString("realName"));
        }
        if(!StringUtils.isEmpty(apiReq.get("phone"))){
            userPromoted.setPhone(apiReq.getString("phone"));
        }
        if(!StringUtils.isEmpty(apiReq.get("occupation"))){
            userPromoted.setOccupation(apiReq.getInt("occupation"));
        }
        if(!StringUtils.isEmpty(apiReq.get("province"))){
            userPromoted.setProvince(apiReq.getString("province"));
        }
        if(!StringUtils.isEmpty(apiReq.get("city"))){
            userPromoted.setCity(apiReq.getString("city"));
        }
        if(!StringUtils.isEmpty(apiReq.get("district"))){
            userPromoted.setDistrict(apiReq.getString("district"));
        }
        if(!StringUtils.isEmpty(apiReq.get("wechatId"))){
            userPromoted.setWechatId(apiReq.getString("wechatId"));
        }


        userPromoted.setState(1);
        int result = this.userPromotedMapper.updateByPrimaryKeySelective(userPromoted);

        //发送短信
        if(result<=0){
            return new ApiResponse(ApiMsgEnum.FAIL,0,userPromoted);
        }
        SendMessageUntil.sendSmsUserPromoted(userPromoted.getPhone());//
        //系统推送短信
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setTitle("乐凡推广大使申请消息");
        messageInfo.setConent("您好，感谢您申请成为乐凡推广大使，我们会在24小时内与您联系，如需其他咨询，可致电24小时服务电话4006303071，赠人玫瑰手留余香！");
        messageInfo.setSenderId(0);
        messageInfo.setSenderName("系统消息");
        messageInfo.setReceiverId(userId);
        messageInfo.setReceiverName(userPromoted.getRealName());
        messageInfo.setMessageType(1);
        messageInfo.setSendTime(new Date());
        messageInfo.setDeleteFlag(0);
        this.messageInfoMapper.insertSelective(messageInfo);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月DD日 hh:mm:ss");
        String dataStr = simpleDateFormat.format(messageInfo.getSendTime());
        //添加推送模板消息
        UserLogin userLogin=this.userLoginMapper.selectByPrimaryKey(userId);
        String openId=userLogin.getWechatId();
        String msgStr="您好，感谢您申请成为乐凡推广大使，我们会在24小时内与您联系，如需其他咨询，可致电24小时服务电话4006303071，赠人玫瑰手留余香！";

        String formId=apiReq.getString("province");
        sendModelMessage(msgStr,openId,formId,userPromoted.getRealName(),dataStr);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,userPromoted);
    }
    /**
     * 我的推广列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "我的推广列表", value = "promotedInfo-list", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse<List<PromotedInfo>> getPromotedInfoList(ApiRequest apiReq) {
        Long userId = apiReq.getCurrentUserId();
        this.setPageIndex(apiReq);
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("userId",userId);
        UserPromoted userPromoted=this.userPromotedMapper.selectUserPromotedByUserId(paramMap);
        apiReq.put("promoterId",userPromoted.getId());
        List<PromotedInfo> list = promotedInfoMapper.selectPromotedInfoList(apiReq);
        return new ApiResponse<List<PromotedInfo>>(ApiMsgEnum.SUCCESS, (list == null ? 0 : list.size()), list);
    }

    /**
     * 推广人详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "推广人详情", value = "promotedInfo-indetails", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)"),@ApiParam(descript = "推广标识ID", name = "id")})
    @Override
    public ApiResponse<PromotedInfo> selectPromotedInfoById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        PromotedInfo promotedInfo = this.promotedInfoMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,promotedInfo);
    }


    /**
     * 修改推广人信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "修改推广人备注相关信息", value = "promotedInfo-update", apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)"), @ApiParam(descript = "推广标识ID", name = "id"),
            @ApiParam(descript = "客户备注", name = "customerDesc"),@ApiParam(descript = "客户描述", name = "customerText"),@ApiParam(descript = "客户名片", name = "customerBusinessCard"),
            @ApiParam(descript = "客户微信号", name = "customerWechatid")})
    @Override
    public ApiResponse<PromotedInfo> updatePromotedInfoById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        PromotedInfo promotedInfo = new PromotedInfo();
        promotedInfo.setId(id);
        if(!StringUtils.isEmpty(apiReq.get("customerDesc"))){
            promotedInfo.setCustomerDesc(apiReq.getString("customerDesc"));
        }
        if(!StringUtils.isEmpty(apiReq.get("customerText"))){
            promotedInfo.setCustomerText(apiReq.getString("customerText"));
        }
        if(!StringUtils.isEmpty(apiReq.get("customerBusinessCard"))){
            promotedInfo.setCustomerBusinessCard(apiReq.getString("customerBusinessCard"));
        }
        if(!StringUtils.isEmpty(apiReq.get("customerWechatid"))){
            promotedInfo.setCustomerWechatid(apiReq.getString("customerWechatid"));
        }
        int result = this.promotedInfoMapper.updateByPrimaryKeySelective(promotedInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,promotedInfo);
    }

    public String sendModelMessage(String messageStr,String openId,String fromId,String name,String dateStr) throws IOException {
        //  redisService.set("accessToken","76abyf31Hlu7JDoEhGCC_OPd1q-cQ2vgZrDAYc9bVHDKReGHZaB5lh1XzCpnVW2YlAgJpJZUki6AR_3ZOLjOdKPYNW3zkgW3D7RyG9bvoyhQ-bp1JUh4HphrerrAqmSlLZCeAFASGJ");
        String accessToken = redisService.get(redisPrefix+"accessToken");
        String data="{" +
                "  \"touser\": \""+openId+"\",  " +
                "  \"template_id\": \"b2-MmfdmQfzWZdCUNAMbjnCNsd-gWa9ZrqRDV4Hc_dA\"," +
                "  \"page\": \"/pages/user/userMsg\"," +
                "  \"form_id\": \""+fromId+"\"," +
                "  \"data\": {" +
                "      \"keyword1\": {" +
                "          \"value\": \""+name+"\"," +
                "          \"color\": \"#173177\"" +
                "      }," +
                "      \"keyword2\": {" +
                "          \"value\": \""+messageStr+"\"," +
                "          \"color\": \"#173177\"" +
                "      }," +
                "      \"keyword3\": {" +
                "          \"value\": \""+dateStr+"\"," +
                "          \"color\": \"#173177\"" +
                "      }" +
                "  }," +
                "  \"emphasis_keyword\": \"keyword1.DATA\"" +
                "}";
        System.out.println("data"+data);
        String url2="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+accessToken;
        String str2 = HttpClientUtils.postJson1(url2, data, "utf-8");
        if(str2.indexOf("errcode")!=-1){
            JSONObject jsonObject2 = JSON.parseObject(str2);
            String errcode="";
            if(jsonObject2.get("errcode")!=null){
                errcode=jsonObject2.get("errcode").toString();
            }
            if(jsonObject2.get("errcode").toString()!=null&&jsonObject2.get("errcode").toString().equals("42001")||jsonObject2.get("errcode").toString().equals("40001")){
                String url1="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+mpwxAppId+"&secret="+mpwxSecret+"";
                Map<String,String> map=new HashMap<String,String>();
                String str = HttpClientUtils.httpPost(url1, map);
                JSONObject jsonObject = JSON.parseObject(str);
                String access_token = jsonObject.get("access_token").toString();
                redisService.set(redisPrefix+"accessToken",access_token);
                String url3="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+access_token;
                str2 = HttpClientUtils.postJson1(url3, data, "utf-8");
            }
        }
        return str2;
    }

}
