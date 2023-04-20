package shop.mtcoding.village.dto.hashtag.request;

import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.hashtag.Hashtag;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class HashtagSaveDTO {

    private List<String> hashtagName;

    public Hashtag toEntity() {
        Hashtag hashtag = new Hashtag();
        hashtag.setHashtagName(hashtagName);
        return hashtag;
    }

}
