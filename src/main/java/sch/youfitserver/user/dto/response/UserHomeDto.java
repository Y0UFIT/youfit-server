package sch.youfitserver.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHomeDto {

    private Long userId;
    private String nickname;
    private String changeChart;
}
