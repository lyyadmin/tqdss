package byxx.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.Properties;
import javax.swing.JOptionPane;

public class LoadConfig {
	
	private static LoadConfig instance = null;
	
	private Properties proIni = null;
	public static final String conFile = "config.ini";
	private ConfigDTO configDto = null;
	
	private LoadConfig() {
		
	}
	
	public static LoadConfig getInstance() {
		if(instance == null)
		{
			instance = new LoadConfig();
		}
		return instance;
	}

	public Properties getProIni() {
		if (proIni == null) {
			proIni = readIni(conFile);
		}
		return proIni;
	}

	/**
	 * 读文件置入属性列表.
	 */
	private Properties readIni(String filePath) {
		Properties info = new Properties();
		BufferedReader filereader = null;
		String tmp = null, key, value = "";
		boolean multiLine = false;
		try {
			filereader = new BufferedReader(new FileReader(filePath));
			while ((tmp = filereader.readLine()) != null) {
				tmp = tmp.trim();
				if (tmp.length() < 1 || tmp == null) {
					continue; // 越过空行
				} else if (tmp.charAt(0) == '#' || tmp.charAt(0) == '!') { // 越过注释行
					continue;
				}
				;
				if (tmp.charAt(tmp.length() - 1) == '\\') { // 多行处理
					tmp = tmp.substring(0, tmp.length() - 1);
					if (multiLine == true) {
						value = value + tmp;
					} else {
						value = tmp;
					}
					multiLine = true;
					continue;
				}
				if (multiLine == true) {
					tmp = value + tmp;
					multiLine = false;
				}
				try {
					key = (tmp.substring(0, tmp.indexOf("="))).trim();
					value = (tmp.substring(tmp.indexOf("=") + 1)).trim();
					info.put(key, value);
					key = "";
					value = "";
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "配置文件未找到！", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
		try {
			filereader.close();
		} catch (Exception e) {
		}
		return info;
	}

	public ConfigDTO getParas() {
		if (configDto == null) {
			configDto = new ConfigDTO();
//			String tStr = null;
//			tStr = getProIni().getProperty("服务器ID");
//			if (tStr != null)
//				configDto.setClient_id(tStr);
//			tStr = getProIni().getProperty("服务器地址");
//			if (tStr != null)
//				configDto.setUrl(tStr);
//			tStr = getProIni().getProperty("数据库类型");
//			if (tStr != null)
//				configDto.setDbType(tStr);
//			tStr = getProIni().getProperty("数据库地址");
//			if (tStr != null)
//				configDto.setDbUrl(tStr);
//			tStr = getProIni().getProperty("数据库名称");
//			if (tStr != null)
//				configDto.setDbName(tStr);
//			tStr = getProIni().getProperty("数据库用户名");
//			if (tStr != null)
//				configDto.setDbUsername(tStr);
//			tStr = getProIni().getProperty("数据库密码");
//			if (tStr != null)
//				configDto.setDbPassword(tStr);
//			tStr = getProIni().getProperty("段码");
//			if (tStr != null)
//				configDto.setSecCode(tStr);
		}
		return configDto;
	}

	public void readPara() {
		getParas();
		System.out.println("配置文件加载成功" + new Timestamp(System.currentTimeMillis()));
	}
}
