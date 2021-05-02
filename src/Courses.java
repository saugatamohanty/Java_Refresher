import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;

/*
 * Represents results of courses taken by a student.
 */
public class Courses {
    private static ArrayList<Courses.CourseRecord> myCourseRecords;
    enum CompletionStatus {INCOMPLETE, WITHDRAWAL, COMPLETE};

    // Value class representing the data for a course.
    static public class CourseRecord {
        private final String name;
        private final double mark;
        private final int yearTaken;
        private final CompletionStatus status;
        CourseRecord( String name, double mark, int yearTaken, CompletionStatus status ) {
            this.name = name;
            this.mark = mark;

            this.yearTaken = yearTaken;
            this.status = status;
        }
        public String getName() { return name; }
        public double getMark() { return mark; }
        public int getYearTaken() { return yearTaken; }
        public CompletionStatus getStatus() { return status; }
        public String toString() {
            return "Course: " + name + "; mark: " + mark + "; year:" + yearTaken
                    + "; status:" + status;
        }
    }

    // Map of course names to all the fields
    private Map<String, CourseRecord> courseRecords;

    public Courses( List<CourseRecord> courseRecords ) {
        this.courseRecords = courseRecords.stream()
                .collect(
                        Collectors.toMap(CourseRecord::getName, Function.identity() ) );
    }

    /* Return the average marks per year for the given courses.
     * Only include completes for the year.
     */
    public Map<Integer, Double> averageSomeCoursesPerYear(
            Set<String> courseNames ) {

        Map<Integer, Double> averagesPerYear = new HashMap<>();
        Map<Integer, ArrayList<Double>> markTempMap = new HashMap<>();


        for(String courseName:courseNames){
            CourseRecord interestRecord = courseRecords.get(courseName);
            //System.out.println(courseName + " -- " +interestRecord.getName() + "--" + interestRecord.getStatus());

            if(interestRecord != null){
                if(interestRecord.getStatus() == CompletionStatus.COMPLETE){
                    int recordYear = interestRecord.getYearTaken();


                    double recordMarks = interestRecord.getMark();

                    if(markTempMap.containsKey(Integer.valueOf(recordYear))){
                        ArrayList numbers = markTempMap.get(Integer.valueOf(recordYear));
                        numbers.add(Double.valueOf(recordMarks));

                    }else{
                        ArrayList numbers = new ArrayList();
                        numbers.add(Double.valueOf(recordMarks));
                        markTempMap.put(Integer.valueOf(recordYear), numbers);

                    }

                }
            }

        }
        for (Map.Entry<Integer, ArrayList<Double>> set : markTempMap.entrySet()) {
            double sum = 0.0D;
            List<Double> numbers = set.getValue();
            for(Double number:numbers){
                sum = sum + number.doubleValue();
            }


            double average = sum/numbers.size();


            averagesPerYear.put(set.getKey(), (Double.valueOf(average)));
        }
        // Complete this method

        return averagesPerYear;
    }

    public void print() {
        System.out.println("Courses: ");
        for (String courseName : courseRecords.keySet()) {
            System.out.println(
                    courseRecords.get( courseName ).toString() );
        }
    }

    public static void main(String args[]){
        myCourseRecords = new ArrayList<>();
        myCourseRecords.add( new Courses.CourseRecord("MT101", 80.0, 2020,
                Courses.CompletionStatus.COMPLETE ) );
        myCourseRecords.add( new Courses.CourseRecord("CS101", 81.0, 2020,
                Courses.CompletionStatus.INCOMPLETE ) );
        myCourseRecords.add( new Courses.CourseRecord("HS101", 82.0, 2020,
                Courses.CompletionStatus.WITHDRAWAL ) );
        myCourseRecords.add( new Courses.CourseRecord("PS101", 83.0, 2020,
                Courses.CompletionStatus.COMPLETE ) );
        myCourseRecords.add( new Courses.CourseRecord("PH101", 84.0, 2020,
                Courses.CompletionStatus.COMPLETE ) );
        myCourseRecords.add( new Courses.CourseRecord("CS201", 85.0, 2021,
                Courses.CompletionStatus.COMPLETE ) );
        myCourseRecords.add( new Courses.CourseRecord("HS201", 86.0, 2021,
                Courses.CompletionStatus.COMPLETE ) );
        Courses courses = new  Courses( myCourseRecords );
        Set<String> courseNames = new HashSet<>();


        courseNames.add( "MT101" );
        courseNames.add( "CS101" );
        courseNames.add( "HS101" );
        courseNames.add( "PS101" );
        courseNames.add( "PH101" );
        courseNames.add( "NOT" );
        courseNames.add( "CS201" );
        courseNames.add( "HS201" );
        Map<Integer, Double> avgPerYear =
                courses.averageSomeCoursesPerYear( courseNames );


/*
    Courses courses = new  Courses( myCourseRecords );
    Set<String> courseNames = new HashSet<>();

     courseNames.add( "MT101" );
     courseNames.add( "CS101" );
     courseNames.add( "HS101" );
     courseNames.add( "PH101" );
    Map<Integer, Double> avgPerYear =
            courses.averageSomeCoursesPerYear( courseNames );*/
        System.out.println(avgPerYear);
    }


}
