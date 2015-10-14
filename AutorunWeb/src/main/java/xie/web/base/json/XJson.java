package xie.web.base.json;

import java.io.Serializable;
import java.util.Map;

public interface XJson extends Serializable {
	Map<String, String> getJsonMap(String jsonStr);

	String getJsonString(Map<String, String> jsonMap);
}
