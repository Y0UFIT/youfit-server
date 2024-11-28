package sch.youfitserver.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import sch.youfitserver.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

}
