package sch.youfitserver.center.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sch.youfitserver.center.dto.CenterDto;
import sch.youfitserver.center.dto.CenterListDto;
import sch.youfitserver.center.service.CenterService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    // 체육센터 전체 리스트
    @GetMapping("/center")
    public ResponseEntity<List<CenterListDto>> getCenters() throws IOException {
        List<CenterListDto> centers = centerService.getCenters();
        return ResponseEntity.ok(centers);
    }

    // 체육센터의 상세 정보
    @GetMapping("/center/{centerId}")
    public ResponseEntity<CenterDto> getCenter(@PathVariable Long centerId) throws IOException {
        CenterDto centerDto = centerService.getCenter(centerId);
        return ResponseEntity.ok(centerDto);
    }
}
