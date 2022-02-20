/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.StudentAttendance;
import model.TimeSlot;

/**
 *
 * @author midni
 */
public class StudentAttendanceDBContext extends DBContext {

    public ArrayList<StudentAttendance> getStudentAttendance(String studentID, int subjectID) {
        ArrayList<StudentAttendance> list = new ArrayList<>();
        try {
            String sql = "SELECT Attendence.StudentID, StudentName, TeacherName, ClassName, Schedule.SubjectID, SubjectName, Present, ScheduleDate, TimeSlotStart, TimeSlotEnd FROM dbo.Attendence \n"
                    + "  INNER JOIN dbo.Schedule ON Schedule.ScheduleID = Attendence.ScheduleID \n"
                    + "  INNER JOIN dbo.Subject ON Subject.SubjectID = Schedule.SubjectID \n"
                    + "  INNER JOIN dbo.Student ON Student.StudentID = Attendence.StudentID\n"
                    + "  INNER JOIN dbo.TimeSlot ON TimeSlot.TimeSlotID = Schedule.TimeSlotID\n"
                    + "  INNER JOIN dbo.Teacher ON Teacher.TeacherID = Schedule.TeacherID\n"
                    + "  INNER JOIN dbo.Class ON Class.ClassID = Schedule.ClassID\n"
                    + "  WHERE Attendence.StudentID = ? AND Schedule.SubjectID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentID);
            stm.setInt(2, subjectID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                StudentAttendance sa = new StudentAttendance();
                sa.setStudentID(rs.getString("StudentID"));
                sa.setStudentName(rs.getString("StudentName"));
                sa.setSubjectID(rs.getInt("SubjectID"));
                sa.setSubjectName(rs.getString("SubjectName"));
                if (rs.getInt("Present") == 1) {
                    sa.setPresent(true);
                } else {
                    sa.setPresent(false);
                }
                sa.setScheduleDate(rs.getDate("ScheduleDate"));
                TimeSlot t = new TimeSlot();
                t.setTimeSlotStart(rs.getTime("TimeSlotStart"));
                t.setTimeSlotEnd(rs.getTime("TimeSlotEnd"));
                sa.setTimeslot(t);
                sa.setTeacherName(rs.getString("TeacherName"));
                sa.setClassName(rs.getString("ClassName"));
                list.add(sa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Integer> listSubjectID(String studentID) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            String sql = "SELECT SubjectID FROM dbo.Attendence INNER JOIN dbo.Schedule ON Schedule.ScheduleID = Attendence.ScheduleID\n"
                    + "  INNER JOIN dbo.Student ON Student.StudentID = Attendence.StudentID WHERE Attendence.StudentID = ? GROUP BY SubjectID";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("SubjectID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
