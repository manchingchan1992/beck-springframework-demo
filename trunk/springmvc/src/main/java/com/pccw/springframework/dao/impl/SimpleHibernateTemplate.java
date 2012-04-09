package com.pccw.springframework.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.util.Assert;

import com.pccw.springframework.constant.TransactionIndicator;
import com.pccw.springframework.repository.BaseEntity;

public class SimpleHibernateTemplate<T, PK extends Serializable>{
	@SuppressWarnings("unused")
	private Class<T> entityClass;
	private SessionFactory sessionFactory;

	public SimpleHibernateTemplate(Class<T> entityClass,
			SessionFactory sessionFactory) {
		super();
		this.entityClass = entityClass;
		this.sessionFactory = sessionFactory;
	}	
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public void save(T entity){
		Assert.notNull(entity);
		if(entity instanceof BaseEntity){
			((BaseEntity)entity).setLastTransactionIndicator(TransactionIndicator.INSERT);
		}
		getSession().save( entity );
	}
	
	public void delete(T entity){
		Assert.notNull(entity);
		getSession().delete(entity);
	}
	
	/*
	 * Mark As Delete
	 * */
	public void markAsDelete(T entity){
		Assert.notNull(entity);
		if(entity instanceof BaseEntity){
			((BaseEntity)entity).setLastTransactionIndicator(TransactionIndicator.DELETE);
		}
		getSession().update(entity);
	}
	
	public void update(T entity){
		Assert.notNull(entity);
		if(entity instanceof BaseEntity){
			((BaseEntity)entity).setLastTransactionIndicator(TransactionIndicator.UPDATE);
		}
		getSession().update(entity);
	}
	
	public void saveOrUpdate(T entity){
		Assert.notNull(entity);
		if(entity instanceof BaseEntity){
			((BaseEntity)entity).setLastTransactionIndicator(TransactionIndicator.UPDATE);
		}
		getSession().saveOrUpdate(entity);
	}
}
