/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.TeacherDBContext;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Teacher;

/**
 *
 * @author midni
 */
public class TeacherAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TeacherDBContext db = new TeacherDBContext();
        ArrayList<Teacher> list = db.getTeacher("");
        request.setAttribute("list", list);
        request.getRequestDispatcher("../view/admin/teacher_admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        TeacherDBContext db = new TeacherDBContext();
        ArrayList<Teacher> list = db.getTeacher(name);
        request.setAttribute("list", list);

        request.getRequestDispatcher("../view/admin/teacher_admin.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
