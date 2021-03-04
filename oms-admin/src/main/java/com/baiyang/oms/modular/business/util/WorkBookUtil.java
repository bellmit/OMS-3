package com.baiyang.oms.modular.business.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
//import org.apache.poi.ss.util.Region;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baiyang.oms.core.util.MyStringUtil;
import com.baiyang.oms.modular.business.model.pojo.InExcel;

public class WorkBookUtil {


    /**
     * @param fileName    文件名 如："学生表"
     * @param excelHeader excel表头数组，存放"姓名#name"格式字符串，"姓名"为excel标题行， "name"为对象字段名
     * @param dataList    数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @return 返回一个HSSFWorkbook
     * @throws Exception
     */
    public static <T> HSSFWorkbook export(String fileName, String[] excelHeader,
                                          Collection<T> dataList) throws Exception {
        // 创建一个Workbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 设置标题样式
        HSSFCellStyle titleStyle = wb.createCellStyle();
        // 设置单元格边框样式
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        // 设置单元格对齐方式
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // 设置字体样式
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 15); // 字体高度
        titleFont.setFontName("黑体"); // 字体样式
        titleStyle.setFont(titleFont);
        // 在Workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(fileName);
        // 标题数组
        String[] titleArray = new String[excelHeader.length];
        // 字段名数组
        String[] fieldArray = new String[excelHeader.length];
        for (int i = 0; i < excelHeader.length; i++) {
            System.out.println(excelHeader[i]);
            String[] tempArray = excelHeader[i].split("#");// 临时数组 分割#
            System.out.println("length==" + tempArray.length);
            titleArray[i] = tempArray[0];
            fieldArray[i] = tempArray[1];
        }
        // 在sheet中添加标题行
        HSSFRow row = sheet.createRow((int) 0);// 行数从0开始
        HSSFCell sequenceCell = row.createCell(0);// cell列 从0开始 第一列添加序号
        sequenceCell.setCellValue("序号");
        sequenceCell.setCellStyle(titleStyle);
        // sheet.autoSizeColumn(0);// 自动设置宽度
        // 为标题行赋值
        for (int i = 0; i < titleArray.length; i++) {
            HSSFCell titleCell = row.createCell(i + 1);// 0号位被序号占用，所以需+1
            titleCell.setCellValue(titleArray[i]);
            titleCell.setCellStyle(titleStyle);
            //   sheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
        }
        // 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
        HSSFCellStyle dataStyle = wb.createCellStyle();
        // 设置数据边框
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置居中样式
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // 设置数据字体
        Font dataFont = wb.createFont();
        dataFont.setFontHeightInPoints((short) 12); // 字体高度
        dataFont.setFontName("宋体"); // 字体
        dataStyle.setFont(dataFont);
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataList.iterator();

        int index = 0;
        while (it.hasNext()) {
            boolean type = false;
            index++;// 0号位被占用 所以+1
            row = sheet.createRow(index);
            // 为序号赋值
            HSSFCell sequenceCellValue = row.createCell(0);// 序号值永远是第0列
            sequenceCellValue.setCellValue(index);
            sequenceCellValue.setCellStyle(dataStyle);
            // sheet.autoSizeColumn(0);
            T t = (T) it.next();
            // 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值

            for (int i = 0; i < fieldArray.length; i++) {
                int flg = i + 1;
                HSSFCell dataCell = row.createCell(flg);
                dataCell.setCellStyle(dataStyle);
                //  sheet.autoSizeColumn(flg);

                String[] strings = fieldArray[i].split(",");

                String fieldName = "";
                String typeName = "";
                if (strings.length == 2) {
                    fieldName = strings[0];
                    typeName = strings[1];
                } else {
                    fieldName = strings[0];
                }

                // String fieldName = fieldArray[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
                Class<? extends Object> tCls = t.getClass();// 泛型为Object以及所有Object的子类
                Method getMethod = tCls.getMethod(getMethodName, new Class[]{});// 通过方法名得到对应的方法
                Object value = getMethod.invoke(t, new Object[]{});// 动态调用方,得到属性值
                if (value != null) {
                    dataCell.setCellValue(value.toString());// 为当前列赋值

                    //根据条件单元格合并

//	     if(typeName.equals("1")){
//		     HSSFRow row1=sheet.getRow(index-1);
//				HSSFCell cell=row1.getCell((short)(flg));
//		     if(value.toString().equals(getCellStringValue(cell))){
//		    	 if(flg == 1){
//		    		 sheet.addMergedRegion(new Region((short)(index-1), (short)(flg), (short)(index), (short)(flg))); //单元格合并
//		    		 type = true;
//		    	 }else {
//		    		 if(type){
//		    			 sheet.addMergedRegion(new Region((short)(index-1), (short)(flg), (short)(index), (short)(flg)));
//		    		 }
//		    		
//		    	 }
//		     }
//	     }  

                }
            }
        }
        return wb;
    }
    // XSSFCellStyle.ALIGN_CENTER 居中对齐
    // XSSFCellStyle.ALIGN_LEFT 左对齐
    // XSSFCellStyle.ALIGN_RIGHT 右对齐
    // XSSFCellStyle.VERTICAL_TOP 上对齐
    // XSSFCellStyle.VERTICAL_CENTER 中对齐
    // XSSFCellStyle.VERTICAL_BOTTOM 下对齐

    // CellStyle.BORDER_DOUBLE 双边线
    // CellStyle.BORDER_THIN 细边线
    // CellStyle.BORDER_MEDIUM 中等边线
    // CellStyle.BORDER_DASHED 虚线边线
    // CellStyle.BORDER_HAIR 小圆点虚线边线
    // CellStyle.BORDER_THICK 粗边线

    public static String getCellStringValue(HSSFCell cell) {
        String cellValue = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING://字符串类型
                cellValue = cell.getStringCellValue();
                if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
                    cellValue = " ";
                break;
            case HSSFCell.CELL_TYPE_NUMERIC: //数值类型
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA: //公式
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                cellValue = " ";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                break;
            default:
                break;
        }
        return cellValue;
    }


    /**
     * 导出Excel 2007 OOXML (.xlsx)格式
     *
     * @param title       标题行
     * @param headMap     属性-列头
     * @param jsonArray   数据集
     * @param datePattern 日期格式，传null值则默认 年月日
     * @param colWidth    列宽 默认 至少17个字节
     * @param out         输出流
     */
    public static <T> SXSSFWorkbook exportX(String fileName, String[] excelHeader,
                                            Collection<T> dataList) throws Exception {
        // 创建一个Workbook，对应一个Excel文件
        SXSSFWorkbook wb = new SXSSFWorkbook();
        // 设置标题样式
        CellStyle titleStyle = wb.createCellStyle();
        // 设置单元格边框样式
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
        // 设置单元格对齐方式
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // 设置字体样式
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 15); // 字体高度
        titleFont.setFontName("黑体"); // 字体样式
        titleStyle.setFont(titleFont);
        // 在Workbook中添加一个sheet,对应Excel文件中的sheet
        SXSSFSheet sheet = wb.createSheet(fileName);
        // 标题数组
        String[] titleArray = new String[excelHeader.length];
        // 字段名数组
        String[] fieldArray = new String[excelHeader.length];
        for (int i = 0; i < excelHeader.length; i++) {
//				  System.out.println(excelHeader[i]);
            String[] tempArray = excelHeader[i].split("#");// 临时数组 分割#
//			   System.out.println("length=="+tempArray.length);
            titleArray[i] = tempArray[0];
            fieldArray[i] = tempArray[1];
        }
        // 在sheet中添加标题行
        SXSSFRow row = sheet.createRow((int) 0);// 行数从0开始
//			  SXSSFCell sequenceCell = row.createCell(0);// cell列 从0开始 第一列添加序号
//			  sequenceCell.setCellValue("序号");
//			  sequenceCell.setCellStyle(titleStyle);

//			  sheet.trackAllColumnsForAutoSizing();
//			  sheet.autoSizeColumn(0);// 自动设置宽度

        // 为标题行赋值
        for (int i = 0; i < titleArray.length; i++) {
            SXSSFCell titleCell = row.createCell(i);// 0号位被序号占用，所以需+1
            titleCell.setCellValue(titleArray[i]);
            titleCell.setCellStyle(titleStyle);
            //   sheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
        }
        // 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
        CellStyle dataStyle = wb.createCellStyle();
        // 设置数据边框
        dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置居中样式
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // 设置数据字体
        Font dataFont = wb.createFont();
        dataFont.setFontHeightInPoints((short) 12); // 字体高度
        dataFont.setFontName("宋体"); // 字体
        dataStyle.setFont(dataFont);
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataList.iterator();

        int index = 0;
        while (it.hasNext()) {
            boolean type = false;
            index++;// 0号位被占用 所以+1
            row = sheet.createRow(index);
            // 为序号赋值
//			   SXSSFCell sequenceCellValue = row.createCell(0);// 序号值永远是第0列
//			   sequenceCellValue.setCellValue(index);
//			   sequenceCellValue.setCellStyle(dataStyle);
            // sheet.autoSizeColumn(0);
            T t = (T) it.next();
            // 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值

            for (int i = 0; i < fieldArray.length; i++) {
//				int flg = i+1;
                SXSSFCell dataCell = row.createCell(i);
                dataCell.setCellStyle(dataStyle);
                //  sheet.autoSizeColumn(flg);

                String[] strings = fieldArray[i].split(",");

                String fieldName = "";
                String typeName = "";
                if (strings.length == 2) {
                    fieldName = strings[0];
                    typeName = strings[1];
                } else {
                    fieldName = strings[0];
                }

                // String fieldName = fieldArray[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
                Class<? extends Object> tCls = t.getClass();// 泛型为Object以及所有Object的子类
                Method getMethod = tCls.getMethod(getMethodName, new Class[]{});// 通过方法名得到对应的方法
                Object value = getMethod.invoke(t, new Object[]{});// 动态调用方,得到属性值
                if (value != null) {
                    dataCell.setCellValue(value.toString());// 为当前列赋值

                    //根据条件单元格合并

//			     if(typeName.equals("1")){
//				     HSSFRow row1=sheet.getRow(index-1);
//						HSSFCell cell=row1.getCell((short)(flg));
//				     if(value.toString().equals(getCellStringValue(cell))){
//				    	 if(flg == 1){
//				    		 sheet.addMergedRegion(new Region((short)(index-1), (short)(flg), (short)(index), (short)(flg))); //单元格合并
//				    		 type = true;
//				    	 }else {
//				    		 if(type){
//				    			 sheet.addMergedRegion(new Region((short)(index-1), (short)(flg), (short)(index), (short)(flg)));
//				    		 }
//				    		
//				    	 }
//				     }
//			     }  

                }
            }
        }
        return wb;
    }

    public static Map<String, List<InExcel>> getExcel(InputStream inputStream) throws Exception {
        Map<String, List<InExcel>> contentMap = new HashMap<String, List<InExcel>>();
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
        // 读取第一章表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        Object value = null;
        XSSFRow row = null;
        XSSFCell cell = null;
//	 		 String[] excleTitle;
//	 		XSSFRow headerrow = sheet.getRow(0); // 表头 得到标题的内容对象
//	 		int colNum = headerrow.getPhysicalNumberOfCells();// 得到每行的列数。
//	 		excleTitle = new String[colNum];
//	 		for (int i = 0; i < colNum; i++) {
//	 			excleTitle[i] = getStringCellValue(headerrow.getCell((short) i));
//	 		}
        // System.out.println(sheet.getPhysicalNumberOfRows());
        // 循环内容项 不循环标题 所以+1
//	 		System.out.println("sheeet====="+sheet.getPhysicalNumberOfRows());
        for (int i = sheet.getFirstRowNum() + 1; i <= sheet
                .getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
//	 			String carry="";
//	 			String orderId = "";
            List<InExcel> list = new ArrayList<>();
            InExcel iexcel = new InExcel();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (null != cell) {
                    value = getStringCellValue(cell);
//	 					System.out.println("value===="+value);
                    if (MyStringUtil.isEmpty(value + "")) {
                        continue;
                    }
                }
                if (j == row.getFirstCellNum()) {
                    iexcel.setOriginalCode(value + "");
                }

                if (j == row.getFirstCellNum() + 1) {
//	 					orderId = value+"";
                    iexcel.setOrderId(value + "");
                }
                if (j == row.getFirstCellNum() + 2) {
//	 					orderId = value+"";
                    iexcel.setInsteaSupplierName(value + "");
                }
                if (j == row.getFirstCellNum() + 3) {
                    iexcel.setProductCode(value + "");
                }
                if (j == row.getFirstCellNum() + 4) {
                    iexcel.setProductName(value + "");
                }
                if (j == row.getFirstCellNum() + 5) {
                    iexcel.setInsteaPrice(new BigDecimal(value + ""));
                }
                if (j == row.getFirstCellNum() + 6) {
                    iexcel.setMerchantExpressNbr(value + "");
                }
                if (j == row.getFirstCellNum() + 7) {
                    iexcel.setExpressName(value + "");
                }
//	 				list.add(iexcel);
            }
            if (contentMap.get(iexcel.getOriginalCode()) != null) {
                list = contentMap.get(iexcel.getOriginalCode());
                list.add(iexcel);
                contentMap.put(iexcel.getOriginalCode(), list);
            } else {
                if (iexcel.getOriginalCode() != null) {
                    list.add(iexcel);
                    contentMap.put(iexcel.getOriginalCode(), list);
                }

            }


        }
        xwb.close();
        return contentMap;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell
     * @return
     */
    public static String getStringCellValue(XSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 通用读取Excel表格列数据，返回字符串
     */
    public static String parseExcel(Cell cell) {
        String result = new String();
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    if ((int) value != value) {
                        return String.valueOf(value);
                    }
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
            default:
                result = "";
                break;
        }
        return result;
    }


}
