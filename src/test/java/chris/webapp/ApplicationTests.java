package chris.webapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import chris.webapp.web.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private EmployeeController employeeController;

	@Test
	public void contextLoads() {
		assertNotNull(employeeController);
	}
}
