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
import model.TimeSlot;

/**
 *
 * @author midni
 */
public class TimeSlotDBContext extends DBContext {

    public ArrayList<TimeSlot> getTimeSlot(int id) {
        ArrayList<TimeSlot> list = new ArrayList<>();
        try {
            String sql = "SELECT [TimeSlotID], [TimeSlotStart], [TimeSlotEnd] FROM [Attendance_Management].[dbo].[TimeSlot]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (id == -1) {
                    TimeSlot t = new TimeSlot();
                    t.setTimeSlotID(rs.getInt("TimeSlotID"));
                    t.setTimeSlotStart(rs.getTime("TimeSlotStart"));
                    t.setTimeSlotEnd(rs.getTime("TimeSlotEnd"));
                    list.add(t);
                } else if (rs.getInt("TimeSlotID") == id) {
                    TimeSlot t = new TimeSlot();
                    t.setTimeSlotID(id);
                    t.setTimeSlotStart(rs.getTime("TimeSlotStart"));
                    t.setTimeSlotEnd(rs.getTime("TimeSlotEnd"));
                    list.add(t);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
