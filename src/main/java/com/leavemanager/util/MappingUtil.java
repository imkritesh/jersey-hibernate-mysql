package com.leavemanager.util;
import com.leavemanager.domain.Employee;
import com.leavemanager.domain.Vacations;
import com.leavemanager.dto.*;

public class MappingUtil {
    //employeedto->emloyee
    public static EmployeeDto  employeeToDto(Employee employee){
        if(employee == null)
            return null;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFistName(employee.getFistName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setAddress(employee.getAddress());
        employeeDto.setMobileNumber(employee.getMobilenumber());
        employeeDto.setDob(employee.getDob());
        employeeDto.setGender(employee.getGender());
        employeeDto.setPosition(employee.getPosition());
        return employeeDto;
    }
    //employee->employeedto
    public static Employee dtoToEmployee(EmployeeDto employeeDto){
        if(employeeDto == null)
            return null;
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFistName(employeeDto.getFistName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setAddress(employeeDto.getAddress());
        employee.setMobilenumber(employeeDto.getMobileNumber());
        employee.setDob(employeeDto.getDob());
        employee.setGender(employeeDto.getGender());
        employee.setPosition(employeeDto.getPosition());
        return employee;
    }
    //vacations->vacationsdto
    public static VacationsDto vacationsToDto(Vacations vacations){
        if(vacations == null)
            return null;
        VacationsDto vacationsDto = new VacationsDto();
        vacationsDto.setId(vacations.getId());
        vacationsDto.setStartDate(vacations.getStartDate());
        vacationsDto.setEndDate(vacations.getEndDate());
        vacationsDto.setReason(vacations.getReason());
        vacationsDto.setEmployee(employeeToDto(vacations.getEmployee()));
        return vacationsDto;
    }
    //vacationsdto->vacations
    public static Vacations dtoToVacations(VacationsDto vacationsDto){
        if(vacationsDto == null)
            return null;
        Vacations vacations = new Vacations();
        vacations.setId(vacationsDto.getId());
        vacations.setStartDate(vacationsDto.getStartDate());
        vacations.setEndDate(vacationsDto.getEndDate());
        vacations.setReason(vacationsDto.getReason());
        vacations.setEmployee(dtoToEmployee(vacationsDto.getEmployee()));
        return vacations;
    }
}
