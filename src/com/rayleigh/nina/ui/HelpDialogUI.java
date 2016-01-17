package com.rayleigh.nina.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HelpDialogUI extends JDialog {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8905761413268887197L;
	private final JPanel		contentPanel		= new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HelpDialogUI dialog = new HelpDialogUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HelpDialogUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JEditorPane dtrpnEtltoolsetlexcelidddletl = new JEditorPane();
		dtrpnEtltoolsetlexcelidddletl
				.setText("ETLTools:\u7528\u4E8E\u534F\u52A9\u6784\u5EFA\u6570\u636E\u4ED3\u5E93\uFF0C\u7528\u4E8E\u6570\u636E\u4ED3\u5E93\u7684ETL\u642D\u5EFA\u8FC7\u7A0B\u3002\r\n\u529F\u80FD\u5305\u62EC\uFF1A\r\n1.\u5C06\u89C4\u8303\u5316\u7684Excel\u6839\u636E\u6307\u5B9A\u6A21\u677F\u548C\u8868\u7ED3\u6784\u6279\u91CF\u5BFC\u5165\u6570\u636E\u5E93\u3002\r\n2.\u6839\u636EID\u7B80\u8981\u6570\u636E\u6620\u5C04\u6216\u8005\u8868\u751F\u6210\u6E90\u7CFB\u7EDF\u6570\u636E\u5B57\u5178\u3002\r\n3.\u6839\u636E\u6570\u636E\u5B57\u5178\u751F\u6210\u5EFA\u8868ddl\u548CETL\u914D\u7F6E\u4F5C\u4E1A\u3002\r\n4.\u6279\u91CF\u5BFC\u5165\u4EE3\u7801\u8868\u548C\u4EE3\u7801\u6620\u5C04\u8868\u3002\r\n5.SDM\u5165\u5E93\u3002\r\n6.\u6839\u636EPDM\u7CFB\u7EDF\u6765\u751F\u6210\u5BF9\u5E94\u7684DQC\u7CFB\u7EDF\uFF0C\u5E76\u751F\u6210\u914D\u7F6EDQC\u7CFB\u7EDF\u7684ETL\u4F5C\u4E1A\u3002\r\n7.\u53D8\u66F4\u6253\u5305\u3002\u53D8\u66F4\u8FC1\u51FA\u548C\u8FC1\u5165\uFF0C\u53D8\u66F4\u7248\u672C\u6821\u9A8C\uFF0C\u53D8\u66F4\u6253\u5305\u3002\r\n\u7248\u6743\u6240\u6709\uFF1ATeradata");
		dtrpnEtltoolsetlexcelidddletl.setToolTipText("");
		dtrpnEtltoolsetlexcelidddletl.setEditable(false);
		dtrpnEtltoolsetlexcelidddletl.setBounds(0, 11, 424, 207);
		contentPanel.add(dtrpnEtltoolsetlexcelidddletl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
