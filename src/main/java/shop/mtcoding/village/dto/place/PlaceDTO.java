package shop.mtcoding.village.dto.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.village.model.place.PlaceAddress;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {

    private Long id;

    private String title;

    private PlaceAddress address;
}
