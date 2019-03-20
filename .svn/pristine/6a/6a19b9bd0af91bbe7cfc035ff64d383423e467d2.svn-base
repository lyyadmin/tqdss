package byxx.cxf.utils;

import java.lang.reflect.Field;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import byxx.resource.PbInterface;
import byxx.resource.PbResource;

import com.tenly.common.projecttools.Util;

public class CXFUtils {
	public static PbResource getPbResource(){
		PbResource pbResource=null;
		try{
			URL wsdlURL = new URL("http://10.128.30.99:7001/LumsSoapWs/PbResource?WSDL");
			QName SERVICE = new QName("http://resource.byxx/", "PbInterface");
			PbInterface pbInterface = new PbInterface(wsdlURL, SERVICE);
			pbResource = pbInterface.getPbResourceImplPort();
		}catch(Exception e){
			e.printStackTrace();
		}
		return pbResource;
	}
	public static Date DateToXML(XMLGregorianCalendar gc) {
		GregorianCalendar ca = gc.toGregorianCalendar();
		return ca.getTime();
	}

	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
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


	public static String DateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
		
	}
	
	/**
	 * 向前推行月份
	 * @param month
	 * @return
	 */
	public static String getBeforMonth(int month){
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		//获取系统当前日期
		String systemCurrentDate = Util.getSystemCurrentDate();
		try {
			Date parse = sdf.parse(systemCurrentDate);
			calendar.setTime(parse);
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-month);
			Date updateDate2 = calendar.getTime();
			systemCurrentDate = sdf.format(updateDate2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return systemCurrentDate;
	}
	
	public static String getDateFormat(String dt){
		String[] splits = dt.split(" ");
		String replaceAll = splits[0].replaceAll("-", "").trim();
		return replaceAll;
	}
	/**
     * 获取利用反射获取类里面的值和名称
     *把实体bean对象转换为map对象
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
}
