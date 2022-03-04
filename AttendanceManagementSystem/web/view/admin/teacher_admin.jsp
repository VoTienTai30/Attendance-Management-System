<%-- 
    Document   : teacher_admin
    Created on : Jan 28, 2022, 6:36:05 PM
    Author     : midni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Teacher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher</title>
        <link href="../css/TeacherAdminStyle.css" rel="stylesheet" type="text/css"/>
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
            <a href="#">Teacher</a>
            <a href="../admin/student">Student</a>
            <a href="../admin/schedule">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="../admin/account">Total Account</a>
        </div>

        <div id="teacher">
            <div id="add-teacher">
                <div class="teacher-title">Add New Teacher</div>
                <div id="add-teacher-form">
                    <form action="../admin/teacher/add" method="post">
                        <table>
                            <tr>
                                <td>Teacher Name:</td>
                                <td><input type="text" name="teacherName"></td>
                            </tr>
                            <tr>
                                <td>Teacher Gender:</td>
                                <td>
                                    <input type="radio" name="teacherGender" value="1">Male
                                    <input type="radio" name="teacherGender" value="0">Female
                                </td>
                            </tr>
                            <tr>
                                <td>Teacher Address:</td>
                                <td><input type="text" name="teacherAddress"></td>
                            </tr>
                            <tr>
                                <td>Teacher Email:</td>
                                <td><input type="text" name="teacherMail"></td>
                            </tr>
                            <tr>
                                <td>Teacher Phone:</td>
                                <td><input type="text" name="teacherPhone"></td>
                            </tr>
                            <tr>
                                <td>Date of Birth:</td>
                                <td><input type="date" name="teacherDOB"></td>
                            </tr>
                            <tr>
                                <td>Username:</td>
                                <td><input type="text" name="user"></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type="text" name="pass"></td>
                            </tr>
                            <tr id="save-btn"><td colspan="2"><input type="submit" value="Save"></td></tr>
                        </table>
                    </form>
                </div>
            </div>

            <div id="teacher-list">
                <div class="teacher-title">Teacher List</div>
                <form action="../admin/teacher" method="post">
                    <input name="name" placeholder="Search by teacher name" type="text">
                    <input type="submit" value="Search">
                </form>
                <table id="teacher-table" border="1px" border-collapse="collapse">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Teacher Name</th>
                            <th>Teacher Gender</th>
                            <th>Teacher Address</th>
                            <th>Teacher Email</th>
                            <th>Teacher Phone</th>
                            <th>Date of Birth</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="i" value="1"></c:set>
                        <c:forEach items="${requestScope.list}" var="teacher">
                            <tr>
                                <td>${i}<c:set var="i" value="${i+1}"></c:set></td>
                                <td>${teacher.teacherName}</td>
                                <td><c:if test="${teacher.teacherGender}">Male</c:if><c:if test="${teacher.teacherGender==false}">Female</c:if></td>
                                <td>${teacher.teacherAddress}</td>
                                <td>${teacher.teacherEmail}</td>
                                <td>${teacher.teacherPhone}</td>
                                <td>${teacher.teacherDOB}</td>
                                <td><a href="../admin/teacher/edit?id=${teacher.teacherID}">Edit</a></td>
                                <td><a href="../admin/teacher/delete?id=${teacher.teacherID}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
