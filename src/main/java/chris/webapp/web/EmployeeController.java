package chris.webapp.web;

import chris.webapp.domain.Employee;
import chris.webapp.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path="/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Redirect the URI "/employee" to "/employee/".
     */
    @GetMapping("")
    public ModelAndView redirectPostToPost() {
        return new ModelAndView("redirect:/employee/");
    }

    @GetMapping("/")
    public String home() {
        return "employee-home";  // view name
    }

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

    // @ResponseBody means the returned String is the response, not a view name
    @PostMapping(path="/add")
    public @ResponseBody String addNewEmployee (
        @RequestParam String name,
        @RequestParam String department) {

        Employee n = new Employee();
        n.setName(name);
        n.setDepartment(department);
        employeeRepository.save(n);
        return "Saved";
    }

    // Returns a JSON or XML with the employees
    @GetMapping(path="/list-json")
    public @ResponseBody Iterable<Employee> getAllEmployees(@RequestParam(required = false) String department) {
        if (department != null) {
            return employeeRepository.findByDepartment(department);
        } else {
            return employeeRepository.findAll();
        }
    }
}