package org.example.service;

import com.sun.tools.javac.Main;
import org.example.Entity.Employee;
import org.example.Repository.EmployeeRepository;
import org.example.controller.EmployeeController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@MockBean(EmployeeRepository.class)
@Import(EmployeeService.class)
@ContextConfiguration(classes = {EmployeeController.class, EmployeeService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
class EmployeeIntegrationTest {

    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeRepository employeeRepository; // 👈 Mocked

    @Test
    void testGetAllEmployees() {
        // Arrange (Setup fake data)
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "Alice", "IT"),
                new Employee(2L, "Bob", "HR")
        );

        // Define mock behavior (what should happen when repo is called)
        Mockito.when(employeeRepository.findAll()).thenReturn(mockEmployees);

        // Act (Call actual controller)
        List<Employee> employees = employeeController.getAllEmployees();

        // Assert (Verify output)
        assertThat(employees).hasSize(2);
        assertThat(employees.get(0).getName()).isEqualTo("Alice");
        assertThat(employees.get(1).getDepartment()).isEqualTo("HR");

        // Verify behavior (interaction)
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    void testAddEmployee() {
        // Arrange
        Employee input = new Employee(null, "Charlie", "Finance");
        Employee saved = new Employee(3L, "Charlie", "Finance");

        Mockito.when(employeeRepository.save(input)).thenReturn(saved);

        // Act
        Employee result = employeeController.addEmployee(input);

        // Assert
        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getName()).isEqualTo("Charlie");

        // Verify repository call
        Mockito.verify(employeeRepository, Mockito.times(1)).save(input);
    }
}

