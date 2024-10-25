package pro.sky.skyproBookEmployee1;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmployeeService {
    private final Map<String,Employee> employees;
    private static final int MAX_IMPLOYEES = 100;

    public EmployeeService(Map<String, Employee> employees) {
        this.employees = employees;
    }

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_IMPLOYEES) {
            throw new EmployeeStorageIsFullException("Память заполнена");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee)){
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        employees.put(employee);
        return employee;
    }
    public Employee deliteEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if(!employees.containsKey(employee)){
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }
    public Employee findEmployee(String firstName, String lastName){
        for(Employee employee:employees){
            if(employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)){
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }
    public Map<String, Employee> getAllEmployees(){
        return employees;
    }
}