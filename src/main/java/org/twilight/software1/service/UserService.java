package org.twilight.software1.service;

import org.twilight.software1.entity.User;

public interface UserService {
    /**
     * 根据本地数据库验证用户
     * @param username
     * @param password
     * @param role
     * @return
     */
    boolean validateCredentials(String username, String password, String role);
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     *  根据用户名判断用户是否存在
     * @param username
     * @return
     */
    boolean existsByUsername(String username);
    /**
     * 保存用户
     * @param user
     * @return
     */
    boolean saveUser(User user);

    /**
     * 根据用户名获取用户ID
     * @param username
     * @return
     */
    String getUserIdByUsername(String username);
}