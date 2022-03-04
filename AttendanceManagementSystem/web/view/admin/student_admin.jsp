<%-- 
    Document   : student_admin
    Created on : Jan 29, 2022, 7:54:39 PM
    Author     : midni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student</title>
        <link href="../css/StudentAdminStyle.css" rel="stylesheet" type="text/css"/>
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
            <a href="#">Student</a>
            <a href="../admin/schedule">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="../admin/account">Total Account</a>
        </div>

        <div id="student">
            <div id="student-list">
                <div class="student-title">Student List</div>
                <div id="student-add-search">
                    <button id="add-btn">Add New Student</button>
                    <form action="../admin/student" method="post">
                        <input name="id" placeholder="Search by student ID" type="text">
                        <input type="submit" value="Search">
                    </form>
                </div>
                <table id="student-table" border="1px" border-collapse="collapse">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Student Gender</th>
                            <th>Student Address</th>
                            <th>Student Email</th>
                            <th>Student Phone</th>
                            <th>Date of Birth</th>
                            <th>Class</th>
                            <th>Semester</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="i" value="1"></c:set>
                        <c:forEach items="${requestScope.listStudent}" var="student">
                            <tr>
                                <td>${i}<c:set var="i" value="${i+1}"></c:set></td>
                                <td>${student.studentID}</td>
                                <td>${student.studentName}</td>
                                <td><c:if test="${student.studentGender}">Male</c:if><c:if test="${student.studentGender==false}">Female</c:if></td>
                                <td>${student.studentAddress}</td>
                                <td>${student.studentEmail}</td>
                                <td>${student.studentPhone}</td>
                                <td>${student.studentDOB}</td>
                                <td>${student.classID.className}</td>
                                <td>${student.semester.semesterName}</td>
                                <td><a href="../admin/student/edit?id=${student.studentID}">Edit</a></td>
                                <td><a href="../admin/student/delete?id=${student.studentID}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div> 
        </div>

        <div id="add-student-container">
            <div id="add-student">
                <div class="student-title">Add New Student</div>
                <div id="add-student-form">
                    <form action="../admin/student/add" method="post">
                        <table>
                            <tr>
                                <td>Student ID:</td>
                                <td><input type="text" name="studentID"></td>
                            </tr>
                            <tr>
                                <td>Student Name:</td>
                                <td><input type="text" name="studentName"></td>
                            </tr>
                            <tr>
                                <td>Student Gender:</td>
                                <td>
                                    <input type="radio" name="studentGender" value="1">Male
                                    <input type="radio" name="studentGender" value="0">Female
                                </td>
                            </tr>
                            <tr>
                                <td>Student Address:</td>
                                <td><input type="text" name="studentAddress"></td>
                            </tr>
                            <tr>
                                <td>Student Email:</td>
                                <td><input type="text" name="studentEmail"></td>
                            </tr>
                            <tr>
                                <td>Student Phone:</td>
                                <td><input type="text" name="studentPhone"></td>
                            </tr>
                            <tr>
                                <td>Date of Birth:</td>
                                <td><input type="date" name="studentDOB"></td>
                            </tr>
                            <tr>
                                <td>Class:</td>
                                <td>
                                    <select name="class">
                                        <c:forEach items="${requestScope.listClass}" var="c">
                                            <option value="${c.classID}">${c.className}</option>
                                        </c:forEach>
                                    </select>
                                </td>
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
                            <tr>
                                <td>Username:</td>
                                <td><input type="text" name="user"></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type="text" name="pass"></td>
                            </tr>
                            <tr id="save-btn">
                                <td colspan="2">
                                    <input type="submit" value="Save">
                                    <button type="button" id="cancel-btn">Cancel</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div> 
        </div>

        <script>
            var element = document.getElementById('add-student-container');
            var showButton = document.getElementById('add-btn');
            var closeButton = document.getElementById('cancel-btn');

            function show() {
                element.style.display = 'flex';
            }

            function hide() {
                element.style.display = 'none';
            }

            showButton.addEventListener('click', show);
            closeButton.addEventListener('click', hide);
        </script>
    </body>
</html>
