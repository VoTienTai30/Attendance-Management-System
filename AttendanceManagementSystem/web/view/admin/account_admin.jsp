<%-- 
    Document   : account_admin
    Created on : Jan 31, 2022, 9:47:48 AM
    Author     : midni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link href="../css/AccountAdminStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <a href="../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: ${sessionScope.account.displayName} | <a href="../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="../admin/home">Home</a>
            <a href="../admin/subject">Subject</a>
            <a href="../admin/class">Class</a>
            <a href="../admin/teacher">Teacher</a>
            <a href="../admin/student">Student</a>
            <a href="../admin/schedule">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="../admin/account">Total Account</a>
        </div>

        <div id="account">
            <div id="account-list">
                <div class="account-title">Admin Account List</div>
                <table class="account-table" border="1px" border-collapse="collapse">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Display Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="x" value="1"></c:set>
                        <c:forEach items="${requestScope.list}" var="a">
                            <c:if test="${a.role.roleID==1}">
                                <tr>
                                    <td>${x}<c:set var="x" value="${x+1}"></c:set></td>
                                    <td>${a.user}</td>
                                    <td>${a.pass}</td>
                                    <td>${a.displayName}</td>
                                </tr>
                            </c:if>
                        </c:forEach> 
                    </tbody>
                </table>

                <div class="account-title">Teacher Account List</div>
                <table class="account-table" border="1px" border-collapse="collapse">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Display Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="x" value="1"></c:set>
                        <c:forEach items="${requestScope.list}" var="a">
                            <c:if test="${a.role.roleID==2}">
                                <tr>
                                    <td>${x}<c:set var="x" value="${x+1}"></c:set></td>
                                    <td>${a.user}</td>
                                    <td>${a.pass}</td>
                                    <td>${a.displayName}</td>
                                </tr>
                            </c:if>
                        </c:forEach> 
                    </tbody>
                </table>

                <div class="account-title">Student Account List</div>
                <table class="account-table" border="1px" border-collapse="collapse">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Display Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="x" value="1"></c:set>
                        <c:forEach items="${requestScope.list}" var="a">
                            <c:if test="${a.role.roleID==3}">
                                <tr>
                                    <td>${x}<c:set var="x" value="${x+1}"></c:set></td>
                                    <td>${a.user}</td>
                                    <td>${a.pass}</td>
                                    <td>${a.displayName}</td>
                                </tr>
                            </c:if>
                        </c:forEach> 
                    </tbody>
                </table>
            </div> 
        </div>
    </body>
</html>
