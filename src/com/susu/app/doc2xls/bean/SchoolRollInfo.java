package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * ѧ��������Ϣ
 * 
 * @author liming.slm
 *
 */
public class SchoolRollInfo {
	@TitleAnno(title = "ѧ������")
	private String schoolNum;
	@TitleAnno(title = "����ѧ��")
	private String classNum;
	@TitleAnno(title = "")
	private String grade;
	@TitleAnno(title = "���")
	private String _class;
	/**
	 * ��ѧʱ��
	 */
	@TitleAnno(title = "��ѧ����")
	private String rxTime;
	/**
	 * ��ѧ��ʽ
	 */
	@TitleAnno(title = "��ѧ��ʽ")
	private String rxType;

	/**
	 * �Ͷ�����
	 */
	@TitleAnno(title = "�Ͷ���ʽ")
	private String jdType;
	/**
	 * ��Դ
	 */
	@TitleAnno(title = "ѧ����Դ")
	private String source;

	public String getSchoolNum() {
		return schoolNum;
	}

	public void setSchoolNum(String schoolNum) {
		this.schoolNum = schoolNum;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public String getRxTime() {
		return rxTime;
	}

	public void setRxTime(String rxTime) {
		this.rxTime = rxTime;
	}

	public String getRxType() {
		return rxType;
	}

	public void setRxType(String rxType) {
		this.rxType = rxType;
	}

	public String getJdType() {
		return jdType;
	}

	public void setJdType(String jdType) {
		this.jdType = jdType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
