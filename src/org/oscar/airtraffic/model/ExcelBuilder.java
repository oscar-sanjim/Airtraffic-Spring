package org.oscar.airtraffic.model;

import java.io.OutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class ExcelBuilder extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("Content-Disposition", "attatchment;filename=logs.xlsx");
		List<Log> logList = (List<Log>)model.get("logList");
		
		Sheet s = workbook.createSheet("Excel_Test");
		
		int counter = 0;
		for (Log log : logList) {
			Row r = s.createRow(counter);
			System.out.println(log.getDate());
			r.createCell(0).setCellValue(log.getDate().toString());
			r.createCell(1).setCellValue(log.getRoute());
			r.createCell(2).setCellValue(log.getApiKey());
			counter++;
		}
		
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			
		}finally{
			outputStream.close();
		}
	}
 
}
