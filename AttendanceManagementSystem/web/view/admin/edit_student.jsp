<%-- 
    Document   : edit_student
    Created on : Jan 29, 2022, 10:17:12 PM
    Author     : midni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Student"%>
<%@page import="dal.StudentDBContext"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Student</title>
        <link href="../../css/EditStudentAdminStyle.css" rel="stylesheet" type="text/css"/>
        </head>
    <body>
        <header>
            <a href="../../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: ${sessionScope.account.displayName} | <a href="../../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="../../admin/home">Home</a>
            <a href="../../admin/subject">Subject</a>
            <a href="../../admin/class">Class</a>
            <a href="../../admin/teacher">Teacher</a>
            <a href="../../admin/student">Student</a>
            <a href="../../admin/schedule">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="../../admin/account">Total Account</a>
        </div>

        <div id="student">
            <form action="../student/edit" method="post">
                <table>
                    <tr>
                        <th colspan="2">Edit Student</th>
                    </tr>
                    <tr>
                        <td><span>Student ID:</span></td>
                        <td>
                            <span>${requestScope.student.studentID}</span>
                            <input type="hidden" name="studentID" value="${requestScope.student.studentID}">
                        </td>
                    </tr>
                    <tr>
                        <td><span>Student Name:</span></td>
                        <td><input name="studentName" type="text" class="input-box" value="${requestScope.student.studentName}"></td>
                    </tr>
                    <tr>
                        <td><span>Student Gender:</span></td>
                        <td>
                            <input <c:if test="${requestScope.student.studentGender}">checked="checked"</c:if> type="radio" name="studentGender" value="1">Male
                            <input <c:if test="${requestScope.student.studentGender==false}">checked="checked"</c:if> type="radio" name="studentGender" value="0">Female
                            </td>
                        </tr>
                        <tr>
                            <td><span>Student Address:</span></td>
                            <td><input name="studentAddress" type="text" class="input-box" value="${requestScope.student.studentAddress}"></td>
                    </tr>
                    <tr>
                        <td><span>Student Email:</span></td>
                        <td><input name="studentEmail" type="text" class="input-box" value="${requestScope.student.studentEmail}"></td>
                    </tr>
                    <tr>
                        <td><span>Student Phone:</span></td>
                        <td><input name="studentPhone" type="text" class="input-box" value="${requestScope.student.studentPhone}"></td>
                    </tr>
                    <tr>
                        <td><span>Date of Birth:</span></td>
                        <td><input type="date" name="studentDOB" class="input-box" value="${requestScope.student.studentDOB}"></td>
                    </tr>
                    <tr>
                        <td>Class:</td>
                        <td>
                            <select name="class">
                                <c:forEach items="${requestScope.listClass}" var="c">
                                    <option <c:if test="${requestScope.student.classID.classID==c.classID}">selected="selected"</c:if> value="${c.classID}">${c.className}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Semester:</td>
                        <td>
                            <select name="semester">
                                <option <c:if test="${requestScope.student.semester.semesterID == 1}">selected="selected"</c:if> value="1">Semester 1</option>
                                <option <c:if test="${requestScope.student.semester.semesterID == 2}">selected="selected"</c:if> value="2">Semester 2</option>
                                <option <c:if test="${requestScope.student.semester.semesterID == 3}">selected="selected"</c:if> value="3">Semester 3</option>
                                <option <c:if test="${requestScope.student.semester.semesterID == 4}">selected="selected"</c:if> value="4">Semester 4</option>
                                <option <c:if test="${requestScope.student.semester.semesterID == 5}">selected="selected"</c:if> value="5">Semester 5</option>
                                <option <c:if test="${requestScope.student.semester.semesterID == 6}">selected="selected"</c:if> value="6">Semester 6</option>
                                <option <c:if test="${requestScope.student.semester.semesterID == 7}">selected="selected"</c:if> value="7">Semester 7</option>
                                <option <c:if test="${requestScope.student.semester.semesterID == 8}">selected="selected"</c:if> value="8">Semester 8</option>
                                <option <c:if test="${requestScope.student.semester.semesterID == 9}">selected="selected"</c:if> value="9">Semester 9</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><span>Username:</span></td>
                        <td>
                            <span>${requestScope.student.studentUsername.user}</span>
                            <input type="hidden" name="user" class="input-box" value="${requestScope.student.studentUsername.user}">
                        </td>
                    </tr>
                    <tr>
                        <td><span>Password:</span></td>
                        <td><input type="text" name="pass" class="input-box" value="${requestScope.student.studentUsername.pass}"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" id="submit-btn" value="Save"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
