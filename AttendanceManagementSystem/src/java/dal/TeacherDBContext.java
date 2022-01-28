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
import model.Teacher;

/**
 *
 * @author midni
 */
public class TeacherDBContext extends DBContext {

    public void addTeacher(Teacher t) {
        try {
            String sql = "INSERT INTO [dbo].[Teacher] ([TeacherName], [TeacherGender], "
                    + "[TeacherAddress], [TeacherEmail], [TeacherPhone], [TeacherDOB]) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, t.getTeacherName());
            if (t.isTeacherGender()) {
                stm.setInt(2, 1);
            } else {
                stm.setInt(2, 0);
            }
            stm.setString(3, t.getTeacherAddress());
            stm.setString(4, t.getTeacherEmail());
            stm.setString(5, t.getTeacherPhone());
            stm.setDate(6, t.getTeacherDOB());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTeacher(int id) {
        try {
            String sql = "DELETE FROM [Attendance_Management].[dbo].[Teacher] WHERE [TeacherID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editTeacher(int TeacherID, String TeacherName, int TeacherGender, String TeacherAddress, String TeacherEmail, String TeacherPhone, Date TeacherDOB) {
        try {
            String sql = "UPDATE [Attendance_Management].[dbo].[Teacher] SET [TeacherName] = ?, "
                    + "[TeacherGender] = ?, [TeacherAddress] = ?, [TeacherEmail] = ?, [TeacherPhone] = ?, [TeacherDOB] = ? WHERE [TeacherID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, TeacherName);
            stm.setInt(2, TeacherGender);
            stm.setString(3, TeacherAddress);
            stm.setString(4, TeacherEmail);
            stm.setString(5, TeacherPhone);
            stm.setDate(6, TeacherDOB);
            stm.setInt(7, TeacherID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Teacher> getTeacher(String name) {
        ArrayList<Teacher> list = new ArrayList<>();
        try {
            String sql = "SELECT [TeacherID], [TeacherName], [TeacherGender], [TeacherAddress], "
                    + "[TeacherEmail], [TeacherPhone], [TeacherDOB] FROM [Attendance_Management].[dbo].[Teacher]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getString("TeacherName").toLowerCase().contains(name.trim().toLowerCase())) {
                    Teacher t = new Teacher();
                    t.setTeacherID(rs.getInt("TeacherID"));
                    t.setTeacherName(rs.getString("TeacherName"));
                    if (rs.getInt("TeacherGender") == 1) {
                        t.setTeacherGender(true);
                    } else {
                        t.setTeacherGender(false);
                    }
                    t.setTeacherAddress(rs.getString("TeacherAddress"));
                    t.setTeacherEmail(rs.getString("TeacherEmail"));
                    t.setTeacherPhone(rs.getString("TeacherPhone"));
                    t.setTeacherDOB(rs.getDate("TeacherDOB"));
                    list.add(t);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
