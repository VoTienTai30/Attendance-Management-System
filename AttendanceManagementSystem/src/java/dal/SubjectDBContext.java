/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
}
