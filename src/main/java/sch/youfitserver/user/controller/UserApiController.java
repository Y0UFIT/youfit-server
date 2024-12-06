package sch.youfitserver.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import sch.youfitserver.fitness.entity.Fitness;
import sch.youfitserver.fitness.entity.FitnessResult;
import sch.youfitserver.fitness.repository.FitnessRepository;
import sch.youfitserver.fitness.repository.FitnessResultRepository;
import sch.youfitserver.user.dto.request.UserRequestDto;
import sch.youfitserver.user.dto.response.UserHomeDto;
import sch.youfitserver.user.dto.response.UserResponseDto;
import sch.youfitserver.user.entity.User;
import sch.youfitserver.user.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor  // final을 사용하는 userService를 매개변수로 갖는 생성자를 생성해줌
/** @RequestBody : 객체를 JSON 형식을 반환한다**/
public class UserApiController {

    private final UserService userService;
    private final FitnessRepository fitnessRepository;
    private final FitnessResultRepository fitnessResultRepository;

    @PatchMapping("/user/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody UserRequestDto request) {
        User updateuser = userService.update(userId, request);
        return ResponseEntity.ok()
                .body(updateuser);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findUser(@PathVariable Long userId){
        User user = userService.findById(userId);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        return ResponseEntity.ok()
                .body(new UserResponseDto(user));
    }
    @GetMapping("/{userId}/home")
    public ResponseEntity<?> homepage(@PathVariable Long userId) {
        // User 테이블에서 User 조회
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body("로그인을 해주세요.");
        }
        // Fitness 테이블에서 User와 연결된 모든 Fitness 데이터 조회
        List<Fitness> fitnessList = fitnessRepository.findByUser_UserId(userId);
        if (fitnessList.isEmpty()) {
            return ResponseEntity.status(404).body("운동측정 결과가 없습니다.");
        }
        // 가장 최근의 FitnessResult를 저장할 변수
        FitnessResult latestFitnessResult = null;
        // 각 Fitness에 대해 FitnessResult 데이터 조회
        for (Fitness fitness : fitnessList) {
            // FitnessResult 중에서 가장 최근의 결과를 찾기
            List<FitnessResult> fitnessResultList = fitnessResultRepository.findByFitness_fitnessId(fitness.getFitnessId());
            if (!fitnessResultList.isEmpty()) {
                // 가장 큰 fitness_result_id를 가진 FitnessResult 찾기
                FitnessResult maxResult = fitnessResultList.stream()
                        .max(Comparator.comparingLong(FitnessResult::getFitnessResultId))
                        .orElse(null);
                // 가장 최근의 FitnessResult 업데이트
                if (maxResult != null && (latestFitnessResult == null || maxResult.getFitnessResultId() > latestFitnessResult.getFitnessResultId())) {
                    latestFitnessResult = maxResult;
                }
            }
        }
        // 가장 최근의 FitnessResult가 없으면 404 응답
        if (latestFitnessResult == null) {
            return ResponseEntity.status(404).body("최근 운동 측정결과를 찾을 수 없습니다.");
        }
        // UserHomeDto 생성 (changeChart를 단일 값으로 포함)
        UserHomeDto response = new UserHomeDto(
                userId,
                user.getNickname(),
                latestFitnessResult.getChangeChart() // 가장 최근의 changeChart를 단일 값으로 포함
        );
        return ResponseEntity.ok(response);
    }
}
