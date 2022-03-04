<%-- 
    Document   : edit_subject
    Created on : Jan 26, 2022, 11:00:36 PM
    Author     : midni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dal.SubjectDBContext"%>
<%@page import="model.Semester"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Subject"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Subject</title>
        <link href="../../css/EditSubjectAdminStyle.css" rel="stylesheet" type="text/css"/>
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

        <div id="subject">
            <form action="../subject/edit" method="post">
                <input type="hidden" name="subjectID" value="${requestScope.subject.subjectID}">
                <table>
                    <tr>
                        <th colspan="2">Edit Subject</th>
                    </tr>
                    <tr>
                        <td><span>Subject Code:</span></td>
                        <td><input value="${requestScope.subject.subjectCode}" name="subjectCode" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Subject Name:</span></td>
                        <td><input value="${requestScope.subject.subjectName}" name="subjectName" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Total Slot:</span></td>
                        <td><input value="${requestScope.subject.totalSlot}" name="totalSlot" type="number" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Semester:</span></td>
                        <td>
                            <select name="semester">
                                <option <c:if test="${requestScope.subject.semester.semesterID==1}">selected="selected"</c:if> value="1">Semester 1</option>
                                <option <c:if test="${requestScope.subject.semester.semesterID==2}">selected="selected"</c:if> value="2">Semester 2</option>
                                <option <c:if test="${requestScope.subject.semester.semesterID==3}">selected="selected"</c:if> value="3">Semester 3</option>
                                <option <c:if test="${requestScope.subject.semester.semesterID==4}">selected="selected"</c:if> value="4">Semester 4</option>
                                <option <c:if test="${requestScope.subject.semester.semesterID==5}">selected="selected"</c:if> value="5">Semester 5</option>
                                <option <c:if test="${requestScope.subject.semester.semesterID==6}">selected="selected"</c:if> value="6">Semester 6</option>
                                <option <c:if test="${requestScope.subject.semester.semesterID==7}">selected="selected"</c:if> value="7">Semester 7</option>
                                <option <c:if test="${requestScope.subject.semester.semesterID==8}">selected="selected"</c:if> value="8">Semester 8</option>
                                <option <c:if test="${requestScope.subject.semester.semesterID==9}">selected="selected"</c:if> value="9">Semester 9</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" id="submit-btn" value="Save"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
