package com.hn.jsw.tj.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hn.jsw.util.TownsDataUtil;

public class ExcelListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			File file = new File(event.getServletContext().getRealPath("/WEB-INF/classes/村为主得分排名表.xls"));
			TownsDataUtil.initDate(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
