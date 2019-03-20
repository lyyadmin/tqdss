package byxx;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import byxx.resource.*;

public class App2 {

	public static void main(String[] args) {
		
		try {
			URL wsdlURL = new URL("http://10.128.30.99:7001/LumsSoapWs/PbResource?WSDL");
			QName SERVICE = new QName("http://resource.byxx/", "PbInterface");
			PbInterface pbInterface = new PbInterface(wsdlURL, SERVICE);
			PbResource pbResource = pbInterface.getPbResourceImplPort();
			System.out.println(new Date());
//
//			List<DriverPlanPojo> plans = pbResource.getDriverPlanByWorkNoAndTimeStr(0, "2018-03-21 00:00:00", "2018-03-22 00:00:00");
//			System.out.println(new Date());
//			System.out.println(plans.size());
			List<DriverDutyPojo> driverDutyRecordByWorkNoAndTimeStr2 = pbResource.getDriverDutyRecordByWorkNoAndTimeStr(4450152, "2018-08-20 01:57:52", "2018-09-20 01:57:52");
			for(DriverDutyPojo ddp :driverDutyRecordByWorkNoAndTimeStr2){
				System.out.println("-----------------------");
				System.out.println(ddp.getOndutytime());

				System.out.println("--------------------------");
			}

			DriverInfoPojo driverInfoByWorkNo = pbResource.getDriverInfoByWorkNo(4450246);
			List<DriverDutyPojo> driverDutyRecordByWorkNoAndTimeStr = pbResource.getDriverDutyRecordByWorkNoAndTimeStr(4450246, "2018-08-21 00:00:00", "2018-08-22 00:00:00");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			/*for (DriverDutyPojo dd:driverDutyRecordByWorkNoAndTimeStr){
				XMLGregorianCalendar ondutytime = dd.getOndutytime();
				XMLGregorianCalendar offdutytime = dd.getOffdutytime();
				if(ondutytime!=null){
					Date date = DateToXML(ondutytime);
					System.out.println();
					System.out.println("出勤地点:"+dd.getOndutycode()+"	出勤时间："+sf.format(date));
					if(offdutytime!=null){
						Date offdutytimeDate = DateToXML(offdutytime);
						System.out.println("退勤地点:"+dd.getOffdutycode()+"	退勤时间："+sf.format(offdutytimeDate));
					}

				}
			}*/
//4450246
			DriverPlanPojo dtp = pbResource.getDriverPlanByWorkNoAndTimeStrAndType(4410115, "2018-07-01 00:00:00", 1);
			System.out.print("计划开站:"+dtp.getPlandepstation()+"	");
			System.out.print("计划车次:"+dtp.getPlantrainnum()+"	");
			System.out.print("计划开车时间:"+dtp.getPlandeptime()+"	");
			System.out.print("计划入寓时间:"+dtp.getPlanlivehousetime()+"	");
			System.out.print("计划出勤时间:"+dtp.getPlanondutytime()+"	");
			System.out.print("备注:"+dtp.getNote()+"	");
			System.out.print("出勤点:"+dtp.getOndutycode()+"	");
			System.out.print("退勤点:"+dtp.getOffdutycode()+"	");
			System.out.print("交路ID:"+dtp.getTaskdicid()+"	");
			System.out.print("实际开车车次:"+dtp.getRealdeptrainnum()+"	");
			System.out.print("作业地点:"+dtp.getJobnode()+"	");

			List<DriverDutyPojo> driverDutyRecordByWorkNoAndTimeStr1 = pbResource.getDriverDutyRecordByWorkNoAndTimeStr(4410115, "2018-07-01 00:00:00", "2018-09-01 00:00:00");

			for(DriverDutyPojo dd:driverDutyRecordByWorkNoAndTimeStr1){
				System.out.println(dd.getTaskName());
			}


			//有问题  测酒数据
			/*List<DriverAlcoholPojo> driverAlcoholByWorkNoAndTime = pbResource.getDriverAlcoholByWorkNoAndTime(4450246, convertToXMLGregorianCalendar(sf.parse("2018-08-02 00:00:00",
					new ParsePosition(0))), convertToXMLGregorianCalendar(sf.parse("2018-09-02 00:00:00", new ParsePosition(0))));
			for(DriverAlcoholPojo dl : driverAlcoholByWorkNoAndTime){
				int alcoholstate = dl.getAlcoholstate();
				System.out.println(alcoholstate);
			}*/

			/*List<DriverAlcoholPojo> driverAlcoholByWorkNoAndTimeStr = pbResource.getDriverAlcoholByWorkNoAndTimeStr(0, "2018-08-02 00:00:00", "2018-09-09 00:00:00");
			for (DriverAlcoholPojo dl:driverAlcoholByWorkNoAndTimeStr){
				if(dl.getChecktime()!=null){
					System.out.println(sf.format(DateToXML(dl.getChecktime())));
				}
			}*/


			/*int taskid = 0;
			int taskidNot0 = 0;
			int taskName = 0;
			int taskNameNotNull = 0;
			for (int i = 0; i < plans.size(); i++) {
				DriverPlanPojo pojo = plans.get(i);
				System.out.println("jobid: " + pojo.getJobid() + " trainnum:" + pojo.getPlantrainnum() + " taskid：" + pojo.getTaskdicid() + " taskName:" + pojo.getTaskName());
				if(pojo.getTaskdicid() == 0) {
					taskid ++ ;
				} else {
					taskidNot0 ++ ;
				}
				if(pojo.getTaskName() == null || pojo.getTaskName().trim().equals("")) {
					taskName++;
				} else {
					taskNameNotNull ++;
				}
			}*/
			//System.out.println("1:" + taskid + " 2:" + taskidNot0 + " 3:" + taskName + " 4:" + taskNameNotNull);

			//根据计划ID获取人员计划
			//pbResource.getDriverPlanByJobIds()
			/*List<DriverDutyPojo> driverDutyRecordByWorkNoAndTimeStr = pbResource.getDriverDutyRecordByWorkNoAndTimeStr(4450246, "2018-01-01 00:00:00", "2018-07-01 00:00:00");

			for(DriverDutyPojo buty:driverDutyRecordByWorkNoAndTimeStr){
				System.out.println(buty.getOndutytime());
				System.out.printf(buty.getOndutycode());
			}*/


		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}

	public static Date DateToXML(XMLGregorianCalendar gc) {
		GregorianCalendar ca = gc.toGregorianCalendar();
		return ca.getTime();
	}
	}
