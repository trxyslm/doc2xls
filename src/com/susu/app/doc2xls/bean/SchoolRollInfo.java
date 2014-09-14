package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * 学籍基本信息
 * 
 * @author liming.slm
 *
 */
public class SchoolRollInfo {
	@TitleAnno(title = "学籍辅号")
	private String schoolNum;
	@TitleAnno(title = "班内学号")
	private String classNum;
	@TitleAnno(title = "")
	private String grade;
	@TitleAnno(title = "班号")
	private String _class;
	/**
	 * 入学时间
	 */
	@TitleAnno(title = "入学年月")
	private String rxTime;
	/**
	 * 入学方式
	 */
	@TitleAnno(title = "入学方式")
	private String rxType;

	/**
	 * 就读方法
	 */
	@TitleAnno(title = "就读方式")
	private String jdType;
	/**
	 * 生源
	 */
	@TitleAnno(title = "学生来源")
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
