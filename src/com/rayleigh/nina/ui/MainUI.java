package com.rayleigh.nina.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.rayleigh.nina.bean.DocBean;
import com.rayleigh.nina.util.XmlUtil;

public class MainUI extends JDialog {

	private JFrame frmD;
	private ConsoleLogUI consoleLog;
	private JFileChooser fc = new JFileChooser();
	private JTextField rootTf;
	private XmlUtil docUtil;
	private DocBean docBean;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmD.setLocationRelativeTo(null);
					window.frmD.setResizable(false);
					window.frmD.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmD = new JFrame();
		frmD.getContentPane().setBackground(Color.WHITE);
		frmD.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		frmD.setTitle("晓晓专用辅助工具");
		frmD.setBounds(100, 100, 840, 672);
		frmD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmD.setJMenuBar(menuBar);

		consoleLog = new ConsoleLogUI();
		consoleLog.setSize(884, 560);
		consoleLog.setLocation(0, 63);
		frmD.getContentPane().add(consoleLog);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					HelpDialogUI dialog = new HelpDialogUI();
					dialog.setModal(true);
					dialog.setResizable(false);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnHelp.add(mntmAbout);
		frmD.getContentPane().setLayout(null);

		JLabel label = new JLabel("文件路径：");
		label.setFont(new Font("宋体", Font.BOLD, 14));
		label.setBounds(24, 22, 75, 16);
		frmD.getContentPane().add(label);

		docBean = new DocBean();
		docUtil = new XmlUtil(docBean);
		docUtil.getIndexOfBean(0);
		
		rootTf = new JTextField();
		rootTf.setEditable(false);
		rootTf.setText(docBean.getRoot());
		rootTf.setColumns(10);
		rootTf.setBounds(109, 17, 467, 28);
		frmD.getContentPane().add(rootTf);

		JButton lookBt = new JButton("\u6D4F\u89C8");
		lookBt.setBounds(610, 16, 93, 28);
		lookBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lookUp("lookBt");
			}
		});
		frmD.getContentPane().add(lookBt);

		JButton comfitmBt = new JButton("确定");
		comfitmBt.setBounds(713, 16, 93, 28);
		frmD.getContentPane().add(comfitmBt);
	}

	//E:/Learning/helpOthers/sd/Test
	private void lookUp(String buttonName) {

		if (buttonName.equals("lookBt")) {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int intRetVal = fc.showOpenDialog(this);
			if (intRetVal == JFileChooser.APPROVE_OPTION) {
				rootTf.setText(fc.getSelectedFile().getPath()
						.replace("\\", "/"));
				docBean.setRoot(fc.getSelectedFile().getPath()
						.replace("\\", "/"));
				docUtil.modifyMsg();
			}
		}
	}
}