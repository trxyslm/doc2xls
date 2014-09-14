package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * 个人基本信息
 * 
 * @author liming.slm
 *
 */
public class StandardInfo {
	@TitleAnno(title = "姓名")
	private String name;
	@TitleAnno(title = "性别")
	private String sex;
	@TitleAnno(title = "出生日期")
	private String birth;
	@TitleAnno(title = "出生地行政区划代码")
	private String birthPlace;
	/**
	 * 籍贯
	 */
	@TitleAnno(title = "籍贯")
	private String jiguan;
	@TitleAnno(title = "民族")
	private String nation;
	@TitleAnno(title = "国籍/地区")
	private String nationality;
	@TitleAnno(title = "身份证件类型")
	private String idType;
	@TitleAnno(title = "身份证件号")
	private String idNum;
	@TitleAnno(title = "港澳台侨外")
	private String overseas;
	/**
	 * 政治面貌
	 */
	@TitleAnno(title = "政治面貌")
	private String zzmm;
	@TitleAnno(title = "健康状况")
	private String healthStatus;
	@TitleAnno(title = "")
	private String pic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getJiguan() {
		return jiguan;
	}

	public void setJiguan(String jiguan) {
		this.jiguan = jiguan;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

	public String getOverseas() {
		return overseas;
	}

	public void setOverseas(String overseas) {
		this.overseas = overseas;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
