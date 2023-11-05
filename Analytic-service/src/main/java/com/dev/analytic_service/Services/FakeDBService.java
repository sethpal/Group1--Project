package com.dev.analytic_service.Services;

import com.dev.analytic_service.Dtos.ClientRequestDto;
import com.dev.analytic_service.Dtos.EmployeeRequestDto;
import com.dev.analytic_service.Dtos.TicketRequestDto;
import com.dev.analytic_service.Enums.State;
import com.dev.analytic_service.Models.Client;
import com.dev.analytic_service.Models.Employee;
import com.dev.analytic_service.Models.Ticket;
import com.dev.analytic_service.Repositories.ClientRepo;
import com.dev.analytic_service.Repositories.EmployeeRepo;
import com.dev.analytic_service.Repositories.TicketRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Service
public class FakeDBService {

    ClientRepo clientRepo;
    EmployeeRepo employeeRepo;
    TicketRepo ticketRepo;
    LocalDateTime localDateTime = LocalDateTime.now();

    public FakeDBService(ClientRepo clientRepo, EmployeeRepo employeeRepo, TicketRepo ticketRepo) {
        this.clientRepo = clientRepo;
        this.employeeRepo = employeeRepo;
        this.ticketRepo = ticketRepo;
    }

    public Client clientRequestDtoToClient
            (ClientRequestDto clientRequestDto) {
        Client client = new Client();
        client.setEmail(clientRequestDto.getEmail());
        client.setName(clientRequestDto.getName());
        client.setAddress(clientRequestDto.getAddress());
        client.setPincode(clientRequestDto.getPincode());

        return client;

    }

    public Employee employeeRequestDtoToEmployee
            (EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();

        employee.setName(employeeRequestDto.getName());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setHire_date(employeeRequestDto.getHire_date());
        employee.setSalary(employeeRequestDto.getSalary());
        employee.setWork_dept(employeeRequestDto.getWork_dept());
        employee.setBirth_date(employeeRequestDto.getBirth_date());

        return employee;
    }

    public Ticket ticketRequestDtoToTicket
            (TicketRequestDto ticketRequestDto) {

        Ticket ticket = new Ticket();

        ticket.setDescription(ticketRequestDto.getDescription());
        ticket.setState(ticketRequestDto.getState());
        ticket.setAssigned_to(
                employeeRepo.
                        findByEmailEqualsIgnoreCase(
                                ticketRequestDto.getAssigned_to_email()
                        )
        );

        ticket.setRaised_by(
                clientRepo.
                        findByEmailEqualsIgnoreCase(ticketRequestDto.getRaised_by_email())
        );

        ticket.setGenerated_on(ticketRequestDto.getGenerated_on());
        ticket.setLast_updated(ticketRequestDto.getLast_updated());

        return ticket;
    }

    public void set_DB() {

        ClientRequestDto clientRequestDto = new ClientRequestDto();

        clientRequestDto.setName("Eric");
        clientRequestDto.setEmail("eric@gmail.com");
        clientRequestDto.setAddress("London,UK");
        clientRequestDto.setPincode(234345);
        clientRepo.save(clientRequestDtoToClient(clientRequestDto));

        clientRequestDto.setName("Albert");
        clientRequestDto.setEmail("albert@gmail.com");
        clientRequestDto.setAddress("New York,US");
        clientRequestDto.setPincode(123345);
        clientRepo.save(clientRequestDtoToClient(clientRequestDto));

        clientRequestDto.setName("Jenny");
        clientRequestDto.setEmail("jenny@gmail.com");
        clientRequestDto.setAddress("Sanghai,China");
        clientRequestDto.setPincode(343553);
        clientRepo.save(clientRequestDtoToClient(clientRequestDto));

        clientRequestDto.setName("Vanessa");
        clientRequestDto.setEmail("vanessa@gmail.com");
        clientRequestDto.setAddress("Texas,US");
        clientRequestDto.setPincode(234345);
        clientRepo.save(clientRequestDtoToClient(clientRequestDto));

        clientRequestDto.setName("Alexander");
        clientRequestDto.setEmail("alexander@gmail.com");
        clientRequestDto.setAddress("California,US");
        clientRequestDto.setPincode(487256);
        clientRepo.save(clientRequestDtoToClient(clientRequestDto));

//-------------------------------------------------------------------------------------------

        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();

        employeeRequestDto.setName("Mithesh");
        employeeRequestDto.setEmail("mithesh@gmail.com");
        employeeRequestDto.setSalary(35000.95);
        employeeRequestDto.setWork_dept("Infra");
        employeeRequestDto.setHire_date(Date.from(localDateTime.minusYears(3).atZone(ZoneId.systemDefault()).toInstant())
        );
        employeeRequestDto.setBirth_date(Date.from(
                localDateTime.minusYears(36).atZone(ZoneId.systemDefault()).toInstant())
        );
        employeeRepo.save(employeeRequestDtoToEmployee(employeeRequestDto));

        employeeRequestDto.setName("Gaurav");
        employeeRequestDto.setEmail("gaurav@gmail.com");
        employeeRequestDto.setSalary(39956.78);
        employeeRequestDto.setWork_dept("Infra");
        employeeRequestDto.setHire_date(Date.from(
                localDateTime.minusYears(1).atZone(ZoneId.systemDefault()).toInstant())
        );
        employeeRequestDto.setBirth_date(Date.from(
                localDateTime.minusYears(24).atZone(ZoneId.systemDefault()).toInstant())
        );
        employeeRepo.save(employeeRequestDtoToEmployee(employeeRequestDto));

        employeeRequestDto.setName("Vishal");
        employeeRequestDto.setEmail("vishal@gmail.com");
        employeeRequestDto.setSalary(55768.66);
        employeeRequestDto.setWork_dept("Development");
        employeeRequestDto.setHire_date(Date.from(
                localDateTime.minusYears(5).atZone(ZoneId.systemDefault()).toInstant())
        );
        employeeRequestDto.setBirth_date(Date.from(
                localDateTime.minusYears(26).atZone(ZoneId.systemDefault()).toInstant())
        );
        employeeRepo.save(employeeRequestDtoToEmployee(employeeRequestDto));

//----------------------------------------------------------------------------------------------
        TicketRequestDto ticketRequestDto = new TicketRequestDto();

        ticketRequestDto.setDescription("Unable to use software");
        ticketRequestDto.setState(State.OPEN);
        ticketRequestDto.setRaised_by_email("alexander@gmail.com");
        ticketRequestDto.setAssigned_to_email("gaurav@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(6).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(3).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));


        ticketRequestDto.setDescription("System restarting frequently");
        ticketRequestDto.setState(State.PENDING_WITH_END_USER);
        ticketRequestDto.setRaised_by_email("jenny@gmail.com");
        ticketRequestDto.setAssigned_to_email("vishal@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(3).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(2).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("Unable to get updates");
        ticketRequestDto.setState(State.WORK_IN_PROGRESS);
        ticketRequestDto.setRaised_by_email("albert@gmail.com");
        ticketRequestDto.setAssigned_to_email("mithesh@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(12).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(9).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("PXE boot issue");
        ticketRequestDto.setState(State.RESOLVED);
        ticketRequestDto.setRaised_by_email("eric@gmail.com");
        ticketRequestDto.setAssigned_to_email("gaurav@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(20).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(2).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("Need backup server access");
        ticketRequestDto.setState(State.WORK_IN_PROGRESS);
        ticketRequestDto.setRaised_by_email("vanessa@gmail.com");
        ticketRequestDto.setAssigned_to_email("vishal@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(18).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(1).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("Need Cisco IP Communicator");
        ticketRequestDto.setState(State.WORK_IN_PROGRESS);
        ticketRequestDto.setRaised_by_email("alexander@gmail.com");
        ticketRequestDto.setAssigned_to_email("gaurav@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(3).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(1).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("Unable to use software");
        ticketRequestDto.setState(State.OPEN);
        ticketRequestDto.setRaised_by_email("Alexander@gmail.com");
        ticketRequestDto.setAssigned_to_email("Gaurav@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(6).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(3).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("Need github student developer pack");
        ticketRequestDto.setState(State.OPEN);
        ticketRequestDto.setRaised_by_email("eric@gmail.com");
        ticketRequestDto.setAssigned_to_email("mithesh@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(8).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(3).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("need ODC access");
        ticketRequestDto.setState(State.RESOLVED);
        ticketRequestDto.setRaised_by_email("jenny@gmail.com");
        ticketRequestDto.setAssigned_to_email("gaurav@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(12).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(11).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("need new laptop");
        ticketRequestDto.setState(State.OPEN);
        ticketRequestDto.setRaised_by_email("albert@gmail.com");
        ticketRequestDto.setAssigned_to_email("vishal@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(2).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(1).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("Unable to use software");
        ticketRequestDto.setState(State.WORK_IN_PROGRESS);
        ticketRequestDto.setRaised_by_email("vanessa@gmail.com");
        ticketRequestDto.setAssigned_to_email("gaurav@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(21).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(3).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("Need Cisco IP Communicator");
        ticketRequestDto.setState(State.WORK_IN_PROGRESS);
        ticketRequestDto.setRaised_by_email("eric@gmail.com");
        ticketRequestDto.setAssigned_to_email("vishal@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(8).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(3).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("Unable to download audio drivers");
        ticketRequestDto.setState(State.OPEN);
        ticketRequestDto.setRaised_by_email("jenny@gmail.com");
        ticketRequestDto.setAssigned_to_email("mithesh@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(13).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(1).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("need ODC5 access");
        ticketRequestDto.setState(State.RESOLVED);
        ticketRequestDto.setRaised_by_email("jenny@gmail.com");
        ticketRequestDto.setAssigned_to_email("vishal@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(12).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(11).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));

        ticketRequestDto.setDescription("need ODC6 access");
        ticketRequestDto.setState(State.RESOLVED);
        ticketRequestDto.setRaised_by_email("jenny@gmail.com");
        ticketRequestDto.setAssigned_to_email("mithesh@gmail.com");
        ticketRequestDto.setGenerated_on(Date.from(
                localDateTime.minusDays(12).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRequestDto.setLast_updated(Date.from(
                localDateTime.minusDays(11).atZone(ZoneId.systemDefault()).toInstant())
        );
        ticketRepo.save(ticketRequestDtoToTicket(ticketRequestDto));


        System.out.println("DB SET");


    }


}
