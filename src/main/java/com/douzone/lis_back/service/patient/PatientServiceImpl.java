package com.douzone.lis_back.service.patient;
import com.douzone.lis_back.domain.PatientDTO;
import com.douzone.lis_back.domain.patientdomain.PatientDomainDTO;
import com.douzone.lis_back.mapper.patientmapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientMapper patientMapper;

    @Override
    public List<HashMap<String, Object>> getPatientInfoByPatientNo(String patientInfo, String searchCon) {
        List<HashMap<String,Object>> patientInfos = new ArrayList<>();
        if("환자번호".equals(searchCon)) {
            patientInfos = patientMapper.findPatientInfoByPatientNo(patientInfo);
        }
        if("이름".equals(searchCon)) {
            patientInfos = patientMapper.findPatientInfoByPatientName(patientInfo);
        }

        return patientInfos;
    }

    @Override
    public List<PatientDomainDTO> patientData(String barcode) {
        return patientMapper.patientData(barcode);
    }

    @Override
    public List<PatientDTO> patientList(){
        return patientMapper.patientAll();
    }

    @Override
    public List<HashMap<String, Object>> getVisitDataByPatientNo(String patientNo, String visitStatus) {
        List<HashMap<String, Object>> visitInfo = new ArrayList<>();
            visitInfo = patientMapper.findVisitDataByPatientNo(patientNo, visitStatus);
            visitInfo.forEach((map) -> map.put("VISIT_DT", map.get("VISIT_DT").toString().split("T")[0]));
        return visitInfo;
    }
}
