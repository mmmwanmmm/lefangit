package com.lefanfs.apicenter.appapi.impl;

import com.lefanfs.apicenter.appapi.LoanApplicationAPi;
import com.lefanfs.apicenter.dao.CommonAreaMapper;
import com.lefanfs.apicenter.dao.LoanApplicationMapper;
import com.lefanfs.apicenter.dao.PromotedInfoMapper;
import com.lefanfs.apicenter.model.CommonArea;
import com.lefanfs.apicenter.model.LoanApplication;
import com.lefanfs.apicenter.model.PromotedInfo;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/3/8.
 */
@Service
@ApiService(descript = "贷款申请API")
public class LoanApplicationAPiImpl extends BaseServiceImpl implements LoanApplicationAPi{
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;

    @Autowired
    private CommonAreaMapper commonAreaMapper;

    @Autowired
    private PromotedInfoMapper promotedInfoMapper;

    /**
     * 用户案例详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "申请贷款", value = "insert-loan-application", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)"),@ApiParam(descript = "用户姓名(*)", name = "userName"),
            @ApiParam(descript = "手机号码(*)", name = "userPhone"),@ApiParam(descript = "是否为交通事故(*)", name = "isTrafficAccident") ,@ApiParam(descript = "贷款金额(*)", name = "loanMoney")
            ,@ApiParam(descript = "贷款用途(*)", name = "loanPurpose"),@ApiParam(descript = "事故发生省(*)", name = "accidentProvince"),@ApiParam(descript = "事故发生市(*)", name = "accidentCity")
            ,@ApiParam(descript = "事故发生区(*)", name = "accidentDistrict"),@ApiParam(descript = "事故发生详细地址(*)", name = "accidentAddress")})
    @Override
    public ApiResponse insertLoanApplication(ApiRequest apiReq) {
        Long userId=apiReq.getCurrentUserId();
        String userName = apiReq.getString("userName");
        String userPhone = apiReq.getString("userPhone");
        Integer isTrafficAccident = apiReq.getInt("isTrafficAccident");
        Double loanMoney = apiReq.getDouble("loanMoney");
        Integer loanPurpose = apiReq.getInt("loanPurpose");
        String accidentProvince = apiReq.getString("accidentProvince");
        String accidentCity = apiReq.getString("accidentCity");
        String accidentDistrict = apiReq.getString("accidentDistrict");
        String accidentAddress = apiReq.getString("accidentAddress");
        LoanApplication loanApplication=new LoanApplication();
        loanApplication.setUserId(userId);
        loanApplication.setUserName(userName);
        loanApplication.setUserPhone(userPhone);
        loanApplication.setIsTrafficAccident(isTrafficAccident);
        loanApplication.setLoanMoney(loanMoney);
        loanApplication.setLoanPurpose(loanPurpose);
        loanApplication.setAccidentProvince(accidentProvince);
        loanApplication.setAccidentCity(accidentCity);
        loanApplication.setAccidentDistrict(accidentDistrict);
        loanApplication.setAccidentAddress(accidentAddress);
        loanApplication.setDeleteFlag(0);
        loanApplication.setCreateTime(new Date());
        loanApplication.setState(1);//状态(1:报案，2：受理.3:驳回，4：申请中，5,：完成)
        this.loanApplicationMapper.insertSelective(loanApplication);
        //申请贷款，变更推广人信息
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId",userId);
        PromotedInfo promotedInfo= this.promotedInfoMapper.selectPromotedInfoByParam(paramMap);
        if(promotedInfo!=null){
            promotedInfo.setCustomerState(2);//申请贷款未获得通过
            this.promotedInfoMapper.updateByPrimaryKeySelective(promotedInfo);
        }

        Map retMap = new HashMap();
        retMap.put("loanApplication",loanApplication);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,retMap);
    }


    /**
     * 我的贷款申请列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "我的贷款申请列表", value = "loanApplication-list", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)"),@ApiParam(descript = "当前第几页", name = "page")})
    @Override
    public ApiResponse<List<LoanApplication>> getLoanApplicationList(ApiRequest apiReq) {
        Long userId=apiReq.getCurrentUserId();
        Integer page = apiReq.getInt("page");
        int pageSize = 10;
        if (page==null || page<1){
            page = 1;
        }
        List<LoanApplication> loanApplications=loanApplicationMapper.selectLoanApplicationByParam(null,userId,null,null,(page-1)*pageSize,pageSize);
        return new ApiResponse(ApiMsgEnum.SUCCESS,loanApplications==null?0:loanApplications.size(),loanApplications);
    }
    /**
     * 我的贷款详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "我的贷款详情", value = "loan-application-indetails", apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)"),@ApiParam(descript = "贷款申请ID", name = "id")})
    @Override
    public ApiResponse<LoanApplication> selectLoanApplicationById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        LoanApplication loanApplication = this.loanApplicationMapper.selectByPrimaryKey(id);
        //贷款申请省市区转换
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
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,loanApplication);
    }
}
