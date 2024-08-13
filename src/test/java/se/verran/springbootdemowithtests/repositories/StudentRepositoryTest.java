package se.verran.springbootdemowithtests.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.verran.springbootdemowithtests.entities.Student;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentRepositoryTest {

    //TODO CHANGE ALL OF THIS - h2 database should be used - NO MOCKING
    private StudentRepository studentRepositoryMock;

    @BeforeEach
    void setUp() {
        studentRepositoryMock = mock(StudentRepository.class);
    }

    @Test
    void existsStudentByEmail() {
        String studentEmail = "freddans@testmail.com";

        when(studentRepositoryMock.existsStudentByEmail(studentEmail)).thenReturn(true);

        boolean isStudent = studentRepositoryMock.existsStudentByEmail(studentEmail);

        assertTrue(isStudent, "Unexpected answer");

        verify(studentRepositoryMock).existsStudentByEmail(studentEmail);
    }
}