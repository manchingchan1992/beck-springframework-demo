package com.pccw.springframework.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pccw.springframework.dto.SortDTO;

public class PageSort implements Serializable {
	private static final long serialVersionUID = 1L;

	List<SortDTO> sorts = new ArrayList<SortDTO>();

	public void addSort(SortDTO sort) {
		sorts.add(sort);
	}

	public List<SortDTO> getSorts() {
		return sorts;
	}

	public void setSorts(List<SortDTO> sorts) {
		this.sorts = sorts;
	}
}
