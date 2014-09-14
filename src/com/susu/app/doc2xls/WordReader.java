package com.susu.app.doc2xls;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.susu.app.doc2xls.bean.AuxiliaryInfo;
import com.susu.app.doc2xls.bean.ContactInfo;
import com.susu.app.doc2xls.bean.ExtInfo;
import com.susu.app.doc2xls.bean.GuardianInfo;
import com.susu.app.doc2xls.bean.SchoolInfo;
import com.susu.app.doc2xls.bean.SchoolRollInfo;
import com.susu.app.doc2xls.bean.StandardInfo;
import com.susu.app.doc2xls.bean.Student;
import com.susu.app.doc2xls.bean.TrafficType;
/**
 * 读取指定文档的表格数据
 * @author liming.slm
 *
 */
public class WordReader {
	public List<Student> read(String path){
		if(null == path || path.length() == 0){
			System.out.println("请输入合法的文件路径！");
			return null;
		}
		System.out.println("开始读取文件[" + path + "]的信息");
		List<Student> students = new ArrayList<Student>();
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);// 加载文档
			POIFSFileSystem pfs = new POIFSFileSystem(in);
			HWPFDocument hwpf = new HWPFDocument(pfs);
			Range range = hwpf.getRange();// 获取文档的读取范围
			TableIterator it = new TableIterator(range);
			// 迭代文档中的表格
			while (it.hasNext()) {
				Student student = new Student();
				int processType = -1;
				Table tb = (Table) it.next();
				// 迭代行，默认从0开始
				for (int i = 0; i < tb.numRows(); i++) {
					TableRow tr = tb.getRow(i);
					// 迭代列，默认从0开始
					if(i == 0){
						//学校信息
						fillSchoolInfo(student, tr);
					}else if(tr.numCells() == 1){
						//分类
						String classification = tr.getCell(0).text();
						classification = getVal(classification);
						if("学生个人基础信息".equals(classification)){
							processType = 2;
						}else if("学生个人辅助信息".equals(classification)){
							processType = 3;
						}else if("学生学籍基本信息".equals(classification)){
							processType = 4;
						}else if("学生个人联系信息".equals(classification)){
							processType = 5;
						}else if("学生个人扩展信息".equals(classification)){
							processType = 6;
						}else if("学生上下学交通方式".equals(classification)){
							processType = 7;
						}else if("学生家庭成员或监护人信息一".equals(classification)){
							processType = 8;
						}else if("学生家庭成员或监护人信息二".equals(classification)){
							processType = 9;
						}
					}else if(tr.numCells() == 6 && i != 1){
						//具体对象赛值
						for (int j = 0; j < tr.numCells(); j++) {
							TableCell td = tr.getCell(j);
//							System.out.println(td.text().substring(0, td.text().length() - 1));
							switch (processType) {
							case 2:
								fillStandardInfo(student, tr);
								break;
							case 3:
								fillAuxiliaryInfo(student, tr);
								break;
							case 4:
								fillSchoolRollInfo(student, tr);
								break;
							case 5:
								fillContactInfo(student, tr);
								break;
							case 6:
								fillExtInfo(student, tr);
								break;
							case 7:
								fillTrafficType(student, tr);
								break;
							case 8:
								fillGuardianInfo(student.getGuardianInfos().get(0), tr);
								break;
							case 9:
								fillGuardianInfo(student.getGuardianInfos().get(1), tr);
								break;
							default:
								break;
							}
						}
					}else{
						continue;
					}
				}
				students.add(student);
			}
		} catch (Exception e) {
			System.out.println("读取文件[" + path + "]出现异常！");
			e.printStackTrace();
		} finally {
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("文件[" + path + "]信息读取完毕");
		return students;
	}
	
	private String getVal(String source){
		if(null != source && source.length() > 0){
			return source.substring(0, source.length() - 1).trim();
		}
		return source;
	}
	
	private boolean checkValidate(String property, String val){
		if("★".endsWith(property) && (null == val || val.length() == 0)){
			System.out.println("属性[" + property + "]为必填项，请填写！");
			return false;
		}
		return true;
	}
	
	/**
	 * 填充学校信息
	 * @param student
	 * @param tr
	 */
	private void fillSchoolInfo(Student student, TableRow tr){
		SchoolInfo schoolInfo = student.getSchoolInfo();
		for(int i = 0; i < tr.numCells(); i++){
			if(i == 1 || i == 3){
				continue;
			}
			TableCell td = tr.getCell(i);
			String property = td.text();
			property = getVal(property);
			String val = getVal(tr.getCell(i + 1).text());
			if("学校名称".equals(property)){
				schoolInfo.setName(val);
				continue;
			}
			if("学校标识码".equals(property)){
				schoolInfo.setCode(val);
				continue;
			}
		}	
	}
	
	/**
	 * 填充学生基本信息
	 * @param student
	 * @param tr
	 */
	private void fillStandardInfo(Student student, TableRow tr){
		StandardInfo standardInfo = student.getStandardInfo();
		for(int i = 0; i < tr.numCells(); i++){
			if(i == 0 || i == 3 || i == 2 || i == 5){
				continue;
			}
			TableCell td = tr.getCell(i);
			String property = td.text();
			property = getVal(property);
			String val = getVal(tr.getCell(i + 1).text());
			if("姓名★".equals(property)){
				standardInfo.setName(val);
				continue;
			}
			if("身份证件类型★".equals(property)){
				standardInfo.setIdType(val);
				continue;
			}
			if("性别★".equals(property)){
				standardInfo.setSex(val);
				continue;
			}
			if("身份证件号".equals(property)){
				standardInfo.setIdNum(val);
				continue;
			}
			if("出生日期★".equals(property)){
				standardInfo.setBirth(val);
				continue;
			}
			if("港澳台侨外★".equals(property)){
				standardInfo.setOverseas(val);
				continue;
			}
			if("出生地★".equals(property)){
				standardInfo.setBirthPlace(val);
				continue;
			}
			if("政治面貌".equals(property)){
				standardInfo.setZzmm(val);
				continue;
			}
			if("籍贯★".equals(property)){
				standardInfo.setJiguan(val);
				continue;
			}
			if("健康状况★".equals(property)){
				standardInfo.setHealthStatus(val);
				continue;
			}
			if("民族★".equals(property)){
				standardInfo.setNation(val);
				continue;
			}
			if("照片".equals(property)){
				standardInfo.setPic(val);
				continue;
			}
			if("国籍/地区★".equals(property)){
				standardInfo.setNationality(val);
				continue;
			}
		}
	}
	
	/**
	 * 填充学生辅助信息
	 * @param student
	 * @param tr
	 */
	private void fillAuxiliaryInfo(Student student, TableRow tr){
		AuxiliaryInfo auxiliaryInfo = student.getAuxiliaryInfo();
		for(int i = 0; i < tr.numCells(); i++){
			if(i == 0 || i == 3 || i == 2 || i == 5){
				continue;
			}
			TableCell td = tr.getCell(i);
			String property = td.text();
			property = getVal(property);
			String val = getVal(tr.getCell(i + 1).text());
			if("姓名拼音".equals(property)){
				auxiliaryInfo.setNamePY(val);
				continue;
			}
			if("户口所在地★".equals(property)){
				auxiliaryInfo.setHouseholdAddress(val);
				continue;
			}
			if("曾用名".equals(property)){
				auxiliaryInfo.setFormerName(val);
				continue;
			}
			if("户口性质★".equals(property)){
				auxiliaryInfo.setHouseholdType(val);
				continue;
			}
			if("身份证件有效期".equals(property)){
				auxiliaryInfo.setIdValidate(val);
				continue;
			}
			if("特长".equals(property)){
				auxiliaryInfo.setSpeciality(val);
				continue;
			}
		}
	}
	
	/**
	 * 填充学籍基本信息
	 * @param student
	 * @param tr
	 */
	private void fillSchoolRollInfo(Student student, TableRow tr){
		SchoolRollInfo schoolRollInfo = student.getSchoolRollInfo();
		for(int i = 0; i < tr.numCells(); i++){
			if(i == 0 || i == 3 || i == 2 || i == 5){
				continue;
			}
			TableCell td = tr.getCell(i);
			String property = td.text();
			property = getVal(property);
			String val = getVal(tr.getCell(i + 1).text());
			if("学籍辅号".equals(property)){
				schoolRollInfo.setSchoolNum(val);
				continue;
			}
			if("入学年月★".equals(property)){
				schoolRollInfo.setRxTime(val);
				continue;
			}
			if("班内学号".equals(property)){
				schoolRollInfo.setClassNum(val);
				continue;
			}
			if("入学方式★".equals(property)){
				schoolRollInfo.setRxType(val);
				continue;
			}
			if("年级★".equals(property)){
				schoolRollInfo.setGrade(val);
				continue;
			}
			if("就读方式★".equals(property)){
				schoolRollInfo.setJdType(val);
				continue;
			}
			if("班级★".equals(property)){
				schoolRollInfo.set_class(val);
				continue;
			}
			if("学生来源".equals(property)){
				schoolRollInfo.setSource(val);
				continue;
			}
		}
	}
	
	/**
	 * 填充联系信息
	 * @param student
	 * @param tr
	 */
	private void fillContactInfo(Student student, TableRow tr){
		ContactInfo contactInfo = student.getContactInfo();
		for(int i = 0; i < tr.numCells(); i++){
			if(i == 0 || i == 3 || i == 2 || i == 5){
				continue;
			}
			TableCell td = tr.getCell(i);
			String property = td.text();
			property = getVal(property);
			String val = getVal(tr.getCell(i + 1).text());
			if("现住址★".equals(property)){
				contactInfo.setAddress(val);
				continue;
			}
			if("邮政编码★".equals(property)){
				contactInfo.setPosterCode(val);
				continue;
			}
			if("通信地址★".equals(property)){
				contactInfo.setPostAddress(val);
				continue;
			}
			if("电子信箱".equals(property)){
				contactInfo.setEmail(val);
				continue;
			}
			if("家庭地址★".equals(property)){
				contactInfo.setHomeAddress(val);
				continue;
			}
			if("主页地址".equals(property)){
				contactInfo.setPersonalPage(val);
				continue;
			}
			if("联系电话★".equals(property)){
				contactInfo.setPhone(val);
				continue;
			}
		}
	}
	
	/**
	 * 填充扩展信息
	 * @param student
	 * @param tr
	 */
	private void fillExtInfo(Student student, TableRow tr){
		ExtInfo extInfo = student.getExtInfo();
		for(int i = 0; i < tr.numCells(); i++){
			if(i == 0 || i == 3 || i == 2 || i == 5){
				continue;
			}
			TableCell td = tr.getCell(i);
			String property = td.text();
			property = getVal(property);
			String val = getVal(tr.getCell(i + 1).text());
			if("是否独生子女★".equals(property)){
				extInfo.setDszn(val);
				continue;
			}
			if("随班就读".equals(property)){
				extInfo.setSbjd(val);
				continue;
			}
			if("是否受过学前教育★".equals(property)){
				extInfo.setPrimaryEdu(val);
				continue;
			}
			if("残疾类型".equals(property)){
				extInfo.setDisabledType(val);
				continue;
			}
			if("是否留守儿童★".equals(property)){
				extInfo.setLset(val);
				continue;
			}
			if("是否由政府购买学位".equals(property)){
				extInfo.setGovPay(val);
				continue;
			}
			if("是否进城务工人员随迁子女★".equals(property)){
				extInfo.setJcwgzn(val);
				continue;
			}
			if("是否需要申请资助★".equals(property)){
				extInfo.setApplyHelp(val);
				continue;
			}
			if("是否孤儿★".equals(property)){
				extInfo.setOrphan(val);
				continue;
			}
			if("是否享受一补★".equals(property)){
				extInfo.setEnjoyGrants(val);
				continue;
			}
			if("是否烈士或优抚子女★".equals(property)){
				extInfo.setLszn(val);
				continue;
			}
		}
	}
	
	/**
	 * 填充交通方式
	 * @param student
	 * @param tr
	 */
	private void fillTrafficType(Student student, TableRow tr){
		 TrafficType trafficType = student.getTrafficType();
		 for(int i = 0; i < tr.numCells(); i++){
				if(i == 0 || i == 3 || i == 2 || i == 5){
					continue;
				}
				TableCell td = tr.getCell(i);
				String property = td.text();
				property = getVal(property);
				String val = getVal(tr.getCell(i + 1).text());
				if("上下学距离（公里）".equals(property)){
					trafficType.setDistance(val);
					continue;
				}
				if("是否需要乘坐校车".equals(property)){
					trafficType.setSchoolBus(val);
					continue;
				}
				if("上下学交通方式".equals(property)){
					trafficType.setType(val);
					continue;
				}
		 }	
	}
	
	/**
	 * 填充监护人信息
	 * @param guardianInfo
	 * @param tr
	 */
	private void fillGuardianInfo(GuardianInfo guardianInfo, TableRow tr){
		for(int i = 0; i < tr.numCells(); i++){
			if(i == 0 || i == 3 || i == 2 || i == 5){
				continue;
			}
			TableCell td = tr.getCell(i);
			String property = td.text();
			property = getVal(property);
			String val = getVal(tr.getCell(i + 1).text());
			if("姓名★".equals(property)){
				guardianInfo.setName(val);
				continue;
			}
			if("户口所在地★".equals(property)){
				guardianInfo.setHouseholdAddress(val);
				continue;
			}if("关系★".equals(property)){
				guardianInfo.setRelation(val);
				continue;
			}if("联系电话★".equals(property)){
				guardianInfo.setPhone(val);
				continue;
			}if("关系说明".equals(property)){
				guardianInfo.setRelationDesc(val);
				continue;
			}if("是否监护人★".equals(property)){
				guardianInfo.setGuardian(val);
				continue;
			}if("民族".equals(property)){
				guardianInfo.setNation(val);
				continue;
			}if("身份证件类型".equals(property)){
				guardianInfo.setIdType(val);
				continue;
			}if("工作单位".equals(property)){
				guardianInfo.setWorkPlace(val);
				continue;
			}if("身份证件号".equals(property)){
				guardianInfo.setIdNum(val);
				continue;
			}if("现住址★".equals(property)){
				guardianInfo.setAddress(val);
				continue;
			}if("职务".equals(property)){
				guardianInfo.setDuties(val);
				continue;
			}
		}	
	}
	
	public static void main(String[] args) {
		WordReader wd = new WordReader();
		List<Student> list = wd.read("D:\\test\\test.doc");
		System.out.println();
	}
	
}
