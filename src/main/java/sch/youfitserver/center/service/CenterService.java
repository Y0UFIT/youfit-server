package sch.youfitserver.center.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sch.youfitserver.center.dto.CenterDto;
import sch.youfitserver.center.dto.CenterListDto;
import sch.youfitserver.center.entity.Center;
import sch.youfitserver.center.repository.CenterRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;

    @Transactional
    public List<CenterListDto> getCenters() {
        List<Center> centers = centerRepository.findAll();

        return centers.stream()
                .map(this::convertToCenterListDto)
                .collect(Collectors.toList());
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

    public CenterDto getCenter(Long centerId) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 체육센터를 찾을 수 없습니다."));

        return CenterDto.builder()
                .region(center.getRegion())
                .location(center.getLocation())
                .centerName(center.getCenterName())
                .lat(center.getLat())
                .lot(center.getLot())
                .addr(center.getAddr())
                .tel(center.getTel())
                .build();
    }
}
