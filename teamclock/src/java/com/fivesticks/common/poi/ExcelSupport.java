/*
 * Created on Mar 10, 2006 by Reid
 */
package com.fivesticks.common.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 2006-03-10 RSC Helper class for all of the excel stuff we need to do.
 * 
 * @author Reid
 */
public class ExcelSupport {

    private HSSFWorkbook workbook;
    
    private HSSFSheet activeSheet;
    
    public HSSFCellStyle numericCellStyle;
    
    public HSSFCellStyle percentageCellStyle;
    
    public HSSFCellStyle decimalCellStyle;
   
    public HSSFDataFormat numericData;
   
    private HSSFCellStyle dollarStyle;
    
    private HSSFCellStyle headlineStyle;
    
    private HSSFRow currentRow;
    
    private int nextRowId;
    
    private int cellCount;
    
    public ExcelSupport() {
        super();
        workbook = new HSSFWorkbook();
 
        numericCellStyle = workbook.createCellStyle();
        numericData = workbook.createDataFormat();
        numericCellStyle.setDataFormat(numericData.getFormat("#,##0.00"));
        
        percentageCellStyle = workbook.createCellStyle();
        percentageCellStyle.setDataFormat((short) 4);
        
        decimalCellStyle = workbook.createCellStyle();
        decimalCellStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0.0#####"));
        
        
        dollarStyle = workbook.createCellStyle();
        dollarStyle.setDataFormat((short) 8);
        
        headlineStyle = workbook.createCellStyle();
        
        HSSFFont headlineFont = workbook.createFont();
//      set font 1 to 12 point type
        headlineFont.setFontHeightInPoints((short) 24);

//         make it bold
//        arial is the default font
        headlineFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headlineStyle.setFont(headlineFont);
    }

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }
    
    public HSSFSheet getSheet() {
        if (activeSheet == null)
            addNewSheet("MenuMine"); // default name
        
        return activeSheet;
    }
    
    
    public void addNewSheet(String name) {
        activeSheet = workbook.createSheet(name);
    }
    
    public HSSFRow addRow() {
        
        HSSFRow ret = this.addRow(nextRowId);
        nextRowId++;
        return ret;
    }
    
    public HSSFRow addRow(int i) {
        return this.addRow((short) i);
    }
    
    public HSSFRow addRow(short i) {
        return this.addRow(i, this.getSheet());
    }

    public HSSFRow addRow(short i, HSSFSheet sheet) {
        return sheet.createRow(i);
    }
    
    public HSSFCell getCell(int cellId, HSSFRow row) {
        HSSFCell ret = row.createCell((short)cellId);
        return ret;
    }
    public void decorateRowWithCell(int cellId, HSSFRow row, Object value) {
        this.decorateRowWithCell((short) cellId, row, value, null);
    }
    
    public void decorateRowWithCell(short cellId, HSSFRow row, Object value, HSSFCellStyle cellStyle) {
        
        HSSFCell cell = row.createCell(cellId);
        
        if (value == null) {
            
        } else if (value instanceof Double) {
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(((Double)value).doubleValue());
        } else if (value instanceof Integer) {
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(((Integer)value).intValue());
        } else {
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(value.toString());
        }
        
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
        
    }
    
    public void decorateRowWithHeadlineCell(int cellId, HSSFRow row, Object value) {
        this.decorateRowWithCell((short) cellId, row, value, headlineStyle);
    }
    
   
    
    public void decorateRowWithNumericCell(int cellId, HSSFRow row, int value) {
        this.decorateRowWithCell((short) cellId, row, new Integer(value), numericCellStyle);
    }
    
    public void decorateRowWithNumericCell(int cellId, HSSFRow row, double value) {
        this.decorateRowWithCell((short) cellId, row, new Double(value), numericCellStyle);
    }
    
    public void decorateRowWithPercentageCell(int cellId, HSSFRow row, Object value) {
        this.decorateRowWithCell((short) cellId, row, value,percentageCellStyle);
    }

    public void decorateRowWithDecimalCell(int cellId, HSSFRow row, double value) {
        this.decorateRowWithCell((short) cellId, row, new Double(value), decimalCellStyle);
    }

    public void decorateRowWithDecimalCell(int cellId, HSSFRow row, Object value) {
        this.decorateRowWithCell((short) cellId, row, value, decimalCellStyle);
    }

    /**
     * @return Returns the cellCount.
     */
    public int getCellCount() {
        return cellCount;
    }

    /**
     * @return Returns the currentRow.
     */
    public HSSFRow getCurrentRow() {
        return currentRow;
    }

    
    
}
