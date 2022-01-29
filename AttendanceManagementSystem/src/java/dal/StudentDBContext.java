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
import model.Semester;
import model.Student;

/**
 *
 * @author midni
 */
public class StudentDBContext extends DBContext {

    public void addStudent(Student student) {
        try {
            String sql = "INSERT INTO [dbo].[Student] ([StudentID], [StudentName], [StudentGender], [StudentAddress], "
                    + "[StudentEmail], [StudentPhone], [StudentDOB], [SemesterID]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, student.getStudentID());
            stm.setString(2, student.getStudentName());
            if (student.isStudentGender()) {
                stm.setInt(3, 1);
            } else {
                stm.setInt(3, 0);
            }
            stm.setString(4, student.getStudentAddress());
            stm.setString(5, student.getStudentEmail());
            stm.setString(6, student.getStudentPhone());
            stm.setDate(7, student.getStudentDOB());
            stm.setInt(8, student.getSemester().getSemesterID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStudent(String id) {
        try {
            String sql = "DELETE FROM [Attendance_Management].[dbo].[Student] WHERE [StudentID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editStudent(String StudentID, String StudentName, int StudentGender, String StudentAddress, String StudentEmail, String StudentPhone, Date StudentDOB, int SemesterID) {
        try {
            String sql = "UPDATE [Attendance_Management].[dbo].[Student] SET [StudentName] = ?, [StudentGender] = ?, [StudentAddress] = ?, \n"
                    + "[StudentEmail] = ?, [StudentPhone] = ?, [StudentDOB] = ?, [SemesterID] = ? WHERE [StudentID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, StudentName);
            stm.setInt(2, StudentGender);
            stm.setString(3, StudentAddress);
            stm.setString(4, StudentEmail);
            stm.setString(5, StudentPhone);
            stm.setDate(6, StudentDOB);
            stm.setInt(7, SemesterID);
            stm.setString(8, StudentID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Student> getStudent(String id) {
        ArrayList<Student> list = new ArrayList<>();
        try {
            String sql = "SELECT [StudentID], [StudentName], [StudentGender], [StudentAddress], "
                    + "[StudentEmail], [StudentPhone], [StudentDOB], [SemesterID] FROM [Attendance_Management].[dbo].[Student]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getString("StudentID").toLowerCase().contains(id.trim().toLowerCase())) {
                    Student student = new Student();
                    Semester semester = new Semester();
                    student.setStudentID(rs.getString("StudentID"));
                    student.setStudentName(rs.getString("StudentName"));
                    if (rs.getInt("StudentGender") == 1) {
                        student.setStudentGender(true);
                    } else {
                        student.setStudentGender(false);
                    }
                    student.setStudentAddress(rs.getString("StudentAddress"));
                    student.setStudentEmail(rs.getString("StudentEmail"));
                    student.setStudentPhone(rs.getString("StudentPhone"));
                    student.setStudentDOB(rs.getDate("StudentDOB"));
                    semester.setSemesterID(rs.getInt("SemesterID"));
                    semester.setSemesterName("Semester " + rs.getInt("SemesterID"));
                    student.setSemester(semester);
                    list.add(student);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
