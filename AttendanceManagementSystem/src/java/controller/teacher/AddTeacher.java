/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.teacher;

import dal.AccountDBContext;
import dal.TeacherDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Role;
import model.Teacher;

/**
 *
 * @author midni
 */
public class AddTeacher extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TeacherDBContext db = new TeacherDBContext();
        AccountDBContext accountDB = new AccountDBContext();
        Teacher t = new Teacher();
        Account a = new Account();
        Role r = new Role();

        a.setUser(request.getParameter("user"));
        a.setPass(request.getParameter("pass"));
        r.setRoleID(2);
        r.setRoleName("teacher");
        a.setDisplayName(request.getParameter("teacherName"));
        a.setRole(r);
        accountDB.addAccount(a);

        t.setTeacherName(request.getParameter("teacherName"));
        if (Integer.parseInt(request.getParameter("teacherGender")) == 1) {
            t.setTeacherGender(true);
        } else {
            t.setTeacherGender(false);
        }
        t.setTeacherAddress(request.getParameter("teacherAddress"));
        t.setTeacherEmail(request.getParameter("teacherMail"));
        t.setTeacherPhone(request.getParameter("teacherPhone"));
        t.setTeacherDOB(Date.valueOf(request.getParameter("teacherDOB")));
        t.setTeacherUsername(a);
        db.addTeacher(t);

        response.sendRedirect("../teacher");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
