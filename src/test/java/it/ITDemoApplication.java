package it;

import com.example.demo.DemoApplication;
import com.foreach.across.test.support.config.MockAcrossServletContextInitializer;
import com.foreach.across.test.support.config.MockMvcConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;

/**
 * Integration test that bootstraps the entire application but without a web container.
 * MockMVC is initialized and can be called to perform calls on the bootstrapped application.
 * <p>
 * Boostrapping is done with profile "integration-test" active, you can provide an
 * <em>application-integration-test.yml</em> on the (test) classpath to specify application configuration options.
 * <p>
 * If specific application properties have been provided, only <em>application.yml</em> will apply.
 * If not database has been configured, default configuration will use a memory database (if available on the classpath).
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = { DemoApplication.class, MockMvcConfiguration.class })
@ActiveProfiles("integration-test")
@ContextConfiguration(initializers = MockAcrossServletContextInitializer.class)
public class ITDemoApplication
{
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void bootstrappedOk() throws Exception {
		// Test should really do something - but when it gets called, bootstrap has been successful
		assertNotNull( mockMvc );
	}
}