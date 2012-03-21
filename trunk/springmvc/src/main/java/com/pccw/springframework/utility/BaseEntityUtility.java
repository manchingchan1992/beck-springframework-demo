package com.pccw.springframework.utility;

import java.util.Date;

import com.pccw.springframework.constant.TransactionIndicator;
import com.pccw.springframework.repository.BaseEntity;

public class BaseEntityUtility {
	
	public static void setCommonProperties(BaseEntity baseEntity,String transactionIndicator){
		if(baseEntity == null) return;
		
		if(TransactionIndicator.INSERT.equals(transactionIndicator)){
			baseEntity.setCreateBy("1203100001");
			baseEntity.setCreateDateTime(new Date());
		}
		baseEntity.setLastUpdateBy("1203100001");
		baseEntity.setLastUpdateDateTime(new Date());
		baseEntity.setLastTransactionIndicator(transactionIndicator);
	}
}
