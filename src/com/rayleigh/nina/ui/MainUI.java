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
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.rayleigh.nina.bean.DocBean;
import com.rayleigh.nina.bean.OutlineBean;
import com.rayleigh.nina.log.ConsoleTextPane;
import com.rayleigh.nina.util.FileUtil;
import com.rayleigh.nina.util.OutLineUtil;
import com.rayleigh.nina.util.XmlUtil;
import com.rayleigh.nina.util.WordUtil;

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
					for (int i = 0; i < 1000; i++) {
						System.err.println(i);
					}
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
		rootTf.setBounds(109, 17, 427, 28);
		frmD.getContentPane().add(rootTf);

		JButton lookBt = new JButton("\u6D4F\u89C8");
		lookBt.setBounds(549, 16, 79, 28);
		lookBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lookUp("lookBt");
			}
		});
		frmD.getContentPane().add(lookBt);

		JButton comfitmBt = new JButton("确定");
		comfitmBt.setBounds(638, 16, 76, 28);
		comfitmBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				process();

			}
		});
		frmD.getContentPane().add(comfitmBt);

		JButton btnpdf = new JButton("PDF");
		btnpdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toPdf();
			}
		});
		btnpdf.setBounds(739, 5, 75, 26);
		frmD.getContentPane().add(btnpdf);

		JButton button = new JButton("\u5355\u9875");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getOnePageDocs();
			}
		});
		button.setBounds(739, 35, 75, 25);
		frmD.getContentPane().add(button);
	}

	// E:/Learning/helpOthers/sd/Test
	private void lookUp(String buttonName) {

		if (buttonName.equals("lookBt")) {
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
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

	private void getOnePageDocs() {
		ConsoleTextPane.getInstance().clearLog();
		WordUtil wd = new WordUtil(false);
		String foldeRoot = docBean.getRoot().substring(0,
				docBean.getRoot().lastIndexOf('.'))
				+ "/拆分后/";
		wd.setSaveOnExit(false);
		File file = new File(foldeRoot);

		String[] folders = file.list();

		for (int i = 0; i < folders.length; i++) {
			File files = new File(foldeRoot + folders[i] + "/word文件");
			String[] fileList = files.list();

			String docPath = null;
			for (int j = 0; j < fileList.length; j++) {
				docPath = foldeRoot + folders[i] + "/word文件/" + fileList[j];
				wd.openDocument(docPath.replace("/", "\\"));

				String result = "";
				int k = 0;
				while (k < 3) {
					int lastFirst = docPath.lastIndexOf('/');
					result = docPath.substring(lastFirst) + result;
					docPath = docPath.substring(0, lastFirst);
					k++;
				}
				int numOfPages = wd.getNumOfPages();
				if (numOfPages == 1) {
					System.err.println(result.substring(1) + " : " + numOfPages
							+ " 页");
				} else {
					System.out.println(result.substring(1) + " : " + numOfPages
							+ " 页");
				}
			}

		}
		wd.closeDocument();
		wd.close();

	}

	private void toPdf() {
		ConsoleTextPane.getInstance().clearLog();
		WordUtil pdf = new WordUtil(false);
		String foldeRoot = docBean.getRoot().substring(0,
				docBean.getRoot().lastIndexOf('.'))
				+ "/拆分后/";

		File file = new File(foldeRoot);

		String[] folders = file.list();

		for (int i = 0; i < folders.length; i++) {
			File files = new File(foldeRoot + folders[i] + "/word文件");
			String[] fileList = files.list();
			for (int j = 0; j < fileList.length; j++) {
				String docPath = foldeRoot + folders[i] + "/word文件/"
						+ fileList[j];
				String pdfPath = foldeRoot
						+ folders[i]
						+ "/PDF文件/"
						+ fileList[j]
								.substring(0, fileList[j].lastIndexOf('.'))
						+ ".pdf";
				pdf.toPdf(docPath.replace("/", "\\"),
						pdfPath.replace("/", "\\"));
			}
		}
		pdf.closeDocument();
		pdf.close();
	}

	private void process() {
		ConsoleTextPane.getInstance().clearLog();
		String root = docBean.getRoot().substring(0,
				docBean.getRoot().lastIndexOf('/'));
		String initFolder = docBean.getRoot().substring(0,
				docBean.getRoot().lastIndexOf('.'));
		String filename = root + "/outline.txt";

		ArrayList<OutlineBean> outlins = new OutLineUtil()
				.readFileByLines(filename);
		FileUtil.mkdir(initFolder);
		FileUtil.mkdir(initFolder + "/拆分后");
		FileUtil.mkdir(initFolder + "/原稿");
		FileUtil.copy(
				docBean.getRoot(),
				initFolder
						+ "/原稿/"
						+ docBean.getRoot().substring(
								docBean.getRoot().lastIndexOf('/'),
								docBean.getRoot().length()));
		for (int i = 0; i < outlins.size(); i++) {
			if (outlins.get(i).isFold()) {
				FileUtil.mkdir(initFolder + "/拆分后/" + outlins.get(i).getName());
				FileUtil.mkdir(initFolder + "/拆分后/" + outlins.get(i).getName()
						+ "/PDF文件");
				FileUtil.mkdir(initFolder + "/拆分后/" + outlins.get(i).getName()
						+ "/word文件");
			}
			if (outlins.get(i).isFile()) {
				FileUtil.copy(root + "/文件模板.doc", initFolder + "/拆分后/"
						+ outlins.get(i).getParent() + "/word文件/"
						+ outlins.get(i).getName() + ".doc");
			}
		}
		System.out.println("基础文件生成完毕！");
	}
}