package byxx.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.namespace.QName;

import byxx.resource.LocoPlanPojo;
import byxx.resource.PbInterface;
import byxx.resource.PbResource;

public class MainFrame extends JFrame {

	private JPanel mainPanel = null;
	private JPanel buttonPanel = null;
	private JTextArea jTextArea = null;
	private JButton jButton = null;
	private JScrollPane jScrollPane = null;
	
	private LoadTask loadTask = null;
	
	public SimpleDateFormat allTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public MainFrame() {
		super();
		initialize();
	}

	private void initialize() {
        this.setSize(new Dimension(320, 240));
        this.setLocationRelativeTo(null);
        this.setContentPane(getMainPanel());
	}

	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(getButtonPanel(), BorderLayout.SOUTH);
//			mainPanel.add(getJTextArea(), BorderLayout.CENTER);
			mainPanel.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return mainPanel;
	}
	
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	public JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}
	
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.setPreferredSize(new Dimension(0, 40));
			buttonPanel.add(getJButton(), gridBagConstraints);
		}
		return buttonPanel;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setMargin(new Insets(1, 1, 1, 1));
			jButton.setText("启动");
			jButton.setPreferredSize(new Dimension(60, 20));
			jButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = getJButton().getText();
					if(name.equals("启动")) {
						getJTextArea().setText("程序启动完成\n");
						getJButton().setText("退出");
						
						getLoadTask();
					} else {
						System.exit(0);
					}
				}
			});
		}
		return jButton;
	}
	
	private LoadTask getLoadTask() {
		boolean needStart=false;
		if(loadTask == null) {
			loadTask = new LoadTask();
			needStart = true;
		}
		if(needStart) {
			Timer loadTimer = new Timer("读取文件线程");
			loadTask.tmr = loadTimer;
			loadTimer.schedule(loadTask, 1000, 1000 * 60 * 5);
		}
		
		return loadTask;
	}
	
	private class LoadTask extends TimerTask {
		
		private Timer tmr=null;
		
		public void run() {

			try {
				Calendar now = Calendar.getInstance();
				now.setTimeInMillis(System.currentTimeMillis());
				
				System.out.println("线程执行，当前时间:" + now.getTime());
				
				Date nowDate = now.getTime();
				
				Calendar bTime = Calendar.getInstance();
				bTime.setTime(nowDate);
				bTime.add(Calendar.HOUR_OF_DAY, -1);
				
				Calendar eTime = Calendar.getInstance();
				eTime.setTime(nowDate);
				eTime.add(Calendar.HOUR_OF_DAY, 10);
				
				String bTimeStr = allTimeFormat.format(bTime.getTime());
				String eTimeStr = allTimeFormat.format(eTime.getTime());
				
				System.out.println("线程执行，开始时间:" + bTimeStr + " 结束时间:" + eTimeStr);
				
				URL wsdlURL = new URL("http://10.128.2.229:7002/LumsSoapWs/PbResource?WSDL");
				QName SERVICE = new QName("http://resource.byxx/", "PbInterface");
				PbInterface pbInterface = new PbInterface(wsdlURL, SERVICE);
				PbResource pbResource = pbInterface.getPbResourceImplPort();

				List<LocoPlanPojo> list = pbResource.getLocoPlanByDispLocoCodeAndTimeStr("合肥东整备场", bTimeStr, eTimeStr);
				
				if(list != null) {
					System.out.println("从接口获取的数据" + list.size());
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < list.size(); i++) {
						LocoPlanPojo pojo = list.get(i);
						sb.append("第" + i + "条");
						sb.append("jobid:" + pojo.getJobid());
						sb.append("车次" + pojo.getPlantrainnum());
						sb.append("开点" + pojo.getPlandeptime());
						sb.append("\n");
					}
					System.out.println(sb.toString());
				} else {
					System.out.println("list=null");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
