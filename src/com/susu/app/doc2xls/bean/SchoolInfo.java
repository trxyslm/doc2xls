package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * ѧУ
 * 
 * @author liming.slm
 *
 */
public class SchoolInfo {
	private String name;
	@TitleAnno(title = "ѧУ��ʶ��")
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
