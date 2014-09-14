package com.susu.app.doc2xls;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

import com.susu.app.doc2xls.bean.Student;

/**
 * 系统启类 main方法
 * @author liming.slm
 *
 */
public class Starter {
	private final LinkedBlockingQueue<Student> queue = new LinkedBlockingQueue<Student>();
	private volatile int readCounter = 0;
	private volatile int writeCounter = 0;
	private volatile boolean readOver = false;
	private static final String TARGET = "\\result\\result.xls"; 
	public static final String[] TITLE_ARRAY = {"学校标识码", "姓名", "性别", "出生日期", "出生地行政区划代码", "籍贯", "民族", "国籍/地区", "身份证件类型", "港澳台侨外",
		"健康状况", "政治面貌", "身份证件号", "户口性质", "户口所在地行政区划", "班号", "入学年月", "入学方式", "就读方式", "现住址", "通信地址", "家庭地址", "联系电话",
		"邮政编码", "是否独生子女", "是否受过学前教育", "是否留守儿童", "是否需要申请资助", "是否享受一补", "是否孤儿", "是否烈士或优抚子女", "上下学距离", "上下学方式",
		"是否需要乘坐校车", "曾用名", "身份证件有效期", "血型", "特长", "学籍辅号", "班内学号", "学生来源", "电子信箱", "主页地址", "残疾类型", "是否由政府购买学位",
		"随班就读", "成员1姓名", "成员1关系", "成员1关系说明", "成员1现住址", "成员1户口所在地行政区划", "成员1联系电话", "成员1是否监护人", "成员1身份证件类型",
		"成员1身份证件号", "成员1民族", "成员1工作单位", "成员1职务", "成员2姓名", "成员2关系", "成员2关系说明", "成员2现住址", "成员2户口所在地行政区划", "成员2联系电话",
		"成员2是否监护人", "成员2身份证件类型", "成员2身份证件号", "成员2民族", "成员2工作单位", "成员2职务", "是否进城务工人员随迁子女"};
	
	public static void main(String[] args) {
		Starter starter = new Starter();
		starter.start();
	}
	
	private void start(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入 全国中小学学籍管理系统学生基本信息表 所在的目录：");
		String rootPath = scanner.nextLine();
		if(null != rootPath && rootPath.length() > 0 && !rootPath.equals("\r")){
			System.out.print("本次输入的目录为：" + rootPath + "，确认请输入Y，重新输入请输入N：");
			String confirm = scanner.nextLine();
			if("Y".equalsIgnoreCase(confirm)){
				System.out.println("开始处理[" + rootPath + "]下的文件");
				work(rootPath);
			}else if("N".equalsIgnoreCase(confirm)){
				start();
			}else{
				System.out.print("请输入Y或者N：");
				String conf = scanner.nextLine();
				while(!(conf.equalsIgnoreCase("Y") || conf.equalsIgnoreCase("N"))){
					System.out.print("请输入Y或者N：");
					conf = scanner.nextLine();
				}
				if("Y".equalsIgnoreCase(conf)){
					System.out.println("开始处理[" + rootPath + "]下的文件");
					work(rootPath);
				}else if("N".equalsIgnoreCase(conf)){
					start();
				}	
			}
		}else{
			start();
		}
	}
	
	private void work(final String rootPath){
		long begin = System.currentTimeMillis();
		final List<String> files = new ArrayList<String>();
		FileUtil.listFile(rootPath, files);
		if(null != files && files.size() > 0){
			final CountDownLatch cdl = new CountDownLatch(2);
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					WordReader reader = new WordReader();
					for(String path : files){
						List<Student> studentList = reader.read(path);
						if(null != studentList && studentList.size() > 0){
							readCounter += studentList.size();
							queue.addAll(studentList);
						}
					}
					readOver = true;
					cdl.countDown();
				}
			}).start();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					FileUtil.createTargetFile(rootPath + TARGET);
					ExcelWriter writer = new ExcelWriter();
					Student student = null;
					try {
						while((student = queue.take()) != null){
							writer.write(student, rootPath + TARGET);
							if(readCounter == ++writeCounter && readOver){
								break;
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					cdl.countDown();
				}
			}).start();
			
			try {
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("处理结束，结果保存在[" + rootPath + TARGET + "]中，共耗时：" + (System.currentTimeMillis() - begin) + "毫秒");
		}else{
			System.out.println("目录下[" + rootPath + "]没有doc文件，请重新运行程序！");
		}
		System.out.println();
		System.exit(0);
	}
}
