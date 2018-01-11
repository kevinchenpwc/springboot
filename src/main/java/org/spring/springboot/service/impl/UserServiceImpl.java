package org.spring.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.spring.springboot.dao.UserMapper;
import org.spring.springboot.domain.Role;
import org.spring.springboot.domain.UserInfo;
import org.spring.springboot.service.RoleService;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author
 * @version 1.0 2016/10/11
 * @description
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserInfo userInfo = getUserByName(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException(userName);
        }

        //根据用户获取用户角色
        List<Role> roles = roleService.getUserRole(userInfo.getUserId());
        //定义权限集合
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        //添加权限到集合中
        for (Role role : roles){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleType()));
        }
        boolean booleanStatus = true;
        if(userInfo.getStatus() == 0){
            booleanStatus = false;
        }
        //加密密码
        User user = new User(userInfo.getUserName(),userInfo.getPassword(),booleanStatus,true,true, true, grantedAuthorities);
        return user;
    }

    @Override
    public UserInfo getUserByName(String userName) {
        UserInfo userInfo = userMapper.getUserByName(userName);
        return userInfo;
    }
}
