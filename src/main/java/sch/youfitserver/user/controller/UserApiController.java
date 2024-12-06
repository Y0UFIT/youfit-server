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

import java.util.Optional;

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
            return ResponseEntity.status(404).body("User not found");
        }
        // Fitness 테이블에서 User와 연결된 Fitness 데이터 조회
        Optional<Fitness> fitnessOptional = fitnessRepository.findByUser_UserId(userId);
        if (fitnessOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Fitness data not found for userId: " + userId);
        }
        Fitness fitness = fitnessOptional.get();
        // FitnessResult 테이블에서 Fitness와 연결된 데이터 조회
        Optional<FitnessResult> fitnessResultOptional = fitnessResultRepository.findByFitness_fitnessId(fitness.getFitnessId());
        if (fitnessResultOptional.isEmpty()) {
            return ResponseEntity.status(404).body("FitnessResult data not found for fitnessId: " + fitness.getFitnessId());
        }
        FitnessResult fitnessResult = fitnessResultOptional.get();
        // 조건 확인
        if (user.equals(fitness.getUser()) && fitness.equals(fitnessResult.getFitness())) {
            // UserHomeDto 생성
            UserHomeDto response = new UserHomeDto(
                    userId,
                    user.getNickname(),
                    fitnessResult.getChangeChart() // FitnessResult의 원하는 필드
            );
            return ResponseEntity.ok(response);
        }
        // 조건이 충족되지 않으면 400 Bad Request
        return ResponseEntity.status(400).body("Data mismatch");
    }

}
