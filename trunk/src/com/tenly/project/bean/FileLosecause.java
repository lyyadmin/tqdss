package com.tenly.project.bean;

public class FileLosecause {
	private String driverId;
	private String loseCause; 
	private String trainNum;
	private String trainDate; 
	private String driverCheckoutTime;
	private String createDate;
	private String driverName;
	private String other;
	
	public FileLosecause() {
	}

	public FileLosecause(String driverId, String loseCause, String trainNum,
			String trainDate, String driverCheckoutTime, String createDate,
			String driverName, String other) {
		this.driverId = driverId;
		this.loseCause = loseCause;
		this.trainNum = trainNum;
		this.trainDate = trainDate;
		this.driverCheckoutTime = driverCheckoutTime;
		this.createDate = createDate;
		this.driverName = driverName;
		this.other = other;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getLoseCause() {
		return loseCause;
	}

	public void setLoseCause(String loseCause) {
		this.loseCause = loseCause;
	}

	public String getTrainNum() {
		return trainNum;
	}

	public void setTrainNum(String trainNum) {
		this.trainNum = trainNum;
	}

	public String getTrainDate() {
		return trainDate;
	}

	public void setTrainDate(String trainDate) {
		this.trainDate = trainDate;
	}

	public String getDriverCheckoutTime() {
		return driverCheckoutTime;
	}

	public void setDriverCheckoutTime(String driverCheckoutTime) {
		this.driverCheckoutTime = driverCheckoutTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
}
