package chris.webapp.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import chris.webapp.domain.Employee;
import chris.webapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController controller;

    @Mock
    EmployeeRepository employeeRepository;

    @Captor
    ArgumentCaptor<Employee> employeeCaptor;

    @Test
    public void createEmployee_savesToRepository() {
        controller.createEmployee("Chris", "Engineering");

        verify(employeeRepository).save(employeeCaptor.capture());
        assertEquals("Chris", employeeCaptor.getValue().getName());
        assertEquals("Engineering", employeeCaptor.getValue().getDepartment());
    }
}
