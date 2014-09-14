package com.susu.app.doc2xls.bean;

import com.susu.app.doc2xls.annotation.TitleAnno;

/**
 * 上下学交通方式
 * @author liming.slm
 *
 */
public class TrafficType {
	@TitleAnno(title = "上下学距离")
	private String distance;
	
	@TitleAnno(title = "上下学方式")
	private String type;
	
	@TitleAnno(title = "是否需要乘坐校车")
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
