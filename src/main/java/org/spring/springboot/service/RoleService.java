package org.spring.springboot.service;

import java.util.List;

import org.spring.springboot.domain.Role;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
public interface RoleService {

    /**
     * 根据用户ID获取用户角色
     * @param userId
     * @return
     */
    public List<Role> getUserRole(Integer userId);
}
