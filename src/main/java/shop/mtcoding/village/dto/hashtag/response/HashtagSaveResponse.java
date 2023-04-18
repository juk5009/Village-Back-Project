package shop.mtcoding.village.dto.hashtag.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HashtagSaveResponse {

    private String hashtag;

    public HashtagSaveResponse(String hashtag) {
        this.hashtag = hashtag;
    }
}
