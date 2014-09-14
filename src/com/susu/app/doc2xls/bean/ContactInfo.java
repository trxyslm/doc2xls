package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * 学生个人联系信息
 * @author liming.slm
 *
 */
public class ContactInfo {
	
	/**
	 * 现住址
	 */
	@TitleAnno(title = "现住址")
	private String address;
	/**
	 * 通信地址
	 */
	@TitleAnno(title = "通信地址")
	private String postAddress;
	/**
	 * 家庭住址
	 */
	@TitleAnno(title = "家庭地址")
	private String homeAddress;
	@TitleAnno(title = "联系电话")
	private String phone;
	@TitleAnno(title = "邮政编码")
	private String posterCode;
	@TitleAnno(title = "电子信箱")
	private String email;
	/**
	 * 个人主页
	 */
	@TitleAnno(title = "主页地址")
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
