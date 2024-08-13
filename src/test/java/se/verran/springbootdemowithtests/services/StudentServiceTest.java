package se.verran.springbootdemowithtests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se.verran.springbootdemowithtests.controllers.SchoolController;
import se.verran.springbootdemowithtests.controllers.StudentController;
import se.verran.springbootdemowithtests.entities.Student;
import se.verran.springbootdemowithtests.repositories.StudentRepository;

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

        assertThrows(ResponseStatusException.class, () -> {
            studentService.addStudent(student);
        }, "Unexpected answer");

        verify(studentRepositoryMock).existsStudentByEmail(studentMail);
    }

    @Test
    void getAllStudents() {
        List<Student> actualStudentList = Collections.emptyList();

        when(studentRepositoryMock.findAll()).thenReturn(actualStudentList);

        assertEquals(actualStudentList, studentService.getAllStudents(), "Unexpected answer");

        verify(studentRepositoryMock).findAll();
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
        when(studentRepositoryMock.existsById(anyInt())).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            studentService.deleteStudent(anyInt());
        }, "Exception was not thrown");
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
        when(studentRepositoryMock.existsById(anyInt())).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            studentService.updateStudent(student);
        }, "Exception was not thrown");

        verify(studentRepositoryMock).existsById(anyInt());
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
        when(studentRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            studentService.getStudentById(anyInt());
        }, "Exception was not thrown.");

        verify(studentRepositoryMock).findById(anyInt());
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

        assertThrows(ResponseStatusException.class, () -> {
            studentService.setGradeForStudentById(studentId, grade);
        }, "Exception not thrown");
    }

    @Test
    void setGradeForStudentByIdWithLessThanShouldReturnException() {
        int studentId = 1;
        String grade = "-1";

        assertThrows(ResponseStatusException.class, () -> {
            studentService.setGradeForStudentById(studentId, grade);
        }, "Exception where grade is less than 0 not thrown");
    }

    @Test
    void setGradeForStudentIdWithMoreThanShouldReturnException() {
        int studentId = 1;
        String grade = "6";

        assertThrows(ResponseStatusException.class, () -> {
            studentService.setGradeForStudentById(studentId, grade);
        }, "Exception where grade is more than 5 not thrown.");
    }

    @Test
    void setGradeForStudentByIdWhereStudentNotFoundShouldReturnException() {
        int studentId = 1;
        String grade = "3.2";

        when(studentRepositoryMock.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            studentService.setGradeForStudentById(studentId, grade);
        }, "Exception where student could not be found was not thrown.");

        verify(studentRepositoryMock).findById(studentId);
    }
}