package sch.youfitserver.center.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CenterDto {

    private String region;
    private String location;
    private String centerName;
    private Double lat;
    private Double lot;
    private String addr;
    private String tel;
}