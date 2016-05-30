package com.dasa.communication.vo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

/**
 * @파일명: SendNotification.java
 * @작성일: 2014. 3. 17.
 * @작성자: 전진기
 * @프로그램 설명: 알림 메시지 전송
 */
public class SendNotification {
	
	@Value("#{sys_info['apns_host']}")
	private String apns_host;
	
	@Value("#{sys_info['apns_port']}")
	private int apns_port;
	
	@Value("#{sys_info['apns_path']}")
	private String apns_path;

	@Value("#{sys_info['apns_pw']}")
	private String apns_pw;

	private Sender sender;
	private Message msg;

	private static final Logger logger = LoggerFactory.getLogger(Notification.class);

	@Autowired(required = true)
	private HttpServletRequest request;

	/**
	 * @메서드명: sendNotificationToGcmServer
	 * @작성일: 2015. 1. 6.
	 * @작성자: 전진기
	 * @설명: Android 알림 메시지
	 * @param "Notification 객체"
	 * @param "알림 메시지 내용"
	 * @return "알림 여부"
	 */
	public boolean sendNotificationToGcmServer(Notification notification, String value) throws Exception {
		Message.Builder builder = null;

		try {
			sender = new Sender(Notification.getStringAndroidApiKey());
			builder = new Message.Builder();
			value = URLDecoder.decode(value, "UTF-8");
			builder.addData("contents", value);
			logger.info("###### value ####### {} " + value);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

			return false;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		msg = builder.build();

		try {
			if (notification.getStringPropRegId() != null && !notification.getStringPropRegId().equals("")) {
				Result result = sender.send(msg, notification.getStringPropRegId(), 5);
				logger.info("###### result ####### {}" + result);
			}
		} catch (IOException e) {
			e.printStackTrace();

			return false;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * @메서드명: sendNotificationToGcm
	 * @작성일: 2014. 5. 2.
	 * @작성자: 전진기
	 * @설명: Android 알림 메시지
	 * @param notification
	 * @return "알림 여부"
	 */
	public boolean sendNotificationToGcm2(Notification notification, String approveCount) throws Exception {
		Message.Builder builder = null;

		try {
			sender = new Sender(Notification.getStringAndroidApiKey());
			builder = new Message.Builder();
			String value = "";
			value = approveCount;
			value = URLDecoder.decode(value, "UTF-8");
			builder.addData("contents", value);
			logger.info("###### value ####### {} " + value);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

			return false;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		msg = builder.build();

		try {
			if (notification.getStringPropRegId() != null && !notification.getStringPropRegId().equals("")) {
				Result result = sender.send(msg, notification.getStringPropRegId(), 5);
				logger.info("###### result ####### {}" + result);
			}
		} catch (IOException e) {
			e.printStackTrace();

			return false;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * @메서드명: sendNotificationToGcm
	 * @작성일: 2014. 5. 2.
	 * @작성자: 전진기
	 * @설명: Android 알림 메시지
	 * @param notification
	 * @return "알림 여부"
	 */
	public boolean sendNotificationToGcm_old(Notification notification, String approveCount) throws Exception {
		Message.Builder builder = null;

		try {
			sender = new Sender(Notification.getStringAndroidApiKey());
			builder = new Message.Builder();
			String value = "";
			value = URLDecoder.decode(approveCount, "UTF-8");
			builder.addData("contents", value);
			logger.info("###### value ####### {} " + value);
		}

		catch (UnsupportedEncodingException e) {
			e.printStackTrace();

			return false;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		msg = builder.build();

		try {
			if (notification.getStringPropRegId() != null && !notification.getStringPropRegId().equals("")) {
				Result result = sender.send(msg, notification.getStringPropRegId(), 5);
				logger.info("###### result ####### {}" + result);
			}
		} catch (IOException e) {
			e.printStackTrace();

			return false;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * @메서드명: sendNotificationToGcm
	 * @작성일: 2014. 5. 2.
	 * @작성자: 전진기
	 * @설명: Android 알림 메시지
	 * @param notification
	 * @return "알림 여부"
	 */
	public boolean sendNotificationToGcm(List<Notification> vo, String subject) throws Exception {
		Message.Builder builder = null;

		try {
			sender = new Sender(Notification.getStringAndroidApiKey());
			builder = new Message.Builder();
			String value = "";
			value = URLDecoder.decode(subject, "UTF-8");
			builder.addData("contents", value);

			logger.info("###### value ####### {} " + value);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		msg = builder.build();
		try {

			List<String> tokens = new ArrayList<String>();

			for (Notification notification : vo) {

				if (notification.getStringPropRegId() != null && !notification.getStringPropRegId().equals("")) {

					tokens.add(notification.getStringPropRegId());
					Result result = sender.send(msg, notification.getStringPropRegId(), 5);
					logger.info("###### result ####### {}" + result);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean sendNotificationToApns(List<Notification> list, String subject) throws Exception {
		try {
			Notification notification = new Notification();
			PushNotificationManager pushManager = PushNotificationManager.getInstance();

			PayLoad payLoad = new PayLoad();
			payLoad.addAlert(subject);
			payLoad.addBadge(1);
			payLoad.addSound("default");

			for (Notification vo : list) {
				pushManager.addDevice("iPhone", vo.getStringPropRegId());
				pushManager.initializeConnection(apns_host, apns_port, apns_path, apns_pw, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
				Device client = pushManager.getDevice("iPhone");
				pushManager.sendNotification(client, payLoad);
				pushManager.stopConnection();
				pushManager.removeDevice("iPhone");
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean sendPushMessage(List<Notification> notificationList_android, List<Notification> notificationList_ios, String subject) {
		try {
			if (notificationList_android != null && notificationList_android.size() > 0)
				sendNotificationToGcm(notificationList_android, subject);

			if (notificationList_ios != null && notificationList_ios.size() > 0)
				sendNotificationToApns(notificationList_ios, subject);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	};

}
