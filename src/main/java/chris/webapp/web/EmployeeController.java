package chris.webapp.web;

import chris.webapp.domain.Employee;
import chris.webapp.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Renders a JSON string of all the employees.
     *
     * (Just an example, not used by the app).
     */
    @GetMapping("")
    public @ResponseBody Iterable<Employee> fetchAllEmployees(@RequestParam(required = false) String department) {
        if (department != null) {
            return employeeRepository.findByDepartment(department);
        } else {
            return employeeRepository.findAll();
        }
    }

    /**
     * Redirect the URI "/employees/" to "/employees".
     */
    @GetMapping("/")
    public ModelAndView rootSlashRedirect() {
        return new ModelAndView("redirect:/employees");
    }

    /**
     * Render a page listing all the employees.
     */
    @GetMapping(path="/list")
    public String getAllEmployees(@RequestParam(required = false) String department, Model model) {
        Iterable<Employee> employees;

        if (department != null) {
            employees = employeeRepository.findByDepartment(department);
        } else {
            employees = employeeRepository.findAll();
        }

        model.addAttribute("employees", employees);
        return "employee-list";
    }

    /**
     * Render a page allowing a new employee record to be created.
     */
    @GetMapping(path="/create")
    public String createEmployee(@RequestParam(required = false) String created, Model model) {
        if (created != null && created.toLowerCase().equals("true")) {
            model.addAttribute("showEmployeeCreatedMessage", true);
        }

        return "employee-create";
    }

    /**
     * Create an employee.
     */
    @PostMapping(path="")
    public ModelAndView createEmployee(
        @RequestParam String name,
        @RequestParam String department) {

        Employee n = new Employee();
        n.setName(name);
        n.setDepartment(department);
        employeeRepository.save(n);
        return new ModelAndView("redirect:/employees/create?created=true");
    }

    /**
     * Delete an employee.
     */
    @DeleteMapping(path="{id}")
    // @ResponseBody means the returned string will be rendered directly instead of treating it as a view name
    public @ResponseBody String deleteEmployee(@PathVariable Integer id) {
        employeeRepository.deleteById(id);
        return "Employee " + id + " deleted";
    }
}