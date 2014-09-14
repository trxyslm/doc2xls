package com.susu.app.doc2xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.susu.app.doc2xls.annotation.TitleAnno;
import com.susu.app.doc2xls.bean.GuardianInfo;
import com.susu.app.doc2xls.bean.Student;

/**
 * 将指定的信息写入excel
 * @author liming.slm
 *
 */
public class ExcelWriter {
	
	public boolean write(Student student, String path){
		boolean suc = false;
		System.out.println("开始写学生[" + student.getStandardInfo().getName() + "]的信息");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try{
			File target = new File(path);
			fis = new FileInputStream(target);
			
			POIFSFileSystem ps = new POIFSFileSystem(fis);
	        HSSFWorkbook wb = new HSSFWorkbook(ps); 
	        HSSFSheet sheet = wb.getSheetAt(0);
	        int rowNum = sheet.getLastRowNum();
	        HSSFRow row = sheet.createRow(rowNum + 1);
	        
	        HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			 // 设置边框
			style.setBottomBorderColor(HSSFColor.BLACK.index);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			
	        Map<String, HSSFCell> title2Cell = new HashMap<String, HSSFCell>(Starter.TITLE_ARRAY.length);
	        for(int i = 0, len = Starter.TITLE_ARRAY.length; i < len; i++){
	        	HSSFCell cell = row.createCell(i);
	        	cell.setCellStyle(style);
	        	title2Cell.put(Starter.TITLE_ARRAY[i], cell);
	        }
	        
	        Class<?> studentClazz = student.getClass();
	        Method[] studentMethod = studentClazz.getDeclaredMethods();
	        for(Method sm : studentMethod){
	        	if(sm.getName().startsWith("get")){
	        		Object field = sm.invoke(student);
	        		if(field instanceof List){
	        			List<GuardianInfo> guardianInfos = (List<GuardianInfo>) field;
	        			for(int i = 0; i < guardianInfos.size(); i++){
	        				GuardianInfo gi = guardianInfos.get(i);
	        				Class<?> giClazz = gi.getClass();
	        				Field[] fields = giClazz.getDeclaredFields();
	        				setVal(title2Cell, fields, gi, "成员" + (i + 1));
	        			}
	        		}else{
	        			Class<?> fieldClazz = field.getClass();
	        			Field[] fields = fieldClazz.getDeclaredFields();
	        			setVal(title2Cell, fields, field, null);
	        		}
	        	}
	        }
	        
	        fos = new FileOutputStream(target);
	        wb.write(fos);
	        suc = true;
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("写学生[" + student.getStandardInfo().getName() + "]信息出现异常！");
		}finally{
			if(null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != fos){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("学生[" + student.getStandardInfo().getName() + "]的信息处理完毕");
		return suc;
	}
	
	private void setVal(Map<String, HSSFCell> title2Cell,Field[] fields, Object clazz, String prefix) throws IllegalArgumentException, IllegalAccessException{
		for(Field f : fields){
			TitleAnno anno = f.getAnnotation(TitleAnno.class);
			String title = null;
			if(null != anno){
				title = anno.title();
			}
			if(null != title && title.length() > 0){
				f.setAccessible(true);
				String value = (String) f.get(clazz);
				String key = (null == prefix) ? title : prefix + title;
				HSSFCell cell = title2Cell.get(key);
				if(null != cell){
					cell.setCellValue(value);
				}
			}
		}
	}
}
