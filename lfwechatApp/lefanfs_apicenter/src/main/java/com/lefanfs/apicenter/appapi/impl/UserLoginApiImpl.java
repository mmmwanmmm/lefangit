package com.lefanfs.apicenter.appapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefanfs.apicenter.dao.*;
import com.lefanfs.apicenter.model.*;
import com.lefanfs.apicenter.util.HttpClientUtils;
import com.lefanfs.apicenter.util.SendMessageUntil;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.base.utils.RandomIDUtil;
import com.lefanfs.apicenter.appapi.UserLoginApi;
import com.lefanfs.apicenter.dto.UserSessionDto;
import com.lefanfs.apicenter.service.RedisService;
import com.lefanfs.apicenter.service.SmsService;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@ApiService(descript = "用户登录相关API")
public class UserLoginApiImpl extends BaseServiceImpl implements UserLoginApi {
	private static final Logger loger = Logger.getLogger(UserLoginApiImpl.class);
	@Autowired
	private UserLoginMapper userLoginMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private SmsService smsService;

	@Autowired
	private RedisService redisService;

    @Autowired
    private SendSmsMapper sendSmsMapper;

	@Resource
	private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private PromotedInfoMapper promotedInfoMapper;


    @Autowired
    private UserPromotedMapper userPromotedMapper;

    @Value("${sms.repeat.time}")
    private Integer smsRepeatTime;

    @Value("${sms.expired.time}")
    private Integer smsExpiredTime;

    @Value("${mpwx_appId}")
    private String mpwxAppId;

    @Value("${mpwx_secret}")
    private String mpwxSecret;



    @SuppressWarnings("rawtypes")
	@ApiMethod(descript = "用户登录", value = "user-login", apiParams = { @ApiParam(descript = "手机号(*)", name = "phone"), @ApiParam(descript = "验证码(*)", name = "validateCode"),
            @ApiParam(descript = "昵称", name = "nickName"), @ApiParam(descript = "头像", name = "img"), @ApiParam(descript = "性别", name = "gender"), @ApiParam(descript = "推广人ID", name = "promotedId"),
            @ApiParam(descript = "用户openId", name = "openId")})
	@Override
	public ApiResponse login(ApiRequest apiReq) {
		String phone = apiReq.getString("phone");
		String validateCode = apiReq.getString("validateCode");
        String openId = apiReq.getString("openId");
        String nickName="";
        if(!StringUtils.isEmpty(apiReq.get("nickName"))){
            nickName=apiReq.getString("nickName");
        }
        String img="";
        if(!StringUtils.isEmpty(apiReq.get("img"))){
            img=apiReq.getString("img");
        }
        Integer gender=0;
        Integer isPromoted=0;
        if(!StringUtils.isEmpty(apiReq.get("gender"))){
            gender=apiReq.getInt("gender");
        }
        Long promotedId=0L;
        if(!StringUtils.isEmpty(apiReq.get("promotedId"))){
            promotedId=apiReq.getLong("promotedId");
        }
		if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(validateCode)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		UserSessionDto sessionDto = new UserSessionDto();
		UserLogin user = this.userLoginMapper.selectByPhone(phone);
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
            HashMap<String,Object> map =new HashMap<String, Object>();
            /*map.put("telphone",phone);
            map.put("smsCode",validateCode);
            map.put("updateTime",new Date());
            map.put("longTime",smsExpiredTime);
            SendSms oldSendSMS =sendSmsMapper.selectSendSMS(map);
            if(oldSendSMS==null){
                return new ApiResponse<UserSessionDto>(ApiMsgEnum.SmsCodeOutTime);
            }else{
                //sp验证成功，将该验证码失效
                map.clear();
                map.put("telphone",phone);
                sendSmsMapper.deleteSendSMSByParam(map);
            }*/
            //判断是否注册，未注册返回信息，已经注册--查看用户是否冻结，已经冻结则返回冻结信息，未冻结则返回信息账户信息
            map.clear();


			Long userId = null;
			if (user == null) {
				UserLogin record = new UserLogin();
				record.setUserTelphone(phone);
                record.setWechatId(openId);
				this.userLoginMapper.insert(record);
				userId = record.getUserId();
				UserInfo userInfoRecord = new UserInfo();
				userInfoRecord.setUserId(userId);
				userInfoRecord.setModifyTime(new Date());
				userInfoRecord.setDeleteFlag(0);
				userInfoRecord.setUserState(0);
                userInfoRecord.setNickName(nickName);
                userInfoRecord.setImg(img);
                userInfoRecord.setSex(gender);
                isPromoted=0;
				this.userInfoMapper.insertSelective(userInfoRecord);
                //增加推广人信息
                if(promotedId!=0){
                    Map<String, Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("userId",promotedId);
                    UserPromoted userPromoted = this.userPromotedMapper.selectUserPromotedByUserId(paramMap);
                    if(userPromoted!=null){
                        //确认该用户是否具有推广权限
                        if(userPromoted.getState()==2){
                            PromotedInfo promotedInfo=new PromotedInfo();
                            promotedInfo.setPromoterId(userPromoted.getId());
                            UserInfo userInfo1 = userInfoMapper.selectByPrimaryKey(promotedId);
                            promotedInfo.setPromoterName(userPromoted.getRealName());
                            promotedInfo.setCustomerId(userId);
                            promotedInfo.setCustomerState(1);
                            promotedInfo.setCreateTime(new Date());
                            promotedInfo.setCustomerName(nickName);
                            promotedInfo.setPromoterType(1);
                            promotedInfo.setCustomerImg(img);
                            promotedInfo.setCustomerPhone(phone);
                            promotedInfo.setAccountState(1);
                            this.promotedInfoMapper.insertSelective(promotedInfo);
                        }else{
                            return new ApiResponse<UserSessionDto>(ApiMsgEnum.QrcodeError);
                        }
                    }else{
                        return new ApiResponse<UserSessionDto>(ApiMsgEnum.QrcodeError);
                    }

                }
				sessionDto.setIcon(img);
				sessionDto.setPhone(record.getUserTelphone());
				sessionDto.setNickname(this.getDisplayName(record.getUserTelphone(), nickName));
			} else {
				userId = user.getUserId();
				UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
                if(userInfo.getUserState()==1){
                    return new ApiResponse<UserSessionDto>(ApiMsgEnum.UserBeenLookedException, 1, sessionDto);
                }
                userInfo.setNickName(nickName);
                userInfo.setImg(img);
                userInfo.setSex(gender);
                isPromoted=userInfo.getIsPromoter();
                this.userInfoMapper.updateByPrimaryKeySelective(userInfo);
				sessionDto.setIcon(userInfo.getImg());
				sessionDto.setPhone(user.getUserTelphone());
				sessionDto.setNickname(this.getDisplayName(user.getUserTelphone(), userInfo.getNickName()));
			}
			String loginToken = RandomIDUtil.getNewUUID();
			// 用户session写入缓存
			sessionDto.setUserId(userId);
			sessionDto.setUserToken(loginToken);
            sessionDto.setIsPromoter(isPromoted);
			redisService.setUserSession(loginToken, sessionDto);

			platformTransactionManager.commit(transactionStatus);
			//sessionDto.setUserId(null);
			return new ApiResponse<UserSessionDto>(ApiMsgEnum.LoginSuccess, 1, sessionDto);
		} catch (RuntimeException e) {
			loger.error(e);
			platformTransactionManager.rollback(transactionStatus);
			return new ApiResponse(ApiMsgEnum.BAD_REQUEST);
		}
	}


	@SuppressWarnings("rawtypes")
	@ApiMethod(needLogin = true, descript = "用户退出", value = "user-logout")
	@Override
	public ApiResponse logout(ApiRequest apiReq) {
		redisService.removeUserSession(apiReq.getUserToken());
		return new ApiResponse(ApiMsgEnum.LogoutSuccess);
	}

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = false, descript = "根据微信openID查询用户", value = "select-user-byCode",apiParams = { @ApiParam(descript = "微信客户端code(*)", name = "jsCode")})
    @Override
    public ApiResponse selectUserByOpenId(ApiRequest apiReq) throws IOException {
        String jsCode = apiReq.getString("jsCode");
        String openId = getUserOpenId(jsCode);
        UserLogin userLogin=this.userLoginMapper.selectByWechatId(openId);
        if(userLogin!=null){
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,userLogin);
        }else{
            return new ApiResponse(ApiMsgEnum.UserDosentExist,1,openId);
        }
    }

    /**
     * 发送验证短信
     * @param apiRequest
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = false, descript = "发送验证短信", value = "user-sendSMS",apiParams = { @ApiParam(descript = "用户手机号(*)", name = "telphone")})
    @Override
    public ApiResponse<UserInfo> sendSMS(ApiRequest apiRequest) {
        Object telphoneObj = apiRequest.get("telphone");
        if (StringUtils.isEmpty(telphoneObj)) {
            return new ApiResponse<UserInfo>(ApiMsgEnum.MISS_PARAMETER);
        }
        ApiResponse<UserInfo> apiResponse = null;
        try {
            String telphone=telphoneObj.toString();

            if(!isMobile(telphone)){
                return new ApiResponse<UserInfo>(ApiMsgEnum.MISS_PARAMETER);
            }
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("telphone",telphone);
            SendSms oldSendSMS =sendSmsMapper.selectSendSMS(map);
            //10分钟之内的不发
            map.clear();
            map.put("telphone",telphone);
            map.put("updateTime",new Date());
            map.put("longTime",smsRepeatTime);
            SendSms sendSMS =sendSmsMapper.selectSendSMS(map);

            if(sendSMS!=null){
                return new ApiResponse<UserInfo>(ApiMsgEnum.ValidateCodeSendSuccess);//告诉用户正常发送，其实后台不发送新验证码
            }
            String smsCode=createRandomVcode();
            SendSms newSendSMS= SendMessageUntil.sendSmsValidateCode(telphone,smsCode);
            int flag=0;
            if(newSendSMS!=null&&newSendSMS.getTelphone()!=null){
                if(oldSendSMS!=null){
                    oldSendSMS.setUpdateTime(new Date());
                    oldSendSMS.setSmsCode(newSendSMS.getSmsCode());
                    flag=sendSmsMapper.updateByPrimaryKeySelective(oldSendSMS);
                }else{
                    Date date=new Date();
                    newSendSMS.setCreateTime(date);
                    newSendSMS.setUpdateTime(date);
                    flag=sendSmsMapper.insertSelective(newSendSMS);
                }
            }
            if(flag>0){
                apiResponse = new ApiResponse<UserInfo>(ApiMsgEnum.ValidateCodeSendSuccess);//正常发送
            }else{
                apiResponse = new ApiResponse<UserInfo>(ApiMsgEnum.FAIL);//验证码发送失败
            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<UserInfo>(ApiMsgEnum.BAD_REQUEST);//出现异常
        }
        return apiResponse;
    }


    public String getUserOpenId(String code) throws IOException {
        String openId="";
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+mpwxAppId+"&secret="+mpwxSecret+"&js_code="+code+"&grant_type=authorization_code";
        Map<String,String> map=new HashMap<String,String>();
        String str = HttpClientUtils.httpPost(url,map);
        JSONObject jsonObject = JSON.parseObject(str);
        String session_key = jsonObject.get("session_key").toString();
        String openid = jsonObject.get("openid").toString();
        /**
         * {"session_key":"e+gt7uMAXyA8RjQoTvVGvg==","expires_in":7200,"openid":"oF0sJ0Sr9k7hDsQAow7wnF5qalLA"}
         */
        System.out.println("str:"+str);
      return openid;
    }
    /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[0-9]{11}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    /**
     * 随机生成6位随机验证码
     */
    public static String createRandomVcode(){
        //验证码
        StringBuffer vcode = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            vcode.append((int)(Math.random() * 9));
        }
        return vcode.toString();
    }
}
