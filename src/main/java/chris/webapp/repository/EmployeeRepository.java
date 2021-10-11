package chris.webapp.repository;

import chris.webapp.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called employeeRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    // Spring Data implements this method nd the SQL query automatically, based on the method name.
    List<Employee> findByDepartment(String department);
}
