package byxx.config;

public class ConfigDTO {
	
	private String client_id = null;	//短信服务器ClientID,用于接收消息发送短信
	private String url = null;			//服务器地址
	private String dbType = null;		//数据库类型 	0:Oracle;1:Sql Server
	private String dbUrl = null;		//数据库地址
	private String dbName = null;		//数据库名称
	private String dbUsername = null;	//数据库用户名
	private String dbPassword = null;	//数据库密码
	private String secCode = null;		//段码

	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbPassword() {
		if(dbPassword == null) {
			dbPassword = "";
		}
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getSecCode() {
		return secCode;
	}
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
}
