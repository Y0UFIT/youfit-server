package sch.youfitserver.center.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "center")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Center {

    @Id
    @Column(name = "center_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long centerId;

    private String region;

    private String location;

    @Column(name = "center_name")
    private String centerName;

    @Column(name = "lat")
    private Double lat; // 위도

    @Column(name = "lot")
    private Double lot; // 경도

    private String addr; // 센터 주소

    private String tel; // 센터 번호

}
