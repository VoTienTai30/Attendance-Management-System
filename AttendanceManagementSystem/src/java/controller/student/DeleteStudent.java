/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

import dal.AccountDBContext;
import dal.ClassMemberDBContext;
import dal.StudentDBContext;
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
public class DeleteStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        StudentDBContext db = new StudentDBContext();
        AccountDBContext accountDB = new AccountDBContext();
        ClassMemberDBContext classMemberDB = new ClassMemberDBContext();

        String user = db.getStudentByID(id).getStudentUsername().getUser();
        classMemberDB.deleteClassMember(id);
        db.deleteStudent(id);
        accountDB.deleteAccount(user);
        response.sendRedirect("../student");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
