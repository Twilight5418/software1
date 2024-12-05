package org.twilight.software1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.twilight.software1.dto.ApiResponse;
import org.twilight.software1.dto.LoginRequest;
import org.twilight.software1.dto.RegisterRequest;
import org.twilight.software1.entity.User;
import org.twilight.software1.service.UserService;



@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录接口
     *
     * @param loginRequest 包含用户名、密码和角色的登录请求
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@Valid @RequestBody LoginRequest loginRequest) {
        boolean isValid = userService.validateCredentials(
                loginRequest.getUsername(),
                loginRequest.getPassword(),
                loginRequest.getRole()
        );

        if (isValid) {
            User user = userService.findUserByUsername(loginRequest.getUsername());
            ApiResponse<User> response = new ApiResponse<>(200, "登录成功", user);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<User> response = new ApiResponse<>(401, "用户名、密码或角色错误", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    /**
     * 注册接口
     *
     * @param registerRequest 包含用户名、密码和角色的注册请求
     * @return 注册结果
     */
     @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        // 检查用户名是否已存在
        boolean exists = userService.existsByUsername(registerRequest.getUsername());
        if (exists) {
            ApiResponse<User> response = new ApiResponse<>(409, "用户名已存在", null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // 创建新用户
        User newUser = new User();
        newUser.setName(registerRequest.getUsername());
        newUser.setPassword(registerRequest.getPassword()); // 密码将在服务层加密
        newUser.setRole(registerRequest.getRole());

        boolean isSaved = userService.saveUser(newUser);
        if (isSaved) {
            ApiResponse<User> response = new ApiResponse<>(201, "注册成功", newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            ApiResponse<User> response = new ApiResponse<>(500, "注册失败", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
