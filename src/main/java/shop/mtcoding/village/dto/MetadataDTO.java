package shop.mtcoding.village.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.village.model.metadata.MetaData;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MetadataDTO {
    @JsonProperty("test")
    private String test;

    public MetaData toEntity() {
        return new MetaData(null, test);

    }


}