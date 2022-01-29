/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author midni
 */
public class EditStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentID = request.getParameter("id");
        request.setAttribute("studentID", studentID);
        request.getRequestDispatcher("/view/admin/edit_student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDBContext db = new StudentDBContext();
        String studentID = request.getParameter("studentID");
        String studentName = request.getParameter("studentName");
        int studentGender = Integer.parseInt(request.getParameter("studentGender"));
        String studentAddress = request.getParameter("studentAddress");
        String studentEmail = request.getParameter("studentEmail");
        String studentPhone = request.getParameter("studentPhone");
        Date studentDOB = Date.valueOf(request.getParameter("studentDOB"));
        int semesterID = Integer.parseInt(request.getParameter("semester"));
        db.editStudent(studentID, studentName, studentGender, studentAddress, studentEmail, studentPhone, studentDOB, semesterID);
        response.sendRedirect("../student");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
