package shop.mtcoding.village.dto.hashtag.request;

import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.hashtag.Hashtag;

import java.util.List;

@ToString
@Getter
public class HashtagSaveRequest {

    private String hashtagName;

    public Hashtag toEntity() {
        return new Hashtag(hashtagName, null);
    }

}
