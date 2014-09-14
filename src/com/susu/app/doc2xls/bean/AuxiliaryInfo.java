package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * ���˸�����Ϣ
 * 
 * @author liming.slm
 *
 */
public class AuxiliaryInfo {
	/**
	 * ����ƴ��
	 */
	@TitleAnno(title = "")
	private String namePY;
	@TitleAnno(title = "������")
	private String formerName;
	/**
	 * ���֤��Ч��
	 */
	@TitleAnno(title = "���֤����Ч��")
	private String idValidate;
	/**
	 * �������ڵ�
	 */
	@TitleAnno(title = "�������ڵ���������")
	private String householdAddress;
	/**
	 * ��������
	 */
	@TitleAnno(title = "��������")
	private String householdType;
	@TitleAnno(title = "�س�")
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
