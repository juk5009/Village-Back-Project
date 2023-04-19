package shop.mtcoding.village.dto.facilityInfo.request;

import shop.mtcoding.village.model.facilityInfo.FacilityInfo;

import java.util.ArrayList;
import java.util.List;

public class FacilityInfoSaveDTO {
    private List<String> facilityName;

    public FacilityInfo toEntity() {

        FacilityInfo facilityInfo = new FacilityInfo();
        List<String> facility = new ArrayList<>();
        String[] facilityNames = {""};
        for (String facilityName : facilityNames) {
            facility.add(facilityName);
        }
        facilityInfo.setFacilityName(facilityName);
        return new FacilityInfo(facilityName, null);
    }
}
