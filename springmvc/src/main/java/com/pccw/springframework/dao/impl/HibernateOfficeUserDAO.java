package com.pccw.springframework.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pccw.springframework.dao.OfficeUserDAO;
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

}
