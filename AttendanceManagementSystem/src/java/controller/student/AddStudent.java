/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

import dal.AccountDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Role;
import model.Semester;
import model.Student;

/**
 *
 * @author midni
 */
public class AddStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDBContext db = new StudentDBContext();
        AccountDBContext accountDB = new AccountDBContext();
        Student s = new Student();
        Account a = new Account();
        Role r = new Role();
        Semester semester = new Semester();

        a.setUser(request.getParameter("user"));
        a.setPass(request.getParameter("pass"));
        r.setRoleID(3);
        r.setRoleName("student");
        a.setDisplayName(request.getParameter("studentName"));
        a.setRole(r);
        accountDB.addAccount(a);

        s.setStudentID(request.getParameter("studentID"));
        s.setStudentName(request.getParameter("studentName"));
        if (Integer.parseInt(request.getParameter("studentGender")) == 1) {
            s.setStudentGender(true);
        } else {
            s.setStudentGender(false);
        }
        s.setStudentAddress(request.getParameter("studentAddress"));
        s.setStudentEmail(request.getParameter("studentEmail"));
        s.setStudentPhone(request.getParameter("studentPhone"));
        s.setStudentDOB(Date.valueOf(request.getParameter("studentDOB")));
        semester.setSemesterID(Integer.parseInt(request.getParameter("semester")));
        semester.setSemesterName("Semester" + request.getParameter("semester"));
        s.setSemester(semester);
        s.setStudentUsername(a);

        db.addStudent(s);
        response.sendRedirect("../student");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
