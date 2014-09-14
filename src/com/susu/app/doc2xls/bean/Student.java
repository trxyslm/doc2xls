package com.susu.app.doc2xls.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生信息，包括个人基本信息，个人辅助信息，学籍基本信息等
 * 
 * @author liming.slm
 *
 */
public class Student {
	private SchoolInfo schoolInfo;
	private StandardInfo standardInfo;
	private AuxiliaryInfo auxiliaryInfo;
	private SchoolRollInfo schoolRollInfo;
	private ContactInfo contactInfo;
	private ExtInfo extInfo;
	private TrafficType trafficType;
	private List<GuardianInfo> guardianInfos;

	public Student() {
		super();
		this.standardInfo = new StandardInfo();
		this.auxiliaryInfo = new AuxiliaryInfo();
		this.contactInfo = new ContactInfo();
		this.extInfo = new ExtInfo();
		//只能有两个监护人
		this.guardianInfos = new ArrayList<GuardianInfo>();
		guardianInfos.add(new GuardianInfo());
		guardianInfos.add(new GuardianInfo());
		this.schoolInfo = new SchoolInfo();
		this.schoolRollInfo = new SchoolRollInfo();
		this.trafficType = new TrafficType();
	}

	public Student(SchoolInfo schoolInfo, StandardInfo standardInfo,
			AuxiliaryInfo auxiliaryInfo, SchoolRollInfo schoolRollInfo,
			ContactInfo contactInfo, ExtInfo extInfo, TrafficType trafficType,
			List<GuardianInfo> guardianInfos) {
		super();
		this.schoolInfo = schoolInfo;
		this.standardInfo = standardInfo;
		this.auxiliaryInfo = auxiliaryInfo;
		this.schoolRollInfo = schoolRollInfo;
		this.contactInfo = contactInfo;
		this.extInfo = extInfo;
		this.trafficType = trafficType;
		this.guardianInfos = guardianInfos;
	}

	public SchoolInfo getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(SchoolInfo schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	public StandardInfo getStandardInfo() {
		return standardInfo;
	}

	public void setStandardInfo(StandardInfo standardInfo) {
		this.standardInfo = standardInfo;
	}

	public AuxiliaryInfo getAuxiliaryInfo() {
		return auxiliaryInfo;
	}

	public void setAuxiliaryInfo(AuxiliaryInfo auxiliaryInfo) {
		this.auxiliaryInfo = auxiliaryInfo;
	}

	public SchoolRollInfo getSchoolRollInfo() {
		return schoolRollInfo;
	}

	public void setSchoolRollInfo(SchoolRollInfo schoolRollInfo) {
		this.schoolRollInfo = schoolRollInfo;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public ExtInfo getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(ExtInfo extInfo) {
		this.extInfo = extInfo;
	}

	public TrafficType getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(TrafficType trafficType) {
		this.trafficType = trafficType;
	}

	public List<GuardianInfo> getGuardianInfos() {
		return guardianInfos;
	}

	public void setGuardianInfos(List<GuardianInfo> guardianInfos) {
		this.guardianInfos = guardianInfos;
	}
}
