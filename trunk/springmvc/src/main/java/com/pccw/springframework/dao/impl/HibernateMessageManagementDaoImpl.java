package com.pccw.springframework.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pccw.springframework.dao.MessageManagementDAO;
import com.pccw.springframework.repository.EmailMessage;

@Component(value="messageManagementDao")
public class HibernateMessageManagementDaoImpl implements MessageManagementDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<EmailMessage> getMessagesForInbox() {
		Object messages = hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return null;
			}
		});
		return null;
	}

}
