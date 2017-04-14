package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.apicenter.backendapi.BackendLoanApplicationAPi;
import com.lefanfs.apicenter.dao.*;
import com.lefanfs.apicenter.model.*;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.apicenter.util.SendMessageUntil;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Jani on 2017/3/10.
 */
@Service
@ApiService(descript = "后台贷款申请管理API")
public class BackendLoanApplicationAPiImpl extends BaseServiceImpl implements BackendLoanApplicationAPi {

    @Autowired
    private LoanApplicationMapper loanApplicationMapper;

    @Autowired
    private PromotedInfoMapper promotedInfoMapper;

    @Autowired
    private MessageInfoMapper messageInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CommonAreaMapper commonAreaMapper;


    /**
     * 用户贷款申请列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "用户贷款申请列表", value = "backend-loan-application-list", apiParams = { @ApiParam(descript = "用户ID", name = "userId"),
            @ApiParam(descript = "贷款状态", name = "state"),@ApiParam(descript = "贷款申请手机号", name = "userPhone"),@ApiParam(descript = "是否为交通事故", name = "isTrafficAccident")})
    @Override
    public ApiResponse<List<LoanApplication>> getBkLoanApplicationList(ApiRequest apiReq) {
        Long userId = apiReq.getLong("userId");
        Integer state = apiReq.getInt("state");
        String userPhone = apiReq.getString("userPhone");
        Integer isTrafficAccident = apiReq.getInt("isTrafficAccident");
        Integer page = apiReq.getInt("page");
        int pageSize = 10;
        if (page==null || page<1){
            page = 1;
        }
        List<LoanApplication> loanApplicationsReset=new ArrayList<LoanApplication>();
        List<LoanApplication> loanApplications=loanApplicationMapper.selectLoanApplicationByParam(state,userId,userPhone,isTrafficAccident,(page-1)*pageSize,pageSize);
        for(LoanApplication loanApplication:loanApplications){
            String provinceId = loanApplication.getAccidentProvince();
            String city = loanApplication.getAccidentCity();
            String district = loanApplication.getAccidentDistrict();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String[] aredIdArray =new String[]{provinceId,city,district};
            paramMap.put("areaList",aredIdArray);
            List<CommonArea>  commonAreas = commonAreaMapper.selectAreaByAreaIdList(paramMap);
            for(CommonArea commonArea:commonAreas){
                if(commonArea.getAreaType()==1)
                    loanApplication.setAccidentProvince(commonArea.getAreaName());
                else if(commonArea.getAreaType()==2)
                    loanApplication.setAccidentCity(commonArea.getAreaName());
                else  if(commonArea.getAreaType()==3)
                    loanApplication.setAccidentDistrict(commonArea.getAreaName());
            }
            loanApplicationsReset.add(loanApplication);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,loanApplicationsReset==null?0:loanApplicationsReset.size(),loanApplicationsReset);
    }

    /**
     * 贷款申请状态修改
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "贷款申请状态修改", value = "backend-update-loan-application", apiParams = { @ApiParam(descript = "贷款申请ID", name = "id"),
           @ApiParam(descript = "贷款状态", name = "state")})
    @Override
    public ApiResponse<LoanApplication> updateBkLoanApplicationById(ApiRequest apiReq) throws Exception {
        Long id = apiReq.getLong("id");
        Integer state = apiReq.getInt("state");
        LoanApplication loanApplication=this.loanApplicationMapper.selectByPrimaryKey(id);
        loanApplication.setState(state);
        int result = this.loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
        //添加系统消息状态(1:报案，2：受理.3:驳回，4：申请中，5,：完成)
       /* Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId",loanApplication.getUserId())
        UserInfo userInfo = this.userInfoMapper.selectUserInfoByUserId(paramMap);*/
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy年MM月dd天");
        String dateStr = sdf.format(loanApplication.getCreateTime());
        String loanTypeStr="医疗费用垫付";
        if(loanApplication.getLoanPurpose()==1){
            loanTypeStr="医疗费用垫付";
        }else if(loanApplication.getLoanPurpose()==2){
            loanTypeStr="生活康复支出";
        }
       String loanStateStr="";
        MessageInfo  messageInfo = new MessageInfo();
        if(state==2){
            loanStateStr="已经受理";
            messageInfo.setTitle("贷款申请受理通知");
            messageInfo.setConent("您好，您提交的垫资申请我们"+loanStateStr+"，服务专员将在24小时内与您联系，请您保持电话畅通，如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边");

        }
        if(state==3){
            loanStateStr="已经驳回";
            messageInfo.setTitle("贷款申请驳回通知");
            messageInfo.setConent("您好，您提交的垫资申请我们"+loanStateStr+"，服务专员将在24小时内与您联系，请您保持电话畅通，如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边");

        }
        if(state==4){
            loanStateStr="已经提交申请";
            messageInfo.setTitle("贷款申请变更通知");
            messageInfo.setConent("您好，您提交的垫资申请我们"+loanStateStr+"，服务专员将在24小时内与您联系，请您保持电话畅通，如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边");

        }
        if(state==5){
            //申请贷款，变更推广人信息
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("customerId",loanApplication.getUserId());
            PromotedInfo promotedInfo= this.promotedInfoMapper.selectPromotedInfoByParam(paramMap);
            if(promotedInfo!=null){
                promotedInfo.setCustomerState(3);//
                this.promotedInfoMapper.updateByPrimaryKeySelective(promotedInfo);
            }
            loanStateStr="已经提交完成";
            messageInfo.setTitle("贷款申请完成通知");
            messageInfo.setConent("您好，您提交的垫资申请我们"+loanStateStr+"，服务专员将在24小时内与您联系，请您保持电话畅通，如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边");

        }
        String  resultStr= SendMessageUntil.sendSmsLoanApplication(loanApplication.getUserPhone(),loanStateStr);
        messageInfo.setSenderId(0);
        messageInfo.setSenderName("系统消息");
        messageInfo.setReceiverId(loanApplication.getUserId());
        messageInfo.setReceiverName(loanApplication.getUserName());
        messageInfo.setMessageType(1);
        messageInfo.setSendTime(new Date());
        messageInfo.setDeleteFlag(0);
        this.messageInfoMapper.insertSelective(messageInfo);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }
    /**
     * 贷款申请删除
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "贷款申请删除", value = "backend-delete-loan-application", apiParams = { @ApiParam(descript = "贷款申请ID", name = "id")})
    @Override
    public ApiResponse<LoanApplication> deleteBkLoanApplicationById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        LoanApplication loanApplication=new LoanApplication();
        loanApplication.setId(id);
        loanApplication.setDeleteFlag(1);
        int result = this.loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }
}
