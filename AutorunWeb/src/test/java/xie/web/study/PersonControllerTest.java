package xie.web.study;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;


public class PersonControllerTest {

	@Test
	@Ignore
	public void testLPorfile() throws IOException {
		//HttpClient httpClient = HttpClientBuilder.create().build();
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://localhost:8080/AutorunWeb/fuhao/person/profile/11/22/false");

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		String jsonStr = EntityUtils.toString(entity, "utf-8");
		System.out.println(jsonStr);

		Map<String,String> map = new HashMap<String, String>();
		map.put("id", "id111");
		map.put("name", "name2222");
		map.put("status", "false");
		
		ObjectMapper jsonMapper = new ObjectMapper();
		String expected = jsonMapper.writeValueAsString(map);
		
		Assert.assertEquals(expected, jsonStr);
	}

	@Test
	@Ignore
	public void testLogin() throws IOException {
		//HttpClient httpClient = HttpClients.createDefault();
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://localhost:8080/AutorunWeb/fuhao/person/login");
		List<NameValuePair> forms = new ArrayList<NameValuePair>();
		forms.add(new BasicNameValuePair("id", "id111"));
		forms.add(new BasicNameValuePair("name", "name2222"));
		forms.add(new BasicNameValuePair("status", "false"));

//		httpPost.setEntity(new UrlEncodedFormEntity(forms));
		httpPost.setEntity(new StringEntity("{\"id\"=\"id111\"}"));

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		String jsonStr = EntityUtils.toString(entity, "utf-8");
		System.out.println(jsonStr);

		Map<String,String> map = new HashMap<String, String>();
		map.put("id", "id111");
		map.put("name", "name2222");
		map.put("status", "false");
		
		ObjectMapper jsonMapper = new ObjectMapper();
		String expected = jsonMapper.writeValueAsString(map);
		
		Assert.assertEquals(expected, jsonStr);
	}

	@Test
	public void index() {
	}
}
