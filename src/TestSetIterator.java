import java.util.*;

public class TestSetIterator<courseNames> {
    enum CompletionStatus {INCOMPLETE, WITHDRAWAL, COMPLETE};
    private static ArrayList<Courses.CourseRecord> myCourseRecords;


    public static void main(String agrs[]) {


        myCourseRecords = new ArrayList<>();
        myCourseRecords.add(new Courses.CourseRecord("MT101", 80.0, 2020,
                Courses.CompletionStatus.COMPLETE));
        myCourseRecords.add(new Courses.CourseRecord("CS101", 81.0, 2020,
                Courses.CompletionStatus.INCOMPLETE));
        myCourseRecords.add(new Courses.CourseRecord("HS101", 82.0, 2020,
                Courses.CompletionStatus.WITHDRAWAL));
        myCourseRecords.add(new Courses.CourseRecord("PS101", 83.0, 2020,
                Courses.CompletionStatus.COMPLETE));
        myCourseRecords.add(new Courses.CourseRecord("PH101", 84.0, 2020,
                Courses.CompletionStatus.COMPLETE));
        myCourseRecords.add(new Courses.CourseRecord("CS201", 85.0, 2021,
                Courses.CompletionStatus.COMPLETE));
        myCourseRecords.add(new Courses.CourseRecord("HS201", 86.0, 2021,
                Courses.CompletionStatus.COMPLETE));

        Courses courses = new Courses(myCourseRecords);
        HashSet<String> courseNames = new HashSet<>();
        courseNames.add("MT101");
        courseNames.add("CS101");
        courseNames.add("HS101");
        courseNames.add("PH101");
        Map<Integer, Double> avgPerYear =
                courses.averageSomeCoursesPerYear(courseNames);
        System.out.println(avgPerYear);
    }

}
