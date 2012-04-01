package com.pccw.springframework.convertor;

import com.pccw.springframework.dto.BaseDTO;
import com.pccw.springframework.repository.BaseEntity;

public class BasePropertiesConvertor {
	
	public static BaseDTO toDto (BaseEntity po , BaseDTO dto) {
	
		if(po != null){
			dto.setCreateBy(po.getCreateBy());
			dto.setCreateDateTime(po.getCreateDateTime());
			dto.setLastUpdateBy(po.getLastUpdateBy());
			dto.setLastUpdateDateTime(po.getLastUpdateDateTime());
			dto.setLastUpdateTransaction(po.getLastTransactionIndicator());
		}
		
		return dto;
	}
	
	public static BaseEntity toPo(BaseEntity po , BaseDTO dto){

		if(dto != null){
			po.setCreateBy(dto.getCreateBy());
			po.setCreateDateTime(dto.getCreateDateTime());
			po.setLastUpdateBy(dto.getLastUpdateBy());
			po.setLastUpdateDateTime(dto.getLastUpdateDateTime());
			po.setLastTransactionIndicator(dto.getLastUpdateTransaction());
		}
		
		return po;
	}
}
