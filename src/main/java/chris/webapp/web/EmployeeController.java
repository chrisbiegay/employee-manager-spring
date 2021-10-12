package chris.webapp.web;

import chris.webapp.domain.Employee;
import chris.webapp.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView rootRedirect() {
        return new ModelAndView("redirect:/employee/");
    }

    @GetMapping("/")
    public ModelAndView rootSlashRedirect() {
        return new ModelAndView("redirect:/employee/list");
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
    @GetMapping(path="/add")
    public String addNewEmployee(@RequestParam(required = false) String created, Model model) {
        if (created != null && created.toLowerCase().equals("true")) {
            model.addAttribute("showEmployeeAddedMessage", true);
        }

        return "employee-add";
    }

    /**
     * Handle the create-employee form submission.
     */
    @PostMapping(path="/add")
    public ModelAndView addNewEmployee (
        @RequestParam String name,
        @RequestParam String department) {

        Employee n = new Employee();
        n.setName(name);
        n.setDepartment(department);
        employeeRepository.save(n);
        return new ModelAndView("redirect:/employee/add?created=true");
    }

    /**
     * Renders a JSON string of all the employees.
     */
    @GetMapping(path="/list-json")
    public @ResponseBody Iterable<Employee> getAllEmployees(@RequestParam(required = false) String department) {
        if (department != null) {
            return employeeRepository.findByDepartment(department);
        } else {
            return employeeRepository.findAll();
        }
    }
}