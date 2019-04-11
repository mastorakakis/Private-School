package functions;

import entities.Course;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Schedule {

    public static int getWeekDays(Course course) {
        List<String> daily = new ArrayList();
        LocalDate startDate = course.getStartDate();
        LocalDate endDate = course.getEndDate();
        int weekDays = 1;
        while (startDate.isBefore(endDate)) {
            DayOfWeek day = startDate.getDayOfWeek();
            if (day != DayOfWeek.SUNDAY && day != DayOfWeek.SATURDAY) {
                weekDays++;
            }
            startDate = startDate.plusDays(1);
        }
        return weekDays;
    }

    public static void getJava(Course course) {

    }

}
