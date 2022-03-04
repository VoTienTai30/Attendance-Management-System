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
import model.Attendance;
import model.Schedule;
import model.Student;

/**
 *
 * @author midni
 */
public class AttendanceDBContext extends DBContext {

    public void addAttendance(ArrayList<Attendance> list) {
        PreparedStatement stm = null;
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [dbo].[Attendence]\n"
                    + "           ([StudentID]\n"
                    + "           ,[ScheduleID]\n"
                    + "           ,[Present]\n"
                    + "           ,[AttendenceDate])\n"
                    + "     VALUES\n"
                    + "           (?, ?, 0, NULL)";
            for (Attendance a : list) {
                stm = connection.prepareStatement(sql);
                stm.setString(1, a.getStudentID().getStudentID());
                stm.setInt(2, a.getScheduleID().getScheduleID());
                stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void editAttendance(ArrayList<Attendance> list) {
        PreparedStatement stm = null;
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE dbo.Attendence SET Present = ?, AttendenceDate = GETDATE() WHERE AttendenceID = ?";
            for (Attendance a : list) {
                stm = connection.prepareStatement(sql);
                if (a.isPresent()) {
                    stm.setInt(1, 1);
                } else {
                    stm.setInt(1, 0);
                }
                stm.setInt(2, a.getAttendenceID());
                stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void deleteAttendance(int scheduleID) {
        String sql = "DELETE FROM dbo.Attendence WHERE ScheduleID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, scheduleID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<Attendance> getAttendance(int scheduleID) {
        ArrayList<Attendance> list = new ArrayList<>();
        try {
            String sql = "SELECT AttendenceID, Attendence.StudentID, StudentName, ScheduleID, Present, AttendenceDate "
                    + "FROM dbo.Attendence INNER JOIN dbo.Student ON Student.StudentID = Attendence.StudentID WHERE ScheduleID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, scheduleID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setAttendenceID(rs.getInt("AttendenceID"));
                Student s = new Student();
                s.setStudentID(rs.getString("StudentID"));
                s.setStudentName(rs.getString("StudentName"));
                a.setStudentID(s);
                Schedule sche = new Schedule();
                sche.setScheduleID(rs.getInt("ScheduleID"));
                a.setScheduleID(sche);
                if (rs.getInt("Present") == 1) {
                    a.setPresent(true);
                } else {
                    a.setPresent(false);
                }
                a.setAttendenceDate(rs.getDate("AttendenceDate"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean isAttendanceYet(int scheduleID) {
        try {
            String sql = "SELECT TOP (1) * FROM dbo.Attendence WHERE ScheduleID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, scheduleID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getDate("AttendenceDate") == null) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
