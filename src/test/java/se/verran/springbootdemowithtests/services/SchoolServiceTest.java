package se.verran.springbootdemowithtests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import se.verran.springbootdemowithtests.controllers.SchoolController;
import se.verran.springbootdemowithtests.entities.Student;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolServiceTest {

    private StudentService studentServiceMock;
    private SchoolService schoolService;
    private SchoolController schoolController;

    @BeforeEach
    void setUp() {
        studentServiceMock = mock(StudentService.class);
        schoolService = new SchoolService(studentServiceMock);
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups() {

//        List<Student> students = Collections.emptyList();
//
//        when(studentServiceMock.getAllStudents()).thenReturn(students);
//
//        ResponseEntity<Integer> responseNumber = schoolController.countStudents();
//
//        assertEquals(0, responseNumber.getBody());
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOf() {
    }

    @Test
    void calculateAverageGrade() {
    }

    @Test
    void getTopScoringStudents() {
    }
}