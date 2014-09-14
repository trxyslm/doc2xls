package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * ѧ��������ϵ��Ϣ
 * @author liming.slm
 *
 */
public class ContactInfo {
	
	/**
	 * ��סַ
	 */
	@TitleAnno(title = "��סַ")
	private String address;
	/**
	 * ͨ�ŵ�ַ
	 */
	@TitleAnno(title = "ͨ�ŵ�ַ")
	private String postAddress;
	/**
	 * ��ͥסַ
	 */
	@TitleAnno(title = "��ͥ��ַ")
	private String homeAddress;
	@TitleAnno(title = "��ϵ�绰")
	private String phone;
	@TitleAnno(title = "��������")
	private String posterCode;
	@TitleAnno(title = "��������")
	private String email;
	/**
	 * ������ҳ
	 */
	@TitleAnno(title = "��ҳ��ַ")
	private String personalPage;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostAddress() {
		return postAddress;
	}
	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPosterCode() {
		return posterCode;
	}
	public void setPosterCode(String posterCode) {
		this.posterCode = posterCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPersonalPage() {
		return personalPage;
	}
	public void setPersonalPage(String personalPage) {
		this.personalPage = personalPage;
	}
	
}
