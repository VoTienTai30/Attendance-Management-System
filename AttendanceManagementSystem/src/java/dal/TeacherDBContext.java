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
import model.Account;
import model.Role;
import model.Teacher;

/**
 *
 * @author midni
 */
public class TeacherDBContext extends DBContext {

    public void addTeacher(Teacher t) {
        try {
            String sql = "INSERT INTO dbo.Teacher\n"
                    + "  (\n"
                    + "      TeacherName,\n"
                    + "      TeacherGender,\n"
                    + "      TeacherAddress,\n"
                    + "      TeacherEmail,\n"
                    + "      TeacherPhone,\n"
                    + "      TeacherDOB,\n"
                    + "      username\n"
                    + "  )\n"
                    + "  VALUES\n"
                    + "  (   ?,       \n"
                    + "      ?,     \n"
                    + "      ?,    \n"
                    + "      ?,      \n"
                    + "      ?,       \n"
                    + "      ?, \n"
                    + "      ?       \n"
                    + "      )";
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
            stm.setString(7, t.getTeacherUsername().getUser());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTeacher(int id) {
        try {
            String sql = "DELETE FROM dbo.Teacher WHERE TeacherID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editTeacher1(String TeacherName, int TeacherGender, String TeacherAddress,
            String TeacherEmail, String TeacherPhone, Date TeacherDOB, int TeacherID) {
        try {
            String sql = "UPDATE dbo.Teacher SET TeacherName = ?, TeacherGender = ?, TeacherAddress = ?, TeacherEmail = ?, "
                    + "TeacherPhone = ?, TeacherDOB = ?, username = NULL WHERE TeacherID = ?";
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

    public void editTeacher2(String newUser, int TeacherID) {
        try {
            String sql = "UPDATE dbo.Teacher SET username = ? WHERE TeacherID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newUser);
            stm.setInt(2, TeacherID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Teacher> getTeacher(String name) {
        ArrayList<Teacher> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Attendance_Management].[dbo].[Teacher]";
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

    public Teacher getTeacherByID(int id) {
        Teacher t = new Teacher();
        try {
            String sql = "SELECT * FROM dbo.Teacher WHERE TeacherID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                Role r = new Role();
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
                a.setUser(rs.getString("username"));
                a.setDisplayName("");
                a.setPass("");
                r.setRoleID(2);
                r.setRoleName("teacher");
                a.setRole(r);
                t.setTeacherUsername(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
}
