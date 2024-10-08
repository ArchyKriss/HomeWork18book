package pro.sky.skyproBookEmployee1;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private static final int MAX_IMPLOYEES = 100;

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_IMPLOYEES) {
            throw new EmployeeStorageIsFullException("Память заполнена");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)){
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        employees.add(employee);
        return employee;
    }
    public Employee deliteEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        if(!employees.remove(employee)){
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
    public List<Employee> getAllEmployees(){
        return employees;
    }
}