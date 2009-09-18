/*
 * Created on Jan 20, 2005
 *
 * 
 */
package com.fivesticks.common.poi;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.Result;

/**
 * @author Nick
 * 
 *  
 */
public class ExcelResult implements Result {

    public void execute(ActionInvocation invocation) throws Exception {

        HSSFWorkbook workbook = (HSSFWorkbook) invocation.getStack().findValue(
                "workbook");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.addHeader("Content-Type", "application/excel");
        OutputStream os = response.getOutputStream();

        workbook.write(os);
        os.flush();
    }

}