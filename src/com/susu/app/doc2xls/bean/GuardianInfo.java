package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * 监护人信息
 * 
 * @author liming.slm
 *
 */
public class GuardianInfo {
	@TitleAnno(title = "姓名")
	private String name;
	@TitleAnno(title = "关系")
	private String relation;
	@TitleAnno(title = "关系说明")
	private String relationDesc;
	@TitleAnno(title = "民族")
	private String nation;
	@TitleAnno(title = "工作单位")
	private String workPlace;
	@TitleAnno(title = "现住址")
	private String address;
	@TitleAnno(title = "户口所在地行政区划")
	private String householdAddress;
	@TitleAnno(title = "联系电话")
	private String phone;
	@TitleAnno(title = "是否监护人")
	private String guardian;
	@TitleAnno(title = "身份证件类型")
	private String idType;
	@TitleAnno(title = "身份证件号")
	private String idNum;
	@TitleAnno(title = "职务")
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
