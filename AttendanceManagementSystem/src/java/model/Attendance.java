package model;

import java.sql.Date;

/**
 *
 * @author midni
 */
public class Attendance {

    private int attendenceID;
    private Student studentID;
    private Schedule scheduleID;
    private Date attendenceDate;
    private boolean present;

    public int getAttendenceID() {
        return attendenceID;
    }

    public void setAttendenceID(int attendenceID) {
        this.attendenceID = attendenceID;
    }

    public Student getStudentID() {
        return studentID;
    }

    public void setStudentID(Student studentID) {
        this.studentID = studentID;
    }

    public Schedule getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Schedule scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Date getAttendenceDate() {
        return attendenceDate;
    }

    public void setAttendenceDate(Date attendenceDate) {
        this.attendenceDate = attendenceDate;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
