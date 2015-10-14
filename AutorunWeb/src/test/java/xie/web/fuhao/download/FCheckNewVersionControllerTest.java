/**
 * 
 */
package xie.web.fuhao.download;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;

import xie.web.test.main.TestApp;

public class FCheckNewVersionControllerTest {

	static SpringApplication testApp;

	@BeforeClass
	public static void before() throws ClientProtocolException, IOException {
		if (testApp == null) {
			testApp = new SpringApplication(TestApp.class);
		}

		//testApp.run();
	}

	@AfterClass
	public static void after() throws ClientProtocolException, IOException {
	}

	@Test
	public void testGetNewVersion() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpPost = new HttpGet("http://localhost:8080/AutorunWeb/fuhao/check/getNewVersion");

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		String jsonStr = EntityUtils.toString(entity, "utf-8");
		System.out.println(jsonStr);

		String expected = "3.72.014";

		Assert.assertEquals(expected, jsonStr);
	}

	@Test
	public void testGetNewVersionName() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpPost = new HttpGet("http://localhost:8080/AutorunWeb/fuhao/check/getNewVersionName");

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		// String jsonStr = EntityUtils.toString(entity, "utf-8");
		// String jsonStr = EntityUtils.toString(entity, "unicode");
		// String jsonStr = EntityUtils.toString(entity);

		// String jsonStr =new String(EntityUtils.toByteArray(entity), "iso-8859-1");

		String jsonStr = EntityUtils.toString(entity);
		jsonStr = new String(jsonStr.getBytes("iso-8859-1"), "utf-8");

		System.out.println(jsonStr);

		String expected = "大富豪配置M3.72.014";

		Assert.assertEquals(expected, jsonStr);
	}

	@Test
	public void testGetNewVersionLastModify() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpPost = new HttpGet("http://localhost:8080/AutorunWeb/fuhao/check/getNewVersionLastModify");

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		String jsonStr = EntityUtils.toString(entity, "utf-8");
		System.out.println(jsonStr);

		String expected = "1417535931715";

		Assert.assertEquals(expected, jsonStr);
	}
}
