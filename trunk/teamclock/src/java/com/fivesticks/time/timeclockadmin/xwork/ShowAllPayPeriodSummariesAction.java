/*
 * Created on Sep 28, 2004 by Reid
 */
package com.fivesticks.time.timeclockadmin.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.fivesticks.common.poi.ExcelSupport;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.timeclock.UserShiftDisplayWrapper;
import com.fivesticks.time.timeclock.util.PayPeriodDeterminator;
import com.fivesticks.time.timeclock.util.TimeclockDetailDisplayWrapper;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummary;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummaryBuilder;
import com.fstx.stdlib.common.simpledate.DatePair;

/**
 * @author Reid
 */
public class ShowAllPayPeriodSummariesAction extends SessionContextAwareSupport {



    private Collection allUserPayPeriods;

    private String targetPeriod;

    private DatePair startStop;

    private Date targetDate;
    
    private boolean showDetail;

    private HSSFWorkbook workbook;
    /**
     * @return Returns the showDetail.
     */
    public boolean isShowDetail() {
        return showDetail;
    }

    /**
     * @param showDetail The showDetail to set.
     */
    public void setShowDetail(boolean showDetail) {
        this.showDetail = showDetail;
    }

    public String execute() throws Exception {

        if (this.getTargetDate() == null && this.getTargetPeriod() == null) {
            return INPUT;
        }

        PayPeriodDeterminator ppd = new PayPeriodDeterminator();

        /*
         * reid 2005-11-09 Target date doesn't need to be resolved.
         */
        this.setStartStop(ppd.getBySessionTypeAndDate(this.getSessionContext(),
                this.getTargetPeriod(), this.getTargetDate()));

        this.setAllUserPayPeriods(new UserPayPeriodSummaryBuilder()
                .buildResolvedForAllActiveInternalUsers(this.getSessionContext()
                        .getSystemOwner(), this.getStartStop()));

        return SUCCESS;
    }

    /**
     * @return Returns the allUserPayPeriods.
     */
    public Collection getAllUserPayPeriods() {
        return allUserPayPeriods;
    }

    /**
     * @param allUserPayPeriods
     *            The allUserPayPeriods to set.
     */
    public void setAllUserPayPeriods(Collection allUserPayPeriods) {
        this.allUserPayPeriods = allUserPayPeriods;
    }


    /**
     * @return Returns the targetDate.
     */
    public Date getTargetDate() {
        return targetDate;
    }

    /**
     * @param targetDate
     *            The targetDate to set.
     */
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * @return Returns the targetPeriod.
     */
    public String getTargetPeriod() {
        return targetPeriod;
    }

    /**
     * @param targetPeriod
     *            The targetPeriod to set.
     */
    public void setTargetPeriod(String targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    /**
     * @return Returns the startStop.
     */
    public DatePair getStartStop() {
        return startStop;
    }

    /**
     * @param startStop
     *            The startStop to set.
     */
    public void setStartStop(DatePair startStop) {
        this.startStop = startStop;
    }

    /**
     * @return Returns the workbook.
     */
    public HSSFWorkbook getWorkbook() {
        if (this.workbook == null)
            handleBuildWorkbook();
        
        return workbook;
    }

    /**
     * @param workbook The workbook to set.
     */
    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }
    
    private void handleBuildWorkbook() {
        
        ExcelSupport excelSupport = new ExcelSupport();
        
        HSSFRow sheetTitle = excelSupport.addRow();
        excelSupport.decorateRowWithHeadlineCell(0,sheetTitle, "Time Clock Detail Report");
        
        excelSupport.addRow();
        
        HSSFRow totalsHeader = excelSupport.addRow();
        excelSupport.decorateRowWithCell(0,totalsHeader, "Period Totals");
        
        HSSFRow hoursHeader = excelSupport.addRow();
        HSSFRow breakHeader = excelSupport.addRow();
        
        Collection hoursTotals = new ArrayList();
        Collection breakTotals = new ArrayList();
        
        excelSupport.addRow();
        
        for (Iterator iter = this.getAllUserPayPeriods().iterator(); iter.hasNext();) {
            UserPayPeriodSummary element = (UserPayPeriodSummary) iter.next();
            
            HSSFRow employeeName = excelSupport.addRow();
            excelSupport.decorateRowWithCell(0,employeeName, element.getUser().getUsername());
            
            excelSupport.addRow();
            
            HSSFRow titles = excelSupport.addRow();
            excelSupport.decorateRowWithCell(0, titles, "Date");
            excelSupport.decorateRowWithCell(1, titles, "Status");
            excelSupport.decorateRowWithCell(2, titles, "Time");
            excelSupport.decorateRowWithCell(3, titles, "Break Time");
            
            
            if (this.isShowDetail()) {
                excelSupport.decorateRowWithCell(4, titles, "Detail");    
            }
            
            int startRow = 0;
            int stopRow = 0;
            
            startRow = titles.getRowNum();//no minusing 1 because I'm starting on the next row
            
            for (Iterator iterator = element.getDisplayableShifts().iterator(); iterator.hasNext();) {
                UserShiftDisplayWrapper shiftElement = (UserShiftDisplayWrapper) iterator.next();
                
                HSSFRow shiftRow = excelSupport.addRow();
                excelSupport.decorateRowWithCell(0, shiftRow, shiftElement.getShiftStart());
                excelSupport.decorateRowWithCell(1, shiftRow, shiftElement.getStatus());
                excelSupport.decorateRowWithNumericCell(2, shiftRow, ((double)shiftElement.getCurrent().getShiftMinutes()) / 60.0);
                excelSupport.decorateRowWithNumericCell(3, shiftRow, ((double)shiftElement.getCurrent().getShiftBreakMinutes()) / 60.0);
                
                if (this.isShowDetail()) {
                    StringBuffer detail = new StringBuffer();
                    for (Iterator eventIter = shiftElement.getDisplayableEvents().iterator(); eventIter
                            .hasNext();) {
                        TimeclockDetailDisplayWrapper eventElement = (TimeclockDetailDisplayWrapper) eventIter.next();
                        
                        detail.append("" + eventElement.getFormattedEventTimestamp() + " (" + eventElement.getFormattedEvent() + ")");
                        
                    }
                    
                    excelSupport.decorateRowWithCell(4, shiftRow, detail.toString());
                }
                
                stopRow = shiftRow.getRowNum() + 1;
            }
            
            HSSFRow totals = excelSupport.addRow();
            excelSupport.decorateRowWithCell(1,totals, "Totals");
            
            HSSFCell cell = totals.createCell((short) 2);
            cell.setCellStyle(excelSupport.numericCellStyle);
            cell.setCellFormula("sum(c" + startRow + ":c" + stopRow + ")");
            
            HSSFCell cellBreak = totals.createCell((short) 3);
            cellBreak.setCellStyle(excelSupport.numericCellStyle);
            cellBreak.setCellFormula("sum(d" + startRow + ":d" + stopRow + ")");
            
            excelSupport.addRow();
            
            hoursTotals.add("c" + (totals.getRowNum() + 1));
            breakTotals.add("d" + (totals.getRowNum() + 1));
        }
        
        excelSupport.decorateRowWithCell(1,hoursHeader, "Hours");
        HSSFCell gtHours = hoursHeader.createCell((short)2);
        gtHours.setCellStyle(excelSupport.numericCellStyle);
        gtHours.setCellFormula(buildTotalFormula(hoursTotals));

        excelSupport.decorateRowWithCell(1,breakHeader, "Break");
        HSSFCell brkHours = breakHeader.createCell((short)2);
        brkHours.setCellStyle(excelSupport.numericCellStyle);
        brkHours.setCellFormula(buildTotalFormula(breakTotals));

        excelSupport.getSheet().setColumnWidth((short)0, (short)5000);
        excelSupport.getSheet().setColumnWidth((short)1, (short)5000);
        
        
        this.workbook = excelSupport.getWorkbook();
        
        
    }
    
    private String buildTotalFormula(Collection cells) {
        StringBuffer ret = new StringBuffer();
        for (Iterator iter = cells.iterator(); iter.hasNext();) {
            String element = (String) iter.next();
            
            if (ret.length() > 0) {
                ret.append( " + ");
            }
            
            ret.append(element);
            
        }        
        return ret.toString();
    }
}