package xie.web.utils;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;

import xie.test.utils.DomainEquals;

/**
 * Created by hp on 2015/10/16.
 */
public class JsonUtilsTest {

	@Test
	public void getJsonValue() throws IOException {
		Date date = new Date(1444976484307L);
		TestJsonBean testJsonBean = new TestJsonBean("aaa", date, 111, 222);
		String jsonStr = JsonUtils.getJsonStr(testJsonBean);

		TestJsonBean value = JsonUtils.getJsonValue(jsonStr, TestJsonBean.class);
		//Assert.assertEquals(testJsonBean, value);
		DomainEquals.domainEquals(testJsonBean, value);
	}

	@Test
	public void getJsonMap() throws IOException {
		Date date = new Date(1444976484307L);
		Map<String, Object> exceptedMap = new LinkedHashMap<String, Object>();
		exceptedMap.put("string", "aaa");
		exceptedMap.put("date", 1444976484307L); // TODO 此处没法放Date类型
		exceptedMap.put("intValue", 111);
		exceptedMap.put("integer", 222);

		String jsonStr = JsonUtils.getJsonStr(exceptedMap);

		Map<String, Object> actualMap = JsonUtils.getJsonMap(jsonStr);
		Assert.assertEquals(exceptedMap, actualMap);
	}

	@Test
	public void getJsonStr_Object() throws JsonProcessingException {
		Date date = new Date(1444976484307L);
		TestJsonBean testJsonBean = new TestJsonBean("aaa", date, 111, 222);
		String jsonStr = JsonUtils.getJsonStr(testJsonBean);

		Assert.assertEquals("{\"string\":\"aaa\",\"date\":1444976484307,\"intValue\":111,\"integer\":222,\"formatedDate\":\"2015年10月16日 06:21:24:307\"}", jsonStr);
	}

	@Test
	public void getJsonStr_Map() throws JsonProcessingException {
		Date date = new Date(1444976484307L);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("string", "aaa");
		map.put("date", date);
		map.put("intValue", 111);
		map.put("integer", 222);

		String jsonStr = JsonUtils.getJsonStr(map);
		Assert.assertEquals("{\"string\":\"aaa\",\"date\":1444976484307,\"intValue\":111,\"integer\":222}", jsonStr);
	}


	public static class TestJsonBean {
		private String string;
		private Date date;
		private int intValue;
		private Integer integer;

		public TestJsonBean() {
		}

		public TestJsonBean(String string, Date date, int intValue, Integer integer) {
			setString(string);
			setDate(date);
			setIntValue(intValue);
			setInteger(integer);
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		public Date getDate() {
			return date;
		}

		@JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss:SSS")
		public Date getFormatedDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public int getIntValue() {
			return intValue;
		}

		public void setIntValue(int intValue) {
			this.intValue = intValue;
		}

		public Integer getInteger() {
			return integer;
		}

		public void setInteger(Integer integer) {
			this.integer = integer;
		}
	}
}
