package se.verran.springbootdemowithtests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se.verran.springbootdemowithtests.entities.Student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SchoolServiceTest {

    private StudentService studentServiceMock;
    private SchoolService schoolService;

    @BeforeEach
    void setUp() {
        studentServiceMock = mock(StudentService.class);
        schoolService = new SchoolService(studentServiceMock);
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWhereGroupsLessThanTwoShouldReturnString() {
        List<Student> studentList = Arrays.asList(new Student());

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualMessage = schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(1);

        assertEquals("There should be at least two groups", actualMessage, "Messages are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWhereGroupsAreMoreThanStudentsShouldReturnString() {
        List<Student> studentList = Arrays.asList(new Student());
        int nrOfGroups = 2;

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualMessage = schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(nrOfGroups);

        assertEquals("Not able to divide 1 students into 2 groups", actualMessage, "Messages are not identical.");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWhereStudentsPerGroupAreLessThanTwoShouldReturnString() {
        List<Student> studentList = Arrays.asList(new Student(), new Student(), new Student());
        int nrOfGroups = 2;

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualMessage = schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(nrOfGroups);

        assertEquals("Not able to manage 2 groups with 3 students", actualMessage, "Messages are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsSuccessfulWithNoRemainderShouldReturnString() {
        List<Student> studentList = Arrays.asList(new Student(), new Student(), new Student(), new Student(), new Student(), new Student());
        int nrOfGroups = 3;

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualMessage = schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(nrOfGroups);

        assertEquals("3 groups could be formed with 2 students per group", actualMessage, "Messages are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsSuccessfulWithRemainderShouldReturnString() {
        List<Student> studentList = Arrays.asList(new Student(), new Student(), new Student(), new Student(), new Student());
        int nrOfGroups = 2;

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualMessage = schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(nrOfGroups);

        assertEquals("2 groups could be formed with 2 students per group, but that would leave 1 student hanging", actualMessage, "Messages are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWhereStudentsPerGroupAreLessThanTwoShouldReturnString() {
        List<Student> studentList = Arrays.asList(new Student());

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualResult = schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentList.size());

        assertEquals("Size of group should be at least 2", actualResult, "Messages was not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWhereNumberOfStudentsAreLessThanStudentsPerGroupShouldReturnString() {
        List<Student> studentList = Arrays.asList(
                new Student(), new Student(), new Student(),
                new Student(), new Student(), new Student(),
                new Student(), new Student(), new Student()
        );
        int studentsPerGroup = 5;

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualResult = schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentsPerGroup);

        assertEquals("Not able to manage groups of 5 with only 9 students", actualResult, "Messages was not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWhereNumberOfStudentsDividedWithStudentsPerGroupIsLessThanTwoShouldReturnString() {
        List<Student> studentList = Arrays.asList(new Student(), new Student(), new Student());
        int studentsPerGroup = 2;

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualResult = schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentsPerGroup);

        assertEquals("Not able to manage groups of 2 with only 3 students", actualResult, "Messages was not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfSuccessfulWithNoRemainderShouldReturnString() {
        List<Student> studentList = Arrays.asList(new Student(), new Student(), new Student(), new Student());
        int studentsPerGroup = 2;

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualResult = schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentsPerGroup);

        assertEquals("2 students per group is possible, there will be 2 groups", actualResult, "Messages are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfSuccessfulWithRemainderOneShouldReturnString() {
        List<Student> studentList = Arrays.asList(new Student(), new Student(), new Student(), new Student(), new Student());
        int studentsPerGroup = 2;

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String actualResult = schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentsPerGroup);

        assertEquals("2 students per group is possible, there will be 2 groups, there will be 1 student hanging", actualResult, "Messages are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void calculateAverageGradeWithEmptyListShouldReturnException() {
        List<Student> studentList = Collections.emptyList();

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            schoolService.calculateAverageGrade();
        }, "Exception was not thrown");

        assertEquals("No students found", response.getReason(), "Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Status Codes was not identical");

        verify(studentServiceMock).getAllStudents();
    }
    @Test
    void calculateAverageGradeSuccessfulShouldReturnStringFormat() {
        List<Student> studentList = Arrays.asList(
                new Student() {{
                    setJavaProgrammingGrade(1.5);
                }},
                new Student() {{
                    setJavaProgrammingGrade(4.0);
                }},
                new Student() {{
                    setJavaProgrammingGrade(4.5);
                }},
                new Student() {{
                    setJavaProgrammingGrade(5.0);
                }},
                new Student() {{
                    setJavaProgrammingGrade(1.0);
                }},
                new Student() {{
                    setJavaProgrammingGrade(3.0);
                }}
        );

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String result = schoolService.calculateAverageGrade();

        assertEquals("Average grade is 3,2", result, "Expected and Actual message are not identical");

        verify(studentServiceMock).getAllStudents();
    }



    @Test
    void getTopScoringStudentsShouldReturnException() {
        List<Student> studentList = Collections.emptyList();

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        ResponseStatusException response = assertThrows(ResponseStatusException.class, () -> {
            schoolService.getTopScoringStudents();
        }, "Exception was not thrown");

        assertEquals("No students found", response.getReason(), "Exceptions was not identical");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Status Codes was not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void getTopScoringStudentsSuccessfulShouldReturnSortedStudentList() {
        List<Student> studentList = Arrays.asList(
                new Student("Anders", "Andersson", LocalDate.of(1998, 2, 15), "anders@email.com") {{
                    setJavaProgrammingGrade(2.5);
                }},
                new Student("Bertil", "Bertilsson", LocalDate.of(1956, 11, 5), "bertil@bertilssons.se") {{
                    setJavaProgrammingGrade(5.0);
                }},
                new Student("Carl", "Carlsson", LocalDate.of(2001, 3, 18), "carl@gmail.com") {{
                    setJavaProgrammingGrade(1.5);
                }},
                new Student("Mikaela", "Mikaelsdottir", LocalDate.of(1972, 6, 22), "mikaelas@foretag.se") {{
                    setJavaProgrammingGrade(2.0);
                }}
        );

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        List<Student> actualList = schoolService.getTopScoringStudents();

        assertEquals(1, actualList.size(), "More or less than 20% of the students were added into the list");
        assertEquals("Bertil", actualList.get(0).getFirstName(), "Name was not identical");

        verify(studentServiceMock).getAllStudents();
    }
}