package xie.web.utils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by hp on 2015/10/16.
 */
public class JsonUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		System.out.print(mapper.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		System.out.print(mapper.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
	}

	public static <T> T getJsonValue(String jsonStr, Class<T> valueType) throws IOException {
		T map = mapper.readValue(jsonStr, valueType);
		return map;
	}

	public static Map<String, Object> getJsonMap(String jsonStr) throws IOException {
		LinkedHashMap<String, Object> map = mapper.readValue(jsonStr, LinkedHashMap.class);
		return map;
	}

	public static String getJsonStr(Object object) throws JsonProcessingException {
		String jsonStr = mapper.writeValueAsString(object);
		return jsonStr;
	}

	public static String getJsonStr(Map<String, Object> jsonMap) throws JsonProcessingException {
		String jsonStr = mapper.writeValueAsString(jsonMap);
		return jsonStr;
	}
}
