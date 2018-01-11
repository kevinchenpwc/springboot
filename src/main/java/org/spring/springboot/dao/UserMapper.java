package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.UserInfo;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    public UserInfo getUserByName(@Param("userName")String userName);
}
