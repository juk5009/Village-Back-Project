package shop.mtcoding.village.dto.imagemap;


import lombok.*;
import shop.mtcoding.village.model.map.ImageMap;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageMapSaveRequest {
    private Place placeId;
    private double lat;
    private double lng;
    private Integer zoom;


    public ImageMap toEntity(String mapImageUrl) {
        ImageMap imageMap = new ImageMap();
        imageMap.setPlace(placeId);
        imageMap.setLat(lat);
        imageMap.setLng(lng);
        imageMap.setZoom(zoom);
        imageMap.setMapImageUrl(mapImageUrl);
        return imageMap;
    }
}

