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
    private Student studentMock;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentRepositoryMock = mock(StudentRepository.class);
        studentMock = mock(Student.class);
        studentService = new StudentService(studentRepositoryMock);
    }

    @Test
    void addStudentShouldreturnStudent() {
        String studentMail = "freddans@mail.com";

        when(studentMock.getEmail()).thenReturn(studentMail);

        when(studentRepositoryMock.existsStudentByEmail(studentMail)).thenReturn(false);
        when(studentRepositoryMock.save(studentMock)).thenReturn(studentMock);

        Student savedStudent = studentService.addStudent(studentMock);

        assertNotNull(savedStudent);
        assertEquals(studentMail, savedStudent.getEmail());

        verify(studentRepositoryMock).existsStudentByEmail(studentMail);
        verify(studentRepositoryMock).save(studentMock);
    }

    @Test
    void addStudentShouldReturnException() {
        String studentMail = "freddans@mail.com";

        when(studentMock.getEmail()).thenReturn(studentMail);

        when(studentRepositoryMock.existsStudentByEmail(studentMail)).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> {
            studentService.addStudent(studentMock);
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
        when(studentMock.getId()).thenReturn(studentId);

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
        when(studentMock.getId()).thenReturn(studentId);

        when(studentRepositoryMock.existsById(studentId)).thenReturn(true);
        when(studentRepositoryMock.save(studentMock)).thenReturn(studentMock);

        Student expectedStudent = studentMock;
        Student actualStudent = studentService.updateStudent(studentMock);

        assertEquals(expectedStudent, actualStudent, "Expected and Actual student are not the same.");

        verify(studentRepositoryMock).existsById(studentId);
        verify(studentRepositoryMock.save(studentMock));
    }

    @Test
    void updateStudentShouldReturnException() { // ResponseStatusException
        when(studentRepositoryMock.existsById(anyInt())).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            studentService.updateStudent(studentMock);
        }, "Exception was not thrown");

        verify(studentRepositoryMock).existsById(anyInt());
    }

    @Test
    void getStudentByIdShouldReturnStudent() {

        when(studentRepositoryMock.findById(anyInt())).thenReturn(Optional.of(studentMock));

        Student expectedStudent = studentMock;
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
    void setGradeForStudentByIdShouldReturnStudent() { // public Student setGradeForStudentById(int studentId, String gradeAsString)
    }

    @Test
    void setGradeForStudentByIdShouldReturnException() { // ResponseStatusException

    }
}