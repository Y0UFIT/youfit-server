package sch.youfitserver.center.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CenterListDto {

    private Long centerId;
    private String centerName;
    private Double lat;
    private Double lot;
}
