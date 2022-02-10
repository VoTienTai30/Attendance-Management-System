/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

import dal.AccountDBContext;
import dal.ClassDBContext;
import dal.ClassMemberDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
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
        ClassDBContext classDB = new ClassDBContext();
        ArrayList<model.Class> listClass = classDB.getClasses("");
        request.setAttribute("listClass", listClass);
        request.getRequestDispatcher("/view/admin/edit_student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDBContext db = new StudentDBContext();
        AccountDBContext accountDB = new AccountDBContext();
        ClassMemberDBContext classMemberDB = new ClassMemberDBContext();

        String oldStudentID = request.getParameter("oldStudentID");
        String newStudentID = request.getParameter("newStudentID");

        String oldUser = db.getStudentByID(request.getParameter("oldStudentID")).getStudentUsername().getUser();

        String studentName = request.getParameter("studentName");
        int studentGender = Integer.parseInt(request.getParameter("studentGender"));
        String studentAddress = request.getParameter("studentAddress");
        String studentEmail = request.getParameter("studentEmail");
        String studentPhone = request.getParameter("studentPhone");
        Date studentDOB = Date.valueOf(request.getParameter("studentDOB"));
        int semesterID = Integer.parseInt(request.getParameter("semester"));
        db.editStudent1(newStudentID, studentName, studentGender, studentAddress, studentEmail, studentPhone, studentDOB, semesterID, oldStudentID);

        String newUser = request.getParameter("user");
        String pass = request.getParameter("pass");
        String displayName = request.getParameter("studentName");
        int roleID = 3;
        accountDB.editAccount(newUser, pass, displayName, roleID, oldUser);

        db.editStudent2(newUser, newStudentID);
//        classMemberDB.editClassMember(Integer.parseInt(request.getParameter("class")), newStudentID);
        response.sendRedirect("../student");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
