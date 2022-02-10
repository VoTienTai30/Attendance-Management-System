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
import model.Semester;
import model.Student;

/**
 *
 * @author midni
 */
public class StudentDBContext extends DBContext {
    
    public void addStudent(Student s) {
        try {
            String sql = "INSERT INTO dbo.Student\n"
                    + "(\n"
                    + "    StudentID,\n"
                    + "    StudentName,\n"
                    + "    StudentGender,\n"
                    + "    StudentAddress,\n"
                    + "    StudentEmail,\n"
                    + "    StudentPhone,\n"
                    + "    StudentDOB,\n"
                    + "    SemesterID,\n"
                    + "    username\n"
                    + ")\n"
                    + "VALUES\n"
                    + "(   ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?\n"
                    + "    )";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getStudentID());
            stm.setString(2, s.getStudentName());
            if (s.isStudentGender()) {
                stm.setInt(3, 1);
            } else {
                stm.setInt(3, 0);
            }
            stm.setString(4, s.getStudentAddress());
            stm.setString(5, s.getStudentEmail());
            stm.setString(6, s.getStudentPhone());
            stm.setDate(7, s.getStudentDOB());
            stm.setInt(8, s.getSemester().getSemesterID());
            stm.setString(9, s.getStudentUsername().getUser());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteStudent(String id) {
        try {
            String sql = "DELETE FROM dbo.Student WHERE StudentID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editStudent1(String newStudentID, String StudentName, int StudentGender, String StudentAddress,
            String StudentEmail, String StudentPhone, Date StudentDOB, int SemesterID, String oldStudentID) {
        try {
            String sql = "UPDATE dbo.Student SET StudentID = ?, StudentName = ?, StudentGender = ?, "
                    + "StudentAddress = ?, StudentEmail = ?, StudentPhone = ?, StudentDOB = ?, "
                    + "SemesterID = ?, username = NULL WHERE StudentID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newStudentID);
            stm.setString(2, StudentName);
            stm.setInt(3, StudentGender);
            stm.setString(4, StudentAddress);
            stm.setString(5, StudentEmail);
            stm.setString(6, StudentPhone);
            stm.setDate(7, StudentDOB);
            stm.setInt(8, SemesterID);
            stm.setString(9, oldStudentID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editStudent2(String newUser, String StudentID) {
        try {
            String sql = "UPDATE dbo.Student SET username = ? WHERE StudentID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newUser);
            stm.setString(2, StudentID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Student> getStudent(String id) {
        ClassMemberDBContext classMemberDB = new ClassMemberDBContext();
        ArrayList<Student> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.Student";
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
                    student.setClassID(classMemberDB.getClassByStudentID(rs.getString("StudentID")));
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
    
    public Student getStudentByID(String id) {
        ClassMemberDBContext classMemberDB = new ClassMemberDBContext();
        Student s = new Student();
        try {
            String sql = "SELECT * FROM dbo.Student WHERE StudentID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                Role r = new Role();
                Semester semester = new Semester();
                s.setStudentID(rs.getString("StudentID"));
                s.setStudentName(rs.getString("StudentName"));
                if (rs.getInt("StudentGender") == 1) {
                    s.setStudentGender(true);
                } else {
                    s.setStudentGender(false);
                }
                s.setStudentAddress(rs.getString("StudentAddress"));
                s.setStudentEmail(rs.getString("StudentEmail"));
                s.setStudentPhone(rs.getString("StudentPhone"));
                s.setStudentDOB(rs.getDate("StudentDOB"));
                s.setClassID(classMemberDB.getClassByStudentID(rs.getString("StudentID")));
                semester.setSemesterID(rs.getInt("SemesterID"));
                semester.setSemesterName("Semester " + rs.getInt("SemesterID"));
                s.setSemester(semester);
                a.setUser(rs.getString("username"));
                a.setDisplayName(rs.getString("StudentName"));
                a.setPass("");
                r.setRoleID(3);
                r.setRoleName("student");
                a.setRole(r);
                s.setStudentUsername(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
