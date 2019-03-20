//package com.tenly.test;
//
//import chrriis.common.UIUtils;
//import chrriis.dj.nativeswing.NSOption;
//import chrriis.dj.nativeswing.swtimpl.NativeInterface;
//import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class TestSwing extends JPanel {
//    public static void main(String[] args) {
//        UIUtils.setPreferredLookAndFeel();
//        NativeInterface.open();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                JFrame frame = new JFrame("退勤pc测试");
//                frame.setDefaultCloseOperation(3);
//                frame.setIconImage(Toolkit.getDefaultToolkit().createImage(
//                        "/icon.png"));
//                frame.getContentPane().add(new TestSwing(), "Center");
//                frame.setExtendedState(6);
//                frame.setDefaultCloseOperation(3);
//                frame.setLocationByPlatform(true);
//                frame.setVisible(true);
//            }
//        });
//        NativeInterface.runEventPump();
//    }
//    public TestSwing() {
//        super(new BorderLayout());
//        JPanel webBrowserPanel = new JPanel(new BorderLayout());
//        JWebBrowser webBrowser = new JWebBrowser(new NSOption[0]);
//        webBrowser.navigate("http://localhost:8090/admin/system/index.do");
//        webBrowserPanel.add(webBrowser, "Center");
//        add(webBrowserPanel, "Center");
//        webBrowser.setMenuBarVisible(false);
//        webBrowser.setLocationBarVisible(false);
//        webBrowser.setBarsVisible(false);
//        webBrowser.setOpaque(false);
//        webBrowser.setStatusBarVisible(false);
//        JPanel buttonPanel = new JPanel(new FlowLayout(1, 4, 4));
//        add(buttonPanel, "South");
//    }
//}
