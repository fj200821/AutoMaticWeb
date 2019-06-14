package cn.edu.hpu.autoweb.util.mes;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportExcelUtils {

	private HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFCellStyle cellStyle = workbook.createCellStyle();
	HSSFCellStyle cellStyle1 = workbook.createCellStyle();

	public void exportExcel(String title,Object[] headers, List dataset, String[] params,
			OutputStream out) {
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		exportSheetItem(title, headers, dataset, params);
		outputSerialize(out);
	}

	public void exportExcel(String title,List dataset, String[] params, OutputStream out) {
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		exportSheetItem(title, null, dataset, params);
		outputSerialize(out);
	}

	public void exportExcel(String title,Object[] headers, List dataset, OutputStream out) {
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		exportSheetItem(title, headers, dataset, null);
		outputSerialize(out);
	}

	public void exportExcel(String title,List dataset, OutputStream out) {
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		exportSheetItem(title, null, dataset, null);
		outputSerialize(out);
	}
	
	public void exportSingleSheet(String title, Object[] headers, List dataset,
			String[] params) {
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		exportSheetItem(title, headers, dataset, params);
	}
	
	public void outputSerialize(OutputStream out) {
		try {
			workbook.write(out);
		} catch (IOException e) {
			logger.error("ExportExcelUtils::outputSerialize catch Exception:", e);
		}		
	}




	/**
	 * 
	 * @param title
	 *            生成sheet的名称
	 * @param headers
	 *            表头
	 * @param params
	 *            如果dataset为对象，指定输出的对象字段
	 * @param dataset
	 *            数据源
	 */
	public void exportSheetItem(String title, Object[] headers, List dataset,
			String[] params) {
		HSSFSheet sheet;
		if (title != null) {
			sheet = workbook.createSheet(title);
		} else {
			sheet = workbook.createSheet();
		}
		sheet.setDefaultColumnWidth(12);

		int index = -1;
		if (headers != null) {
			Boolean exists = false;
			for (Object obj : headers) {
				if (obj instanceof Object[]) {
					exists = true;
					break;
				}
			}
			if (exists) {
				index += 2;
				excelHeader(workbook, sheet, headers);
			} else {
				index++;
				excelHeader(sheet, headers);
			}
		}

		HSSFRow row;
		Iterator it = dataset.iterator();
		int columnTotalSize = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Object t = (Object) it.next();
			if (t instanceof List) {
				excelData(workbook, row, (List) t);
				columnTotalSize = ((List) t).size();
			} else if (t instanceof Map) {
				if(params!=null&&params.length>0){
					excelData(workbook, row, (Map) t,params);
					columnTotalSize = params.length;
				}else{
					excelData(workbook, row, (Map) t);
					columnTotalSize = ((Map) t).size();
				}
			} else if (t instanceof Object[]) {
				excelData(workbook, row, (Object[]) t);
				columnTotalSize = ((Object[]) t).length;
			} else {
				excelData(workbook, row, t, params);
				columnTotalSize = params.length;
			}
		}
		insertCorpDepartLabel(workbook, sheet, columnTotalSize);
	}
	
	private void insertLabelRow(HSSFWorkbook workbook, HSSFSheet sheet, 
			String fontName, int fontSize, String content, int totalCol) {
		sheet.shiftRows(0, sheet.getLastRowNum(), 1, true,false);
		sheet.createRow(0);
		HSSFFont headfont = workbook.createFont();    
		headfont.setFontName(fontName);    
		headfont.setFontHeightInPoints((short) fontSize);// 字体大小    
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗   

		HSSFRow row = sheet.createRow(0);  
		HSSFCell cell = row.createCell((short)0);  
		cell.setCellValue(new HSSFRichTextString(content));  
		row.setHeight((short) 500);   
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(headfont);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cell.setCellStyle(cellStyle);
		
		if (totalCol > 12)
			totalCol = 12;
		CellRangeAddress range = new CellRangeAddress(0, 0, 0, totalCol);    
		sheet.addMergedRegion(range);  
	}
	
	private void insertCorpDepartLabel(HSSFWorkbook workbook, HSSFSheet sheet, int totalCol) {
	//	insertLabelRow(workbook, sheet, "宋体", 18, "", totalCol - 1);		
	//	insertLabelRow(workbook, sheet, "標楷體", 20, "昆山长盈精密技术有限公司", totalCol - 1); 
		//insertLabelRow(workbook, sheet, "標楷體", 20, "上海智引报表导出", totalCol - 1); 
	}

	/**
	 * 普通列头
	 * 
	 * @param row
	 * @param headers
	 */
	private void excelHeader(HSSFSheet sheet, Object[] headers) {
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		short board = 1;
		cellStyle.setBorderLeft(board); 
		cellStyle.setBorderRight(board);    
		cellStyle.setBorderTop(board);    
		cellStyle.setBorderBottom(board);
		
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(
					headers[i].toString());
			if (text.length() >=10 ) {
				sheet.setColumnWidth(i, 8000);    
			}
			cell.setCellValue(text);
			cell.setCellStyle(cellStyle);
		}
	}

	/**
	 * 创建合并列头
	 * 
	 * @param workbook
	 * @param row
	 * @param column
	 * @param s
	 */
	private void excelHeader(HSSFWorkbook workbook, HSSFSheet sheet,
			Object[] headers) {
		// CellRangeAddress四个参数为：起始行，结束行，起始列，结束列
		HSSFRow row = sheet.createRow(0);
		HSSFRow row1 = sheet.createRow(1);
		int column = 0;
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		cellStyle1.setBorderLeft((short) 1);
		cellStyle1.setBorderRight((short) 1);
		cellStyle1.setBorderTop((short) 1);
		cellStyle1.setBorderBottom((short) 1);
		for (Object obj : headers) {
			if (obj instanceof Object[]) {
				CellRangeAddress region = new CellRangeAddress(0, 0, column, column
						- 2 + ((Object[]) obj).length);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, column, column
						- 2 + ((Object[]) obj).length));
				cellStyle(workbook, row, column, ((Object[]) obj)[0].toString());
				
				int board = 1;
				RegionUtil.setBorderTop(board, region, sheet, workbook);
				RegionUtil.setBorderLeft(board, region, sheet, workbook);
				RegionUtil.setBorderRight(board, region, sheet, workbook);
				RegionUtil.setBorderBottom(board, region, sheet, workbook);
				
				for (int i = 1; i < ((Object[]) obj).length; i++) {
					cellStyle(workbook, row1, column,
							((Object[]) obj)[i].toString());
					column++;
				}
			} else {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, column, column));
				cellStyle(workbook, row, column, obj.toString());
				column++;
			}
		}
	}




	private void cellStyle(HSSFWorkbook workbook, HSSFRow row, int column,
			String s) {
		HSSFCell cell = row.createCell(column);
		excelCell(workbook, cell, s);
		cell.setCellStyle(cellStyle1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void excelData(HSSFWorkbook workbook, HSSFRow row, Object t,
			String[] params) {
		try {
			Field[] fields = t.getClass().getDeclaredFields();
			int count;
			if (params != null) {
				count = params.length;
			} else {
				count = fields.length;
			}
			String getMethodName = null;
			for (int i = 0; i < count; i++) {
				HSSFCell cell = row.createCell(i);
				if (params != null) {
					getMethodName = "get"
							+ params[i].substring(0, 1).toUpperCase()
							+ params[i].substring(1);
				} else {
					Field field = fields[i];
					String fieldName = field.getName();
					getMethodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
				}
				Class tCls = t.getClass();
				Method getMethod;
				getMethod = tCls.getMethod(getMethodName, new Class[] {});
				Object value = getMethod.invoke(t, new Object[] {});
				excelCell(workbook, cell, value);
			}
		} catch (Exception e) {
			logger.error("ExportExcelUtils::excelData catch Exception:", e);
		}
	}

	private void excelData(HSSFWorkbook workbook, HSSFRow row, List t) {
		for (int i = 0; i < t.size(); i++) {
			HSSFCell cell = row.createCell(i);
			excelCell(workbook, cell, t.get(i));
		}
	}

	private void excelData(HSSFWorkbook workbook, HSSFRow row, Map t,String[] f) {

        
		for (int i = 0; i < f.length; i++) {
			HSSFCell cell = row.createCell(i);
			excelCell(workbook, cell, t.get(f[i]), cellStyle);
			cell.setCellStyle(cellStyle);
		}
	}
	
	private void excelData(HSSFWorkbook workbook, HSSFRow row, Map t) {
		Object[] keys = t.keySet().toArray();
		for (int i = 0; i < keys.length; i++) {
			HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
			excelCell(workbook, cell, t.get(keys[i]), cellStyle);
		}
	}

	private void excelData(HSSFWorkbook workbook, HSSFRow row, Object[] t) {
		for (int i = 0; i < t.length; i++) {
			HSSFCell cell = row.createCell(i);
			excelCell(workbook, cell, t[i]);
		}
	}

	private void excelCell(HSSFWorkbook workbook, HSSFCell cell, Object value) {
		if (value != null) {
			Pattern p = Pattern.compile("^-?\\d+(\\.\\d+)?$");
			Matcher matcher = p.matcher(value.toString());
			
			if (matcher.matches()) {
//				cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				cell.setCellValue(Double.parseDouble(value.toString()));
			} else {
				HSSFRichTextString richString = new HSSFRichTextString(
						value.toString());
				cell.setCellValue(richString);
			}
			cell.setCellStyle(cellStyle);
		}
	}
	
    private void excelCell(HSSFWorkbook workbook, HSSFCell cell, Object value, HSSFCellStyle cellStyle) {
        if (value != null) {
            Pattern p = Pattern.compile("^-?\\d+(\\.\\d+)?$");
            Matcher matcher = p.matcher(value.toString());
            
            if (matcher.matches()) {
                cell.setCellValue(Double.parseDouble(value.toString()));
            } else {
                HSSFRichTextString richString = new HSSFRichTextString(
                        value.toString());
                cell.setCellValue(richString);
            }
        }
    }
    
	static private Logger logger = LoggerFactory.getLogger(ExportExcelUtils.class);
}
