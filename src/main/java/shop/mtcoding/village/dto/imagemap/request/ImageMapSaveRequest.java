package shop.mtcoding.village.dto.imagemap.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.village.model.map.ImageMap;
import shop.mtcoding.village.model.place.Place;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageMapSaveRequest {
    private Place place;
    private double lat;
    private double lng;
    private Integer zoom;


    public ImageMap toEntity(String mapImageUrl) {
        ImageMap imageMap = new ImageMap();
        imageMap.setPlace(place);
        imageMap.setLat(lat);
        imageMap.setLng(lng);
        imageMap.setZoom(zoom);
        imageMap.setMapImageUrl(mapImageUrl);
        return imageMap;
    }
}

