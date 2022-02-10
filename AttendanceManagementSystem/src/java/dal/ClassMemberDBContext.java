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
import model.ClassMember;

/**
 *
 * @author midni
 */
public class ClassMemberDBContext extends DBContext {

    public void addClassMember(ClassMember c) {
        String sql = "INSERT INTO dbo.ClassMember\n"
                + "  (\n"
                + "      ClassID,\n"
                + "      StudentID\n"
                + "  )\n"
                + "  VALUES\n"
                + "  (   ?,\n"
                + "      ?\n"
                + "      )";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, c.getClassID().getClassID());
            stm.setString(2, c.getStudentID().getStudentID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void deleteClassMember(String studentID) {
        String sql = "DELETE FROM dbo.ClassMember WHERE StudentID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, studentID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void editClassMember(int classID, String studentID) {
        String sql = "UPDATE dbo.ClassMember SET ClassID = ? WHERE StudentID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, classID);
            stm.setString(2, studentID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<ClassMember> getClassMemberByClassID(int classID) {
        ClassDBContext classDB = new ClassDBContext();
        StudentDBContext studentDB = new StudentDBContext();
        ArrayList<ClassMember> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.ClassMember";
            if (classID > -1) {
                sql += " WHERE ClassID = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (classID > -1) {
                stm.setInt(1, classID);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ClassMember c = new ClassMember();
                c.setClassID(classDB.getClasseByID(rs.getInt("ClassID")));
                c.setStudentID(studentDB.getStudentByID(rs.getString("StudentID")));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public model.Class getClassByStudentID(String studentID) {
        ClassDBContext classDB = new ClassDBContext();
        model.Class c = new model.Class();
        try {
            String sql = "SELECT * FROM dbo.ClassMember WHERE StudentID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c = classDB.getClasseByID(rs.getInt("ClassID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassMemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}
