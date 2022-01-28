/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import dal.AccountDBContext;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author midni
 */
public class Login extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/authentication/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        AccountDBContext AccountDB = new AccountDBContext();
        Account acc = AccountDB.getAccount(user, pass);
        if (acc != null) {
            request.getSession().setAttribute("account", acc);
            if (acc.getRole().getRoleID() == 1) {
                response.sendRedirect("admin/home");
            } else if (acc.getRole().getRoleID() == 2) {
                response.sendRedirect("teacher/home");
            } else {
                response.sendRedirect("student/home");
            }
        } else {
            response.sendRedirect("login");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
