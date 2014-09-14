package com.susu.app.doc2xls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 文件系统工具类
 * 
 * @author liming.slm
 *
 */
public class FileUtil {
	/**
	 * 读取指定目录下的所有文件到指定的集合
	 * 
	 * @param rootPath
	 * @return
	 */
	public static void listFile(String rootPath, List<String> files) {
		if (null == rootPath || rootPath.length() == 0) {
			System.out.println("请输入合法的文件路径!");
			return;
		}
		File root = new File(rootPath);
		if (!root.exists()) {
			System.out.println("路径[" + rootPath + "]不存在！");
			return;
		}
		if (root.isDirectory()) {
			File[] currentFiles = root.listFiles();
			if (null != currentFiles && currentFiles.length > 0) {
				for (File file : currentFiles) {
					if (file.isFile() && filter(file)) {
						files.add(file.getAbsolutePath());
					} else {
						listFile(file.getAbsolutePath(), files);
					}
				}
			}
		} else {
			if (filter(root)) {
				files.add(root.getAbsolutePath());
			}
		}
	}

	public static boolean createTargetFile(String path) {
		if (null != path && path.length() > 0) {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
			File dir = new File(path.substring(0, path.lastIndexOf("\\")));
			dir.mkdirs();
			File target = new File(dir, path.substring(path.lastIndexOf("\\"),
					path.length()));
			try {
				target.createNewFile();
				createExcel(target);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private static void createExcel(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook();// 创建工作薄
		HSSFSheet sheet = wb.createSheet();// 创建工作表
		wb.setSheetName(0, "学生基础信息");// 设置工作表名

		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);
		HSSFCell cell = null;
		
		 HSSFFont font = wb.createFont();
		 font.setFontName("Verdana");
		 font.setBoldweight((short) 100);
		 font.setFontHeight((short) 200);
		 font.setColor(HSSFColor.RED.index);
		 
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFont(font);
		
		 // 设置边框
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		for(int i = 0, len = Starter.TITLE_ARRAY.length; i < len; i++){
			cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(Starter.TITLE_ARRAY[i]);
			sheet.setColumnWidth(i, 5000);
		}
		try {
			wb.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			fos.close();
		}
	}

	private static boolean filter(File file) {
		if (null != file) {
			if (file.getAbsolutePath().endsWith(".doc")
					&& !file.getName().startsWith("~$")) {
				return true;
			}
		}
		return false;
	}

}
