package shop.mtcoding.village.dto.facilityInfo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.place.Place;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FacilityInfoSaveDTO {
//    private List<FacilityInfoDTO> facilityName;

    @Setter
    @Getter
    @ToString
    public static class FacilityInfoSaveDto extends FacilityInfo {
        private String facilityName;
        private Place placeId;

        public FacilityInfo toEntity(String name, Place id) {
            FacilityInfo facilityInfoName = new FacilityInfo();
            facilityInfoName.setPlace(id);
            facilityInfoName.setFacilityName(name);
            return facilityInfoName;
        }
    }
}
