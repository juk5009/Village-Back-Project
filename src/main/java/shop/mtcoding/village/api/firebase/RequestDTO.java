package shop.mtcoding.village.api.firebase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.village.model.fcm.Fcm;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class RequestDTO {
    private String title;
    private String body;
    private String targetToken;

//    public Fcm toEntity() {
////        return new Fcm(targetToken);
//    }
}