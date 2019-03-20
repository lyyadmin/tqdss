package byxx;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import byxx.resource.DriverDutyPojo;
import byxx.resource.DriverPlanPojo;
import byxx.resource.LocoPlanPojo;
import byxx.resource.PbInterface;
import byxx.resource.PbResource;

public class App3 {

	public static void main(String[] args) {
		
		try {
			URL wsdlURL = new URL("http://10.128.20.240:7003/LumsSoapWs/PbResource?WSDL");
			QName SERVICE = new QName("http://resource.byxx/", "PbInterface");
			PbInterface pbInterface = new PbInterface(wsdlURL, SERVICE);
			PbResource pbResource = pbInterface.getPbResourceImplPort();
			System.out.println(new Date());
			List<LocoPlanPojo> plans = pbResource.getLocoPlanByTimeStr("2018-02-22 00:00:00", "2018-02-23 00:00:00");
			System.out.println(new Date());
			System.out.println(plans.size());   
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
