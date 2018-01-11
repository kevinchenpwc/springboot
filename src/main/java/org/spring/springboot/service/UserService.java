package org.spring.springboot.service;

import org.spring.springboot.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
public interface UserService extends UserDetailsService {

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    public UserInfo getUserByName(String userName);
}
