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
import org.springframework.util.CollectionUtils;

import com.pccw.springframework.dao.MessageManagementDAO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;
import com.pccw.springframework.repository.EmailMessage;

@Component(value="messageManagementDao")
public class HibernateMessageManagementDaoImpl implements MessageManagementDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void sendEmail(final EmailMessage emailMessage) {		
		hibernateTemplate.save(emailMessage);
	}
	
	public List<EmailMessage> getMessagesForSearch(final EmailMessagePagedCriteria pagedCriteria){
		@SuppressWarnings("unchecked")
		List<EmailMessage> results = (List<EmailMessage>)hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("FROM EmailMessage msg ");
				hql.append( getHQLFilter(pagedCriteria));
				hql.append( getHQLSort());
				
				Query query = session.createQuery(hql.toString());
				
				setParameter(query,pagedCriteria);
				
				if(pagedCriteria.getPagedCriteria().getPageFilter().getRowEnd() > 0){
					query.setFirstResult(pagedCriteria.getPagedCriteria().getPageFilter().getRowStart());
					query.setMaxResults(pagedCriteria.getPagedCriteria().getPageFilter().getRowEnd()
							          - pagedCriteria.getPagedCriteria().getPageFilter().getRowStart());
				}
				return query.list();
			}
		});
		return results;
	}
	
	public Integer getMessagesCountForSearch(final EmailMessagePagedCriteria pagedCriteria){
		@SuppressWarnings("unchecked")
		Integer result = (Integer)hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("SELECT count(*) ");
				hql.append("FROM EmailMessage msg ");
				hql.append( getHQLFilter(pagedCriteria));
				
				Query query = session.createQuery(hql.toString());
				
				setParameter(query,pagedCriteria);
				
				return ((Long)query.uniqueResult()).intValue();
			}
		});
		return result;
	}
	
	private StringBuffer getHQLFilter(EmailMessagePagedCriteria pagedCriteria){
		StringBuffer hql = new StringBuffer();
		hql.append("WHERE 1=1 ");
		if(true){
			hql.append("AND msg.lastTransactionIndicator != 'D' ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getMessageFrom())){
			hql.append("AND upper(msg.messageFrom) = :messageFrom ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getMessageCc())){
			hql.append("AND upper(msg.messageCc) = :messageCc ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getMessageTo())){
			hql.append("AND upper(msg.messageTo) = :messageTo ");
		}
		return hql;
	}
	
	private StringBuffer getHQLSort(){
		StringBuffer sort = new StringBuffer();
		sort.append("ORDER BY msg.lastUpdateDateTime DESC ");
		return sort;
	}
	
	private void setParameter(Query query , EmailMessagePagedCriteria pagedCriteria){
		if(!StringUtils.isEmpty(pagedCriteria.getMessageFrom())){
			query.setString("messageFrom", pagedCriteria.getMessageFrom().trim().toUpperCase());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getMessageCc())){
			query.setString("messageCc", pagedCriteria.getMessageCc().trim().toUpperCase());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getMessageTo())){
			query.setString("messageTo", pagedCriteria.getMessageTo().trim().toUpperCase());
		}
	}
	
	@SuppressWarnings("unchecked")
	public EmailMessage getEmailMessageBySysRefMsg(final String sysRefMsg){
		EmailMessage msg = (EmailMessage)hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "FROM EmailMessage msg WHERE msg.sysRefMessage = :sysRefMsg AND msg.lastTransactionIndicator != 'D' ";
				Query query = session.createQuery(hql);
				query.setString("sysRefMsg", sysRefMsg);
				List<EmailMessage> msgs = query.list();
				return CollectionUtils.isEmpty(msgs) ? null : msgs.get(0);
			}
		});
		return msg;
	}

	public void updateEmailMessage(EmailMessage msg) {
		hibernateTemplate.update(msg);
	}
	
	@SuppressWarnings("unchecked")
	public void markEmailAsDelete(final String sysRefMsg){
		hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "UPDATE t_msg msg SET msg.lst_tx_actn = 'D' WHERE msg.sys_ref_msg = :sysRefMsg ";
				Query query = session.createSQLQuery(sql);
				query.setString("sysRefMsg",sysRefMsg );
				query.executeUpdate();
				return null;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public void deleteEmailForever(final String sysRefMsg){
		hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "DELETE t_msg msg WHERE msg.sys_ref_msg = :sysRefMsg";
				Query query = session.createSQLQuery(sql);
				query.setString("sysRefMsg", sysRefMsg);
				query.executeUpdate();
				return null;
			}
		});
	}

}
