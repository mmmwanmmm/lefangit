package com.lefanfs.apicenter.backendapi.impl;

import com.lefanfs.apicenter.backendapi.BackendQaInfoApi;
import com.lefanfs.apicenter.dao.QaInfoMapper;
import com.lefanfs.apicenter.model.QaInfo;
import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Jani on 2017/3/22.
 */
@Service
@ApiService(descript = "QA后台管理接口API")
public class BackendQaInfoApiImpl implements BackendQaInfoApi {

    @Autowired
    private QaInfoMapper qaInfoMapper;
    /**
     * QA问答列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "QA问答列表查询", value = "backend-select-qainfo-list", apiParams = {})
    @Override
    public ApiResponse<List<QaInfo>> getQaInfoList(ApiRequest apiReq) {
        Integer page = apiReq.getInt("page");
        int pageSize = 10;
        if (page==null || page<1){
            page = 1;
        }
        List<QaInfo> qaInfos=qaInfoMapper.selectQaInfoListByParam((page - 1) * pageSize, pageSize);
        return new ApiResponse(ApiMsgEnum.SUCCESS,qaInfos==null?0:qaInfos.size(),qaInfos);
    }

    /**
     * 新增QA问答信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增QA问答信息", value = "backend-add-qaInfo", apiParams = {@ApiParam(descript = "问题(*)", name = "question"), @ApiParam(descript = "答案(*)", name = "answer")
            , @ApiParam(descript = "提问者Id(*)", name = "questionId"), @ApiParam(descript = "提问者名称(*)", name = "questionName"),
            @ApiParam(descript = "回答者ID(*)", name = "answerId"), @ApiParam(descript = "回答者名称(*)", name = "answerIdName")})
    @Override
    public ApiResponse<QaInfo> addBkQaInfo(ApiRequest apiReq) {
        QaInfo qaInfo = new QaInfo();
        qaInfo.setQuestion(apiReq.getString("question"));
        qaInfo.setAnswer(apiReq.getString("answer"));
        qaInfo.setDeleteFlag(0);
        qaInfo.setType(1);
        qaInfo.setQuestionTime(new Date());
        qaInfo.setAnswerTime(new Date());
        qaInfo.setQuestionerId(apiReq.getInt("questionId"));
        qaInfo.setQuestionerName(apiReq.getString("questionName"));
        qaInfo.setAnswerId(apiReq.getInt("answerId"));
        qaInfo.setAnswerName(apiReq.getString("answerIdName"));
        int result = qaInfoMapper.insertSelective(qaInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,qaInfo);
    }

    /**
     * 修改QA问答信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新QA问答信息", value = "backend-update-qaInfo", apiParams = {@ApiParam(descript = "主键标识ID(*)", name = "id"),@ApiParam(descript = "问题(*)", name = "question"), @ApiParam(descript = "答案(*)", name = "answer")
            , @ApiParam(descript = "提问者Id(*)", name = "questionId"), @ApiParam(descript = "提问者名称(*)", name = "questionName"),
            @ApiParam(descript = "回答者ID(*)", name = "answerId"), @ApiParam(descript = "回答者名称(*)", name = "answerIdName")})
    @Override
    public ApiResponse<QaInfo> aupdateBkQaInfo(ApiRequest apiReq) {
        QaInfo qaInfo = new QaInfo();
        qaInfo.setId(apiReq.getLong("id"));
        if(!StringUtils.isEmpty(apiReq.get("question"))){
            qaInfo.setQuestion(apiReq.getString("question"));
        }
        if(!StringUtils.isEmpty(apiReq.get("answer"))){
            qaInfo.setAnswer(apiReq.getString("answer"));
        }
        if(!StringUtils.isEmpty(apiReq.get("questionId"))){
            qaInfo.setQuestionerId(apiReq.getInt("questionId"));
        }
        if(!StringUtils.isEmpty(apiReq.get("questionName"))){
            qaInfo.setQuestionerName(apiReq.getString("questionName"));
        }
        if(!StringUtils.isEmpty(apiReq.get("answerId"))){
            qaInfo.setAnswerId(apiReq.getInt("answerId"));
        }
        if(!StringUtils.isEmpty(apiReq.get("answerIdName"))){
            qaInfo.setAnswerName(apiReq.getString("answerIdName"));
        }
        int result = qaInfoMapper.updateByPrimaryKeySelective(qaInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,qaInfo);
    }

    /**
     * 删除QA问答信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除QA问答信息", value = "backend-delete-qaInfo", apiParams = {@ApiParam(descript = "主键标识ID(*)", name = "id")})
    @Override
    public ApiResponse<QaInfo> deleteBkQaInfo(ApiRequest apiReq) {
        QaInfo qaInfo = new QaInfo();
        qaInfo.setId(apiReq.getLong("id"));
        qaInfo.setDeleteFlag(1);
        int result = qaInfoMapper.updateByPrimaryKeySelective(qaInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,qaInfo);
    }

    /**
     * 查询QA问题详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询QA问题详情", value = "backend-indetails-qaInfo", apiParams = {@ApiParam(descript = "主键标识ID(*)", name = "id")})
    @Override
    public ApiResponse<QaInfo> selectBkQaInfoById(ApiRequest apiReq) {
        QaInfo qaInfo = qaInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,qaInfo);
    }
}
