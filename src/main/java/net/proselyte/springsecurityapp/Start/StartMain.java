package net.proselyte.springsecurityapp.Start;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class StartMain {
	private static final Logger LOG = LoggerFactory.getLogger(StartMain.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = null;
		try {
			LOG.info("INIT PHASE START");
			System.out.println("INIT PHASE START");
			ctx = new ClassPathXmlApplicationContext(new String[] { "classpath*:**/applicationContext.xml" }, false);
			ctx.refresh();
			// ctx.registerShutdownHook();
			if (ctx.isActive() && ctx.isRunning()) {
				LOG.info("Spring comessageSourcentext has successfully started {}", new Date(ctx.getStartupDate()));
				System.out.println("Spring context has successfully started {}");
			} else {
				LOG.error("Context is not active or running");
				System.out.println("Context is not active or running");
			}
			PropertySource<?> source = ((PropertySourcesPlaceholderConfigurer) ctx.getBean("messageSource"))
					.getAppliedPropertySources()
					.get(PropertySourcesPlaceholderConfigurer.LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME);
			Properties props = new Properties();
			if (source instanceof PropertiesPropertySource) {
				Map<String, Object> entry = ((PropertiesPropertySource) source).getSource();
				props.putAll(Collections.unmodifiableMap(entry));
			}
			System.out.println(props.toString());
			LOG.info("INIT PHASE END");

		} catch (Exception e) {
			LOG.error("Context creation failure", e);
		} finally {
			ctx.close();
		}
	}

}
