package org.spring.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.Role;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
@Mapper
public interface RoleMapper    {

    /**
     * 根据用户ID获取用户角色
     * @param userId
     * @return
     */
    public List<Role> getUserRole(@Param("userId") Integer userId);
}
