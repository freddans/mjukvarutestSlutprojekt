package se.verran.springbootdemowithtests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se.verran.springbootdemowithtests.controllers.SchoolController;
import se.verran.springbootdemowithtests.controllers.StudentController;
import se.verran.springbootdemowithtests.entities.Student;
import se.verran.springbootdemowithtests.repositories.StudentRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    private StudentRepository studentRepositoryMock;
    private Student student;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentRepositoryMock = mock(StudentRepository.class);
        student = new Student();
        studentService = new StudentService(studentRepositoryMock);
    }

    @Test
    void addStudentShouldreturnStudent() {
        String studentMail = "freddans@mail.com";
        student.setEmail(studentMail);

        when(studentRepositoryMock.existsStudentByEmail(studentMail)).thenReturn(false);
        when(studentRepositoryMock.save(student)).thenReturn(student);

        Student savedStudent = studentService.addStudent(student);

        assertNotNull(savedStudent);
        assertEquals(studentMail, savedStudent.getEmail());

        verify(studentRepositoryMock).existsStudentByEmail(studentMail);
        verify(studentRepositoryMock).save(student);
    }

    @Test
    void addStudentShouldReturnException() {
        String studentMail = "freddans@mail.com";
        student.setEmail(studentMail);

        when(studentRepositoryMock.existsStudentByEmail(studentMail)).thenReturn(true);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            studentService.addStudent(student);
        }, "Unexpected answer");

        assertEquals("Email " + student.getEmail() + " already exists", response.getReason(), "Expected and Actual was not identical");
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode(), "Status Codes are not identical");

        verify(studentRepositoryMock).existsStudentByEmail(studentMail);
    }

    @Test
    void getAllStudents() {
        List<Student> studentList = Arrays.asList(student);
        int expectedAmountOfStudents = studentList.size();

        when(studentRepositoryMock.findAll()).thenReturn(studentList);

        int actualAmountOfStudents = studentService.getAllStudents().size();
        List<Student> actualStudentList = studentService.getAllStudents();


        assertEquals(expectedAmountOfStudents, actualAmountOfStudents, "expected amount of students are not identical to actual amount of students");
        assertEquals(studentList, actualStudentList, "Unexpected answer");

        verify(studentRepositoryMock, times(2)).findAll();
    }

    @Test
    void deleteStudentSuccessful() {
        int studentId = 1;
        student.setId(studentId);

        when(studentRepositoryMock.existsById(studentId)).thenReturn(true);

        studentService.deleteStudent(studentId);

        verify(studentRepositoryMock).existsById(studentId);
        verify(studentRepositoryMock).deleteById(studentId);
    }

    @Test
    void deleteStudentShouldReturnException() {
        int studentId = 1;
        when(studentRepositoryMock.existsById(studentId)).thenReturn(false);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            studentService.deleteStudent(studentId);
        }, "Exception was not thrown");

        assertEquals("Could not find and delete student by id " + studentId, response.getReason(), "Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Status code was not identical");

        verify(studentRepositoryMock).existsById(studentId);
    }

    @Test
    void updateStudentShouldReturnStudent() {
        int studentId = 1;
        student.setId(studentId);

        when(studentRepositoryMock.existsById(studentId)).thenReturn(true);
        when(studentRepositoryMock.save(student)).thenReturn(student);

        Student expectedStudent = student;
        Student actualStudent = studentService.updateStudent(student);

        assertEquals(expectedStudent, actualStudent, "Expected and Actual student are not the same.");

        verify(studentRepositoryMock).existsById(studentId);
        verify(studentRepositoryMock).save(student);
    }

    @Test
    void updateStudentShouldReturnException() {
        int studentId = student.getId();
        when(studentRepositoryMock.existsById(studentId)).thenReturn(false);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            studentService.updateStudent(student);
        }, "Exception was not thrown");

        assertEquals("Could not find and update student by id " + student.getId(), response.getReason(), "Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Status Codes not identical");

        verify(studentRepositoryMock).existsById(studentId);
    }

    @Test
    void getStudentByIdShouldReturnStudent() {
        when(studentRepositoryMock.findById(anyInt())).thenReturn(Optional.of(student));

        Student expectedStudent = student;
        Student actualStudent = studentService.getStudentById(anyInt());

        assertEquals(expectedStudent, actualStudent, "Expected and Actual student are not the same.");

        verify(studentRepositoryMock).findById(anyInt());
    }

    @Test
    void getStudentByIdShouldReturnException() {
        int studentId = student.getId();
        when(studentRepositoryMock.findById(studentId)).thenReturn(Optional.empty());

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            studentService.getStudentById(studentId);
        }, "Exception was not thrown.");

        assertEquals("Could not find student by id " + studentId, response.getReason(), "Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Status Codes not identical");

        verify(studentRepositoryMock).findById(studentId);
    }

    @Test
    void setGradeForStudentByIdShouldReturnStudent() {
        int studentId = 1;
        String grade = "4.5";

        when(studentRepositoryMock.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepositoryMock.save(student)).thenReturn(student);


        Student actualStudent = studentService.setGradeForStudentById(studentId, grade);

        assertEquals(student, actualStudent, "Not same");

        verify(studentRepositoryMock, times(1)).findById(studentId);
        verify(studentRepositoryMock, times(1)).save(student);
    }

    @Test
    void setGradeForStudentByIdGradeFailureShouldReturnException() {
        int studentId = 1;
        String grade = "Not Parseable";

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            studentService.setGradeForStudentById(studentId, grade);
        }, "Exception not thrown");

        assertEquals("Valid grades are 0.0 - 5.0", response.getReason(), "Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "Status Codes was not identical");
    }

    @Test
    void setGradeForStudentByIdWithLessThanShouldReturnException() {
        int studentId = 1;
        String grade = "-1";

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            studentService.setGradeForStudentById(studentId, grade);
        }, "Exception where grade is less than 0 not thrown");

        assertEquals("Valid grades are 0.0 - 5.0", response.getReason(), "Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "Status Codes was not identical");
    }

    @Test
    void setGradeForStudentIdWithMoreThanShouldReturnException() {
        int studentId = 1;
        String grade = "6";

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            studentService.setGradeForStudentById(studentId, grade);
        }, "Exception where grade is more than 5 not thrown.");

        assertEquals("Valid grades are 0.0 - 5.0", response.getReason(), "Exceptions was not identical");
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode(), "Status Codes was not identical");
    }

    @Test
    void setGradeForStudentByIdWhereStudentNotFoundShouldReturnException() {
        int studentId = 1;
        String grade = "3.2";

        when(studentRepositoryMock.findById(studentId)).thenReturn(Optional.empty());

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            studentService.setGradeForStudentById(studentId, grade);
        }, "Exception where student could not be found was not thrown.");

        assertEquals("Could not find and update grades for student by id " + studentId, response.getReason(), "Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Status Codes was not identical");

        verify(studentRepositoryMock).findById(studentId);
    }
}