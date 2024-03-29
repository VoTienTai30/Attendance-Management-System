<%-- 
    Document   : subject_admin
    Created on : Jan 26, 2022, 11:32:12 AM
    Author     : midni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Semester"%>
<%@page import="model.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject</title>
        <link href="../css/SubjectAdminStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <a href="../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: ${sessionScope.account.displayName} | <a href="../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="../admin/home">Home</a>
            <a href="#">Subject</a>
            <a href="../admin/class">Class</a>
            <a href="../admin/teacher">Teacher</a>
            <a href="../admin/student">Student</a>
            <a href="../admin/schedule">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="../admin/account">Total Account</a>
        </div>

        <div id="subject">
            <div id="add-subject">
                <div class="subject-title">Add New Subject</div>
                <div id="add-subject-form">
                    <form action="../admin/subject/add" method="post">
                        <table>
                            <tr>
                                <td>Subject Code:</td>
                                <td><input type="text" name="subjectCode"></td>
                            </tr>
                            <tr>
                                <td>Subject Name:</td>
                                <td><input type="text" name="subjectName"></td>
                            </tr>
                            <tr>
                                <td>Total Slot:</td>
                                <td><input type="number" name="totalSlot"></td>
                            </tr>
                            <tr>
                                <td>Semester:</td>
                                <td>
                                    <select name="semester">
                                        <option value="1">Semester 1</option>
                                        <option value="2">Semester 2</option>
                                        <option value="3">Semester 3</option>
                                        <option value="4">Semester 4</option>
                                        <option value="5">Semester 5</option>
                                        <option value="6">Semester 6</option>
                                        <option value="7">Semester 7</option>
                                        <option value="8">Semester 8</option>
                                        <option value="9">Semester 9</option>
                                    </select>
                                </td>
                            </tr>
                            <tr id="save-btn"><td colspan="2"><input type="submit" value="Save"></td></tr>
                        </table>
                    </form>
                </div>
            </div>
            <div id="subject-list">
                <div class="subject-title">Subject List</div>
                <form action="../admin/subject" method="post">
                    <input name="code" placeholder="Search by subject code" type="text">
                    <input type="submit" value="Search">
                </form>
                <table id="subject-table" border="1px" border-collapse="collapse">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Subject Code</th>
                            <th>Subject Name</th>
                            <th>Total Slot</th>
                            <th>Semester</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="i" value="1"></c:set>
                        <c:forEach items="${requestScope.list}" var="s">
                            <tr>
                                <td>${i}<c:set var="i" value="${i+1}"></c:set></td>
                                <td>${s.subjectCode}</td>
                                <td>${s.subjectName}</td>
                                <td>${s.totalSlot}</td>
                                <td>${s.semester.semesterName}</td>
                                <td><a href="../admin/subject/edit?id=${s.subjectID}">Edit</a></td>
                                <td><a href="../admin/subject/delete?id=${s.subjectID}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
