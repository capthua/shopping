package com.shopping.common.utils.doc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Excel工具类
 *
 * @author capthua
 */
@Slf4j
public class ExcelUtils {

    public static final String DEFAULT_SHEET_NAME="Sheet1";
    public static final String XLS="xls";
    public static final String XLSX="xlsx";

    /**
     * 给行的指定列创建单元格
     *
     * @param row         excel中的行
     * @param columnIndex 列索引
     * @param value       单元格的值
     * @param style       单元格样式
     */
    static void fillCell(Row row, int columnIndex, Object value, CellStyle style) {
        if(value==null){
            return;
        }
        Cell cell = row.createCell(columnIndex);
        if(style!=null){
            cell.setCellStyle(style);
        }
        if (value instanceof String){
            cell.setCellValue((String) value);
        } else if(value instanceof Boolean){
            cell.setCellValue((boolean) value);
        } else if(value instanceof Double){
            cell.setCellValue((Double) value);
        } else if(value instanceof Date){
            cell.setCellValue((Date) value);
        } else if( value instanceof RichTextString){
            cell.setCellValue((RichTextString) value);
        } else if(value instanceof Calendar){
            cell.setCellValue((Calendar) value);
        } else {
            throw new IllegalArgumentException("value类型不正确");
        }

    }

    /**
     * 给行的指定列创建单元格
     *
     * @param row         excel中的行
     * @param columnIndex 列索引
     * @param value       单元格的值
     */
    static void fillCell(Row row, int columnIndex, Object value) {
        fillCell(row, columnIndex, value, null);
    }

    static void fillRow(Row row, List<Object> data){
        fillRow(row,data,null);
    }


    static void fillRow(Row row, List<Object> data, CellStyle style){
        for(int i=0;i<data.size();i++){
            fillCell(row,i,data.get(i),style);
        }
    }

    /**
     *
     * @param sheet
     * @param rowStartIndex 从sheet的哪一行开始
     * @param data 填充的数据
     * @param style
     */
    static void fillSheet(Sheet sheet,int rowStartIndex, List<List<Object>> data,CellStyle style){
        for(int i=0;i<data.size();i++){
            List<Object> rowData=data.get(i);
            Row row = sheet.createRow(rowStartIndex);
            fillRow(row,rowData,style);
            rowStartIndex++;
        }
    }

    /**
     * 填充workbook
     * @param workbook
     * @param sheetName
     * @param rowIndex
     * @param data
     * @param style
     */
    static void fillWorkbook(Workbook workbook,String sheetName, int rowIndex,List<List<Object>> data,CellStyle style){
        if(StringUtils.isBlank(sheetName)){
            sheetName=DEFAULT_SHEET_NAME;
        }
        Sheet sheet=workbook.getSheet(sheetName);
        fillSheet(sheet,rowIndex,data,style);
    }

    static void fillWorkbook(Workbook workbook,String sheetName,List<List<Object>> data){
        fillWorkbook(workbook,sheetName,0,data,null);
    }

    public static Workbook fillWorkbook(InputStream is,List<List<Object>> data,String filenameExtension){
        Workbook workbook=null;
        if(filenameExtension.equals(XLS)){
            try {
                POIFSFileSystem fileSystem = new POIFSFileSystem(is);
                workbook=new HSSFWorkbook(fileSystem);
                fillWorkbook(workbook,null,data);
            } catch (IOException e) {
                log.info("解析excel文件出错",e);
                throw new RuntimeException("解析excel文件出错");
            }
        } else {
            throw new IllegalArgumentException("不支持的文件格式:"+filenameExtension);
        }
        return workbook;
    }


}
