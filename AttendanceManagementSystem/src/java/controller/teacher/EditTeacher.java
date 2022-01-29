/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.teacher;

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
        int teacherID = Integer.parseInt(request.getParameter("teacherID"));
        String teacherName = request.getParameter("teacherName");
        int teacherGender = Integer.parseInt(request.getParameter("teacherGender"));
        String teacherAddress = request.getParameter("teacherAddress");
        String teacherEmail = request.getParameter("teacherMail");
        String teacherPhone = request.getParameter("teacherPhone");
        Date teacherDOB = Date.valueOf(request.getParameter("teacherDOB"));
        db.editTeacher(teacherID, teacherName, teacherGender, teacherAddress, teacherEmail, teacherPhone, teacherDOB);
        response.sendRedirect("../teacher");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
