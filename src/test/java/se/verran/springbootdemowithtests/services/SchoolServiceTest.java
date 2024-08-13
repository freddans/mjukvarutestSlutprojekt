package se.verran.springbootdemowithtests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import se.verran.springbootdemowithtests.controllers.SchoolController;
import se.verran.springbootdemowithtests.entities.Student;

import java.util.ArrayList;
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
        student = new Student();
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

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

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
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

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
        Student student2 = new Student();
        Student student3 = new Student();

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);
        studentList.add(student3);

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

        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();
        Student student5 = new Student();
        Student student6 = new Student();
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);

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
    void calculateAverageGrade() {
    }

    @Test
    void getTopScoringStudents() {
    }
}