package junit;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.neotech.services.ServiceLocator;

public class ServiceLocatorTest {

	@Test
	public void initializationTest() {
		assertNotNull(ServiceLocator.getDataService());
		assertNotNull(ServiceLocator.getPrintService());
	}

}
