/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.classes;

import dal.ClassDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author midni
 */
public class EditClass extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int classID = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("classID", classID);
        request.getRequestDispatcher("/view/admin/edit_class.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDBContext db = new ClassDBContext();
        int classID = Integer.parseInt(request.getParameter("classID"));
        String className = request.getParameter("className");
        db.editClass(classID, className);
        response.sendRedirect("../class");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
