/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;

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
}
