package se.verran.springbootdemowithtests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import se.verran.springbootdemowithtests.controllers.SchoolController;
import se.verran.springbootdemowithtests.entities.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class SchoolServiceTest {

    private StudentService studentServiceMock;
    private SchoolService schoolService;
    private Student student;

    @BeforeEach
    void setUp() {
        studentServiceMock = mock(StudentService.class);
        schoolService = new SchoolService(studentServiceMock);
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWhereGroupsLessThanTwoShouldReturnString() {


//        public String numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(int numberOfGroups) {
//            int numberOfStudents = studentService.getAllStudents().size();
//
//TODO        if(numberOfGroups < 2)
//                return "There should be at least two groups";
//
//            if(numberOfGroups > numberOfStudents)
//                return String.format("Not able to divide %s students into %s groups", numberOfStudents, numberOfGroups);
//            int studentsPerGroup = numberOfStudents / numberOfGroups;
//            if(studentsPerGroup < 2)
//                return String.format("Not able to manage %s groups with %s students", numberOfGroups, numberOfStudents);
//            int remainder = numberOfStudents % numberOfGroups;
//            return String.format("%s groups could be formed with %s students per group%s",
//                    numberOfGroups,
//                    studentsPerGroup,
//                    (remainder == 0 ? "" : String.format(", but that would leave %s student" + (remainder == 1 ? "" : "s") + " hanging", remainder)));
//        }

        List<Student> studentList = Arrays.asList(new Student());

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String expectedMessage = "There should be at least two groups";
        String actualMessage = schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(1);

        assertEquals(expectedMessage, actualMessage, "Messages are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWhereGroupsAreMoreThanStudentsShouldReturnString() {
//        public String numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(int numberOfGroups) {
//            int numberOfStudents = studentService.getAllStudents().size();
//
//            if(numberOfGroups < 2)
//                return "There should be at least two groups";

//TODO            if(numberOfGroups > numberOfStudents)
//                return String.format("Not able to divide %s students into %s groups", numberOfStudents, numberOfGroups);

//            int studentsPerGroup = numberOfStudents / numberOfGroups;
//            if(studentsPerGroup < 2)
//                return String.format("Not able to manage %s groups with %s students", numberOfGroups, numberOfStudents);
//            int remainder = numberOfStudents % numberOfGroups;
//            return String.format("%s groups could be formed with %s students per group%s",
//                    numberOfGroups,
//                    studentsPerGroup,
//                    (remainder == 0 ? "" : String.format(", but that would leave %s student" + (remainder == 1 ? "" : "s") + " hanging", remainder)));
//        }
        List<Student> studentList = Arrays.asList(new Student());

        int nrOfGroups = 2;
        int nrOfStudents = studentList.size();

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String expectedMessage = String.format("Not able to divide %s students into %s groups", nrOfStudents, nrOfGroups);
        String actualMessage = schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(nrOfGroups);

        assertEquals(expectedMessage, actualMessage, "Messages are not identical.");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWhereStudentsPerGroupAreLessThanTwoShouldReturnString() {
//        public String numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(int numberOfGroups) {
//            int numberOfStudents = studentService.getAllStudents().size();
//
//            if(numberOfGroups < 2)
//                return "There should be at least two groups";
//
//            if(numberOfGroups > numberOfStudents)
//                return String.format("Not able to divide %s students into %s groups", numberOfStudents, numberOfGroups);
//
//            int studentsPerGroup = numberOfStudents / numberOfGroups;
//TODO        if(studentsPerGroup < 2)
//                return String.format("Not able to manage %s groups with %s students", numberOfGroups, numberOfStudents);
//
//            int remainder = numberOfStudents % numberOfGroups;
//            return String.format("%s groups could be formed with %s students per group%s",
//                    numberOfGroups,
//                    studentsPerGroup,
//                    (remainder == 0 ? "" : String.format(", but that would leave %s student" + (remainder == 1 ? "" : "s") + " hanging", remainder)));
//        }

        List<Student> studentList = Arrays.asList(new Student(), new Student(), new Student());

        int nrOfGroups = 2;
        int nrOfStudents = studentList.size();

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String expectedMessage = String.format("Not able to manage %s groups with %s students", nrOfGroups, nrOfStudents);
        String actualMessage = schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(nrOfGroups);

        assertEquals(expectedMessage, actualMessage, "Messages are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsSuccessfulShouldReturnString() {
//        public String numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(int numberOfGroups) {
//            int numberOfStudents = studentService.getAllStudents().size();
//
//            if(numberOfGroups < 2)
//                return "There should be at least two groups";
//
//            if(numberOfGroups > numberOfStudents)
//                return String.format("Not able to divide %s students into %s groups", numberOfStudents, numberOfGroups);
//
//            int studentsPerGroup = numberOfStudents / numberOfGroups;
//            if(studentsPerGroup < 2)
//                return String.format("Not able to manage %s groups with %s students", numberOfGroups, numberOfStudents);
//
//            int remainder = numberOfStudents % numberOfGroups;
//TODO        return String.format("%s groups could be formed with %s students per group%s",
//                    numberOfGroups,
//                    studentsPerGroup,
//                    (remainder == 0 ? "" : String.format(", but that would leave %s student" + (remainder == 1 ? "" : "s") + " hanging", remainder)));
//        }

        List<Student> studentList = Arrays.asList(new Student(), new Student(), new Student(), new Student(), new Student(), new Student());

        int nrOfGroups = 3;
        int nrOfStudents = studentList.size();
        int nrOfStudentsPerGroup = nrOfStudents / nrOfGroups;
        int remainder = nrOfStudents % nrOfGroups;

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String expectedMessage = String.format("%s groups could be formed with %s students per group%s",
                    nrOfGroups,
                    nrOfStudentsPerGroup,
                    (remainder == 0 ? "" : String.format(", but that would leave %s student" + (remainder == 1 ? "" : "s") + " hanging", remainder)));

        String actualMessage = schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(nrOfGroups);

        assertEquals(expectedMessage, actualMessage, "Messages are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWhereStudentsPerGroupAreLessThanTwoShouldReturnString() {
//        public String numberOfGroupsWhenDividedIntoGroupsOf(int studentsPerGroup){
//            int numberOfStudents = studentService.getAllStudents().size();
//TODO        if(studentsPerGroup < 2)
//                return "Size of group should be at least 2";
//
//            if(numberOfStudents < studentsPerGroup || numberOfStudents / studentsPerGroup < 2)
//                return String.format("Not able to manage groups of %s with only %s students", studentsPerGroup, numberOfStudents);
//            int numberOfGroups = numberOfStudents / studentsPerGroup;
//            int remainder = numberOfStudents % studentsPerGroup;
//            return String.format("%s students per group is possible, there will be %s groups" +
//                            (remainder == 0 ? "":", there will be " + remainder + " student" + (remainder == 1 ? "" : "s") + " hanging"),
//                    studentsPerGroup, numberOfGroups,
//                    (remainder == 0 ? "":String.format(", there will be %s student" + (remainder == 1 ? "" : "s") + " hanging", remainder)));
//        }
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWhereNumberOfStudentsAreLessThanStudentsPerGroupShouldreturnString() {
//        public String numberOfGroupsWhenDividedIntoGroupsOf(int studentsPerGroup){
//            int numberOfStudents = studentService.getAllStudents().size();
//            if(studentsPerGroup < 2)
//                return "Size of group should be at least 2";
//
//TODO            if(numberOfStudents < studentsPerGroup || NOT TODO HERE numberOfStudents / studentsPerGroup < 2)
//TODO            return String.format("Not able to manage groups of %s with only %s students", studentsPerGroup, numberOfStudents);
//
//            int numberOfGroups = numberOfStudents / studentsPerGroup;
//            int remainder = numberOfStudents % studentsPerGroup;
//            return String.format("%s students per group is possible, there will be %s groups" +
//                            (remainder == 0 ? "":", there will be " + remainder + " student" + (remainder == 1 ? "" : "s") + " hanging"),
//                    studentsPerGroup, numberOfGroups,
//                    (remainder == 0 ? "":String.format(", there will be %s student" + (remainder == 1 ? "" : "s") + " hanging", remainder)));
//        }
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWhereNumberOfStudentsDividedWithStudentsPerGroupIsLessThanTwoShouldReturnString() {
//        public String numberOfGroupsWhenDividedIntoGroupsOf(int studentsPerGroup){
//            int numberOfStudents = studentService.getAllStudents().size();
//            if(studentsPerGroup < 2)
//                return "Size of group should be at least 2";
//
//            if(numberOfStudents < studentsPerGroup || TODO numberOfStudents / studentsPerGroup < 2)
//TODO            return String.format("Not able to manage groups of %s with only %s students", studentsPerGroup, numberOfStudents);
//
//            int numberOfGroups = numberOfStudents / studentsPerGroup;
//            int remainder = numberOfStudents % studentsPerGroup;
//            return String.format("%s students per group is possible, there will be %s groups" +
//                            (remainder == 0 ? "":", there will be " + remainder + " student" + (remainder == 1 ? "" : "s") + " hanging"),
//                    studentsPerGroup, numberOfGroups,
//                    (remainder == 0 ? "":String.format(", there will be %s student" + (remainder == 1 ? "" : "s") + " hanging", remainder)));
//        }
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfSuccessfulShouldReturnString() {
//        public String numberOfGroupsWhenDividedIntoGroupsOf(int studentsPerGroup){
//            int numberOfStudents = studentService.getAllStudents().size();
//            if(studentsPerGroup < 2)
//                return "Size of group should be at least 2";
//
//            if(numberOfStudents < studentsPerGroup || numberOfStudents / studentsPerGroup < 2)
//                return String.format("Not able to manage groups of %s with only %s students", studentsPerGroup, numberOfStudents);
//
//            int numberOfGroups = numberOfStudents / studentsPerGroup;
//            int remainder = numberOfStudents % studentsPerGroup;
//TODO        return String.format("%s students per group is possible, there will be %s groups" +
//                            (remainder == 0 ? "":", there will be " + remainder + " student" + (remainder == 1 ? "" : "s") + " hanging"),
//                    studentsPerGroup, numberOfGroups,
//                    (remainder == 0 ? "":String.format(", there will be %s student" + (remainder == 1 ? "" : "s") + " hanging", remainder)));
//
//        }
    }

    @Test
    void calculateAverageGradeWithEmptyListShouldReturnException() {
//        public String calculateAverageGrade() {
//            List<Student> studentList = studentService.getAllStudents();
//TODO        if(studentList.isEmpty())
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No students found");
//
//            double totalSum = 0.0;
//            for (Student student: studentList)
//                totalSum += student.getJavaProgrammingGrade();
//            double average = totalSum / studentList.size();
//            return String.format("Average grade is %.1f", average);
//        }
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
    void calculateAverageGrade2() {
        //TODO ERROR HERE HAVE TO CONTINUE AND MAKE THIS WORK

//        public String calculateAverageGrade() {
//            List<Student> studentList = studentService.getAllStudents();
//            if(studentList.isEmpty())
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No students found");
//            double totalSum = 0.0;
//            for (Student student: studentList)
//                totalSum += student.getJavaProgrammingGrade();
//            double average = totalSum / studentList.size();
//TODO        return String.format("Average grade is %.1f", average);
//        }
        List<Student> studentList = Arrays.asList(student);
        double totalSum = 0.0;

        for (Student student : studentList) {
            totalSum += student.getJavaProgrammingGrade();
        }
        double average = totalSum / studentList.size();

        when(studentServiceMock.getAllStudents()).thenReturn(studentList);

        String result = schoolService.calculateAverageGrade();

        assertEquals(String.format("Average grade is %.1f", average), result, "Expected and Actual message are not identical");

        verify(studentServiceMock).getAllStudents();
    }

    @Test
    void calculateAverageGrade3() {
    }

    @Test
    void calculateAverageGrade4() {
    }

    @Test
    void getTopScoringStudents() {
    }
}