package cn.org.polaris.controller;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.client.core.WebServiceTemplate;

@Controller
public class IndexController {
	
	@Autowired
	private WebServiceTemplate webServiceTemplate;
	
	@RequestMapping("/index.do")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("index");
		
		System.out.println("##### begin to call web service #####");
		callWebService();
		System.out.println("##### end to call web service #####");
		
		return mv;
	}
	
	private void callWebService(){
		StringBuffer xml = new StringBuffer();
		xml.append("<HolidayRequest xmlns=\"http://mycompany.com/hr/schemas\">");
		xml.append("<HolidayType>");
		xml.append("<StartDate>");
		xml.append("2013-05-20");
		xml.append("</StartDate>");
		xml.append("<EndDate>");
		xml.append("2013-05-25");
		xml.append("</EndDate>");
		xml.append("</HolidayType>");
		xml.append("<EmployeeType>");
		xml.append("<Number>");
		xml.append("80364292");
		xml.append("</Number>");
		xml.append("<FirstName>");
		xml.append("Beck");
		xml.append("</FirstName>");
		xml.append("<LastName>");
		xml.append("Lu");
		xml.append("</LastName>");
		xml.append("</EmployeeType>");
		xml.append("</HolidayRequest>");
		
		StreamSource source = new StreamSource(new StringReader(xml.toString()));
		StreamResult result = new StreamResult(new StringWriter());
		
		webServiceTemplate.sendSourceAndReceiveToResult(source, result);
	}
}
