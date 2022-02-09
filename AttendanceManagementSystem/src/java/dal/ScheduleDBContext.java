/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Schedule;

/**
 *
 * @author midni
 */
public class ScheduleDBContext extends DBContext {

    public void addSchedule(Schedule schedule) {
        try {
            String sql = "INSERT INTO [dbo].[Schedule] ([TeacherID], [SubjectID], [ClassID], [TimeSlotID], [ScheduleDate]) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, schedule.getTeacherID().getTeacherID());
            stm.setInt(2, schedule.getSubjectID().getSubjectID());
            stm.setInt(3, schedule.getClassID().getClassID());
            stm.setInt(4, schedule.getTimeSlotID().getTimeSlotID());
            stm.setDate(5, schedule.getScheduleDate());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSchedule(int id) {
        try {
            String sql = "DELETE FROM [Attendance_Management].[dbo].[Schedule] WHERE [ScheduleID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editSchedule(int scheduleID, int teacherID, int subjectID, int classID, int timeSlotID, Date scheduleDate) {
        try {
            String sql = "UPDATE [Attendance_Management].[dbo].[Schedule] SET [TeacherID] = ?, "
                    + "[SubjectID] = ?, [ClassID] = ?, [TimeSlotID] = ?, [ScheduleDate] = ? WHERE [ScheduleID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, teacherID);
            stm.setInt(2, subjectID);
            stm.setInt(3, classID);
            stm.setInt(4, timeSlotID);
            stm.setDate(5, scheduleDate);
            stm.setInt(6, scheduleID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Schedule> getSchedule(String date) {
        TeacherDBContext teacherDB = new TeacherDBContext();
        SubjectDBContext subjectDB = new SubjectDBContext();
        ClassDBContext classDB = new ClassDBContext();
        TimeSlotDBContext timeSlotDB = new TimeSlotDBContext();
        ArrayList<Schedule> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Schedule";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (date == null) {
                    Schedule schedule = new Schedule();
                    schedule.setScheduleID(rs.getInt("ScheduleID"));
                    schedule.setTeacherID(teacherDB.getTeacherByID(rs.getInt("TeacherID")));
                    schedule.setSubjectID(subjectDB.getSubjectByID(rs.getInt("SubjectID")));
                    schedule.setClassID(classDB.getClasseByID(rs.getInt("ClassID")));
                    schedule.setTimeSlotID(timeSlotDB.getTimeSlot(rs.getInt("TimeSlotID")).get(0));
                    schedule.setScheduleDate(rs.getDate("ScheduleDate"));
                    list.add(schedule);
                } else if (rs.getDate("ScheduleDate").toString().compareTo(date) == 0) {
                    Schedule schedule = new Schedule();
                    schedule.setScheduleID(rs.getInt("ScheduleID"));
                    schedule.setTeacherID(teacherDB.getTeacherByID(rs.getInt("TeacherID")));
                    schedule.setSubjectID(subjectDB.getSubjectByID(rs.getInt("SubjectID")));
                    schedule.setClassID(classDB.getClasseByID(rs.getInt("ClassID")));
                    schedule.setTimeSlotID(timeSlotDB.getTimeSlot(rs.getInt("TimeSlotID")).get(0));
                    schedule.setScheduleDate(rs.getDate("ScheduleDate"));
                    list.add(schedule);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Schedule> getScheduleByUsername(String user) {
        ArrayList<Schedule> rawList = getSchedule(null);
        ArrayList<Schedule> list = new ArrayList<>();
        for (int i = 0; i < rawList.size(); i++) {
            Schedule get = rawList.get(i);
            if (get.getTeacherID().getTeacherUsername().getUser().compareTo(user) == 0) {
                list.add(get);
            }
        }
        return list;
    }
}
