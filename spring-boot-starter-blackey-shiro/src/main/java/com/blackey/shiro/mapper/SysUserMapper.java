package com.blackey.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackey.shiro.domain.SysUserEntity;

import java.util.List;

/**
 * 系统用户
 * 
 * @author kavenW
 *
 * @date 2016年9月18日 上午9:34:11
 */

public interface SysUserMapper extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

}
