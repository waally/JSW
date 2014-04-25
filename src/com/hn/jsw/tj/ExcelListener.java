package com.hn.jsw.tj;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelListener implements ServletContextListener{

	public static  Dept hn = new Dept();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			File file = new File(event.getServletContext().getRealPath("/WEB-INF/classes/村为主得分排名表.xls"));
			System.out.println(file.getAbsolutePath());
			InputStream inp = new FileInputStream(file); 
	        Workbook wb = WorkbookFactory.create(inp); 
	        Sheet sheet = wb.getSheetAt(0);
	        Dept zx= new Dept();
	        int rowNum = 0;
	        for (Row row : sheet) { 
	        	rowNum++;
	        	if(rowNum<3){
	        		continue;
	        	}
	        	if(row.getCell(0).toString()==null||row.getCell(0).toString().length()==0){
	        		break;
	        	}
	        	Dept temp = new Dept();
	        	temp.setId((int)row.getCell(0).getNumericCellValue());
	        	temp.setName(row.getCell(1).toString());
	        	temp.setXh(row.getCell(4).getNumericCellValue());
	        	temp.setEdh(row.getCell(7).getNumericCellValue());
	        	temp.setWrq(row.getCell(10).getNumericCellValue());
	        	temp.setZr(row.getCell(13).getNumericCellValue());
	        	temp.setCx(row.getCell(16).getNumericCellValue());
	        	temp.setJz(row.getCell(19).getNumericCellValue());
	        	if(row.getCell(1).toString().endsWith("镇")||row.getCell(1).toString().endsWith("乡")){
	        		zx = temp;
	        		System.out.println(zx.getName()+"-"+zx.getId());
	        		hn.addSub(zx);
	        	}else{
	        		zx.addSub(temp);
	        	}
	        } 
	        inp.close(); 
	        for(Dept d1:hn.getSubs()){
	        	double sumxh = 0;
	        	double sumedh = 0;
	        	double sumwrq = 0;
	        	double sumzr = 0;
	        	double sumcx = 0;
	        	double sumjz = 0;
	        	int l = d1.getSubs().size();
	        	for(Dept d2:d1.getSubs()){
	        		sumxh+=d2.getXh();
	        		sumedh+=d2.getEdh();
	        		sumwrq+=d2.getWrq();
	        		sumzr+=d2.getZr();
	        		sumcx+=d2.getCx();
	        		sumjz+=d2.getJz();
	        	}
	        	d1.setXh(lw(sumxh/l));
	        	d1.setEdh(lw(sumedh/l));
	        	d1.setWrq(lw(sumwrq/l));
	        	d1.setZr(lw(sumzr/l));
	        	d1.setCx(lw(sumcx/l));
	        	d1.setJz(lw(sumjz/l));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	private double lw(double d){
		int i = (int)d*100;
		return i/100;
	}

}
