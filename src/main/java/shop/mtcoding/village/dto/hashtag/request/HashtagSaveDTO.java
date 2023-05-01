package shop.mtcoding.village.dto.hashtag.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.place.Place;

import java.util.List;
import java.util.Optional;

@ToString
@Getter
public class HashtagSaveDTO {

//    private List<HashtagDto> hashtagDto;

    @Setter
    @Getter
    @ToString
    public static class HashtagSaveDto extends Hashtag {
        private String hashtagName;
        private Place placeId;

        public Hashtag toEntity(String name, Place id) {
            Hashtag hashtag3 = new Hashtag();
            hashtag3.setPlace(id);
            hashtag3.setHashtagName(name);
            return hashtag3;
        }
    }
}
