package shop.mtcoding.village.model.metadata;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "matadata_tb")
public class MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String test;

    @Builder
    public MetaData(Long id, String test) {
        this.id = id;
        this.test = test;
    }
}
