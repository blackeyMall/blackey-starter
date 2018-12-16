package com.blackey.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackey.shiro.domain.SysUserTokenEntity;


/**
 * 系统用户Token
 * 
 * @author kavenW
 *
 * @date 2017-03-23 15:22:07
 */

public interface SysUserTokenMapper extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
