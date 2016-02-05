package com.example;
import com.leavemanager.dto.EmployeeDto;
import com.leavemanager.dto.VacationsDto;
import com.leavemanager.hibernate.ApplicationFacade;
import org.jboss.logging.Param;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("api")
public class MyResource {
    ApplicationFacade applicationFacade = new ApplicationFacade();


    @GET
    @Path("allemployees")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<EmployeeDto> getEmployees() {
        List<EmployeeDto> list = applicationFacade.getAllEmployee();
        return list;
    }
   /* @POST
    @Path("addEmployee")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.TEXT_PLAIN})
    public String addEmployee(@For){

    }*/

    @GET
    @Path("getEmployee/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public EmployeeDto getEmployeeById(@PathParam("id") Long id) {
        EmployeeDto employeeDto = applicationFacade.getEmployeeById(id);
        return employeeDto;
    }

    @DELETE
    @Path("deleteEmployee/{id}")
   /* @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})*/
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void remove(@PathParam("id") Long id) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(id);
        applicationFacade.deleteEmployee(employeeDto);
    }

    @POST
    @Path("addVacation")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.TEXT_PLAIN})
    public String addVacation(@FormParam("employee_id") Long id,
                            @FormParam("startDate") String startDate,
                            @FormParam("endDate") String endDate,
                            @FormParam("reason") String reason
    ) {
        if (id == null || startDate == null || endDate == null || reason == null) {
            System.out.println("hey");
            return "Fill all Fields";
        }
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(id);
        VacationsDto vacationsDto = new VacationsDto();
        vacationsDto.setStartDate(startDate);
        vacationsDto.setEndDate(endDate);
        vacationsDto.setReason(reason);
        boolean check = applicationFacade.addVacationsForEmployee(employeeDto, vacationsDto);
        if (!check)
            return "Employee Doesn't Exist";
        return "Done";
    }
    @GET
    @Path("vacationsForEmployee/{id}")
    /*@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})*/
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<VacationsDto> vacationsForEmployee(@PathParam("id") Long id){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(id);
        return applicationFacade.listVacationsForEmployee(employeeDto);
    }
}
