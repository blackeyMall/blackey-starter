package com.blackey.jpa.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class PkModel<PK extends Serializable> implements Persistable<PK> {
	private static final long serialVersionUID = -715456645278100196L;

	public abstract PK getId();

	public abstract void setId(PK paramPK);

	public boolean isNew() {
		return (null == getId());
	}
}
