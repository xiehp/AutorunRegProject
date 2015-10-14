package xie.web.fuhao.utils;

import java.io.File;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

public class FNewVersionUtilsTest {

	@Test
	public void testGetNewVersionFile() {
		HttpClient httpClent = HttpClients.createDefault();
	}

	@Test
	public void testGetNewVersionFileVersion() {
		Assert.assertEquals(getVersion("XXX3.4.33345.exe"), "3.4.33345");
		Assert.assertEquals(getVersion("XXX3.4.33345.exe"), "3.4.33345");
		Assert.assertEquals(getVersion("3.4.33345.exe"), "3.4.33345");
		Assert.assertEquals(getVersion(".4.3.3345.exe"), "4.3.3345");
		Assert.assertEquals(getVersion(".4.3.3345."), "4.3.3345");
		Assert.assertEquals(getVersion(".4.33.345."), "4.33.345");
		Assert.assertEquals(getVersion("2......2"), "2......2");
		Assert.assertEquals(getVersion("..3..2.."), "3..2");

	}

	private String getVersion(String value) {
		return FNewVersionUtils.getNewVersionFileVersion(new File(value));
	}

}
