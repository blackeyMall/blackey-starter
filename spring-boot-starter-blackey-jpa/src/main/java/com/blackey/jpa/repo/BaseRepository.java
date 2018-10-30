package com.blackey.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO 描述
 *
 * @author  wangwei
 * @date  2018/10/29
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T,String>,JpaSpecificationExecutor<T> {

    /**
     * 假删
     * @param paramString
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update #{#entityName} u set u.deleted = 1 where u.id = ?1")
    Integer fakeDeleteById(String paramString);

    /**
     * 批量假删
     * @param paramList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update #{#entityName} u set u.deleted = 1 where u.id in (?1)")
    Integer fakeDeleteByIds(List<String> paramList);
}


