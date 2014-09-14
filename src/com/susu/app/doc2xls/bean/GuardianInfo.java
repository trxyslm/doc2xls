package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * �໤����Ϣ
 * 
 * @author liming.slm
 *
 */
public class GuardianInfo {
	@TitleAnno(title = "����")
	private String name;
	@TitleAnno(title = "��ϵ")
	private String relation;
	@TitleAnno(title = "��ϵ˵��")
	private String relationDesc;
	@TitleAnno(title = "����")
	private String nation;
	@TitleAnno(title = "������λ")
	private String workPlace;
	@TitleAnno(title = "��סַ")
	private String address;
	@TitleAnno(title = "�������ڵ���������")
	private String householdAddress;
	@TitleAnno(title = "��ϵ�绰")
	private String phone;
	@TitleAnno(title = "�Ƿ�໤��")
	private String guardian;
	@TitleAnno(title = "���֤������")
	private String idType;
	@TitleAnno(title = "���֤����")
	private String idNum;
	@TitleAnno(title = "ְ��")
	private String duties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRelationDesc() {
		return relationDesc;
	}

	public void setRelationDesc(String relationDesc) {
		this.relationDesc = relationDesc;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHouseholdAddress() {
		return householdAddress;
	}

	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGuardian() {
		return guardian;
	}

	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}
}
