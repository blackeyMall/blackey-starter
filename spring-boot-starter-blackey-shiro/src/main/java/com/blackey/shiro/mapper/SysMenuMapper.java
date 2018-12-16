package com.blackey.shiro.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackey.shiro.domain.SysMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author kavenW
 *
 * @date 2016年9月18日 上午9:33:01
 */

public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	@Select("select * from sys_menu where parent_id = #{parentId} order by order_num asc")
	List<SysMenuEntity> queryListParentId(@Param("parentId") Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	@Select("select * from sys_menu where type != 2 order by order_num asc")
	List<SysMenuEntity> queryNotButtonList();

}
