package com.blackey.shiro.service.impl;


import com.blackey.shiro.constants.RoleEnum;
import com.blackey.shiro.domain.SysMenuEntity;
import com.blackey.shiro.domain.SysUserEntity;
import com.blackey.shiro.domain.SysUserTokenEntity;
import com.blackey.shiro.mapper.SysMenuMapper;
import com.blackey.shiro.mapper.SysUserMapper;
import com.blackey.shiro.mapper.SysUserTokenMapper;
import com.blackey.shiro.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserTokenMapper sysUserTokenMapper;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == RoleEnum.ROLE_SUPER.getCode()){
            List<SysMenuEntity> menuList = sysMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysUserMapper.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenMapper.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserMapper.selectById(userId);
    }
}
