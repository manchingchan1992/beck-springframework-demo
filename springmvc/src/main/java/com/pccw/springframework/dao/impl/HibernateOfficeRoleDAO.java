package com.pccw.springframework.dao.impl;

import java.math.BigDecimal;
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

import com.pccw.springframework.constant.CommonConstant;
import com.pccw.springframework.dao.OfficeRoleDAO;
import com.pccw.springframework.dto.OfficeRolePagedCriteria;
import com.pccw.springframework.repository.OfficeRole;

@Component("officeRoleDao")
public class HibernateOfficeRoleDAO implements OfficeRoleDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public OfficeRole getOfficeRoleBySysRefRole(final String sysRefRole) {
		OfficeRole role = (OfficeRole)hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "FROM OfficeRole role WHERE role.sytemRoleRefNum = :sysRefRole AND role.lastTransactionIndicator != 'D' ";
				Query query = session.createQuery(hql);
				query.setString("sysRefRole", sysRefRole);
				return query.uniqueResult();
			}
		});
		return role;
	}

	@SuppressWarnings("unchecked")
	public List<OfficeRole> getOfficeRolesByCriteria(
			final OfficeRolePagedCriteria pagedCriteria) {
		List<OfficeRole> roles = (List<OfficeRole>)hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append("FROM OfficeRole role ");
				buffer.append(getHQLFilter(pagedCriteria));
				buffer.append("ORDER BY role.sytemRoleRefNum ASC ");
				Query query = session.createQuery(buffer.toString());
				setParameter(query , pagedCriteria);
				
				if(pagedCriteria.getPagedCriteria().getPageFilter().getRowEnd() != 0){
					query.setFirstResult(pagedCriteria.getPagedCriteria().getPageFilter().getRowStart());
					query.setMaxResults(pagedCriteria.getPagedCriteria().getPageFilter().getRowEnd() - pagedCriteria.getPagedCriteria().getPageFilter().getRowStart());
				}
				return query.list();
			}
		});
		return roles;
	}
	
	private void setParameter(Query query , OfficeRolePagedCriteria pagedCriteria){
		if(!StringUtils.isEmpty(pagedCriteria.getRoleId())){
			query.setString("roleId", pagedCriteria.getRoleId().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getRoleName())){
			query.setString("roleName", pagedCriteria.getRoleName().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getRoleDesc())){
			query.setString("roleDesc", pagedCriteria.getRoleDesc().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getStatus())){
			query.setString("status", pagedCriteria.getStatus().trim());
		}
	}
	
	private StringBuffer getHQLFilter(OfficeRolePagedCriteria pagedCriteria){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("WHERE 1=1 ");
		buffer.append("AND role.lastTransactionIndicator != 'D' ");
		
		if(!StringUtils.isEmpty(pagedCriteria.getRoleId())){
			buffer.append("AND upper(role.roleId) LIKE '%'||upper(:roleId)||'%' ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getRoleName())){
			buffer.append("AND upper(role.roleName) LIKE '%'||upper(:roleName)||'%' ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getRoleDesc())){
			buffer.append("AND upper(role.roleDesc) LIKE '%'||upper(:roleDesc)||'%' ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getStatus())){
			buffer.append("AND upper(role.status) = upper(:status) ");
		}
		
		return buffer;
	}

	@SuppressWarnings("unchecked")
	public int getOfficeRolesCountByCriteria(
			final OfficeRolePagedCriteria pagedCriteria) {
		Integer count = (Integer)hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append("SELECT count(role) FROM OfficeRole role ");
				buffer.append(getHQLFilter(pagedCriteria));
				Query query = session.createQuery(buffer.toString());
				setParameter(query, pagedCriteria);
				return ((Long)query.uniqueResult()).intValue();
			}
		});
		return count.intValue();
	}

}
