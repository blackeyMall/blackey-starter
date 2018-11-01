package com.blackey.jpa.service.impl;

import com.blackey.jpa.common.Icommon.IForm;
import com.blackey.jpa.common.Icommon.ISearch;
import com.blackey.jpa.model.BaseModel;
import com.blackey.jpa.repo.BaseRepository;
import com.blackey.jpa.service.IBaseService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * 数据库接口实现类
 *
 * @autor wangwei
 * @date: 2018/10/29
 */
public abstract class BaseServiceImpl<M extends BaseModel> implements IBaseService<M> {

    protected abstract BaseRepository<M> getRepo();


    @Override
    public Page<M> pageList(ISearch<M> iISearch) {
        return getRepo().findAll(iISearch.getSpecification(), iISearch.getPage());
    }

    @Override
    public List<M> list(ISearch<M> iISearch) {
        return getRepo().findAll(iISearch.getSpecification(), iISearch.getSort());
    }

    @Override
    public M detail(String id) throws EntityNotFoundException{
        return StringUtils.isEmpty(id) ? null : this.getRepo().getOne(id);
    }

    @Override
    public M loadWithCopy(IForm<M> paramIForm, M entity) {

        if(paramIForm.getId() == null){

            paramIForm.toModel(entity, Boolean.FALSE);
        } else {

          entity = detail(paramIForm.getId());
          paramIForm.toModel(entity,Boolean.TRUE);

          if (paramIForm.isUpdateTimeModified()){
              entity.setTimeModified(System.currentTimeMillis());
          }
        }

        return entity;
    }

    @Override
    public M saveForm(IForm<M> paramIForm, M entity) {
        return save(loadWithCopy(paramIForm,entity));
    }

    @Override
    public M save(M entity) {
        return getRepo().save(entity);
    }

    @Override
    public M saveForm(IForm<M> paramIForm) throws Exception {

        return saveForm(paramIForm, createBean().newInstance());
    }

    @Override
    public Integer fakeDeleteById(String paramString) {
        return getRepo().fakeDeleteById(paramString);
    }

    @Override
    public Integer fakeDeleteById(String[] paramArrayOfString) {
        return getRepo().fakeDeleteByIds(Arrays.asList(paramArrayOfString));
    }
}
