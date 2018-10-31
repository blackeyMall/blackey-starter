package com.blackey.jpa.service;

import com.blackey.jpa.common.Form;
import com.blackey.jpa.common.Search;
import com.blackey.jpa.model.BaseModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 数据库接口
 *
 * @author  wangwei
 * @date 2018/10/29
 */
public interface IBaseService<Model extends BaseModel> {

    /**
     * 根据枚举对象创建实例
     * @return
     */
    default Class<Model> createBean() {
        return ((Class<Model>)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }


    /**
     * 分页查询
     * @param paramSearch
     * @return
     */
    Page<Model> pageList(Search<Model> paramSearch);

    /**
     * 列表查询
     * @param paramSearch
     * @return
     */
    List<Model> list(Search<Model> paramSearch);

    /**
     * 单条查询
     * @param id
     * @return
     */
    Model detail(String id);


    /**
     * 加载并copy实体对象
     * @param paramForm
     * @param entity
     * @return
     */
    Model loadWithCopy(Form<Model> paramForm, Model entity);

    /**
     * 保存form 对象
     * @param paramForm
     * @param entity
     * @return
     */
    Model saveForm(Form<Model> paramForm, Model entity);

    /**
     * 保存entity对象
     * @param entity
     * @return
     */
    Model save(Model entity);


    /**
     * 保存form 对象
     * @param paramForm
     * @return
     * @throws Exception
     */
    Model saveForm(Form<Model> paramForm)
            throws Exception;

    /**
     * 假删
     * @param id
     * @return
     */
    Integer fakeDeleteById(String id);

    /**
     * 批量假删
     * @param ids
     * @return
     */
    Integer fakeDeleteById(String[] ids);

}
