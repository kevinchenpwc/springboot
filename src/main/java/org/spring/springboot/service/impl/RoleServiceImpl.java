package org.spring.springboot.service.impl;

import java.util.List;

import org.spring.springboot.dao.RoleMapper;
import org.spring.springboot.domain.Role;
import org.spring.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getUserRole(Integer userId) {
        List<Role> roles = roleMapper.getUserRole(userId);
        return roles;
    }
}
