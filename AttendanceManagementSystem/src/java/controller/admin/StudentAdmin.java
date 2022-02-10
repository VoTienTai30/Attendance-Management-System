/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.ClassDBContext;
import dal.ClassMemberDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;

/**
 *
 * @author midni
 */
public class StudentAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDBContext studentDB = new StudentDBContext();
        ClassDBContext classDB = new ClassDBContext();

        ArrayList<Student> listStudent = studentDB.getStudent("");
        request.setAttribute("listStudent", listStudent);
        ArrayList<model.Class> listClass = classDB.getClasses("");
        request.setAttribute("listClass", listClass);

        request.getRequestDispatcher("../view/admin/student_admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("id");

        StudentDBContext db = new StudentDBContext();
        ClassDBContext classDB = new ClassDBContext();

        ArrayList<Student> listStudent = db.getStudent(code);
        request.setAttribute("listStudent", listStudent);
        ArrayList<model.Class> listClass = classDB.getClasses("");
        request.setAttribute("listClass", listClass);

        request.getRequestDispatcher("../view/admin/student_admin.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
