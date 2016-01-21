package com.rayleigh.nina.log;

import java.awt.Container;

import javax.swing.JFrame;

import com.rayleigh.nina.ui.ConsoleLogUI;


public class ConsoleTest extends JFrame {
	private static final long serialVersionUID = -2778972378710074697L;
	private ConsoleLogUI consoleLog;

	public ConsoleTest() {
		super("AutoTool");
		consoleLog = new ConsoleLogUI();
	}

	public void display() {
		Container c = this.getContentPane();
		c.setLayout(null);

		consoleLog.setSize(830, 540);
		consoleLog.setLocation(10, 10);
		this.add(consoleLog);

		this.setSize(850, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {


		new ConsoleTest().display();
		for (int i = 0; i < 1000; ++i) {
			System.out
					.println("����ţ�20141013001\nLocked: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\n��ʼ��������");
			System.out
					.println("����ţ�INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\nBack: T01_PTY_IDENT_INFO_H_S12(�����˼�����Ϣ��ʷ).xls\n�������д��");
			System.err
					.println("����ţ�ERROR20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\nBack: T01_PTY_IDENT_INFO_H_S12(�����˼�����Ϣ��ʷ).xls\n�������д��");
			System.out
					.println("����ţ�INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\nBack: T01_PTY_IDENT_INFO_H_S12(�����˼�����Ϣ��ʷ).xls\n�������д��");
			System.out.println("��ʼ�����б����\n���Ե�....\n��ʼ�����б�����");
			System.out
					.println("����ţ�INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\nBack: T01_PTY_IDENT_INFO_H_S12(�����˼�����Ϣ��ʷ).xls\n�������д��");
			System.err
					.println("����ţ�ERROR20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\nBack: T01_PTY_IDENT_INFO_H_S12(�����˼�����Ϣ��ʷ).xls\n�������д��");
			System.out
					.println("����ţ�INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\nBack: T01_PTY_IDENT_INFO_H_S12(�����˼�����Ϣ��ʷ).xls\n�������д��");
			System.out
					.println("����ţ�INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\nBack: T01_PTY_IDENT_INFO_H_S12(�����˼�����Ϣ��ʷ).xls\n�������д��");
			System.err
					.println("����ţ�ERROR20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\nBack: T01_PTY_IDENT_INFO_H_S12(�����˼�����Ϣ��ʷ).xls\n�������д��");
			System.out
					.println("����ţ�INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(����Э���ϵ��ʷ).xls\nBack: T01_PTY_IDENT_INFO_H_S12(�����˼�����Ϣ��ʷ).xls\n�������д��");
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		ConsoleTextPane.getInstance().clearLog();
	}
}
