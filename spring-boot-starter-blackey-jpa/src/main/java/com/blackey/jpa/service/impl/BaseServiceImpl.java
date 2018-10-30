package com.blackey.jpa.service.impl;

import com.blackey.jpa.model.BaseModel;
import com.blackey.jpa.repo.BaseRepository;
import com.blackey.jpa.service.IBaseService;

/**
 * TODO 描述
 *
 * @autor wangwei
 * @date: 2018/10/29
 */
public abstract class BaseServiceImpl<M extends BaseModel> implements IBaseService<M> {

    protected abstract BaseRepository<M> getRepo();


}
