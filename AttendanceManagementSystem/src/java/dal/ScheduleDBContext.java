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
        String sql = "INSERT INTO [dbo].[Schedule] ([TeacherID], [SubjectID], [ClassID], [TimeSlotID], [ScheduleDate]) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, schedule.getTeacherID().getTeacherID());
            stm.setInt(2, schedule.getSubjectID().getSubjectID());
            stm.setInt(3, schedule.getClassID().getClassID());
            stm.setInt(4, schedule.getTimeSlotID().getTimeSlotID());
            stm.setDate(5, schedule.getScheduleDate());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void deleteSchedule(int id) {
        String sql = "DELETE FROM [Attendance_Management].[dbo].[Schedule] WHERE [ScheduleID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void editSchedule(int scheduleID, int teacherID, int subjectID, int classID, int timeSlotID, Date scheduleDate) {
        String sql = "UPDATE [Attendance_Management].[dbo].[Schedule] SET [TeacherID] = ?, "
                + "[SubjectID] = ?, [ClassID] = ?, [TimeSlotID] = ?, [ScheduleDate] = ? WHERE [ScheduleID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, teacherID);
            stm.setInt(2, subjectID);
            stm.setInt(3, classID);
            stm.setInt(4, timeSlotID);
            stm.setDate(5, scheduleDate);
            stm.setInt(6, scheduleID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<Schedule> getSchedule(String date, int pageIndex, int pageSize) {
        TeacherDBContext teacherDB = new TeacherDBContext();
        SubjectDBContext subjectDB = new SubjectDBContext();
        ClassDBContext classDB = new ClassDBContext();
        TimeSlotDBContext timeSlotDB = new TimeSlotDBContext();
        ArrayList<Schedule> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY ScheduleID DESC) AS row_index, * FROM dbo.Schedule) schedule\n"
                    + "WHERE row_index BETWEEN (? - 1) * ? + 1 AND ? * ?";
            if (date != null) {
                sql = "SELECT * FROM \n"
                        + "(SELECT ROW_NUMBER() OVER (ORDER BY ScheduleID DESC) AS row_index, * FROM dbo.Schedule WHERE ScheduleDate = ?) schedule\n"
                        + "WHERE row_index BETWEEN (? - 1) * ? + 1 AND ? * ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (date == null) {
                stm.setInt(1, pageIndex);
                stm.setInt(2, pageSize);
                stm.setInt(3, pageIndex);
                stm.setInt(4, pageSize);
            } else {
                stm.setString(1, date);
                stm.setInt(2, pageIndex);
                stm.setInt(3, pageSize);
                stm.setInt(4, pageIndex);
                stm.setInt(5, pageSize);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleID(rs.getInt("ScheduleID"));
                schedule.setTeacherID(teacherDB.getTeacherByID(rs.getInt("TeacherID")));
                schedule.setSubjectID(subjectDB.getSubjectByID(rs.getInt("SubjectID")));
                schedule.setClassID(classDB.getClasseByID(rs.getInt("ClassID")));
                schedule.setTimeSlotID(timeSlotDB.getTimeSlot(rs.getInt("TimeSlotID")).get(0));
                schedule.setScheduleDate(rs.getDate("ScheduleDate"));
                list.add(schedule);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Schedule> getScheduleByUsername(String user) {
        ArrayList<Schedule> rawList = getSchedule(null, 1, count(null));
        ArrayList<Schedule> list = new ArrayList<>();
        for (int i = 0; i < rawList.size(); i++) {
            Schedule get = rawList.get(i);
            if (get.getTeacherID().getTeacherUsername().getUser().compareTo(user) == 0) {
                list.add(get);
            }
        }
        return list;
    }

    public Schedule getScheduleByID(int id) {
        TeacherDBContext teacherDB = new TeacherDBContext();
        SubjectDBContext subjectDB = new SubjectDBContext();
        ClassDBContext classDB = new ClassDBContext();
        TimeSlotDBContext timeSlotDB = new TimeSlotDBContext();
        Schedule schedule = new Schedule();
        try {
            String sql = "SELECT * FROM dbo.Schedule WHERE ScheduleID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                schedule.setScheduleID(rs.getInt("ScheduleID"));
                schedule.setTeacherID(teacherDB.getTeacherByID(rs.getInt("TeacherID")));
                schedule.setSubjectID(subjectDB.getSubjectByID(rs.getInt("SubjectID")));
                schedule.setClassID(classDB.getClasseByID(rs.getInt("ClassID")));
                schedule.setTimeSlotID(timeSlotDB.getTimeSlot(rs.getInt("TimeSlotID")).get(0));
                schedule.setScheduleDate(rs.getDate("ScheduleDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedule;
    }

    public int count(String date) {
        try {
            String sql = "SELECT COUNT(*) Total FROM dbo.Schedule";
            if (date != null) {
                sql += " WHERE ScheduleDate = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (date != null) {
                stm.setDate(1, Date.valueOf(date));
            }
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
