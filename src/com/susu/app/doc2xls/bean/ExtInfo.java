package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * ������չ��Ϣ	
 * @author liming.slm
 *
 */
public class ExtInfo {
	/**
	 * ������Ů
	 */
	@TitleAnno(title = "�Ƿ������Ů")
	private String dszn;
	/**
	 * �Ƿ���ѧǰ����
	 */
	@TitleAnno(title = "�Ƿ��ܹ�ѧǰ����")
	private String primaryEdu;
	/**
	 * �Ƿ����ض�ͯ
	 */
	@TitleAnno(title = "�Ƿ����ض�ͯ")
	private String lset;
	/**
	 * �Ƿ��������Ů
	 */
	@TitleAnno(title = "�Ƿ��������Ա��Ǩ��Ů")
	private String jcwgzn;
	
	/**
	 * �Ƿ�¶�
	 */
	@TitleAnno(title = "�Ƿ�¶�")
	private String orphan;
	/**
	 * ��ʿ��Ů
	 */
	@TitleAnno(title = "�Ƿ���ʿ���Ÿ���Ů")
	private String lszn;
	/**
	 * ���Ͷ�
	 */
	@TitleAnno(title = "���Ͷ�")
	private String sbjd;
	/**
	 * �м�����
	 */
	@TitleAnno(title = "�м�����")
	private String disabledType;
	
	@TitleAnno(title = "�Ƿ�����������ѧλ")
	private String govPay;
	
	@TitleAnno(title = "�Ƿ���Ҫ��������")
	private String applyHelp;
	
	@TitleAnno(title = "�Ƿ�����һ��")
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
