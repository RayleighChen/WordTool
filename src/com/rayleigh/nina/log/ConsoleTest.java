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
					.println("变更号：20141013001\nLocked: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\n初始化变更完成");
			System.out
					.println("变更号：INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\nBack: T01_PTY_IDENT_INFO_H_S12(当事人鉴别信息历史).xls\n变更内容写回");
			System.err
					.println("变更号：ERROR20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\nBack: T01_PTY_IDENT_INFO_H_S12(当事人鉴别信息历史).xls\n变更内容写回");
			System.out
					.println("变更号：INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\nBack: T01_PTY_IDENT_INFO_H_S12(当事人鉴别信息历史).xls\n变更内容写回");
			System.out.println("初始化所有变更号\n请稍等....\n初始化所有变更完成");
			System.out
					.println("变更号：INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\nBack: T01_PTY_IDENT_INFO_H_S12(当事人鉴别信息历史).xls\n变更内容写回");
			System.err
					.println("变更号：ERROR20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\nBack: T01_PTY_IDENT_INFO_H_S12(当事人鉴别信息历史).xls\n变更内容写回");
			System.out
					.println("变更号：INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\nBack: T01_PTY_IDENT_INFO_H_S12(当事人鉴别信息历史).xls\n变更内容写回");
			System.out
					.println("变更号：INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\nBack: T01_PTY_IDENT_INFO_H_S12(当事人鉴别信息历史).xls\n变更内容写回");
			System.err
					.println("变更号：ERROR20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\nBack: T01_PTY_IDENT_INFO_H_S12(当事人鉴别信息历史).xls\n变更内容写回");
			System.out
					.println("变更号：INFOO20141013001\nBack: T03_ACC_DEV_AGMT_RELA_H_S01(介质协议关系历史).xls\nBack: T01_PTY_IDENT_INFO_H_S12(当事人鉴别信息历史).xls\n变更内容写回");
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
