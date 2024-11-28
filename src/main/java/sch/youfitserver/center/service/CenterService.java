package sch.youfitserver.center.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sch.youfitserver.center.dto.CenterListDto;
import sch.youfitserver.center.entity.Center;
import sch.youfitserver.center.repository.CenterRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;

    @Transactional
    public ResponseEntity<List<CenterListDto>> getCenters() {
        List<Center> centers = centerRepository.findAll();

        List<CenterListDto> centerList = centers.stream()
                .map(this::convertToCenterListDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(centerList);
    }

    private CenterListDto convertToCenterListDto(Center center) {

        // Center -> CenterListDTO 변환
        return CenterListDto.builder()
                .centerId(center.getCenterId())
                .centerName(center.getCenterName())
                .lat(center.getLat())
                .lot(center.getLot())
                .build();
    }
}
