package com.tenly;


import byxx.cxf.utils.CXFUtils;
import byxx.resource.DriverAlcoholPojo;
import byxx.resource.DriverDutyPojo;
import byxx.resource.PbResource;
import com.tenly.common.projecttools.Commons;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args) throws Exception{
			String startDate = "2019-02-15 19:17:01";
			String enddate = "2019-03-20 19:17:00";
	//	Sendmsg("上海天律调试信息");
//		double datePoor = Commons.getTimes(Commons.getDate(startDate), Commons.getDate(enddate));
//		System.out.println(datePoor);
//		testSendmsg();
		//----------------------------酒测数据
		PbResource pbResource = CXFUtils.getPbResource();
		List<DriverAlcoholPojo> driverAlcoholByWorkNoAndTimeStr =
				pbResource.getDriverAlcoholByWorkNoAndTimeStr(4502585,startDate,enddate);
			for (DriverAlcoholPojo driverAlcoholPojo : driverAlcoholByWorkNoAndTimeStr) {
			System.out.print("酒精测试ID:"+driverAlcoholPojo.getAlcoholid()+"\t");
			System.out.print("酒测状态:"+driverAlcoholPojo.getAlcoholstate()+"\t");
			System.out.print("酒测值:"+driverAlcoholPojo.getAlcoholvalue()+"\t");
			System.out.print("测酒岗位:"+driverAlcoholPojo.getClientid()+"\t");
			System.out.print("乘务员ID:"+driverAlcoholPojo.getDriverid()+"\t");
			System.out.print("乘务员姓名"+driverAlcoholPojo.getDriverName()+"\t");
			System.out.print("计划ID:"+driverAlcoholPojo.getJobid()+"\t");
			System.out.print("测酒时间:"+driverAlcoholPojo.getChecktime()+"\t");
			System.out.println();
		}
		//出退勤时间地点  getDriverDutyRecordByWorkNoAndTimeStr
		/**
		private int workNo;			//工号
		private String driverName;	//姓名
		private String trainNum;		//车次
		private String lowerCode;	//机调室
		private String dispLocoCode;//整备室	
		private String dispManCode;	//派班室
		private String model;		//机型
		private int loco;			//机车号
		private int taskId;			//交路ID
		private String taskName;	//交路
		private String note;			//备注
		private String team;		//车队
		private String group;		//指导组
		
		System.out.print("出勤时间："+driverDutyPojo.getOndutytime()+"\t");
				System.out.print("出勤方式："+driverDutyPojo.getOndutytype()+"\t");
				System.out.print("出勤答题分数："+driverDutyPojo.getScore()+"\t");
				System.out.print("出勤点："+driverDutyPojo.getOndutycode()+"\t");//人员计划ID
				System.out.print("人员计划ID："+driverDutyPojo.getJobid()+"\t");
				System.out.print("退勤点："+driverDutyPojo.getOffdutycode()+"\t");//offdutytime
				System.out.print("退勤时间："+driverDutyPojo.getOffdutytime()+"\t");
				System.out.print("退勤方式(0指纹，1手工)："+driverDutyPojo.getOffdutytype()+"\t");//offdutytype
				System.out.print("退勤方式(0指纹，1手工)："+driverDutyPojo.getOffdutytype()+"\t");
				//private String trainNum;		//车次
				System.out.print("车次："+driverDutyPojo.getTrainNum()+"\t");
				System.out.print("交路："+driverDutyPojo.getTaskName()+"\t");
				System.out.print("交路ID："+driverDutyPojo.getTaskId()+"\t");
				System.out.println();
		 */
		//查询徐州和上海所有的人
		/*Connection connect = null;
		connect = DBUtils.connect();
		PreparedStatement prepareStatement = null;
		PreparedStatement prepareStatement1 = null;
		 ResultSet executeQuery = null;
		 PreparedStatement prepareStatement2 = null;
		 ResultSet executeQuery2 =null;
		 int count=1;
		try{
			String driverAll = "select driver_id,count(driver_id) from dm_driver_org group by driver_id";
			 prepareStatement1 = connect.prepareStatement(driverAll);
			 executeQuery = prepareStatement1.executeQuery();
			List<Integer> driverListId = new ArrayList<Integer>();
			while (executeQuery.next()){
					Integer int1 = executeQuery.getInt("driver_id");
					driverListId.add(int1);
			}
			
			DBUtils.release(executeQuery);
			DBUtils.release(prepareStatement1);
			DBUtils.release(connect);
			connect = DBUtils.connect();
			
			String cc = "select * from driver_duty_info group by workNo";
			 prepareStatement2 = connect.prepareStatement(cc);
			 executeQuery2 = prepareStatement2.executeQuery();
			 
			List<Integer> driverListId2 = new ArrayList<Integer>();
			while (executeQuery2.next()){
					Integer int1 = executeQuery2.getInt("workNo");
					driverListId2.add(int1);
			}
			
			List<Integer> listsInteger = new ArrayList<Integer>();
			for(int c = 0;c<driverListId.size();c++){
				int m = driverListId.get(c);
				boolean s = false;
				for(int b = 0;b<driverListId2.size();b++){
					int n = driverListId2.get(b);
					if(m == n){
						s=true;
						break;
					}
				}
				if(!s){
					listsInteger.add(m);
				}
			}
			
			DBUtils.release(executeQuery2);
			DBUtils.release(prepareStatement2);
			DBUtils.release(connect);
			connect = DBUtils.connect();
			//executeQuery
			String sql = "insert into driver_duty_info(id,driverid,ondutytime,ondutytype,score,ondutycode,"
					+"examtime,jobid,offdutycode,offdutytime,offdutytype,workNo,driverName,trainNum,lowerCode,dispLocoCode,"
					+"dispManCode,model,loco,taskId,taskName,note,cargo,team,group_,driverSecCode,driverSecStr) values ("
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			 prepareStatement = connect.prepareStatement(sql);
			for(int i = 0;i<listsInteger.size();i++){
				List<DriverDutyPojo> driverDutyRecordByWorkNoAndTimeStr = 
						pbResource.getDriverDutyRecordByWorkNoAndTimeStr(listsInteger.get(i),startDate,enddate);
//				List<Integer> listJobId= new ArrayList<Integer>();
				for (DriverDutyPojo driverDutyPojo : driverDutyRecordByWorkNoAndTimeStr) {
//					listJobId.add(Math.abs(driverDutyPojo.getJobid()));
					prepareStatement.setInt(1, driverDutyPojo.getId());
					prepareStatement.setInt(2, driverDutyPojo.getDriverid());
					if(driverDutyPojo.getOndutytime()==null){
						prepareStatement.setString(3,null);
					}else{
						prepareStatement.setString(3, 
								CXFUtils.DateToString(CXFUtils.DateToXML(driverDutyPojo.getOndutytime())));
					}
					
					prepareStatement.setInt(4, driverDutyPojo.getOndutytype());
					prepareStatement.setInt(5, driverDutyPojo.getScore());
					prepareStatement.setString(6,driverDutyPojo.getOndutycode());
					
					if(driverDutyPojo.getExamtime()==null){
						prepareStatement.setString(7,null);
					}else{
						prepareStatement.setString(7, 
								CXFUtils.DateToString(CXFUtils.DateToXML(driverDutyPojo.getExamtime())));
					}
					
					
					prepareStatement.setInt(8, driverDutyPojo.getJobid());
					
					prepareStatement.setString(9, driverDutyPojo.getOffdutycode());
					if(driverDutyPojo.getOffdutytime()==null){
						prepareStatement.setString(10,null);
					}else{
						prepareStatement.setString(10,
								CXFUtils.DateToString(CXFUtils.DateToXML(driverDutyPojo.getOffdutytime())));
					}
					prepareStatement.setInt(11, driverDutyPojo.getOffdutytype());
					prepareStatement.setInt(12, driverDutyPojo.getWorkNo());
					prepareStatement.setString(13, driverDutyPojo.getDriverName());
					prepareStatement.setString(14, driverDutyPojo.getTrainNum());
					prepareStatement.setString(15, driverDutyPojo.getLowerCode());
					prepareStatement.setString(16, driverDutyPojo.getDispLocoCode());
					prepareStatement.setString(17, driverDutyPojo.getDispManCode());
					
					
					
					prepareStatement.setString(18, driverDutyPojo.getModel());
					prepareStatement.setInt(19, driverDutyPojo.getLoco());
					prepareStatement.setInt(20, driverDutyPojo.getTaskId());
					
					prepareStatement.setString(21, driverDutyPojo.getTaskName());
					prepareStatement.setString(22, driverDutyPojo.getNote());
					prepareStatement.setInt(23, driverDutyPojo.getCargo());
					prepareStatement.setString(24, driverDutyPojo.getTeam());
					prepareStatement.setString(25, driverDutyPojo.getGroup());
					prepareStatement.setString(26, driverDutyPojo.getDriverSecCode());
					prepareStatement.setString(27, driverDutyPojo.getDriverSecStr());
//					System.out.println(prepareStatement);
					prepareStatement.addBatch();
					count++;
				}
				
					prepareStatement.executeBatch();
			}
			
			DBUtils.release(prepareStatement);
			DBUtils.release(connect);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//prepareStatement
			DBUtils.release(prepareStatement);
			DBUtils.release(connect);
			//executeQuery
			DBUtils.release(executeQuery);
			DBUtils.release(prepareStatement1);
			
			DBUtils.release(executeQuery2);
			DBUtils.release(prepareStatement2);
			
			DBUtils.release(connect);
		}*/

		//获取计划
		/*DriverPlanPojo driverPlanByWorkNoAndTimeStrAndType =
				pbResource.getDriverPlanByWorkNoAndTimeStrAndType(
						4423062, "2019-01-23 12:51:35", 1);


		*//* 出退勤数据测试 */
		//System.out.println("-============================================================");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<DriverDutyPojo> driverDutyRecordByWorkNoAndTimeStr =
				pbResource.getDriverDutyRecordByWorkNoAndTimeStr(4505788, startDate, enddate);
		for(DriverDutyPojo dd:driverDutyRecordByWorkNoAndTimeStr){
			list.add(CXFUtils.objectToMap(dd));
		}
		for(Map<String,Object> o:list){
			Object ondate = o.get("ondutytime");
			Object offdate = o.get("offdutytime");
			if(ondate!=null){
				XMLGregorianCalendar dt = (XMLGregorianCalendar)ondate;
				o.put("出勤",CXFUtils.DateToString(CXFUtils.DateToXML(dt)));
			}
			if(offdate!=null){
				XMLGregorianCalendar dt = (XMLGregorianCalendar)offdate;
				o.put("退勤",CXFUtils.DateToString(CXFUtils.DateToXML(dt)));
			}
		}
		Commons.sortWithDate(list,"ondutytime","offdutytime");


		for(Map<String,Object> o:list){
			System.out.println(o);
		}
/*
		for (DriverDutyPojo driverDutyPojo:driverDutyRecordByWorkNoAndTimeStr) {
			System.out.print("出勤时间："+driverDutyPojo.getOndutytime()+"\t");
			System.out.print("出勤方式："+driverDutyPojo.getOndutytype()+"\t");
			System.out.print("出勤答题分数："+driverDutyPojo.getScore()+"\t");
			System.out.print("出勤点："+driverDutyPojo.getOndutycode()+"\t");//人员计划ID
			System.out.print("人员计划ID："+driverDutyPojo.getJobid()+"\t");
			System.out.print("退勤点："+driverDutyPojo.getOffdutycode()+"\t");//offdutytime
			System.out.print("退勤时间："+driverDutyPojo.getOffdutytime()+"\t");
			System.out.print("退勤方式(0指纹，1手工)："+driverDutyPojo.getOffdutytype()+"\t");//offdutytype
			System.out.print("退勤方式(0指纹，1手工)："+driverDutyPojo.getOffdutytype()+"\t");
			//private String trainNum;		//车次
			System.out.print("车次："+driverDutyPojo.getTrainNum()+"\t");
			System.out.print("交路："+driverDutyPojo.getTaskName()+"\t");
			System.out.print("交路ID："+driverDutyPojo.getTaskId()+"\t");
			System.out.println();
		}*/


		
		
		
		//executeQuery
		//getDriverPlanByTimeStr   获取时间范围内的人员计划
		/**
			private int jobid;					//计划ID
			private String plandepstation;		//计划开站
			private String plantrainnum;		    //计划车次
			private Date plandeptime;			//计划开车时间
			private String model;				//机型
			private int loco;					//机车号
			private String dispmancode;			//派班室
			private String lowercode;			//机调室
			private Date realdeptime;			//实际开车时间
			private Date planlivehousetime;		//计划入寓时间
			private Date planondutytime;		//计划出勤时间
			private Date plancalltime;			//计划叫班时间
			private String note;				    //备注
			private String ondutycode;			//出勤点
			private String offdutycode;			//退勤点
			private int taskdicid;				//交路ID
			private Date realcalltime;			//实际叫班时间
			private String delaycode;			//备班室
			private String housecode;			//公寓
			private String realdeptrainnum;		//实际开车车次
			private String jobnode;				//作业地点

		 */







		/*此接口已经停用
		 * List<DriverPlanPojo> driverPlanByTimeStr = pbResource.getDriverPlanByTimeStr(startDate,enddate);
		for (DriverPlanPojo driverPlanPojo : driverPlanByTimeStr) {
			System.out.print("计划开站："+driverPlanPojo.getPlandepstation()+"\t");
			System.out.print("计划车次："+driverPlanPojo.getPlantrainnum()+"\t");
			System.out.print("计划开车时间："+driverPlanPojo.getPlandeptime()+"\t");
			System.out.print("机型："+driverPlanPojo.getModel()+"\t");//人员计划ID
			System.out.print("机车号："+driverPlanPojo.getLoco()+"\t");
			System.out.print("派班室："+driverPlanPojo.getDispmancode()+"\t");//offdutytime
			System.out.print("机调室："+driverPlanPojo.getLowercode()+"\t");
			System.out.print("实际开车时间："+driverPlanPojo.getRealdeptime()+"\t");//offdutytype
			System.out.print("计划入寓时间："+driverPlanPojo.getPlanlivehousetime()+"\t");
			//private String trainNum;		//车次
			System.out.print("计划出勤时间："+driverPlanPojo.getPlanondutytime()+"\t");
			System.out.print("计划叫班时间："+driverPlanPojo.getPlancalltime()+"\t");
			System.out.print("出勤点："+driverPlanPojo.getOndutycode()+"\t");
			
			System.out.print("出勤点："+driverPlanPojo.getOndutycode()+"\t");
			System.out.print("退勤点："+driverPlanPojo.getOffdutycode()+"\t");
			System.out.print("交路ID："+driverPlanPojo.getTaskdicid()+"\t");
			System.out.print("实际叫班时间："+driverPlanPojo.getRealcalltime()+"\t");
			
			
			System.out.print("实际叫班时间："+driverPlanPojo.getRealcalltime()+"\t");
			System.out.print("备班室："+driverPlanPojo.getDelaycode()+"\t");
			System.out.print("公寓："+driverPlanPojo.getHousecode()+"\t");
			System.out.print("实际开车车次："+driverPlanPojo.getRealdeptrainnum()+"\t");
			
			System.out.print("作业地点："+driverPlanPojo.getJobnode()+"\t");
			System.out.println();
			
		}*/
		//getDriverPlanByJobIds   根据计划id获取人员计划      调取是空
		/*List<DriverPlanPojo> driverPlanByJobIds = pbResource.getDriverPlanByJobIds(listJobId);
		for (DriverPlanPojo driverPlanPojo : driverPlanByJobIds) {
			System.out.print("计划开站："+driverPlanPojo.getPlandepstation()+"\t");
			System.out.print("计划车次："+driverPlanPojo.getPlantrainnum()+"\t");
			System.out.print("计划开车时间："+driverPlanPojo.getPlandeptime()+"\t");
			System.out.print("机型："+driverPlanPojo.getModel()+"\t");//人员计划ID
			System.out.print("机车号："+driverPlanPojo.getLoco()+"\t");
			System.out.print("派班室："+driverPlanPojo.getDispmancode()+"\t");//offdutytime
			System.out.print("机调室："+driverPlanPojo.getLowercode()+"\t");
			System.out.print("实际开车时间："+driverPlanPojo.getRealdeptime()+"\t");//offdutytype
			System.out.print("计划入寓时间："+driverPlanPojo.getPlanlivehousetime()+"\t");
			//private String trainNum;		//车次
			System.out.print("计划出勤时间："+driverPlanPojo.getPlanondutytime()+"\t");
			System.out.print("计划叫班时间："+driverPlanPojo.getPlancalltime()+"\t");
			System.out.print("出勤点："+driverPlanPojo.getOndutycode()+"\t");
			
			System.out.print("出勤点："+driverPlanPojo.getOndutycode()+"\t");
			System.out.print("退勤点："+driverPlanPojo.getOffdutycode()+"\t");
			System.out.print("交路ID："+driverPlanPojo.getTaskdicid()+"\t");
			System.out.print("实际叫班时间："+driverPlanPojo.getRealcalltime()+"\t");
			
			
			System.out.print("实际叫班时间："+driverPlanPojo.getRealcalltime()+"\t");
			System.out.print("备班室："+driverPlanPojo.getDelaycode()+"\t");
			System.out.print("公寓："+driverPlanPojo.getHousecode()+"\t");
			System.out.print("实际开车车次："+driverPlanPojo.getRealdeptrainnum()+"\t");
			
			System.out.print("作业地点："+driverPlanPojo.getJobnode()+"\t");
			System.out.println();
		}*/
		
		
		
		//getDriverPlanByWorkNoAndTimeStrAndType   获取该时间之后的第一条人员计划
		/**
		 * 说明：这里因为接口只能返回该时间之后的第一条人员计划，
		 *所以需要计算出退勤时间范围的天数，然后循环去获取该时间范围的所有人员计划
		 */
		
		
//		Connection connect = DBUtils.connect();
//		PreparedStatement prepareStatement = null;
//		PreparedStatement prepareStatement1 = null;
//		 ResultSet executeQuery = null;
//		 PreparedStatement prepareStatement2 = null;
//		 ResultSet executeQuery2 =null;
//		 int count=1;
//			String driverAll = "select driver_id,count(driver_id) from dm_driver_org group by driver_id";
//			 prepareStatement1 = connect.prepareStatement(driverAll);
//			 executeQuery = prepareStatement1.executeQuery();
//			List<Integer> driverListId = new ArrayList<Integer>();
//			while (executeQuery.next()){
//					Integer int1 = executeQuery.getInt("driver_id");
//					driverListId.add(int1);
//			}
//			
//			DBUtils.release(executeQuery);
//			DBUtils.release(prepareStatement1);
//			DBUtils.release(connect);
//		int timeDistance = Commons.getTimeDistance(Commons.getDate(startDate),Commons.getDate(enddate));
//		List<DriverPlanPojo> list1 = new ArrayList<DriverPlanPojo>();
//		
		/*for(int i=0;i<=timeDistance;i++){
			DriverPlanPojo driverPlanPojo = pbResource.getDriverPlanByWorkNoAndTimeStrAndType(
					4107437,Commons.getLeiJiaDate(Commons.getDate(startDate), i),1);
			System.out.print("计划开站："+driverPlanPojo.getPlandepstation()+"\t");
			System.out.print("计划车次："+driverPlanPojo.getPlantrainnum()+"\t");
			System.out.print("计划开车时间："+driverPlanPojo.getPlandeptime()+"\t");
			System.out.print("机型："+driverPlanPojo.getModel()+"\t");//人员计划ID
			System.out.print("机车号："+driverPlanPojo.getLoco()+"\t");
			System.out.print("派班室："+driverPlanPojo.getDispmancode()+"\t");//offdutytime
			System.out.print("机调室："+driverPlanPojo.getLowercode()+"\t");
			System.out.print("实际开车时间："+driverPlanPojo.getRealdeptime()+"\t");//offdutytype
			System.out.print("计划入寓时间："+driverPlanPojo.getPlanlivehousetime()+"\t");
			//private String trainNum;		//车次
			System.out.print("计划出勤时间："+driverPlanPojo.getPlanondutytime()+"\t");
			System.out.print("计划叫班时间："+driverPlanPojo.getPlancalltime()+"\t");
			System.out.print("出勤点："+driverPlanPojo.getOndutycode()+"\t");
			
			System.out.print("出勤点："+driverPlanPojo.getOndutycode()+"\t");
			System.out.print("退勤点："+driverPlanPojo.getOffdutycode()+"\t");
			System.out.print("交路ID："+driverPlanPojo.getTaskdicid()+"\t");
			System.out.print("实际叫班时间："+driverPlanPojo.getRealcalltime()+"\t");
			
			
			System.out.print("实际叫班时间："+driverPlanPojo.getRealcalltime()+"\t");
			System.out.print("备班室："+driverPlanPojo.getDelaycode()+"\t");
			System.out.print("公寓："+driverPlanPojo.getHousecode()+"\t");
			System.out.print("实际开车车次："+driverPlanPojo.getRealdeptrainnum()+"\t");
			
			System.out.print("作业地点："+driverPlanPojo.getJobnode()+"\t");
			System.out.println();
			
		}*/
//			List<String> sls = new ArrayList<String>();
//			for(int c =0;c<driverListId.size();c++){
//				DriverPlanPojo driverPlanByWorkNoAndTimeStrAndType = 
//						pbResource.getDriverPlanByWorkNoAndTimeStrAndType(
//								driverListId.get(c),Commons.getLeiJiaDate(Commons.getDate(startDate), i),1);
//				if(driverPlanByWorkNoAndTimeStrAndType!=null){
//					String ssw = "";
//					ssw+=driverPlanByWorkNoAndTimeStrAndType.getArvStation()+"getArvStation\t";
//					ssw+=driverPlanByWorkNoAndTimeStrAndType.getArvTrain()+"getArvTrain\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getDelaycode()+"getDelaycode\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getDispmancode()+"getDispmancode\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getHousecode()+"getHousecode\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getJobnode()+"getJobnode\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getTaskdicid()+"getTaskdicid\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getRealdeptime()+"getRealdeptime\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getRealcalltime()+"getRealcalltime\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getPlanondutytime()+"getPlanondutytime\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getPlanlivehousetime()+"getPlanlivehousetime\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getPlandeptime()+"getPlandeptime\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getPlancalltime()+"getPlancalltime\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getPlantrainnum()+"getPlantrainnum\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getTaskName()+"getTaskName\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getRealdeptrainnum()+"getRealdeptrainnum\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getPlantrainnum()+"getPlantrainnum\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getPlandepstation()+"getPlandepstation\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getOndutycode()+"getOndutycode\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getOffdutycode()+"getOffdutycode\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getNote()+"getNote\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getModel()+"getModel\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getLowercode()+"getLowercode\t";
//							ssw+=driverPlanByWorkNoAndTimeStrAndType.getJobnode()+"getJobnode\t";
//							sls.add(ssw);
//				}
//			}
//			Commons.getWriteFile(sls);
//		}
		
		//测试list移除
//		List<String> lists = new ArrayList<String>();
//		for(int i= 0;i<10;i++){
//			lists.add("sssssss"+i);
//		}
//		
//		for(int k = 0;k<lists.size();k++){
//			if(lists.get(k).equals("sssssss2")){
//				lists.remove(k);
//			}
//		}
//		
//		for(int c = 0;c<lists.size();c++){
//			System.out.println(lists.get(c));
//		}
		
		
	 } 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void testSendmsg()throws Exception{
		 HttpClient client = new HttpClient();  
		 	PostMethod post = new PostMethod("http://10.128.40.98/pisp/service/sms.asmx?op=postSingle");
		       post.addRequestHeader("Content-Type","text/xml; charset=utf-8");// 在头文件中设置转码  
		        NameValuePair[] data = { new NameValuePair("account", "shtlgs"),// 注册的用户名
		                new NameValuePair("password", "123456"),// 注册成功后，登录网站后得到的密钥
		                new NameValuePair("mobile", "13472497544"),// 手机号码
		                new NameValuePair("content", "上海天律测试信息"),
		                new NameValuePair("code", "123456")};// 短信内容  
	        client.executeMethod(post);  
	        Header[] headers = post.getResponseHeaders();  
	        int statusCode = post.getStatusCode();
	        System.out.println("========>statusCode:" + statusCode);
	        String result = new String(post.getResponseBodyAsString().getBytes(  
	                "utf8"));  
	        System.out.println(result);  
		}




	/**
	 * 短信发送
	 * @param msg 短信内容
	 *
	 * @throws Exception
	 */
	private static void Sendmsg(String msg)throws Exception{
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://10.128.40.98/pisp/service/sms.asmx?op=postSingle");
		post.addRequestHeader("Content-Type","text/xml; charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("account", "shtlgs"),// 注册的用户名
				new NameValuePair("password", "123456"),// 注册成功后，登录网站后得到的密钥
				new NameValuePair("mobile", "18800382031"),// 手机号码
				//new NameValuePair("mobile", "13472497544"),// 手机号码
				new NameValuePair("content", msg),// 短信内容
				new NameValuePair("code", "E2WPM2vd")};//授权码
		post.setRequestBody(data);
		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		//返回消息
		String result = new String(post.getResponseBodyAsString().getBytes(
				"gbk"));
		System.out.println(result);
	}
	}


