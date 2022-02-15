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

/**
 *
 * @author midni
 */
public class ClassDBContext extends DBContext {

    public void addClass(model.Class c) {
        String sql = "INSERT INTO [dbo].[Class] ([ClassName]) VALUES (?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, c.getClassName());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void deleteClass(int id) {
        String sql = "DELETE FROM [Attendance_Management].[dbo].[Class] WHERE [ClassID] = ?";
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
                    Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void editClass(int ClassID, String ClassName) {
        String sql = "UPDATE [Attendance_Management].[dbo].[Class] SET [ClassName] = ? WHERE [ClassID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, ClassName);
            stm.setInt(2, ClassID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<model.Class> getClasses(String s) {
        ArrayList<model.Class> list = new ArrayList<>();
        try {
            String sql = "SELECT [ClassID], [ClassName] FROM [Attendance_Management].[dbo].[Class]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getString("ClassName").toLowerCase().contains(s)) {
                    model.Class c = new model.Class();
                    c.setClassID(rs.getInt("ClassID"));
                    c.setClassName(rs.getString("ClassName"));
                    list.add(c);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public model.Class getClasseByID(int id) {
        model.Class c = new model.Class();
        try {
            String sql = "SELECT [ClassID], [ClassName] FROM [Attendance_Management].[dbo].[Class] WHERE [ClassID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c.setClassID(rs.getInt("ClassID"));
                c.setClassName(rs.getString("ClassName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}
