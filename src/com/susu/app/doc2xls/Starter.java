package com.susu.app.doc2xls;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

import com.susu.app.doc2xls.bean.Student;

/**
 * ϵͳ���� main����
 * @author liming.slm
 *
 */
public class Starter {
	private final LinkedBlockingQueue<Student> queue = new LinkedBlockingQueue<Student>();
	private volatile int readCounter = 0;
	private volatile int writeCounter = 0;
	private volatile boolean readOver = false;
	private static final String TARGET = "\\result\\result.xls"; 
	public static final String[] TITLE_ARRAY = {"ѧУ��ʶ��", "����", "�Ա�", "��������", "������������������", "����", "����", "����/����", "���֤������", "�۰�̨����",
		"����״��", "������ò", "���֤����", "��������", "�������ڵ���������", "���", "��ѧ����", "��ѧ��ʽ", "�Ͷ���ʽ", "��סַ", "ͨ�ŵ�ַ", "��ͥ��ַ", "��ϵ�绰",
		"��������", "�Ƿ������Ů", "�Ƿ��ܹ�ѧǰ����", "�Ƿ����ض�ͯ", "�Ƿ���Ҫ��������", "�Ƿ�����һ��", "�Ƿ�¶�", "�Ƿ���ʿ���Ÿ���Ů", "����ѧ����", "����ѧ��ʽ",
		"�Ƿ���Ҫ����У��", "������", "���֤����Ч��", "Ѫ��", "�س�", "ѧ������", "����ѧ��", "ѧ����Դ", "��������", "��ҳ��ַ", "�м�����", "�Ƿ�����������ѧλ",
		"���Ͷ�", "��Ա1����", "��Ա1��ϵ", "��Ա1��ϵ˵��", "��Ա1��סַ", "��Ա1�������ڵ���������", "��Ա1��ϵ�绰", "��Ա1�Ƿ�໤��", "��Ա1���֤������",
		"��Ա1���֤����", "��Ա1����", "��Ա1������λ", "��Ա1ְ��", "��Ա2����", "��Ա2��ϵ", "��Ա2��ϵ˵��", "��Ա2��סַ", "��Ա2�������ڵ���������", "��Ա2��ϵ�绰",
		"��Ա2�Ƿ�໤��", "��Ա2���֤������", "��Ա2���֤����", "��Ա2����", "��Ա2������λ", "��Ա2ְ��", "�Ƿ��������Ա��Ǩ��Ů"};
	
	public static void main(String[] args) {
		Starter starter = new Starter();
		starter.start();
	}
	
	private void start(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("������ ȫ����Сѧѧ������ϵͳѧ��������Ϣ�� ���ڵ�Ŀ¼��");
		String rootPath = scanner.nextLine();
		if(null != rootPath && rootPath.length() > 0 && !rootPath.equals("\r")){
			System.out.print("���������Ŀ¼Ϊ��" + rootPath + "��ȷ��������Y����������������N��");
			String confirm = scanner.nextLine();
			if("Y".equalsIgnoreCase(confirm)){
				System.out.println("��ʼ����[" + rootPath + "]�µ��ļ�");
				work(rootPath);
			}else if("N".equalsIgnoreCase(confirm)){
				start();
			}else{
				System.out.print("������Y����N��");
				String conf = scanner.nextLine();
				while(!(conf.equalsIgnoreCase("Y") || conf.equalsIgnoreCase("N"))){
					System.out.print("������Y����N��");
					conf = scanner.nextLine();
				}
				if("Y".equalsIgnoreCase(conf)){
					System.out.println("��ʼ����[" + rootPath + "]�µ��ļ�");
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
			System.out.println("������������������[" + rootPath + TARGET + "]�У�����ʱ��" + (System.currentTimeMillis() - begin) + "����");
		}else{
			System.out.println("Ŀ¼��[" + rootPath + "]û��doc�ļ������������г���");
		}
		System.out.println();
		System.exit(0);
	}
}
