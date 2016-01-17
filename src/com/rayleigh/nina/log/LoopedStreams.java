package com.rayleigh.nina.log;

import java.io.*;

public class LoopedStreams {
	private PipedOutputStream pipedOS = new PipedOutputStream();
	private boolean keepRunning = true;
	private ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream() {
		public void close() {
			keepRunning = false;
			try {
				super.close();
				pipedOS.close();
			} catch (IOException e) {
				// 记录错误或其他处�?
				// 为简单计，此处我们直接结�?
				System.exit(1);
			}
		}
	};

	private PipedInputStream pipedIS = new PipedInputStream() {
		public void close() {
			keepRunning = false;
			try {
				super.close();
			} catch (IOException e) {
				// 记录错误或其他处�?
				// 为简单计，此处我们直接结�?
				System.exit(1);
			}
		}
	};

	public LoopedStreams() throws IOException {
		pipedOS.connect(pipedIS);
		startByteArrayReaderThread();
	} // LoopedStreams()

	public InputStream getInputStream() {
		return pipedIS;
	} // getInputStream()

	public OutputStream getOutputStream() {
		return byteArrayOS;
	} // getOutputStream()

	private void startByteArrayReaderThread() {
		new Thread(new Runnable() {
			public void run() {
				while (keepRunning) {
					// �?��流里面的字节�?
					if (byteArrayOS.size() > 0) {
						byte[] buffer = null;
						synchronized (byteArrayOS) {
							buffer = byteArrayOS.toByteArray();
							byteArrayOS.reset(); // 清除缓冲�?
						}
						try {
							// 把提取到的数据发送给PipedOutputStream
							pipedOS.write(buffer, 0, buffer.length);
						} catch (IOException e) {
							// 记录错误或其他处�?
							// 为简单计，此处我们直接结�?
							System.exit(1);
						}
					} else
						// 没有数据可用，线程进入睡眠状�?
						try {
							// 每隔1秒查看ByteArrayOutputStream�?��新数�?
							Thread.sleep(100);
						} catch (InterruptedException e) {
						}
				}
			}
		}).start();
	} // startByteArrayReaderThread()
} // LoopedStreams
