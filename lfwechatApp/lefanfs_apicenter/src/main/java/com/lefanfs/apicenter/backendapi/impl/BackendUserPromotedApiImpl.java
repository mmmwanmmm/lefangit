package com.lefanfs.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefanfs.apicenter.backendapi.BackendUserPromotedApi;
import com.lefanfs.apicenter.dao.*;
import com.lefanfs.apicenter.model.*;
import com.lefanfs.apicenter.service.RedisService;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.apicenter.util.HttpClientUtils;
import com.lefanfs.apicenter.util.QrUtils;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.base.utils.HttpClientUtil;
import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/3/15.
 */
@Service
@ApiService(descript = "后台推广用户认证管理相关API")
public class BackendUserPromotedApiImpl  extends BaseServiceImpl implements BackendUserPromotedApi {

    @Autowired
    private UserPromotedMapper userPromotedMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PromotedInfoMapper promotedInfoMapper;
    @Autowired
    private CommonAreaMapper commonAreaMapper;

    @Autowired
    private RedisService redisService;

    @Value("${mpwx_appId}")
    private String mpwxAppId;

    @Value("${mpwx_secret}")
    private String mpwxSecret;

    @Value("${file.server.rootDir}")
    private String fileServerRootDir;

    @Value("${file.server}")
    private String fileServer;

    @Value("${redis_prefix}")
    private String redisPrefix;


    @Autowired
    private UserLoginMapper userLoginMapper;


    /**
     * 推广认证用户列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "推广认证用户列表", value = "backend-user-promoted-list", apiParams = { @ApiParam(descript = "当前第几页", name = "page")})
    @Override
    public ApiResponse<List<UserPromoted>> getBkUserPromotedList(ApiRequest apiReq) {
        Integer page = apiReq.getInt("page");
        int pageSize = 10;
        if (page==null || page<1){
            page = 1;
        }
        page =(page - 1) * pageSize;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageIndex",page);
        paramMap.put("pageSize",pageSize);
        List<UserPromoted> userPromoteds=userPromotedMapper.selectUserPromotedByParam(paramMap);
        List<UserPromoted> userPromotedsResets=new ArrayList<UserPromoted>();
        for(UserPromoted userPromoted:userPromoteds){
            String provinceId = userPromoted.getProvince();
            String city = userPromoted.getCity();
            String district = userPromoted.getDistrict();
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            String[] aredIdArray =new String[]{provinceId,city,district};
            paramMap1.put("areaList",aredIdArray);
            List<CommonArea>  commonAreas = commonAreaMapper.selectAreaByAreaIdList(paramMap1);
            for(CommonArea commonArea:commonAreas){
                if(commonArea.getAreaType()==1)
                    userPromoted.setProvince(commonArea.getAreaName());
                else if(commonArea.getAreaType()==2)
                    userPromoted.setCity(commonArea.getAreaName());
                else  if(commonArea.getAreaType()==3)
                    userPromoted.setDistrict(commonArea.getAreaName());
            }
            userPromotedsResets.add(userPromoted);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,userPromotedsResets==null?0:userPromotedsResets.size(),userPromotedsResets);
    }
    /**
     * 推广认证用户状态修改
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "推广认证用户状态修改", value = "backend-update-user-promoted", apiParams = { @ApiParam(descript = "贷款申请ID", name = "id"),
            @ApiParam(descript = "推广用户认证状态（*）", name = "state"),@ApiParam(descript = "审核不通过原因", name = "reason")})
    @Override
    public ApiResponse<UserPromoted> updateBkUserPromotedById(ApiRequest apiReq) throws IOException {
        Long id = apiReq.getLong("id");
        if(StringUtils.isEmpty(apiReq.get("id"))||StringUtils.isEmpty(apiReq.get("state"))){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        Integer  state = apiReq.getInt("state");
        String reason = apiReq.getString("reason");
        UserPromoted userPromoted=userPromotedMapper.selectByPrimaryKey(id);
        userPromoted.setState(state);
        if(state==2){
            String qrCode=fileServer+"/"+getQrCode(userPromoted.getUserId().toString());
            userPromoted.setPromotedQrcode(qrCode);
            Map retMap = new HashMap();
            retMap.put("userId",userPromoted.getUserId());
            UserInfo userInfo=this.userInfoMapper.selectUserInfoByUserId(retMap);
            if(userInfo!=null){
                userInfo.setPromotedQrcode(qrCode);
                userInfo.setIsPromoter(1);
                //修改二维码
                userInfoMapper.updateByPrimaryKeySelective(userInfo);
                //添加推送模板消息
               /* UserLogin userLogin=this.userLoginMapper.selectByPrimaryKey(userPromoted.getUserId());
                String openId=userLogin.getWechatId();
                String msgStr="您好，感谢您申请成为乐凡推广大使，我们会在24小时内与您联系，如需其他咨询，可致电24小时服务电话4006303071，赠人玫瑰手留余香！";
                sendModelMessage(msgStr,openId,"ca7eddd139c23e9a6aa388ec43b4a46c");*/

            }
        }else if(state==3){
            userPromoted.setReason(reason);
            //添加推送模板消息
            /*UserLogin userLogin=this.userLoginMapper.selectByPrimaryKey(userPromoted.getUserId());
            String openId=userLogin.getWechatId();
            String msgStr="您好，感谢您申请成为乐凡推广大使，我们会在24小时内与您联系，如需其他咨询，可致电24小时服务电话4006303071，赠人玫瑰手留余香！";
            sendModelMessage(msgStr,openId,"ca7eddd139c23e9a6aa388ec43b4a46c");*/
        }
        int result = this.userPromotedMapper.updateByPrimaryKeySelective(userPromoted);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    public String getQrCode(String userId) throws IOException {
      //  redisService.set("accessToken","76abyf31Hlu7JDoEhGCC_OPd1q-cQ2vgZrDAYc9bVHDKReGHZaB5lh1XzCpnVW2YlAgJpJZUki6AR_3ZOLjOdKPYNW3zkgW3D7RyG9bvoyhQ-bp1JUh4HphrerrAqmSlLZCeAFASGJ");
        String accessToken = redisService.get(redisPrefix+"accessToken");
        String data="{\"path\":\"pages/user/userLogin?promotedId="+userId+"\",\"width\": \"430\"}";
        String url2="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+accessToken;
        String str2 = HttpClientUtils.postJson(url2, data, "utf-8",fileServerRootDir);
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
                String url3="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+access_token;
                str2 = HttpClientUtils.postJson(url3, data, "utf-8",fileServerRootDir);
            }
        }
        return str2;
    }

    public String sendModelMessage(String messageStr,String openId,String fromId) throws IOException {
        //  redisService.set("accessToken","76abyf31Hlu7JDoEhGCC_OPd1q-cQ2vgZrDAYc9bVHDKReGHZaB5lh1XzCpnVW2YlAgJpJZUki6AR_3ZOLjOdKPYNW3zkgW3D7RyG9bvoyhQ-bp1JUh4HphrerrAqmSlLZCeAFASGJ");
        String accessToken = redisService.get(redisPrefix+"accessToken");
        String data="{\n" +
                "  \"touser\": \""+openId+"\",  \n" +
                "  \"template_id\": \"b2-MmfdmQfzWZdCUNAMbjnCNsd-gWa9ZrqRDV4Hc_dA\", \n" +
                "  \"page\": \"\",          \n" +
                "  \"form_id\": \""+fromId+"\",         \n" +
                "  \"data\": {\n" +
                "      \"keyword1\": {\n" +
                "          \"value\": \"万俊\", \n" +
                "          \"color\": \"#173177\"\n" +
                "      }, \n" +
                "      \"keyword2\": {\n" +
                "          \"value\": \"您好，您提交的垫资申请我们已经受理，服务专员将在24小时内与您联系，请您保持电话畅通，如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边\", \n" +
                "          \"color\": \"#173177\"\n" +
                "      }, \n" +
                "      \"keyword3\": {\n" +
                "          \"value\": \"2017-3-28 10:47\", \n" +
                "          \"color\": \"#173177\"\n" +
                "      } \n" +
                "  },\n" +
                "  \"emphasis_keyword\": \"keyword1.DATA\" \n" +
                "}";
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
    //
    /**
     * 推广记录列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "推广记录列表查询", value = "backend-promoted-info-list", apiParams = { @ApiParam(descript = "当前第几页", name = "page"),
            @ApiParam(descript = "用户名", name = "userName"), @ApiParam(descript = "用户ID", name = "userId")})
    @Override
    public ApiResponse<List<PromotedInfo>> getBkPromotedInfoList(ApiRequest apiReq) {
        Integer page = apiReq.getInt("page");
        String userId = apiReq.getString("userId");

        Map<String, Object> paramMap1=new HashMap<String, Object>();
        paramMap1.put("userId",userId);
        UserPromoted userPromoted=this.userPromotedMapper.selectUserPromotedByUserId(paramMap1);
        String userName = apiReq.getString("userName");
        int pageSize = 10;
        if (page==null || page<1){
            page = 1;
        }
        page =(page - 1) * pageSize;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageIndex",page);
        paramMap.put("pageSize",pageSize);
        Long  promoterId=null;
        if(userPromoted!=null){
            promoterId = userPromoted.getId();
        }
        paramMap.put("promoterId",promoterId);
        paramMap.put("promoterName",userName);
        List<PromotedInfo> promotedInfos=promotedInfoMapper.selectPromotedInfoList(paramMap);



        return new ApiResponse(ApiMsgEnum.SUCCESS,promotedInfos==null?0:promotedInfos.size(),promotedInfos);
    }


    /**
     * 推广记录结算列表修改
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "推广记录结算列表修改", value = "backend-update-promoted-info", apiParams = { @ApiParam(descript = "推广记录ID", name = "id"),
            @ApiParam(descript = "推广用户认证状态（*）", name = "state"),@ApiParam(descript = "结算的凭证", name = "accountImg")})
    @Override
    public ApiResponse<PromotedInfo> updateBkPromotedInfoById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        if(StringUtils.isEmpty(apiReq.get("id"))||StringUtils.isEmpty(apiReq.get("state"))){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        Integer  state = apiReq.getInt("state");
        String accountImg = apiReq.getString("accountImg");
        PromotedInfo promotedInfo=promotedInfoMapper.selectByPrimaryKey(id);
        promotedInfo.setAccountState(state);
        promotedInfo.setAccountImg(accountImg);
        int result = this.promotedInfoMapper.updateByPrimaryKeySelective(promotedInfo);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }
}
