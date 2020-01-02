package com.tntcpu.javabase.tij.concurrency.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-11-21
 **/
public class Test {
	public static void main(String[] args) {
		String a = "{\"arriveTime\":\"2019-12-04 09:31:00\",\"driverId\":\"05841363\",\"endStationId\":\"605-0-4\",\"endStationName\":\"汽车北站下客\",\"isoverFlag\":0,\"leaveTime\":\"2019-12-04 07:53:00\",\"packageTime\":\"2019-12-04 09:37:52\",\"planArriveTime\":\"2019-12-04 09:18:00\",\"planLeaveTime\":\"2019-12-04 07:54:00\",\"roadpaper\":[{\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 09:55:07\",\"passengerup\":0,\"stationNo\":1},{\"inStationTime\":\"2019-12-04 09:58:17\",\"isConsumer\":0,\"outStationTime\":\"2019-12-04 09:58:49\",\"passengerup\":91,\"stationNo\":47},{\"inStationTime\":\"2019-12-04 09:59:12\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:00:11\",\"passengerup\":0,\"stationNo\":48},{\"inStationTime\":\"2019-12-04 10:00:40\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:01:12\",\"passengerup\":0,\"stationNo\":49},{\"inStationTime\":\"2019-12-04 10:06:53\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:07:23\",\"passengerup\":0,\"stationNo\":54},{\"inStationTime\":\"2019-12-04 10:02:56\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:03:17\",\"passengerup\":0,\"stationNo\":50},{\"inStationTime\":\"2019-12-04 10:03:49\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:04:00\",\"passengerup\":0,\"stationNo\":51},{\"inStationTime\":\"2019-12-04 10:05:49\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:06:30\",\"passengerup\":0,\"stationNo\":53},{\"inStationTime\":\"2019-12-04 10:05:14\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:05:35\",\"passengerup\":0,\"stationNo\":52},{\"inStationTime\":\"2019-12-04 10:09:57\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:10:20\",\"passengerup\":0,\"stationNo\":56},{\"inStationTime\":\"2019-12-04 10:13:27\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:14:01\",\"passengerup\":0,\"stationNo\":58},{\"inStationTime\":\"2019-12-04 10:12:30\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:12:58\",\"passengerup\":0,\"stationNo\":57},{\"inStationTime\":\"2019-12-04 10:08:21\",\"isConsumer\":-1,\"outStationTime\":\"2019-12-04 10:08:57\",\"passengerup\":0,\"stationNo\":55}],\"runDirection\":2,\"startStationId\":\"605-0-3\",\"startStationName\":\"天泰体育场上客\",\"taskNo\":1,\"updown\":1}";
		JSONObject jsonObject = JSON.parseObject(a);
		JSONArray roadpaper = jsonObject.getJSONArray("roadpaper");
		System.out.println(roadpaper.toJSONString());
		JSONObject o = (JSONObject) roadpaper.get(1);
		o.put("stationNo",999);
//		System.out.println(roadpaper.toJSONString());
		roadpaper.set(1,o);
		System.out.println(roadpaper.toJSONString());

	}
}
