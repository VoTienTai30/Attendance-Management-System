/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.teacher;

import dal.AccountDBContext;
import dal.TeacherDBContext;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Teacher;

/**
 *
 * @author midni
 */
public class EditTeacher extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int teacherID = Integer.parseInt(request.getParameter("id"));
        TeacherDBContext teacherDB = new TeacherDBContext();
        Teacher t = teacherDB.getTeacherByID(teacherID);
        request.setAttribute("teacher", t);
        request.getRequestDispatcher("/view/admin/edit_teacher.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        TeacherDBContext db = new TeacherDBContext();
        AccountDBContext accountDB = new AccountDBContext();

        int teacherID = Integer.parseInt(request.getParameter("teacherID"));

        String teacherName = request.getParameter("teacherName");
        int teacherGender = Integer.parseInt(request.getParameter("teacherGender"));
        String teacherAddress = request.getParameter("teacherAddress");
        String teacherEmail = request.getParameter("teacherMail");
        String teacherPhone = request.getParameter("teacherPhone");
        Date teacherDOB = Date.valueOf(request.getParameter("teacherDOB"));
        db.editTeacher(teacherName, teacherGender, teacherAddress, teacherEmail, teacherPhone, teacherDOB, teacherID);

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String displayName = request.getParameter("teacherName");
        int roleID = 2;

        accountDB.editAccount(pass, displayName, roleID, user);

        response.sendRedirect("../teacher");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
