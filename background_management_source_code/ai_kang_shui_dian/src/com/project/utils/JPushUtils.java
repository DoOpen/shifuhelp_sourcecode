package com.project.utils;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtils {

	private static final String appKey = "4903776f21cfd4500575f126";
	private static final String masterSecret = "6bb6c08b5964257aea24c9fb";

	// private static final String appKey ="ecf1e8ebf9974faa69959e58";
	// private static final String masterSecret = "217e44900184edb7bccd8fae";
	public static void send(List<String> ids, String title, String content) throws Exception {
		Map<String, String> params = new LinkedHashMap<String, String>();
		Map<String, List<String>> audiences = new LinkedHashMap<String, List<String>>();
		StringBuffer sb = new StringBuffer("https://bjapi.push.jiguang.cn/v3/push");
		audiences.put("registration_id", ids);
		params.put("platform", "all");
		params.put("audience", new Gson().toJson(ids));
		params.put("notification", "这是推送消息");
		params.put("cid", TimeUtils.getCurrentTime("yyyyMMddHHmmss") + new Random().nextInt(Integer.MAX_VALUE));
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "utf-8"));
		}
		System.out.println(
				HttpUtils.pushPost(sb.toString(), EncodeUtils.base64Encode(appKey + ":" + masterSecret, "utf-8")));
	}

	public static void myJPushClient(String content, String registrationId, String type) {

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		JPushClient jPushClient = new JPushClient(masterSecret, appKey);

		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience(Audience.alias(registrationId))
				.setMessage(Message.newBuilder().setMsgContent(content).addExtra("from", "JPush").build())
				.setNotification(Notification.newBuilder().addPlatformNotification(IosNotification.newBuilder()
						.setAlert(content).setBadge(5).setSound("default").addExtra("from", "JPush").build()).build())
				.setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
				.build();
		try {
			PushResult result = jPushClient.sendPush(payload);
			System.out.println("success");
			System.out.println(result.msg_id);
			System.out.println(result.sendno);
		} catch (APIConnectionException e) {
			System.out.println("connection error");
			e.printStackTrace();
		} catch (APIRequestException e) {
			System.out.println("request error");
			e.printStackTrace();
		}
	}
}
