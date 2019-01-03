package com.xdbigdata.app_center.feign;

import com.xdbigdata.app_center.common.Contation;
import com.xdbigdata.app_center.domain.InforMation;
import com.xdbigdata.mybatis.dto.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class InfomationServiceResult {
    @Autowired
    private InformationService informationService;

    @Autowired
    private IRemoteService iRemoteService;

    public InforMation getInfoMationReslut(String sn,String userType) {
        InforMation inforMation = new InforMation();
        if (userType.equals(Contation.STUDENT)) {
            CommonResult userReslut = informationService.findResultBySn(sn, Contation.WELCOME_FAMILY);
            CommonResult userReslut1 = informationService.findResultBySn(sn, Contation.WELCOME);
            CommonResult studentReslut = iRemoteService.findStudentChangeInfo(sn, Contation.BACIS);
            if (Objects.isNull(userReslut.getData()) || Objects.isNull(userReslut1.getData()) || Objects.isNull(studentReslut.getData())) {
                inforMation.setIsUpdate("0");
                inforMation.setUrl("");
            }else {
                inforMation.setIsUpdate("0");
                inforMation.setUrl("");
            }
        }else {
            inforMation.setIsUpdate("0");
            inforMation.setUrl("");
        }
        return inforMation;
    }
}
