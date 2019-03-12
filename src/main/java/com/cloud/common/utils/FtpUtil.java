package com.cloud.common.utils;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ftp工具类
 * 
 * @author ding
 *
 */
public class FtpUtil {

	private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

	/**
	 * 登入ftp成功
	 * 
	 */
	public static boolean ftpLogin(FTPClient ftpClient, String strIp, int intPort, String user, String password) {
		boolean isLogin = false;
		try {
			if (intPort > 0) {
				try {
					ftpClient.connect(strIp, intPort);
				} catch (UnknownHostException e) {
					throw new Exception(e.getMessage());
				}

			} else {
				ftpClient.connect(strIp);
			}
			// FTP服务器连接回答
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return isLogin;
			}
			isLogin = ftpClient.login(user, password);
			if (isLogin) {
				// 设置传输协议
				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.setBufferSize(1024 * 2);
				ftpClient.setDataTimeout(30 * 1000);
				ftpClient.setControlEncoding("UTF-8");
				// 设置被动模式
				ftpClient.enterLocalPassiveMode();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return isLogin;
	}

	/**
	 * 登入ftp成功
	 * 
	 */
	public static boolean ftpLogin(String strIp, int intPort, String user, String password) {
		boolean isLogin = false;
		try {
			FTPClient ftpClient = new FTPClient();
			if (intPort > 0) {
				ftpClient.connect(strIp, intPort);
			} else {
				ftpClient.connect(strIp);
			}
			// FTP服务器连接回答
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return isLogin;
			}
			isLogin = ftpClient.login(user, password);
			if (isLogin) {
				// 设置传输协议
				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.setBufferSize(1024 * 2);
				ftpClient.setDataTimeout(30 * 1000);
				ftpClient.setControlEncoding("UTF-8");
				// 设置被动模式
				ftpClient.enterLocalPassiveMode();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return isLogin;
	}

	/**
	 * 退出关闭服务器链接
	 * 
	 */
	public static void ftpLogOut(FTPClient ftpClient) {
		if (null != ftpClient && ftpClient.isConnected()) {
			try {
				boolean reuslt = ftpClient.logout();// 退出FTP服务器
				if (reuslt) {
					logger.info("成功退出服务器");
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.warn("退出FTP服务器异常！" + e.getMessage());
			} finally {
				try {
					ftpClient.disconnect();// 关闭FTP服务器的连接
				} catch (IOException e) {
					e.printStackTrace();
					logger.warn("关闭FTP服务器的连接异常！");
				}
			}
		}
	}
	public static boolean isExsits(FTPClient ftpClient,String ftpPath) {
		boolean ise = false;
		String fistPath = ftpPath.substring(0, ftpPath.lastIndexOf("/"));
		int one = ftpPath.lastIndexOf("/");
		String lastPath = ftpPath.substring((one+1),ftpPath.length());
		try {
			FTPFile[] files = ftpClient.listFiles(fistPath);
			for (int i = 0; i < files.length; i++) {
				if(files[i].getName().equals(lastPath)){
					ise = true;
					CommonUtils.getSucResultMap("该目录存在");
				}else{
					ise = false;
				}
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		return ise;
	}
}
