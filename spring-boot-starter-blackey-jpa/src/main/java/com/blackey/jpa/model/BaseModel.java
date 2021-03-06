package com.blackey.jpa.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * 基类 baseModel
 *
 * @author  wangwei
 * @date  2018/10/29
 */
@MappedSuperclass
public class BaseModel extends PkModel<String> {

    private static final long serialVersionUID = -715456645278100196L;
    private static final int pk_length = 36;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = pk_length)
    private String id;

    @Column(updatable = false)
    private Long timeCreated;

    @Basic
    private Long timeModified;

    @Basic
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean deleted;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Long getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(Long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Long getTimeModified() {
        return this.timeModified;
    }

    public void setTimeModified(Long timeModified) {
        this.timeModified = timeModified;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(getClass().equals(obj.getClass()))) {
            return false;
        }

        PkModel<?> that = (PkModel<?>) obj;

        return ((null != getId()) && getId().equals(that.getId()));
    }

    @Override
    public int hashCode() {
        int hashCode = 17;

        hashCode += ((null == getId()) ? 0 : getId().hashCode() * 31);

        return hashCode;
    }

    @PrePersist
    void onCreate() {
        setTimeCreated(System.currentTimeMillis());
        setTimeModified(System.currentTimeMillis());
        setDeleted(Boolean.FALSE);
    }

    @PreUpdate
    void onPersist() {
        setTimeModified(System.currentTimeMillis());
    }
}
