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
				.setText("晓晓专用工具：\n听说姑娘重复拆书挺辛苦的，想着做一个合理的小工具，方便自动化的使用\n这样工作效率起来了，可以做些其它愉快的事情了\n\n版权所有：Chenli，vchenli.com");
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
