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

/**
 *
 * @author midni
 */
public class EditTeacher extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int teacherID = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("teacherID", teacherID);
        request.getRequestDispatcher("/view/admin/edit_teacher.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TeacherDBContext db = new TeacherDBContext();
        AccountDBContext accountDB = new AccountDBContext();

        int teacherID = Integer.parseInt(request.getParameter("teacherID"));

        String oldUser = db.getTeacherByID(Integer.parseInt(request.getParameter("teacherID"))).getTeacherUsername().getUser();

        String teacherName = request.getParameter("teacherName");
        int teacherGender = Integer.parseInt(request.getParameter("teacherGender"));
        String teacherAddress = request.getParameter("teacherAddress");
        String teacherEmail = request.getParameter("teacherMail");
        String teacherPhone = request.getParameter("teacherPhone");
        Date teacherDOB = Date.valueOf(request.getParameter("teacherDOB"));
        db.editTeacher1(teacherName, teacherGender, teacherAddress, teacherEmail, teacherPhone, teacherDOB, teacherID);

        String newUser = request.getParameter("user");
        String pass = request.getParameter("pass");
        String displayName = request.getParameter("teacherName");
        int roleID = 2;

        accountDB.editAccount(newUser, pass, displayName, roleID, oldUser);
        db.editTeacher2(newUser, teacherID);

        response.sendRedirect("../teacher");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
