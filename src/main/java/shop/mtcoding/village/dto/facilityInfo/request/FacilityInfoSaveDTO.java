package shop.mtcoding.village.dto.facilityInfo.request;

import lombok.Getter;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FacilityInfoSaveDTO {
    private List<String> facilityName;

    public FacilityInfo toEntity() {

        FacilityInfo facilityInfo = new FacilityInfo();
        facilityInfo.setFacilityName(facilityName);
        return facilityInfo;
    }
}
