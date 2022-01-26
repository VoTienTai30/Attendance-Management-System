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
import model.Semester;
import model.Subject;

public class SubjectDBContext extends DBContext {

    public void addSubject(Subject s) {
        try {
            String sql = "INSERT INTO [dbo].[Subject] ([SubjectCode], [TotalSlot], [SemesterID], [SubjectName]) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getSubjectCode());
            stm.setInt(2, s.getTotalSlot());
            stm.setInt(3, s.getSemester().getSemesterID());
            stm.setString(4, s.getSubjectName());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Subject> getSubject(String code) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String sql = "SELECT [SubjectCode], [TotalSlot], [SemesterID], [SubjectName] FROM [Attendance_Management].[dbo].[Subject]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getString("SubjectCode").toLowerCase().contains(code.trim().toLowerCase())) {
                    Subject s = new Subject();
                    Semester semester = new Semester();
                    s.setSubjectCode(rs.getString("SubjectCode"));
                    s.setTotalSlot(rs.getInt("TotalSlot"));
                    semester.setSemesterID(rs.getInt("SemesterID"));
                    semester.setSemesterName("Semester " + rs.getInt("SemesterID"));
                    s.setSemester(semester);
                    s.setSubjectName(rs.getString("SubjectName"));
                    list.add(s);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
