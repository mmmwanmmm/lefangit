package com.lefanfs.apicenter.appapi.impl;

import com.lefanfs.base.annotations.ApiMethod;
import com.lefanfs.base.annotations.ApiParam;
import com.lefanfs.base.annotations.ApiService;
import com.lefanfs.base.dto.ApiRequest;
import com.lefanfs.base.dto.ApiResponse;
import com.lefanfs.base.enums.ApiMsgEnum;
import com.lefanfs.apicenter.appapi.AreaApi;
import com.lefanfs.apicenter.dao.CommonAreaMapper;
import com.lefanfs.apicenter.dto.AddressDTO;
import com.lefanfs.apicenter.model.CommonArea;
import com.lefanfs.apicenter.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 16/11/4.
 */
@Service
@ApiService(descript = "省市区服务")
public class AreaApiImpl extends BaseServiceImpl implements AreaApi {
    @Autowired
    private CommonAreaMapper areaMapper;

    @ApiMethod(descript = "省份列表", value = "area-province-list", apiParams = {  })
    @Override
    public ApiResponse getAllProvince(ApiRequest apiReq) {
        List<CommonArea> areas = areaMapper.selectByType(1);
        List<Map> retAreas = new ArrayList<>();
        for (CommonArea area:areas){
            Map areaMap = new HashMap();
            areaMap.put("areaId",area.getAreaId());
            areaMap.put("areaName",area.getAreaName());
            retAreas.add(areaMap);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,retAreas);
    }

    @ApiMethod(descript = "全部城市", value = "area-children", apiParams = { @ApiParam(name = "areaParentId",descript = "上级区域id(*)") })
    @Override
    public ApiResponse getChildrenArea(ApiRequest apiReq) {
        Long areaParentId=apiReq.getLong("areaParentId");
        List<CommonArea> areas = areaMapper.selectByParentId(areaParentId);
        List<Map> retAreas = new ArrayList<>();
        for (CommonArea area:areas){
            Map areaMap = new HashMap();
            areaMap.put("areaId",area.getAreaId());
            areaMap.put("areaName",area.getAreaName());
            retAreas.add(areaMap);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,retAreas);
    }

    @ApiMethod(descript = "全部城市", value = "area-all", apiParams = {  })
    @Override
    public ApiResponse getAllAddress(ApiRequest apiReq) {
        List<CommonArea> all = areaMapper.selectAll();
        Long l1 = System.currentTimeMillis();
        List<AddressDTO> addressDTOList = getAddressByParent(0l,all);
        Long l2 = System.currentTimeMillis();
        System.out.println((l2-l1));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,addressDTOList);
    }

    private List<AddressDTO> getAddressByParent(Long parentId,List<CommonArea> all){
        List<CommonArea> areas = getByParent(parentId,all);
        if (CollectionUtils.isEmpty(areas)){
            return null;
        }
        List<AddressDTO> addressDTOList = new ArrayList<>();
        for (CommonArea commonArea:areas){
            AddressDTO dto = new AddressDTO();
            addressDTOList.add(dto);
            dto.setId(commonArea.getAreaId());
            dto.setParentId(commonArea.getParentId());
            dto.setName(commonArea.getAreaName());
            if (commonArea.getAreaType()<3){
                if(dto.getId()==2330){
                    System.out.println("11111111111111");
                }
                dto.setChildren(getAddressByParent(dto.getId(),all));
            }
        }
        return addressDTOList;
    }

    private List<CommonArea> getByParent(Long parentId, List<CommonArea> all) {
        List<CommonArea> list = new ArrayList<>();
        for (CommonArea commonArea:all){
            if (commonArea.getParentId()==parentId){
                list.add(commonArea);
            }
        }
        all.removeAll(list);
        return list;
    }


}
