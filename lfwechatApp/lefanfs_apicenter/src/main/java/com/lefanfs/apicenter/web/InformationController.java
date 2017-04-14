package com.lefanfs.apicenter.web;

import com.lefanfs.apicenter.dao.CommonInformationMapper;
import com.lefanfs.apicenter.model.CommonInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thinkpad on 2016/11/23.
 */

@Controller
@RequestMapping(value = "/information")
public class InformationController extends BaseController {

    @Autowired
    private CommonInformationMapper commonInformationMapper;
    @RequestMapping(value = "/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        Map model = new HashMap();
        CommonInformation commonInformation=commonInformationMapper.selectByPrimaryKey(id);
        if (commonInformation!=null){
            commonInformation.setInformationIcon(this.getCdnUrl(null,commonInformation.getInformationIcon(),null));
        }
        model.put("dto",commonInformation);
        return new ModelAndView("/information/detail",model);
    }
}
