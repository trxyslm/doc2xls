package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * ����ѧ��ͨ��ʽ
 * @author liming.slm
 *
 */
public class TrafficType {
	@TitleAnno(title = "����ѧ����")
	private String distance;
	
	@TitleAnno(title = "����ѧ��ʽ")
	private String type;
	
	@TitleAnno(title = "�Ƿ���Ҫ����У��")
	private String schoolBus;

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSchoolBus() {
		return schoolBus;
	}

	public void setSchoolBus(String schoolBus) {
		this.schoolBus = schoolBus;
	}
	
}
