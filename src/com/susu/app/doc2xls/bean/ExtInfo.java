package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * 个人扩展信息	
 * @author liming.slm
 *
 */
public class ExtInfo {
	/**
	 * 独生子女
	 */
	@TitleAnno(title = "是否独生子女")
	private String dszn;
	/**
	 * 是否有学前教育
	 */
	@TitleAnno(title = "是否受过学前教育")
	private String primaryEdu;
	/**
	 * 是否留守儿童
	 */
	@TitleAnno(title = "是否留守儿童")
	private String lset;
	/**
	 * 是否进城务工子女
	 */
	@TitleAnno(title = "是否进城务工人员随迁子女")
	private String jcwgzn;
	
	/**
	 * 是否孤儿
	 */
	@TitleAnno(title = "是否孤儿")
	private String orphan;
	/**
	 * 烈士子女
	 */
	@TitleAnno(title = "是否烈士或优抚子女")
	private String lszn;
	/**
	 * 随班就读
	 */
	@TitleAnno(title = "随班就读")
	private String sbjd;
	/**
	 * 残疾类型
	 */
	@TitleAnno(title = "残疾类型")
	private String disabledType;
	
	@TitleAnno(title = "是否由政府购买学位")
	private String govPay;
	
	@TitleAnno(title = "是否需要申请资助")
	private String applyHelp;
	
	@TitleAnno(title = "是否享受一补")
	private String enjoyGrants;

	public String getDszn() {
		return dszn;
	}

	public void setDszn(String dszn) {
		this.dszn = dszn;
	}

	public String getPrimaryEdu() {
		return primaryEdu;
	}

	public void setPrimaryEdu(String primaryEdu) {
		this.primaryEdu = primaryEdu;
	}

	public String getLset() {
		return lset;
	}

	public void setLset(String lset) {
		this.lset = lset;
	}

	public String getJcwgzn() {
		return jcwgzn;
	}

	public void setJcwgzn(String jcwgzn) {
		this.jcwgzn = jcwgzn;
	}

	public String getOrphan() {
		return orphan;
	}

	public void setOrphan(String orphan) {
		this.orphan = orphan;
	}

	public String getLszn() {
		return lszn;
	}

	public void setLszn(String lszn) {
		this.lszn = lszn;
	}

	public String getSbjd() {
		return sbjd;
	}

	public void setSbjd(String sbjd) {
		this.sbjd = sbjd;
	}

	public String getDisabledType() {
		return disabledType;
	}

	public void setDisabledType(String disabledType) {
		this.disabledType = disabledType;
	}

	public String getGovPay() {
		return govPay;
	}

	public void setGovPay(String govPay) {
		this.govPay = govPay;
	}

	public String getApplyHelp() {
		return applyHelp;
	}

	public void setApplyHelp(String applyHelp) {
		this.applyHelp = applyHelp;
	}

	public String getEnjoyGrants() {
		return enjoyGrants;
	}

	public void setEnjoyGrants(String enjoyGrants) {
		this.enjoyGrants = enjoyGrants;
	}
	
}
