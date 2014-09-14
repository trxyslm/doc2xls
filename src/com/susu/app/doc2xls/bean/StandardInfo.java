package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * ���˻�����Ϣ
 * 
 * @author liming.slm
 *
 */
public class StandardInfo {
	@TitleAnno(title = "����")
	private String name;
	@TitleAnno(title = "�Ա�")
	private String sex;
	@TitleAnno(title = "��������")
	private String birth;
	@TitleAnno(title = "������������������")
	private String birthPlace;
	/**
	 * ����
	 */
	@TitleAnno(title = "����")
	private String jiguan;
	@TitleAnno(title = "����")
	private String nation;
	@TitleAnno(title = "����/����")
	private String nationality;
	@TitleAnno(title = "���֤������")
	private String idType;
	@TitleAnno(title = "���֤����")
	private String idNum;
	@TitleAnno(title = "�۰�̨����")
	private String overseas;
	/**
	 * ������ò
	 */
	@TitleAnno(title = "������ò")
	private String zzmm;
	@TitleAnno(title = "����״��")
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
