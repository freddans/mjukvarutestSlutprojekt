package se.verran.springbootdemowithtests.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import se.verran.springbootdemowithtests.entities.Student;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        Student student = new Student();
        student.setFirstName("Anders");
        student.setLastName("Andersson");
        student.setBirthDate(LocalDate.of(1989, 1, 22));
        student.setEmail("Anders@Andersson.com");
        studentRepository.save(student);
    }

    @Test
    void existsStudentByEmailShouldReturnTrue() {
        boolean isStudent = studentRepository.existsStudentByEmail("Anders@Andersson.com");

        assertTrue(isStudent, "Email does not exist");
    }

    @Test
    void existsStudentByEmailShouldReturnFalse() {
        boolean isStudent = studentRepository.existsStudentByEmail("Anders@FaultyMail.com");

        assertFalse(isStudent, "Email already exist");
    }
}