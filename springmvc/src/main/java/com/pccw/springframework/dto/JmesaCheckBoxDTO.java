package com.pccw.springframework.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JmesaCheckBoxDTO extends JmesaCriteria {
	private static final long serialVersionUID = 1L;

	private String[] select;

	private String[] currSelect;

	private List<Selection> allSelections = new ArrayList<Selection>();

	public String[] getSelect() {
		return select;
	}

	public void setSelect(String[] select) {
		this.select = select;
	}

	public String[] getCurrSelect() {
		return currSelect;
	}

	public void setCurrSelect(String[] currSelect) {
		this.currSelect = currSelect;
	}

	private List<Selection> getAllSelections() {
		return allSelections;
	}

	private void setAllSelections(List<Selection> allSelections) {
		this.allSelections = allSelections;
	}
	
	public void initAllSelectOption(String[] keys){
		if(keys == null || keys.length == 0){
			setAllSelections(new ArrayList<Selection>());
			return ;
		}
		
		List<Selection> temp = new ArrayList<Selection>();
		for(String key : keys){
			Selection selection = new Selection(key, false);
			temp.add(selection);
		}
		setAllSelections(temp);
	}

}
