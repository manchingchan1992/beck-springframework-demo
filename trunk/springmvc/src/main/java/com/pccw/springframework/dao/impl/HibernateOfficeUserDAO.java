package com.pccw.springframework.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pccw.springframework.dao.OfficeUserDAO;
import com.pccw.springframework.dto.OfficeUserPagedCriteria;
import com.pccw.springframework.repository.OfficeRole;
import com.pccw.springframework.repository.OfficeUser;

@Component(value="officeUserDao")
public class HibernateOfficeUserDAO implements OfficeUserDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public OfficeUser getOfficeUserByLoginId(final String loginId) {
		OfficeUser user = (OfficeUser)hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String HQL  = "FROM OfficeUser usr WHERE usr.loginId = :loginId";
				Query query = session.createQuery(HQL);
				query.setString("loginId", loginId);
				List<OfficeUser> usrs = query.list();
				return CollectionUtils.isEmpty(usrs) ? null : usrs.get(0);
			}
		});
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public OfficeUser getOfficeUserByEmail(final String email){
		OfficeUser user = (OfficeUser)hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "FROM OfficeUser usr WHERE upper(usr.email) = upper(:email) ";
				Query query = session.createQuery(hql);
				
				query.setString("email", email);
				
				return query.uniqueResult();
			}
		});
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<OfficeRole> getAvailableRoles(){
		List<OfficeRole> availableRoles = (List<OfficeRole>)hibernateTemplate.execute(new HibernateCallback() {
			public List<OfficeRole> doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "FROM OfficeRole role WHERE role.lastTransactionIndicator != 'D'";
				Query query = session.createQuery(hql);
				
				return (List<OfficeRole>)query.list();
			}
		});
		
		return availableRoles;
	}
	
	@SuppressWarnings("unchecked")
	public int getUsersCountByCriteria(final OfficeUserPagedCriteria pagedCriteria){
		Integer count = (Integer)hibernateTemplate.execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append("SELECT count(*) FROM OfficeUser usr ");
				
				if(!StringUtils.isEmpty(pagedCriteria.getRole())){
					buffer.append(", usr.roles.elements role ");
				}
				
				buffer.append(getHQLFilter(pagedCriteria));
				
				Query query = session.createQuery(buffer.toString());
				setParameter(query , pagedCriteria);
				
				return ((Long)query.uniqueResult()).intValue();
			}
		});
		
		return count.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<OfficeUser> searchUsersByCriteria(final OfficeUserPagedCriteria pagedCriteria){
		List<OfficeUser> users = (List<OfficeUser>)hibernateTemplate.execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append("SELECT usr FROM OfficeUser usr ");
				
				if(!StringUtils.isEmpty(pagedCriteria.getRole())){
					buffer.append(", usr.roles.elements role ");
				}
				
				buffer.append(getHQLFilter(pagedCriteria));
				buffer.append(getHQLOrder());
				
				Query query = session.createQuery(buffer.toString());
				setParameter(query , pagedCriteria);
				
				if(pagedCriteria.getPagedCriteria().getPageFilter().getRowEnd() > 0){
					query.setFirstResult(pagedCriteria.getPagedCriteria().getPageFilter().getRowStart());
					query.setMaxResults(pagedCriteria.getPagedCriteria().getPageFilter().getRowEnd()
							          - pagedCriteria.getPagedCriteria().getPageFilter().getRowStart());
				}
				return query.list();
			}
		});
		return users;
	}
	
	private StringBuffer getHQLFilter(OfficeUserPagedCriteria pagedCriteria){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("WHERE 1=1 ");
		
		if(!StringUtils.isEmpty(pagedCriteria.getLoginId())){			
			buffer.append("AND upper(usr.loginId) LIKE '%'||upper(:loginId)||'%' ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getEnName())){
			buffer.append("AND upper(usr.enName) LIKE '%'||upper(:enName)||'%' ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getCnName())){
			buffer.append("AND upper(usr.cnName) LIKE '%'||upper(:cnName)||'%' ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getEmail())){
			buffer.append("AND upper(usr.email) LIKE '%'||upper(:email)||'%' ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getAccountStatus())){
			buffer.append("AND upper(usr.accountStatus) = upper(:status) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getRole())){
			buffer.append("AND upper(role.roleId) = upper(:roleId) ");
		}
		return buffer;
	}
	
	private StringBuffer getHQLOrder(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ORDER BY usr.userRecId ASC ");
		return buffer;
	}
	
	private void setParameter(Query query , OfficeUserPagedCriteria pagedCriteria){
		if(!StringUtils.isEmpty(pagedCriteria.getLoginId())){			
			query.setString("loginId", pagedCriteria.getLoginId().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getEnName())){
			query.setString("enName", pagedCriteria.getEnName().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getCnName())){
			query.setString("cnName", pagedCriteria.getCnName().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getEmail())){
			query.setString("email", pagedCriteria.getEmail().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getAccountStatus())){
			query.setString("status", pagedCriteria.getAccountStatus().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getRole())){
			query.setString("roleId", pagedCriteria.getRole());
		}
	}

}
