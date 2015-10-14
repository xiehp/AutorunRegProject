package xie.web.spring.boot.configuration;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

@Configuration
public class MyStringHttpMessageConverters {
	Logger logger = LoggerFactory.getLogger(getClass());

	//@Bean
	public HttpMessageConverters customConverters() {
		logger.info("begin");

		HttpMessageConverter<String> additional = new HttpMessageConverter<String>() {

			@Override
			public boolean canRead(Class<?> clazz, MediaType mediaType) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean canWrite(Class<?> clazz, MediaType mediaType) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public List<MediaType> getSupportedMediaTypes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String read(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void write(String t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
				// TODO Auto-generated method stub

			}
		};
		HttpMessageConverter<String> another = new HttpMessageConverter<String>() {

			@Override
			public boolean canRead(Class<?> clazz, MediaType mediaType) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean canWrite(Class<?> clazz, MediaType mediaType) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public List<MediaType> getSupportedMediaTypes() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String read(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void write(String t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
				// TODO Auto-generated method stub

			}
		};
		HttpMessageConverters httpMessageConverters = new HttpMessageConverters();
		httpMessageConverters = new HttpMessageConverters(additional, another);

		logger.info("end");
		return httpMessageConverters;
	}

}