package com.blackey.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * TODO 描述
 *
 * @autor wangwei
 * @date: 2018/10/29
 */
public interface BaseRepository<T> extends JpaRepository<T,String>,JpaSpecificationExecutor<T> {
}


