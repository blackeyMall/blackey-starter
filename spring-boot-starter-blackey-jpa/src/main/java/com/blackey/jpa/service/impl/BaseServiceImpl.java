package com.blackey.jpa.service.impl;

import com.blackey.jpa.common.Form;
import com.blackey.jpa.common.Search;
import com.blackey.jpa.model.BaseModel;
import com.blackey.jpa.repo.BaseRepository;
import com.blackey.jpa.service.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;

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
    public Page<M> pageList(Search<M> iSearch) {
        return getRepo().findAll(iSearch.getSpecification(), iSearch.getPage());
    }

    @Override
    public List<M> list(Search<M> iSearch) {
        return getRepo().findAll(iSearch.getSpecification(),iSearch.getSort());
    }

    @Override
    public M detail(String id){
        if (StringUtils.isEmpty(id)){
            return null;
        }

        return getRepo().findOne(id);
    }

    @Override
    public M loadWithCopy(Form<M> paramForm, M entity) {

        if(paramForm.getId() == null){

            paramForm.toModel(entity, Boolean.FALSE);
        } else {

          entity = detail(paramForm.getId());
          paramForm.toModel(entity,Boolean.TRUE);

          if (paramForm.isUpdateTimeModified()){
              entity.setTimeModified(System.currentTimeMillis());
          }
        }

        return entity;
    }

    @Override
    public M saveForm(Form<M> paramForm, M entity) {
        return save(loadWithCopy(paramForm,entity));
    }

    @Override
    public M save(M entity) {
        return getRepo().save(entity);
    }

    @Override
    public M saveForm(Form<M> paramForm) throws Exception {

        return saveForm(paramForm, createBean().newInstance());
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
