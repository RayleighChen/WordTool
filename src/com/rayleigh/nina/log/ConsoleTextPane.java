package com.rayleigh.nina.log;

import java.awt.Color;
import java.awt.Dimension;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ConsoleTextPane extends JTextPane {
	/**
	 * 
	 */
	private static final long		serialVersionUID	= 6858592737677134616L;
	public static ConsoleTextPane	consoleTextPane		= null;

	public static synchronized ConsoleTextPane getInstance() {
		if (consoleTextPane == null) {
			consoleTextPane = new ConsoleTextPane();
		}

		return consoleTextPane;
	}

	private ConsoleTextPane() {

		// Set up System.out
		PrintStream mySystemOut = new MyPrintStream(System.out, Color.BLACK);
		System.setOut(mySystemOut);

		// Set up System.err
		PrintStream mySystemErr = new MyPrintStream(System.err, Color.RED);
		System.setErr(mySystemErr);

		this.setEditable(true);
		setPreferredSize(new Dimension(640, 120));
	}

	/**
	 * Returns the number of lines in the document.
	 */
	private final int getLineCount() {
		return this.getDocument().getDefaultRootElement().getElementCount();
	}

	/**
	 * Returns the start offset of the specified line.
	 * 
	 * @param line
	 *            The line
	 * @return The start offset of the specified line, or -1 if the line is
	 *         invalid
	 */
	private int getLineStartOffset(int line) {
		Element lineElement = this.getDocument().getDefaultRootElement().getElement(line);
		if (lineElement == null)
			return -1;
		else return lineElement.getStartOffset();
	}

	/**
	 * ��������ʱǰ�����е��ַ�
	 */
	private void replaceRange(String str, int start, int end) {
		if (end < start) {
			throw new IllegalArgumentException("end before start");
		}
		Document doc = this.getDocument();
		if (doc != null) {
			try {
				if (doc instanceof AbstractDocument) {
					((AbstractDocument) doc).replace(start, end - start, str, null);
				} else {
					doc.remove(start, end - start);
					doc.insertString(start, str, null);
				}
			} catch (BadLocationException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
	}

	class MyPrintStream extends PrintStream {

		private Color	foreground; // ���ʱ����������ɫ

		/**
		 * �����Լ��� PrintStream
		 * 
		 * @param out
		 *            �ɴ��� System.out �� System.err, ʵ�ʲ�������
		 * @param foreground
		 *            ��ʾ������ɫ
		 */
		MyPrintStream(OutputStream out, Color foreground) {

			super(out, true); // ʹ���Զ�ˢ��
			this.foreground = foreground;
		}

		/**
		 * �������ؽ�,���еĴ�ӡ������Ҫ�������һ��ķ���
		 */
		@Override
		public void write(byte[] buf, int off, int len) {
			final String message = new String(buf, off, len);

			/** SWING�ǽ����̷߳�������ķ�ʽ */
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {

						StyledDocument doc = (StyledDocument) getDocument();

						// Create a style object and then set the style
						// attributes
						Style style = doc.addStyle("StyleName", null);

						// Foreground color
						StyleConstants.setForeground(style, foreground);
						StyleConstants.setFontSize(style, 12);

						doc.insertString(doc.getLength(), message, style);
					} catch (BadLocationException e) {
						// e.printStackTrace();
					}

					// Make sure the last line is always visible
					setCaretPosition(getDocument().getLength());

					// Keep the text area down to a certain line count
					int idealLine = 150;
					int maxExcess = 50;

					int excess = getLineCount() - idealLine;
					if (excess >= maxExcess) {
						replaceRange("", 0, getLineStartOffset(excess));
					}
				}
			});
		}
	}

	public void clearLog() {
		this.setText("");
	}

} // ConsoleTextArea