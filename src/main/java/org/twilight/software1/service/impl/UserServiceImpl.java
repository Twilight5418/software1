package org.twilight.software1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.twilight.software1.entity.User;
import org.twilight.software1.mapper.UserMapper;
import org.twilight.software1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 导入必要的类
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 声明并初始化 passwordEncoder
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean validateCredentials(String username, String password, String role) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username)
                    .eq("role", role);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public User findUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean existsByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        Long count = userMapper.selectCount(queryWrapper); // 修改了类型
        return count != null && count > 0;
    }

    @Override
    public boolean saveUser(User user) {
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 生成唯一ID（例如，使用 UUID）
        user.setId(java.util.UUID.randomUUID().toString());
        return userMapper.insert(user) > 0;
    }

    @Override
    public String getUserIdByUsername(String username) {
        User user = findUserByUsername(username);
        return (user != null) ? user.getId() : null;
    }
}
