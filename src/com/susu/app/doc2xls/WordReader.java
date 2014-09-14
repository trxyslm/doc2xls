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
 * ��ȡָ���ĵ��ı������
 * @author liming.slm
 *
 */
public class WordReader {
	public List<Student> read(String path){
		if(null == path || path.length() == 0){
			System.out.println("������Ϸ����ļ�·����");
			return null;
		}
		System.out.println("��ʼ��ȡ�ļ�[" + path + "]����Ϣ");
		List<Student> students = new ArrayList<Student>();
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);// �����ĵ�
			POIFSFileSystem pfs = new POIFSFileSystem(in);
			HWPFDocument hwpf = new HWPFDocument(pfs);
			Range range = hwpf.getRange();// ��ȡ�ĵ��Ķ�ȡ��Χ
			TableIterator it = new TableIterator(range);
			// �����ĵ��еı��
			while (it.hasNext()) {
				Student student = new Student();
				int processType = -1;
				Table tb = (Table) it.next();
				// �����У�Ĭ�ϴ�0��ʼ
				for (int i = 0; i < tb.numRows(); i++) {
					TableRow tr = tb.getRow(i);
					// �����У�Ĭ�ϴ�0��ʼ
					if(i == 0){
						//ѧУ��Ϣ
						fillSchoolInfo(student, tr);
					}else if(tr.numCells() == 1){
						//����
						String classification = tr.getCell(0).text();
						classification = getVal(classification);
						if("ѧ�����˻�����Ϣ".equals(classification)){
							processType = 2;
						}else if("ѧ�����˸�����Ϣ".equals(classification)){
							processType = 3;
						}else if("ѧ��ѧ��������Ϣ".equals(classification)){
							processType = 4;
						}else if("ѧ��������ϵ��Ϣ".equals(classification)){
							processType = 5;
						}else if("ѧ��������չ��Ϣ".equals(classification)){
							processType = 6;
						}else if("ѧ������ѧ��ͨ��ʽ".equals(classification)){
							processType = 7;
						}else if("ѧ����ͥ��Ա��໤����Ϣһ".equals(classification)){
							processType = 8;
						}else if("ѧ����ͥ��Ա��໤����Ϣ��".equals(classification)){
							processType = 9;
						}
					}else if(tr.numCells() == 6 && i != 1){
						//���������ֵ
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
			System.out.println("��ȡ�ļ�[" + path + "]�����쳣��");
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
		System.out.println("�ļ�[" + path + "]��Ϣ��ȡ���");
		return students;
	}
	
	private String getVal(String source){
		if(null != source && source.length() > 0){
			return source.substring(0, source.length() - 1).trim();
		}
		return source;
	}
	
	private boolean checkValidate(String property, String val){
		if("��".endsWith(property) && (null == val || val.length() == 0)){
			System.out.println("����[" + property + "]Ϊ���������д��");
			return false;
		}
		return true;
	}
	
	/**
	 * ���ѧУ��Ϣ
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
			if("ѧУ����".equals(property)){
				schoolInfo.setName(val);
				continue;
			}
			if("ѧУ��ʶ��".equals(property)){
				schoolInfo.setCode(val);
				continue;
			}
		}	
	}
	
	/**
	 * ���ѧ��������Ϣ
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
			if("������".equals(property)){
				standardInfo.setName(val);
				continue;
			}
			if("���֤�����͡�".equals(property)){
				standardInfo.setIdType(val);
				continue;
			}
			if("�Ա��".equals(property)){
				standardInfo.setSex(val);
				continue;
			}
			if("���֤����".equals(property)){
				standardInfo.setIdNum(val);
				continue;
			}
			if("�������ڡ�".equals(property)){
				standardInfo.setBirth(val);
				continue;
			}
			if("�۰�̨�����".equals(property)){
				standardInfo.setOverseas(val);
				continue;
			}
			if("�����ء�".equals(property)){
				standardInfo.setBirthPlace(val);
				continue;
			}
			if("������ò".equals(property)){
				standardInfo.setZzmm(val);
				continue;
			}
			if("�����".equals(property)){
				standardInfo.setJiguan(val);
				continue;
			}
			if("����״����".equals(property)){
				standardInfo.setHealthStatus(val);
				continue;
			}
			if("�����".equals(property)){
				standardInfo.setNation(val);
				continue;
			}
			if("��Ƭ".equals(property)){
				standardInfo.setPic(val);
				continue;
			}
			if("����/������".equals(property)){
				standardInfo.setNationality(val);
				continue;
			}
		}
	}
	
	/**
	 * ���ѧ��������Ϣ
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
			if("����ƴ��".equals(property)){
				auxiliaryInfo.setNamePY(val);
				continue;
			}
			if("�������ڵء�".equals(property)){
				auxiliaryInfo.setHouseholdAddress(val);
				continue;
			}
			if("������".equals(property)){
				auxiliaryInfo.setFormerName(val);
				continue;
			}
			if("�������ʡ�".equals(property)){
				auxiliaryInfo.setHouseholdType(val);
				continue;
			}
			if("���֤����Ч��".equals(property)){
				auxiliaryInfo.setIdValidate(val);
				continue;
			}
			if("�س�".equals(property)){
				auxiliaryInfo.setSpeciality(val);
				continue;
			}
		}
	}
	
	/**
	 * ���ѧ��������Ϣ
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
			if("ѧ������".equals(property)){
				schoolRollInfo.setSchoolNum(val);
				continue;
			}
			if("��ѧ���¡�".equals(property)){
				schoolRollInfo.setRxTime(val);
				continue;
			}
			if("����ѧ��".equals(property)){
				schoolRollInfo.setClassNum(val);
				continue;
			}
			if("��ѧ��ʽ��".equals(property)){
				schoolRollInfo.setRxType(val);
				continue;
			}
			if("�꼶��".equals(property)){
				schoolRollInfo.setGrade(val);
				continue;
			}
			if("�Ͷ���ʽ��".equals(property)){
				schoolRollInfo.setJdType(val);
				continue;
			}
			if("�༶��".equals(property)){
				schoolRollInfo.set_class(val);
				continue;
			}
			if("ѧ����Դ".equals(property)){
				schoolRollInfo.setSource(val);
				continue;
			}
		}
	}
	
	/**
	 * �����ϵ��Ϣ
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
			if("��סַ��".equals(property)){
				contactInfo.setAddress(val);
				continue;
			}
			if("���������".equals(property)){
				contactInfo.setPosterCode(val);
				continue;
			}
			if("ͨ�ŵ�ַ��".equals(property)){
				contactInfo.setPostAddress(val);
				continue;
			}
			if("��������".equals(property)){
				contactInfo.setEmail(val);
				continue;
			}
			if("��ͥ��ַ��".equals(property)){
				contactInfo.setHomeAddress(val);
				continue;
			}
			if("��ҳ��ַ".equals(property)){
				contactInfo.setPersonalPage(val);
				continue;
			}
			if("��ϵ�绰��".equals(property)){
				contactInfo.setPhone(val);
				continue;
			}
		}
	}
	
	/**
	 * �����չ��Ϣ
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
			if("�Ƿ������Ů��".equals(property)){
				extInfo.setDszn(val);
				continue;
			}
			if("���Ͷ�".equals(property)){
				extInfo.setSbjd(val);
				continue;
			}
			if("�Ƿ��ܹ�ѧǰ������".equals(property)){
				extInfo.setPrimaryEdu(val);
				continue;
			}
			if("�м�����".equals(property)){
				extInfo.setDisabledType(val);
				continue;
			}
			if("�Ƿ����ض�ͯ��".equals(property)){
				extInfo.setLset(val);
				continue;
			}
			if("�Ƿ�����������ѧλ".equals(property)){
				extInfo.setGovPay(val);
				continue;
			}
			if("�Ƿ��������Ա��Ǩ��Ů��".equals(property)){
				extInfo.setJcwgzn(val);
				continue;
			}
			if("�Ƿ���Ҫ����������".equals(property)){
				extInfo.setApplyHelp(val);
				continue;
			}
			if("�Ƿ�¶���".equals(property)){
				extInfo.setOrphan(val);
				continue;
			}
			if("�Ƿ�����һ����".equals(property)){
				extInfo.setEnjoyGrants(val);
				continue;
			}
			if("�Ƿ���ʿ���Ÿ���Ů��".equals(property)){
				extInfo.setLszn(val);
				continue;
			}
		}
	}
	
	/**
	 * ��佻ͨ��ʽ
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
				if("����ѧ���루���".equals(property)){
					trafficType.setDistance(val);
					continue;
				}
				if("�Ƿ���Ҫ����У��".equals(property)){
					trafficType.setSchoolBus(val);
					continue;
				}
				if("����ѧ��ͨ��ʽ".equals(property)){
					trafficType.setType(val);
					continue;
				}
		 }	
	}
	
	/**
	 * ���໤����Ϣ
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
			if("������".equals(property)){
				guardianInfo.setName(val);
				continue;
			}
			if("�������ڵء�".equals(property)){
				guardianInfo.setHouseholdAddress(val);
				continue;
			}if("��ϵ��".equals(property)){
				guardianInfo.setRelation(val);
				continue;
			}if("��ϵ�绰��".equals(property)){
				guardianInfo.setPhone(val);
				continue;
			}if("��ϵ˵��".equals(property)){
				guardianInfo.setRelationDesc(val);
				continue;
			}if("�Ƿ�໤�ˡ�".equals(property)){
				guardianInfo.setGuardian(val);
				continue;
			}if("����".equals(property)){
				guardianInfo.setNation(val);
				continue;
			}if("���֤������".equals(property)){
				guardianInfo.setIdType(val);
				continue;
			}if("������λ".equals(property)){
				guardianInfo.setWorkPlace(val);
				continue;
			}if("���֤����".equals(property)){
				guardianInfo.setIdNum(val);
				continue;
			}if("��סַ��".equals(property)){
				guardianInfo.setAddress(val);
				continue;
			}if("ְ��".equals(property)){
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
