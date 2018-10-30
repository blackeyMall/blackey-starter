package com.blackey.mybatis.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Kaven
 * Date: 2018/4/8
 * Desc:
 */
@Setter
@Getter
public abstract class BaseModel<T extends Model> extends Model<T> {

    @TableId
    private String id;

    @TableLogic
    private Integer isDeleted;

    private Date createDate;

    private Date updateDate;

    private String createBy;

    private String updateBy;
}
