package shop.mtcoding.village.core.firebase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class RequestDTO {
    private String title;
    private String body;
    private String targetToken;

}