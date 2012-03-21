package com.pccw.springframework.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.springframework.constant.DateFormatConstant;
import com.pccw.springframework.constant.TransactionIndicator;
import com.pccw.springframework.convertor.EmailMessageManagementConvertor;
import com.pccw.springframework.dao.MessageManagementDAO;
import com.pccw.springframework.dao.impl.SimpleHibernateTemplate;
import com.pccw.springframework.dto.EmailMessageDTO;
import com.pccw.springframework.dto.EmailMessagePagedCriteria;
import com.pccw.springframework.repository.EmailMessage;
import com.pccw.springframework.service.MessageManagementService;
import com.pccw.springframework.utility.BaseEntityUtility;
import com.pccw.springframework.utility.DateUtils;

@Service(value="messageManagementService")
@Transactional
public class MessageManagementServiceImpl implements MessageManagementService{
	@Autowired
	private MessageManagementDAO messageManagementDao;
	
	private SimpleHibernateTemplate<EmailMessage, String> commonMessageManagementDao;
	
	@Autowired
	public void setCommonsDAO(SessionFactory sessionFactory){
		commonMessageManagementDao = new SimpleHibernateTemplate<EmailMessage, String>(EmailMessage.class, sessionFactory);
	}
	
	public void sendEmail(EmailMessageDTO dto) {
		EmailMessage po = EmailMessageManagementConvertor.toPo(dto);
		po.setSysRefMessage(DateUtils.formatDateTime(DateFormatConstant.DATETIME_WITHOUT_SEOARATOR_FULL, new Date()));
		BaseEntityUtility.setCommonProperties(po, TransactionIndicator.INSERT);
//		commonMessageManagementDao.save(po);
		messageManagementDao.sendEmail(po);
	}
	
	public List<EmailMessageDTO> getMessagesForOutbox(EmailMessagePagedCriteria pagedCriteria){
		List<EmailMessageDTO> messages = new ArrayList<EmailMessageDTO>();
		List<Object[]> list = messageManagementDao.getMessagesForOutbox(pagedCriteria);
		
		if(CollectionUtils.isEmpty(list)){
			return messages;
		}
		
		for(Object[] msg : list){
			String messageTo = (String)msg[0];
			String messageTitle = (String)msg[1];
			Date msgCrDttm = (Date)msg[2];
			EmailMessageDTO dto = new EmailMessageDTO();
			dto.setMessageTo(StringUtils.isEmpty(messageTo) ? "" : messageTo);
			dto.setMessageTitle(StringUtils.isEmpty(messageTitle) ? "" : messageTitle);
			dto.setCreateDateTime(msgCrDttm);
			messages.add(dto);
		}
		
		return messages;
	}
	
	public Integer getMessagesCountForOutBox(EmailMessagePagedCriteria pagedCriteria){
		return messageManagementDao.getMessagesCountForOutBox(pagedCriteria);
	}
}
