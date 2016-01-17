package com.rayleigh.nina.ui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.rayleigh.nina.log.ConsoleTextPane;

public class ConsoleLogUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane shellLog;

	public ConsoleLogUI() {
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createEtchedBorder());

		JLabel title = new JLabel("输出日志");
		title.setSize(80, 20);
		title.setLocation(20, 0);
		this.add(title);

		ConsoleTextPane.getInstance().setFont(
				java.awt.Font.decode("monospaced"));
		ConsoleTextPane.getInstance().setEditable(false);
		shellLog = new JScrollPane(ConsoleTextPane.getInstance());

//		ConsoleTextArea.getInstanc()
//				.setFont(java.awt.Font.decode("monospaced"));
//		ConsoleTextArea.getInstanc().setEditable(false);
//		shellLog = new JScrollPane(ConsoleTextArea.getInstanc());

		shellLog.setSize(795, 520);
		shellLog.setLocation(15, 25);
		this.add(shellLog);
	}

}
