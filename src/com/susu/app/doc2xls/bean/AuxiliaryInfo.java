package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * 个人辅助信息
 * 
 * @author liming.slm
 *
 */
public class AuxiliaryInfo {
	/**
	 * 姓名拼音
	 */
	@TitleAnno(title = "")
	private String namePY;
	@TitleAnno(title = "曾用名")
	private String formerName;
	/**
	 * 身份证有效期
	 */
	@TitleAnno(title = "身份证件有效期")
	private String idValidate;
	/**
	 * 户口所在地
	 */
	@TitleAnno(title = "户口所在地行政区划")
	private String householdAddress;
	/**
	 * 户口性质
	 */
	@TitleAnno(title = "户口性质")
	private String householdType;
	@TitleAnno(title = "特长")
	private String speciality;

	public String getNamePY() {
		return namePY;
	}

	public void setNamePY(String namePY) {
		this.namePY = namePY;
	}

	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}

	public String getIdValidate() {
		return idValidate;
	}

	public void setIdValidate(String idValidate) {
		this.idValidate = idValidate;
	}

	public String getHouseholdAddress() {
		return householdAddress;
	}

	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}

	public String getHouseholdType() {
		return householdType;
	}

	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

}
