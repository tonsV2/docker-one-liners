package dk.fitfit.oneliner.configuration;

import com.vlkan.hrrs.servlet.HrrsFilter;
import com.vlkan.hrrs.servlet.HrrsServlet;
import com.vlkan.hrrs.servlet.base64.Base64HrrsFilter;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class HrrsConfig {

	@Bean
	public HrrsFilter provideHrrsFilter() throws IOException {
		File writerTargetFile = File.createTempFile("hrrs-spring-records-", ".csv");
		return new Base64HrrsFilter(writerTargetFile);
	}

	@Bean
	public ServletRegistrationBean provideHrrsServlet() {
		HrrsServlet hrrsServlet = new HrrsServlet();
		return new ServletRegistrationBean(hrrsServlet, "/hrrs");
	}

}
