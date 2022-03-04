<%-- 
    Document   : class_admin
    Created on : Jan 28, 2022, 9:28:12 AM
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
        <title>Class</title>
        <link href="../css/ClassAdminStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <a href="../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: ${sessionScope.account.displayName} | <a href="../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="../admin/home">Home</a>
            <a href="../admin/subject">Subject</a>
            <a href="#">Class</a>
            <a href="../admin/teacher">Teacher</a>
            <a href="../admin/student">Student</a>
            <a href="../admin/schedule">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="../admin/account">Total Account</a>
        </div>

        <div id="class">

            <div id="add-class">
                <div class="class-title">Add New Class</div>
                <div id="add-class-form">
                    <form action="../admin/class/add" method="post">
                        <table>
                            <tr>
                                <td>Class Name:</td>
                                <td><input type="text" name="className"></td>
                            </tr>
                            <tr id="save-btn"><td colspan="2"><input type="submit" value="Save"></td></tr>
                        </table>
                    </form>
                </div>
            </div>

            <div id="class-list">
                <div class="class-title">Class List</div>
                <form action="../admin/class" method="post">
                    <input name="name" placeholder="Search by class name" type="text">
                    <input type="submit" value="Search">
                </form>
                <table id="class-table" border="1px" border-collapse="collapse">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Class Name</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="i" value="1"></c:set>
                        <c:forEach items="${requestScope.list}" var="c">
                            <tr>
                                <td>${i}<c:set var="i" value="${i+1}"></c:set></td>
                                <td>${c.className}</td>
                                <td><a href="../admin/class/edit?id=${c.classID}">Edit</a></td>
                                <td><a href="../admin/class/delete?id=${c.classID}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </body>
</html>
