package cn.edu.hpu.autoweb.util.mes;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

public class ExportExcelQualityUtils {

	public void exportExcel(String[] title,OutputStream out) {
		createSheet1(title[0]);
		createSheet2(title[1]);
		createSheet3(title[2]);
		createSheet4(title[3]);
		outputSerialize(out);
	}
	
	
	private void createSheet1(String title){
		List<String> list = new ArrayList<String>();
		list.add("设备号");
		list.add("物料号");
		list.add("订单号");
		list.add("周期");
		list.add("机型");
		list.add("出口国家（内销）");
		list.add("生产阶段");
		list.add("工艺员");
		list.add("产品图号");
		List<String> listData = new ArrayList<String>();
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		Map map1 = new HashMap();
		map1.put("list", list);
		map1.put("listData", listData);
		List<Map> list2 = new ArrayList<Map>();
		Map map2 = new HashMap();
		map2.put("no", "1");
		map2.put("item", "换产后上批次物料是否清理？");
		map2.put("usercheck", "");
		map2.put("result", "");
		map2.put("remark", "");
		Map map3 = new HashMap();
		map3.put("no", "2");
		map3.put("item", "订单所需物料是否齐套？");
		map3.put("usercheck", "");
		map3.put("result", "");
		map3.put("remark", "");
		list2.add(map2);
		list2.add(map3);
		
		Map map4 = new HashMap();
		map4.put("materialInfo", "");
		map4.put("exterior", "");
		map4.put("fabric", "");
		map4.put("size", "");
		map4.put("performs", "");
		map4.put("fit", "");
		map4.put("mark", "");
		map4.put("package", "");
		map4.put("judge", "");
		map4.put("checker", "");
		
		exportSheetItem1(title, map1, list2, map4);
	}
	
	private void createSheet2(String title){
		Map map = new HashMap();
		map.put("device", "");
		map.put("model", "");
		map.put("no", "");
		map.put("long", "");
		map.put("width", "");
		map.put("standard", "");
		
		Map map2 = new HashMap();
		map2.put("posLongTop", "");
		map2.put("posLongBottom", "");
		map2.put("posWidthLeft", "");
		map2.put("posWidthRight", "");
		map2.put("posLongTopDif", "");
		map2.put("posLongBottomDif", "");
		map2.put("posWidthLeftDif", "");
		map2.put("posWidthRightDif", "");
		map2.put("posCategory", "");
		map2.put("posMT", "");
		map2.put("MP", "");
		map2.put("deformation", "");
		map2.put("defCategory", "");
		map2.put("wallSizeLeftTop", "");
		map2.put("wallSizeLeftMid", "");
		map2.put("wallSizeLeftBottom", "");
		map2.put("wallSizeRightTop", "");
		map2.put("wallSizeRightMid", "");
		map2.put("wallSizeRightBottom", "");
		map2.put("wallSizeCategory", "");
		map2.put("wallSizeMT", "");
		exportSheetItem2(title,map,map2);
	}
	
	private void createSheet3(String title){
		Map map = new HashMap();
		map.put("deviceno", "");
		map.put("machinetype", "");
		map.put("pictureno", "");
		map.put("materialno", "");
		map.put("checker", "");
		map.put("orderno", "");
		map.put("exitorentrance", "");
		
		List<List<Map>> list = new ArrayList<List<Map>>();
		List<Map> list1 = new ArrayList<Map>();
		Map map11 = new HashMap();
		map11.put("type", "材料信息");
		map11.put("no", "1");
		map11.put("item", "用料");
		map11.put("standard", "原材料符合工艺要求");
		map11.put("ri11", "");
		map11.put("ri12", "");
		map11.put("ri13", "");
		map11.put("ri21", "");
		map11.put("ri22", "");
		map11.put("ri23", "");
		map11.put("ri31", "");
		map11.put("ri32", "");
		map11.put("ri33", "");
		map11.put("ri41", "");
		map11.put("ri42", "");
		map11.put("ri43", "");
		map11.put("ri51", "");
		map11.put("ri52", "");
		map11.put("ri53", "");
		list1.add(map11);
		Map map12 = new HashMap();
		map12.put("type", "材料信息");
		map12.put("no", "2");
		map12.put("item", "用料牌号");
		map12.put("standard", "符合工艺要求");
		map12.put("ri11", "");
		map12.put("ri12", "");
		map12.put("ri13", "");
		map12.put("ri21", "");
		map12.put("ri22", "");
		map12.put("ri23", "");
		map12.put("ri31", "");
		map12.put("ri32", "");
		map12.put("ri33", "");
		map12.put("ri41", "");
		map12.put("ri42", "");
		map12.put("ri43", "");
		map12.put("ri51", "");
		map12.put("ri52", "");
		map12.put("ri53", "");
		list1.add(map12);
		Map map13 = new HashMap();
		map13.put("type", "材料信息");
		map13.put("no", "3");
		map13.put("item", "性能");
		map13.put("standard", "环保/阻燃与工艺要求一致");
		map13.put("ri11", "");
		map13.put("ri12", "");
		map13.put("ri13", "");
		map13.put("ri21", "");
		map13.put("ri22", "");
		map13.put("ri23", "");
		map13.put("ri31", "");
		map13.put("ri32", "");
		map13.put("ri33", "");
		map13.put("ri41", "");
		map13.put("ri42", "");
		map13.put("ri43", "");
		map13.put("ri51", "");
		map13.put("ri52", "");
		map13.put("ri53", "");
		list1.add(map13);
		list.add(list1);
		
		List<Map> list2 = new ArrayList<Map>();
		Map map21 = new HashMap();
		map21.put("type", "外观丝印信息");
		map21.put("no", "1");
		map21.put("item", "修边效果");
		map21.put("standard", "符合标准、样件");
		map21.put("ri11", "");
		map21.put("ri12", "");
		map21.put("ri13", "");
		map21.put("ri21", "");
		map21.put("ri22", "");
		map21.put("ri23", "");
		map21.put("ri31", "");
		map21.put("ri32", "");
		map21.put("ri33", "");
		map21.put("ri41", "");
		map21.put("ri42", "");
		map21.put("ri43", "");
		map21.put("ri51", "");
		map21.put("ri52", "");
		map21.put("ri53", "");
		list2.add(map21);
		Map map22 = new HashMap();
		map22.put("type", "外观丝印信息");
		map22.put("no", "2");
		map22.put("item", "膜内划伤");
		map22.put("standard", "符合标准");
		map22.put("ri11", "");
		map22.put("ri12", "");
		map22.put("ri13", "");
		map22.put("ri21", "");
		map22.put("ri22", "");
		map22.put("ri23", "");
		map22.put("ri31", "");
		map22.put("ri32", "");
		map22.put("ri33", "");
		map22.put("ri41", "");
		map22.put("ri42", "");
		map22.put("ri43", "");
		map22.put("ri51", "");
		map22.put("ri52", "");
		map22.put("ri53", "");
		list2.add(map22);
		list.add(list2);
		
		
		exportSheetItem3(title,map,list);
	}
	
	private void createSheet4(String title){
		List<String> list = new ArrayList<String>();
		list.add("生产日期");
		list.add("设备号");
		list.add("机型");
		list.add("出口国家");
		list.add("数量");
		list.add("滚动计划号");
		list.add("订单号");
		list.add("品牌");
		list.add("出口方式");
		List<String> listData = new ArrayList<String>();
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		listData.add("");
		Map map1 = new HashMap();
		map1.put("list", list);
		map1.put("listData", listData);
		
		List<List<Map>> list0 = new ArrayList<List<Map>>();
		List<Map> list1 = new ArrayList<Map>();
		Map map11 = new HashMap();
		map11.put("type", "材料  信息");
		map11.put("no", "1");
		map11.put("item", "用料、性能、用料牌号");
		map11.put("info", "");
		map11.put("checker", "");
		map11.put("checkdate", "");
		list1.add(map11);
		List<Map> list2 = new ArrayList<Map>();
		Map map21 = new HashMap();
		map21.put("type", "外观 丝印 信息");
		map21.put("no", "1");
		map21.put("item", "修边效果");
		map21.put("info", "");
		map21.put("checker", "");
		map21.put("checkdate", "");
		Map map22 = new HashMap();
		map22.put("type", "外观 丝印 信息");
		map22.put("no", "2");
		map22.put("item", "膜内划伤");
		map22.put("info", "");
		map22.put("checker", "");
		map22.put("checkdate", "");
		list2.add(map21);
		list2.add(map22);
		list0.add(list1);
		list0.add(list2);
		
		exportSheetItem4(title,map1,list0);
	}
	
	public void outputSerialize(OutputStream out) {
		try {
			workbook.write(out);
		} catch (IOException e) {
			logger.error("ExportExcelUtils::outputSerialize catch Exception:", e);
		}		
	}
	
	private HSSFWorkbook workbook = new HSSFWorkbook();

	
	
	
	public void exportSheetItem1(String title,Map map1,List list2,Map map){
		HSSFSheet sheet;
		if (title != null) {
			sheet = workbook.createSheet(title);
		} else {
			sheet = workbook.createSheet();
		}
		sheet.setDefaultColumnWidth(12);

		int index = 0;

		HSSFRow row = sheet.createRow(index);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("一、基本信息");
		HSSFCell cell2 = row.createCell(1);
		cell2.setCellValue("");
		HSSFCell cell3 = row.createCell(2);
		cell3.setCellValue("年      月      日/起止时间：");
		sheet.addMergedRegion(new CellRangeAddress(0,0,2,6));
		HSSFCellStyle t = workbook.createCellStyle();
		t.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cell3.setCellStyle(t);
		HSSFCell cell4 = row.createCell(7);
		cell4.setCellValue("");
		HSSFCell cell5 = row.createCell(8);
		cell5.setCellValue("");
		HSSFCell cell6 = row.createCell(9);
		cell6.setCellValue("");
		HSSFCell cell7 = row.createCell(10);
		cell7.setCellValue("");
		HSSFCell cell9 = row.createCell(11);
		cell9.setCellValue("白班/夜班");
		HSSFCell cell10 = row.createCell(12);
		int hour = new Date().getHours();
		String shift = hour >= 8 && hour <= 20 ?"白班":"夜班"; 
		cell10.setCellValue(shift);
		
		index ++;
		HSSFRow row2 = sheet.createRow(index);
		List list = (List) map1.get("list");
		List listData = (List) map1.get("listData");
		for(int i = 0;i < list.size();i++){
			if(i == 0){
				HSSFCell cell0 = row2.createCell(0);
				cell0.setCellValue(list.get(i).toString());
				HSSFCell cell01 = row2.createCell(1);
				cell01.setCellValue(listData.get(i).toString());
			}else if((i-1)%4 == 0){
				HSSFCell cell0 = row2.createCell(2);
				cell0.setCellValue(list.get(i).toString());
				sheet.addMergedRegion(new CellRangeAddress(i/4+1,i/4+1,2,3));
				HSSFCell cell01 = row2.createCell(4);
				cell01.setCellValue(listData.get(i).toString());
				sheet.addMergedRegion(new CellRangeAddress(i/4+1,i/4+1,4,5));
			}else if((i-2)%4 == 0){
				HSSFCell cell0 = row2.createCell(6);
				cell0.setCellValue(list.get(i).toString());
				sheet.addMergedRegion(new CellRangeAddress(i/4+1,i/4+1,6,8));
				HSSFCell cell01 = row2.createCell(9);
				cell01.setCellValue(listData.get(i).toString());
			}else if((i-3)%4 == 0){
				HSSFCell cell0 = row2.createCell(10);
				cell0.setCellValue(list.get(i).toString());
				sheet.addMergedRegion(new CellRangeAddress(i/4+1,i/4+1,10,11));
				HSSFCell cell01 = row2.createCell(12);
				cell01.setCellValue(listData.get(i).toString());
			}else if(i%4==0){
				index ++;
				row2 = sheet.createRow(index);
				HSSFCell cell0 = row2.createCell(0);
				cell0.setCellValue(list.get(i).toString());
				HSSFCell cell01 = row2.createCell(1);
				cell01.setCellValue(listData.get(i).toString());
			}
		}
		

		index ++;
		HSSFRow row3 = sheet.createRow(index);
		HSSFCell cell31 = row3.createCell(0);
		cell31.setCellValue("二、结构、标识、外观、装配");
		HSSFCell cell32 = row3.createCell(1);
		cell32.setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,12));
		
		index ++;
		HSSFRow row4 = sheet.createRow(index);
		HSSFCell cell41 = row4.createCell(0);
		cell41.setCellValue("序号");
		HSSFCell cell42 = row4.createCell(1);
		cell42.setCellValue("项目");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,9));
		HSSFCell cell43 = row4.createCell(10);
		cell43.setCellValue("操作工点检");
		HSSFCell cell44 = row4.createCell(11);
		cell44.setCellValue("判定结果（NG/OK）");
		HSSFCell cell45 = row4.createCell(12);
		cell45.setCellValue("备注");
		
		for(int i = 0;i < list2.size();i++){
			index ++;
			HSSFRow row5 = sheet.createRow(index);
			HSSFCell cell51 = row5.createCell(0);
			cell51.setCellValue(((Map)list2.get(i)).get("no").toString());
			HSSFCell cell52 = row5.createCell(1);
			cell52.setCellValue(((Map)list2.get(i)).get("item").toString());
			sheet.addMergedRegion(new CellRangeAddress(index,index,1,9));
			HSSFCell cell53 = row5.createCell(10);
			cell53.setCellValue(((Map)list2.get(i)).get("usercheck").toString());
			HSSFCell cell54 = row5.createCell(11);
			cell54.setCellValue(((Map)list2.get(i)).get("result").toString());
			HSSFCell cell55 = row5.createCell(12);
			cell55.setCellValue(((Map)list2.get(i)).get("remark").toString());
		}
		
		index ++;
		HSSFRow row6 = sheet.createRow(index);
		HSSFCell cell61 = row6.createCell(0);
		cell61.setCellValue("三、检验结论");
		HSSFCell cell62 = row6.createCell(1);
		cell62.setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,12));
		
		index ++;
		HSSFRow row7 = sheet.createRow(index);
		HSSFCell cell71 = row7.createCell(0);
		cell71.setCellValue("项目");
		HSSFCell cell72 = row7.createCell(1);
		cell72.setCellValue("材料信息");
		HSSFCell cell73 = row7.createCell(2);
		cell73.setCellValue("外观");
		HSSFCell cell74 = row7.createCell(3);
		cell74.setCellValue("结构");
		sheet.addMergedRegion(new CellRangeAddress(index,index,3,4));
		HSSFCell cell75 = row7.createCell(5);
		cell75.setCellValue("尺寸");
		HSSFCell cell76 = row7.createCell(6);
		cell76.setCellValue("性能");
		HSSFCell cell77 = row7.createCell(7);
		cell77.setCellValue("装配");
		sheet.addMergedRegion(new CellRangeAddress(index,index,7,8));
		HSSFCell cell78 = row7.createCell(9);
		cell78.setCellValue("标识");
		HSSFCell cell79 = row7.createCell(10);
		cell79.setCellValue("包装");
		sheet.addMergedRegion(new CellRangeAddress(index,index,10,11));
		HSSFCell cell710 = row7.createCell(12);
		cell710.setCellValue("综合判定");
		

		index ++;
		HSSFRow row8 = sheet.createRow(index);
		HSSFCell cell81 = row8.createCell(0);
		cell81.setCellValue("判定结论");
		HSSFCell cell82 = row8.createCell(1);
		cell82.setCellValue(map.get("materialInfo").toString());
		HSSFCell cell83 = row8.createCell(2);
		cell83.setCellValue(map.get("exterior").toString());
		HSSFCell cell84 = row8.createCell(3);
		cell84.setCellValue(map.get("fabric").toString());
		sheet.addMergedRegion(new CellRangeAddress(index,index,3,4));
		HSSFCell cell85 = row8.createCell(5);
		cell85.setCellValue(map.get("size").toString());
		HSSFCell cell86 = row8.createCell(6);
		cell86.setCellValue(map.get("performs").toString());
		HSSFCell cell87 = row8.createCell(7);
		cell87.setCellValue(map.get("fit").toString());
		sheet.addMergedRegion(new CellRangeAddress(index,index,7,8));
		HSSFCell cell88 = row8.createCell(9);
		cell88.setCellValue(map.get("mark").toString());
		HSSFCell cell89 = row8.createCell(10);
		cell89.setCellValue(map.get("package").toString());
		sheet.addMergedRegion(new CellRangeAddress(index,index,10,11));
		HSSFCell cell810 = row8.createCell(12);
		cell810.setCellValue(map.get("judge").toString());
		

		index ++;
		HSSFRow row9 = sheet.createRow(index);
		HSSFCell cell91 = row9.createCell(0);
		cell91.setCellValue("检验员:");
		cell91.setCellStyle(t);
		sheet.addMergedRegion(new CellRangeAddress(index,index,0,11));
		HSSFCell cell92 = row9.createCell(12);
		cell92.setCellValue(map.get("checker").toString());

		index ++;
		HSSFRow row10 = sheet.createRow(index);
		HSSFCell cell101 = row10.createCell(0);
		cell101.setCellValue("备注:");
		sheet.addMergedRegion(new CellRangeAddress(index,index,0,12));
		index ++;
		HSSFRow row11 = sheet.createRow(index);
		HSSFCell cell111 = row11.createCell(0);
		cell111.setCellValue("产品物料号更换时进行批次确认，转产设备在转产后填写确认单，在换产后，检验员将上个批次的确认单收回存档；操作工点检时用“√”“ⅹ”“/”标识，检验员判定结论用“OK”“NG”“/”标识。");
		sheet.addMergedRegion(new CellRangeAddress(index,index,0,12));
		index ++;
		HSSFRow row12 = sheet.createRow(index);
		HSSFCell cell121 = row12.createCell(0);
		cell121.setCellValue("ZZPT030303-R01");
		sheet.addMergedRegion(new CellRangeAddress(index,index,0,12));
		
		insertLabelRow(workbook, sheet, "標楷體", 20, title, 12); 
	}
	
	
	public void exportSheetItem2(String title,Map map,Map map2){
		HSSFSheet sheet;
		if (title != null) {
			sheet = workbook.createSheet(title);
		} else {
			sheet = workbook.createSheet();
		}
		sheet.setDefaultColumnWidth(7);

		HSSFCellStyle right = workbook.createCellStyle();
		right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		HSSFCellStyle center = workbook.createCellStyle();
		center.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle middle = workbook.createCellStyle();
		middle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		middle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		int index = 0;

		HSSFRow row = sheet.createRow(index);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("一、基本信息");
		HSSFCell cell1 = row.createCell(1);
		int hour = new Date().getHours();
		String shift = hour >= 8 && hour <= 20 ?"白班":"夜班"; 
		cell1.setCellValue("年        月          日           班次：" + shift);
		cell1.setCellStyle(center);
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,7));
		
		index ++;
		HSSFRow row2 = sheet.createRow(index);
		HSSFCell cell21 = row2.createCell(0);
		cell21.setCellValue("设备");
		cell21.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,0,0));
		HSSFCell cell22 = row2.createCell(1);
		cell22.setCellValue("机型");
		cell22.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,1,2));
		HSSFCell cell23 = row2.createCell(3);
		cell23.setCellValue("图号/模号");
		cell23.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,3,3));
		HSSFCell cell24 = row2.createCell(4);
		cell24.setCellValue("标准尺寸");
		cell24.setCellStyle(center);
		sheet.addMergedRegion(new CellRangeAddress(index,index,4,6));
		HSSFCell cell25 = row2.createCell(7);
		cell25.setCellValue("变形量标准");
		cell25.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,7,7));
		

		index ++;
		HSSFRow row3 = sheet.createRow(index);
		HSSFCell cell31 = row3.createCell(4);
		cell31.setCellValue("长：");
		cell31.setCellStyle(center);
		sheet.addMergedRegion(new CellRangeAddress(index,index,4,5));
		HSSFCell cell32 = row3.createCell(6);
		cell32.setCellValue("宽：");
		cell32.setCellStyle(center);
		

		index ++;
		HSSFRow row4 = sheet.createRow(index);
		HSSFCell cell41 = row4.createCell(0);
		cell41.setCellValue(map.get("device").toString());
		cell41.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,0,0));
		HSSFCell cell42 = row4.createCell(1);
		cell42.setCellValue(map.get("model").toString());
		cell42.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,1,2));
		HSSFCell cell43 = row4.createCell(3);
		cell43.setCellValue(map.get("no").toString());
		cell43.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,3,3));
		HSSFCell cell44 = row4.createCell(4);
		cell44.setCellValue(map.get("long").toString());
		cell44.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,4,5));
		HSSFCell cell45 = row4.createCell(6);
		cell45.setCellValue(map.get("width").toString());
		cell45.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,6,6));
		HSSFCell cell46 = row4.createCell(7);
		cell46.setCellValue(map.get("standard").toString());
		cell46.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,7,7));
		
		index += 2;
		HSSFRow row5 = sheet.createRow(index);
		HSSFCell cell51 = row5.createCell(0);
		cell51.setCellValue("二、实测信息");
		HSSFCell cell52 = row5.createCell(1);
		cell52.setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,7));
		
		index ++;
		HSSFRow row6 = sheet.createRow(index);
		HSSFCell cell61 = row6.createCell(0);
		cell61.setCellValue("测量位置");
		cell61.setCellStyle(center);
		HSSFCell cell62 = row6.createCell(1);
		cell62.setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,2));
		HSSFCell cell63 = row6.createCell(3);
		cell63.setCellValue("测量数据");
		cell63.setCellStyle(center);
		HSSFCell cell64 = row6.createCell(4);
		cell64.setCellValue("差值");
		cell64.setCellStyle(center);
		HSSFCell cell65 = row6.createCell(5);
		cell65.setCellValue("判定");
		cell65.setCellStyle(center);
		HSSFCell cell66 = row6.createCell(6);
		cell66.setCellValue("测量时间");
		cell66.setCellStyle(center);
		HSSFCell cell67 = row6.createCell(7);
		cell67.setCellValue("测量人");
		cell67.setCellStyle(center);

		index ++;
		HSSFRow row7 = sheet.createRow(index);
		HSSFCell cell71 = row7.createCell(0);
		cell71.setCellValue("长");
		cell71.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,0,0));
		HSSFCell cell72 = row7.createCell(1);
		cell72.setCellValue("上：");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,2));
		HSSFCell cell73 = row7.createCell(3);
		cell73.setCellValue(map2.get("posLongTop").toString());
		cell73.setCellStyle(center);
		HSSFCell cell74 = row7.createCell(4);
		cell74.setCellValue(map2.get("posLongTopDif").toString());
		cell74.setCellStyle(middle);
		HSSFCell cell75 = row7.createCell(5);
		cell75.setCellValue(map2.get("posCategory").toString());
		cell75.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+3,5,5));
		HSSFCell cell76 = row7.createCell(6);
		cell76.setCellValue(map2.get("posMT").toString());
		cell76.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+4,6,6));
		HSSFCell cell77 = row7.createCell(7);
		cell77.setCellValue(map2.get("MP").toString());
		cell77.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+8,7,7));
		
		index ++;
		HSSFRow row8 = sheet.createRow(index);
		HSSFCell cell81 = row8.createCell(1);
		cell81.setCellValue("下:");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,2));
		HSSFCell cell82 = row8.createCell(3);
		cell82.setCellValue(map2.get("posLongBottom").toString());
		HSSFCell cell83 = row8.createCell(4);
		cell83.setCellValue(map2.get("posLongBottomDif").toString());

		index ++;
		HSSFRow row9 = sheet.createRow(index);
		HSSFCell cell91 = row9.createCell(0);
		cell91.setCellValue("宽");
		cell91.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,0,0));
		HSSFCell cell92 = row9.createCell(1);
		cell92.setCellValue("左:");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,2));
		HSSFCell cell93 = row9.createCell(3);
		cell93.setCellValue(map2.get("posWidthLeft").toString());
		HSSFCell cell94 = row9.createCell(4);
		cell94.setCellValue(map2.get("posWidthLeftDif").toString());
		

		index ++;
		HSSFRow row10 = sheet.createRow(index);
		HSSFCell cell101 = row10.createCell(1);
		cell101.setCellValue("下:");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,2));
		HSSFCell cell102 = row10.createCell(3);
		cell102.setCellValue(map2.get("posWidthRight").toString());
		HSSFCell cell103 = row10.createCell(4);
		cell103.setCellValue(map2.get("posWidthRightDif").toString());
		
		index ++;
		HSSFRow row11 = sheet.createRow(index);
		HSSFCell cell111 = row11.createCell(0);
		cell111.setCellValue("变形量测量数据");
		cell111.setCellStyle(middle);
		HSSFCell cell112 = row11.createCell(1);
		cell112.setCellValue(map2.get("deformation").toString());
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,4));
		HSSFCell cell113 = row11.createCell(4);
		cell113.setCellValue(map2.get("defCategory").toString());
		
		index ++;
		HSSFRow row12 = sheet.createRow(index);
		HSSFCell cell121 = row12.createCell(0);
		cell121.setCellValue("壁厚尺寸");
		cell121.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+3,0,0));
		HSSFCell cell122 = row12.createCell(1);
		cell122.setCellValue("位置");
		cell122.setCellStyle(middle);
		HSSFCell cell123 = row12.createCell(2);
		cell123.setCellValue("测量数据");
		cell123.setCellStyle(middle);
		HSSFCell cell124 = row12.createCell(3);
		cell124.setCellValue("位置");
		cell124.setCellStyle(middle);
		HSSFCell cell125 = row12.createCell(4);
		cell125.setCellValue("测量数据");
		cell125.setCellStyle(middle);
		HSSFCell cell126 = row12.createCell(5);
		cell126.setCellValue("判定");
		cell126.setCellStyle(middle);
		HSSFCell cell127 = row12.createCell(6);
		cell127.setCellValue("测量时间");
		cell127.setCellStyle(middle);
		

		index ++;
		HSSFRow row13 = sheet.createRow(index);
		HSSFCell cell132 = row13.createCell(1);
		cell132.setCellValue("左框");
		cell132.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+2,1,1));
		HSSFCell cell133 = row13.createCell(2);
		cell133.setCellValue("上:" + map2.get("wallSizeLeftTop").toString());
		HSSFCell cell134 = row13.createCell(3);
		cell134.setCellValue("右框");
		cell134.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+2,3,3));
		HSSFCell cell135 = row13.createCell(4);
		cell135.setCellValue("上：" + map2.get("wallSizeRightTop").toString());
		HSSFCell cell136 = row13.createCell(5);
		cell136.setCellValue(map2.get("wallSizeCategory").toString());
		cell136.setCellStyle(middle);
		HSSFCell cell137 = row12.createCell(6);
		cell137.setCellValue(map2.get("wallSizeMT").toString());
		cell137.setCellStyle(middle);

		index ++;
		HSSFRow row14 = sheet.createRow(index);
		HSSFCell cell142 = row14.createCell(2);
		cell142.setCellValue("中:" + map2.get("wallSizeLeftMid").toString());
		HSSFCell cell143 = row14.createCell(4);
		cell143.setCellValue("中:" + map2.get("wallSizeRightMid").toString());
		
		index ++;
		HSSFRow row15 = sheet.createRow(index);
		HSSFCell cell152 = row15.createCell(2);
		cell152.setCellValue("下:" + map2.get("wallSizeLeftBottom").toString());
		HSSFCell cell153 = row15.createCell(4);
		cell153.setCellValue("下:" + map2.get("wallSizeRightBottom").toString());
		
		index ++;
		HSSFRow row16 = sheet.createRow(index);
		HSSFCell cell161 = row16.createCell(0);
		cell161.setCellValue("三、最终结论");
		HSSFCell cell162 = row16.createCell(1);
		cell162.setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,7));

		index ++;
		HSSFRow row17 = sheet.createRow(index);
		HSSFCell cell171 = row17.createCell(0);
		cell171.setCellValue("处理结果");
		cell171.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+2,0,0));
		HSSFCell cell172 = row17.createCell(1);
		cell172.setCellValue("结构工程师签署意见");
		cell172.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,6));
		HSSFCell cell173 = row17.createCell(7);
		cell173.setCellValue("确认人");
		cell173.setCellStyle(middle);

		index ++;
		HSSFRow row18 = sheet.createRow(index);
		HSSFCell cell182 = row18.createCell(1);
		cell182.setCellValue("结构工程师签署意见");
		cell182.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,1,6));
		HSSFCell cell183 = row18.createCell(7);
		cell183.setCellValue("确认人");
		cell183.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,7,7));

		index += 2;
		HSSFRow row19 = sheet.createRow(index);
		HSSFCell cell191 = row19.createCell(0);
		cell191.setCellValue("备注");
		cell191.setCellStyle(middle);
		HSSFCell cell192 = row19.createCell(1);
		cell192.setCellValue("1、对应班次打“√” 2、 判定栏合格填写“OK” 不合格填写“NG” 如不合格时必须由相应工程师签署意见 3、左右壁厚测量时，左右框分别取三个点，即“上”“中”“下”，左右壁厚数据差异≤0.2为合格，＞0.2需由结构工程师出具意见。");
		cell192.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,7));
		
		
		insertLabelRow(workbook, sheet, "標楷體", 20, title, 7); 
	}
	
	
	public void exportSheetItem3(String title,Map map,List list){
		HSSFSheet sheet;
		if (title != null) {
			sheet = workbook.createSheet(title);
		} else {
			sheet = workbook.createSheet();
		}
		sheet.setDefaultColumnWidth(19);

		HSSFCellStyle right = workbook.createCellStyle();
		right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		HSSFCellStyle center = workbook.createCellStyle();
		center.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle middle = workbook.createCellStyle();
		middle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		middle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		int index = 0;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		HSSFRow row = sheet.createRow(index);
		HSSFCell cell = row.createCell(0);
		int hour = new Date().getHours();
		String shift = hour >= 8 && hour <= 20 ?"白班":"夜班"; 
		cell.setCellValue("抽检日期/班次：" + df.format(new Date()) + "/" + shift);
		sheet.addMergedRegion(new CellRangeAddress(index,index+3,0,3));
		HSSFCell cell1 = row.createCell(4);
		cell1.setCellValue("设备号");
		HSSFCell cell2 = row.createCell(5);
		cell2.setCellValue(map.get("deviceno").toString());

		index ++;
		HSSFRow row1 = sheet.createRow(index);
		HSSFCell cell11 = row1.createCell(4);
		cell11.setCellValue("机型");
		HSSFCell cell12 = row1.createCell(5);
		cell12.setCellValue(map.get("machinetype").toString());

		index ++;
		HSSFRow row2 = sheet.createRow(index);
		HSSFCell cell21 = row2.createCell(4);
		cell21.setCellValue("图号");
		HSSFCell cell22 = row2.createCell(5);
		cell22.setCellValue(map.get("pictureno").toString());

		index ++;
		HSSFRow row3 = sheet.createRow(index);
		HSSFCell cell31 = row3.createCell(4);
		cell31.setCellValue("物料号");
		HSSFCell cell32 = row3.createCell(5);
		cell32.setCellValue(map.get("materialno").toString());
		
		index ++;
		HSSFRow row4 = sheet.createRow(index);
		HSSFCell cell41 = row4.createCell(0);
		cell41.setCellValue("检验员签字：" + map.get("checker").toString());
		sheet.addMergedRegion(new CellRangeAddress(index,index+2,0,3));
		HSSFCell cell42 = row4.createCell(4);
		cell42.setCellValue("订单号");
		HSSFCell cell43 = row4.createCell(5);
		cell43.setCellValue(map.get("orderno").toString());

		index ++;
		HSSFRow row5 = sheet.createRow(index);
		HSSFCell cell51 = row5.createCell(4);
		cell51.setCellValue("出口/内销");
		HSSFCell cell52 = row5.createCell(5);
		cell52.setCellValue(map.get("exitorentrance").toString());

		index ++;
		HSSFRow row6 = sheet.createRow(index);
		HSSFCell cell61 = row6.createCell(4);
		cell61.setCellValue("判定");
		HSSFCell cell62 = row6.createCell(5);
		cell62.setCellValue("巡检频次");
		cell62.setCellStyle(center);
		sheet.addMergedRegion(new CellRangeAddress(index,index,5,7));
		HSSFCell cell63 = row6.createCell(8);
		cell63.setCellValue("巡检频次");
		cell63.setCellStyle(center);
		sheet.addMergedRegion(new CellRangeAddress(index,index,8,10));
		HSSFCell cell64 = row6.createCell(11);
		cell64.setCellValue("巡检频次");
		cell64.setCellStyle(center);
		sheet.addMergedRegion(new CellRangeAddress(index,index,11,13));
		HSSFCell cell65 = row6.createCell(14);
		cell65.setCellValue("巡检频次");
		cell65.setCellStyle(center);
		sheet.addMergedRegion(new CellRangeAddress(index,index,14,16));
		HSSFCell cell66 = row6.createCell(17);
		cell66.setCellValue("巡检频次");
		cell66.setCellStyle(center);
		sheet.addMergedRegion(new CellRangeAddress(index,index,17,19));

		index ++;
		HSSFRow row7 = sheet.createRow(index);
		HSSFCell cell71 = row7.createCell(0);
		cell71.setCellValue("分类");
		cell71.setCellStyle(center);
		HSSFCell cell72 = row7.createCell(1);
		cell72.setCellValue("序号");
		cell72.setCellStyle(center);
		HSSFCell cell73 = row7.createCell(2);
		cell73.setCellValue("检验项目");
		cell73.setCellStyle(center);
		HSSFCell cell74 = row7.createCell(3);
		cell74.setCellValue("检验标准说明");
		cell74.setCellStyle(center);
		sheet.addMergedRegion(new CellRangeAddress(index,index,3,4));
		HSSFCell cell75 = row7.createCell(5);
		cell75.setCellValue("1");
		cell75.setCellStyle(center);
		HSSFCell cell76 = row7.createCell(6);
		cell76.setCellValue("2");
		cell76.setCellStyle(center);
		HSSFCell cell77 = row7.createCell(7);
		cell77.setCellValue("3");
		cell77.setCellStyle(center);
		HSSFCell cell78 = row7.createCell(8);
		cell78.setCellValue("1");
		cell78.setCellStyle(center);
		HSSFCell cell79 = row7.createCell(9);
		cell79.setCellValue("2");
		cell79.setCellStyle(center);
		HSSFCell cell710 = row7.createCell(10);
		cell710.setCellValue("3");
		cell710.setCellStyle(center);
		HSSFCell cell711 = row7.createCell(11);
		cell711.setCellValue("1");
		cell711.setCellStyle(center);
		HSSFCell cell712 = row7.createCell(12);
		cell712.setCellValue("2");
		cell712.setCellStyle(center);
		HSSFCell cell713 = row7.createCell(13);
		cell713.setCellValue("3");
		cell713.setCellStyle(center);
		HSSFCell cell714 = row7.createCell(14);
		cell714.setCellValue("1");
		cell714.setCellStyle(center);
		HSSFCell cell715 = row7.createCell(15);
		cell715.setCellValue("2");
		cell715.setCellStyle(center);
		HSSFCell cell716 = row7.createCell(16);
		cell716.setCellValue("3");
		cell716.setCellStyle(center);
		HSSFCell cell717 = row7.createCell(17);
		cell717.setCellValue("1");
		cell717.setCellStyle(center);
		HSSFCell cell718 = row7.createCell(18);
		cell718.setCellValue("2");
		cell718.setCellStyle(center);
		HSSFCell cell719 = row7.createCell(19);
		cell719.setCellValue("3");
		cell719.setCellStyle(center);
		
		
		for(int i = 0;i < list.size();i++){
			index ++;
			HSSFRow row8 = sheet.createRow(index);
			List<Map> list0 = (List<Map>) list.get(i);
			HSSFCell cell81 = row8.createCell(0);
			cell81.setCellValue(list0.get(0).get("type").toString());
			cell81.setCellStyle(middle);
			if(list0.size() > 1)
				sheet.addMergedRegion(new CellRangeAddress(index,index + list0.size()-1,0,0));
			for(int j = 0;j < list0.size(); j++){
				Map map0 = list0.get(j);
				if(j != 0){
					index ++;
					row8 = sheet.createRow(index);
				}
				HSSFCell cell82 = row8.createCell(1);
				cell82.setCellValue(map0.get("no").toString());
				cell82.setCellStyle(middle);
				HSSFCell cell83 = row8.createCell(2);
				cell83.setCellValue(map0.get("item").toString());
				cell83.setCellStyle(middle);
				HSSFCell cell84 = row8.createCell(3);
				cell84.setCellValue(map0.get("standard").toString());
				cell84.setCellStyle(middle);
				sheet.addMergedRegion(new CellRangeAddress(index,index,3,4));
				HSSFCell cell85 = row8.createCell(5);
				cell85.setCellValue(map0.get("ri11").toString());
				cell85.setCellStyle(middle);
				HSSFCell cell86 = row8.createCell(6);
				cell86.setCellValue(map0.get("ri12").toString());
				cell86.setCellStyle(middle);
				HSSFCell cell87 = row8.createCell(7);
				cell87.setCellValue(map0.get("ri13").toString());
				cell87.setCellStyle(middle);
				HSSFCell cell88 = row8.createCell(8);
				cell88.setCellValue(map0.get("ri21").toString());
				cell88.setCellStyle(middle);
				HSSFCell cell89 = row8.createCell(9);
				cell89.setCellValue(map0.get("ri22").toString());
				cell89.setCellStyle(middle);
				HSSFCell cell810 = row8.createCell(10);
				cell810.setCellValue(map0.get("ri23").toString());
				cell810.setCellStyle(middle);
				HSSFCell cell811 = row8.createCell(11);
				cell811.setCellValue(map0.get("ri31").toString());
				cell811.setCellStyle(middle);
				HSSFCell cell812 = row8.createCell(12);
				cell812.setCellValue(map0.get("ri32").toString());
				cell812.setCellStyle(middle);
				HSSFCell cell813 = row8.createCell(13);
				cell813.setCellValue(map0.get("ri33").toString());
				cell813.setCellStyle(middle);
				HSSFCell cell814 = row8.createCell(14);
				cell814.setCellValue(map0.get("ri41").toString());
				cell814.setCellStyle(middle);
				HSSFCell cell815 = row8.createCell(15);
				cell815.setCellValue(map0.get("ri42").toString());
				cell815.setCellStyle(middle);
				HSSFCell cell816 = row8.createCell(16);
				cell816.setCellValue(map0.get("ri43").toString());
				cell816.setCellStyle(middle);
				HSSFCell cell817 = row8.createCell(17);
				cell817.setCellValue(map0.get("ri51").toString());
				cell817.setCellStyle(middle);
				HSSFCell cell818 = row8.createCell(18);
				cell818.setCellValue(map0.get("ri52").toString());
				cell818.setCellStyle(middle);
				HSSFCell cell819 = row8.createCell(19);
				cell819.setCellValue(map0.get("ri53").toString());
				cell819.setCellStyle(middle);
			}
		}

		index ++;
		HSSFRow row9 = sheet.createRow(index);
		HSSFCell cell91 = row9.createCell(0);
		cell91.setCellValue("审核");
		sheet.addMergedRegion(new CellRangeAddress(index,index,0,3));
		HSSFCell cell92 = row9.createCell(4);
		cell92.setCellValue("备注");
		cell92.setCellStyle(middle);
		HSSFCell cell93 = row9.createCell(5);
		cell93.setCellValue("");
		sheet.addMergedRegion(new CellRangeAddress(index,index,5,19));
		
		index ++;
		HSSFRow row10 = sheet.createRow(index);
		HSSFCell cell101 = row10.createCell(0);
		cell101.setCellValue("说明：过程巡检时需对以上信息进行点检确认，有异常或不符合要求时，判定栏填写“×”并在备注栏简述问题点，无异常时判定栏填写“√”");
		sheet.addMergedRegion(new CellRangeAddress(index,index,0,19));
		
		
		insertLabelRow(workbook, sheet, "標楷體", 20, title, 19); 
		
	}
	
	
	public void exportSheetItem4(String title,Map map1,List list0){
		HSSFSheet sheet;
		if (title != null) {
			sheet = workbook.createSheet(title);
		} else {
			sheet = workbook.createSheet();
		}
		sheet.setDefaultColumnWidth(12);

		HSSFCellStyle right = workbook.createCellStyle();
		right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		HSSFCellStyle center = workbook.createCellStyle();
		center.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle middle = workbook.createCellStyle();
		middle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		middle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		int index = 0;

		HSSFRow row2 = sheet.createRow(index);
		List list = (List) map1.get("list");
		List listData = (List) map1.get("listData");
		for(int i = 0;i < list.size();i++){
			HSSFCell cell0 = row2.createCell(2*(i%4));
			cell0.setCellValue(list.get(i).toString());
			HSSFCell cell01 = row2.createCell(2*(i%4)+1);
			cell01.setCellValue(listData.get(i).toString());
			
			if((i+1)%5==0){
				if(i < 5){
					HSSFCell celli = row2.createCell(10);
					celli.setCellValue("检验员");
					celli.setCellStyle(middle);
					HSSFCell cellii = row2.createCell(11);
					cellii.setCellValue("抽检日期");;
					cellii.setCellStyle(middle);
				}
				index ++;
				row2 = sheet.createRow(index);
				HSSFCell cell00 = row2.createCell(2*(i%4));
				cell00.setCellValue(list.get(i).toString());
				HSSFCell cell001 = row2.createCell(2*(i%4)+1);
				cell001.setCellValue(listData.get(i).toString());
			}
		}
		
		index ++;
		HSSFRow row3 = sheet.createRow(index);
		HSSFCell cell31 = row3.createCell(0);
		cell31.setCellValue("跌落试验");
		cell31.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,0,0));
		HSSFCell cell32 = row3.createCell(1);
		cell32.setCellValue("");
		cell32.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index+1,1,11));

		index += 2;
		HSSFRow row4 = sheet.createRow(index);
		HSSFCell cell41 = row4.createCell(0);
		cell41.setCellValue("抽检项目信息：");
		cell41.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index,0,2));
		HSSFCell cell42 = row4.createCell(3);
		cell42.setCellValue("过程抽检信息：");
		cell42.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index,3,9));
		HSSFCell cell43 = row4.createCell(10);
		cell43.setCellValue("");
		HSSFCell cell44 = row4.createCell(11);
		cell44.setCellValue("");
		
		index ++;
		HSSFRow row5 = sheet.createRow(index);
		HSSFCell cell51 = row5.createCell(0);
		cell51.setCellValue("");
		HSSFCell cell52 = row5.createCell(1);
		cell52.setCellValue("序号");
		cell52.setCellStyle(middle);
		HSSFCell cell53 = row5.createCell(2);
		cell53.setCellValue("检验项目");
		cell53.setCellStyle(middle);
		HSSFCell cell54 = row5.createCell(3);
		cell54.setCellValue("");
		cell54.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index,3,9));
		HSSFCell cell55 = row5.createCell(10);
		cell55.setCellValue("");
		HSSFCell cell56 = row5.createCell(11);
		cell56.setCellValue("");
		
		for(int i = 0;i < list0.size();i++){
			index ++;
			HSSFRow row6 = sheet.createRow(index);
			List<Map> listArr = (List<Map>) list0.get(i); 
			HSSFCell cell61 = row6.createCell(0);
			cell61.setCellValue(listArr.get(0).get("type").toString());
			cell61.setCellStyle(middle);
			if(listArr.size() > 1)
				sheet.addMergedRegion(new CellRangeAddress(index,index + listArr.size()-1,0,0));
			for(int j = 0;j < listArr.size(); j++){
				Map map0 = listArr.get(j);
				if(j != 0){
					index ++;
					row6 = sheet.createRow(index);
				}
				HSSFCell cell62 = row6.createCell(1);
				cell62.setCellValue(map0.get("no").toString());
				cell62.setCellStyle(middle);
				HSSFCell cell63 = row6.createCell(2);
				cell63.setCellValue(map0.get("item").toString());
				cell63.setCellStyle(middle);
				HSSFCell cell64 = row6.createCell(3);
				cell64.setCellValue(map0.get("info").toString());
				cell64.setCellStyle(middle);
				sheet.addMergedRegion(new CellRangeAddress(index,index,3,9));
				HSSFCell cell65 = row6.createCell(10);
				cell65.setCellValue(map0.get("checker").toString());
				cell65.setCellStyle(middle);
				HSSFCell cell66 = row6.createCell(11);
				cell66.setCellValue(map0.get("checkdate").toString());
				cell66.setCellStyle(middle);
			}
		}
		

		index ++;
		HSSFRow row7 = sheet.createRow(index);
		HSSFCell cell71 = row7.createCell(0);
		cell71.setCellValue("易损件");
		cell71.setCellStyle(middle);
		sheet.addMergedRegion(new CellRangeAddress(index,index + 1,0,0));
		HSSFCell cell72 = row7.createCell(1);
		cell72.setCellValue("前壳：");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,11));

		index ++;
		HSSFRow row8 = sheet.createRow(index);
		HSSFCell cell81 = row8.createCell(1);
		cell81.setCellValue("后壳：");
		sheet.addMergedRegion(new CellRangeAddress(index,index,1,11));

		index ++;
		HSSFRow row9 = sheet.createRow(index);
		HSSFCell cell91 = row9.createCell(0);
		cell91.setCellValue("检查结果");
		sheet.addMergedRegion(new CellRangeAddress(index,index,0,11));

		index ++;
		HSSFRow row10 = sheet.createRow(index);
		HSSFCell cell101 = row10.createCell(0);
		cell101.setCellValue("备注：");
		sheet.addMergedRegion(new CellRangeAddress(index,index,0,11));
		
		insertLabelRow(workbook, sheet, "標楷體", 20, title, 11); 
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

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		cellStyle.setBorderLeft((short) 1); 
		cellStyle.setBorderRight((short) 1);    
		cellStyle.setBorderTop((short) 1);    
		cellStyle.setBorderBottom((short) 1);
		
		cell.setCellStyle(cellStyle);
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
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft((short) 1); 
        cellStyle.setBorderRight((short) 1);    
        cellStyle.setBorderTop((short) 1);    
        cellStyle.setBorderBottom((short) 1);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
        
		for (int i = 0; i < f.length; i++) {
			HSSFCell cell = row.createCell(i);
			excelCell(workbook, cell, t.get(f[i]), cellStyle);
			cell.setCellStyle(cellStyle);
		}
	}
	
	private void excelData(HSSFWorkbook workbook, HSSFRow row, Map t) {
	    HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft((short) 1); 
        cellStyle.setBorderRight((short) 1);    
        cellStyle.setBorderTop((short) 1);    
        cellStyle.setBorderBottom((short) 1);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
          
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
			
			HSSFCellStyle cellStyle = workbook.createCellStyle();
//			cellStyle.setBorderLeft((short) 1); 
//			cellStyle.setBorderRight((short) 1);    
//			cellStyle.setBorderTop((short) 1);    
//			cellStyle.setBorderBottom((short) 1);
			
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
    
	static private Logger logger = LoggerFactory.getLogger(ExportExcelQualityUtils.class);
}
