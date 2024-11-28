package sch.youfitserver.center.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sch.youfitserver.center.dto.CenterListDto;
import sch.youfitserver.center.service.CenterService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @GetMapping("/center")
    public ResponseEntity<List<CenterListDto>> getCenters() throws IOException {

        return centerService.getCenters();
    }
}
