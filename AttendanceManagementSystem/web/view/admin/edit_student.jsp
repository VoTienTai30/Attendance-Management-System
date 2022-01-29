<%-- 
    Document   : edit_student
    Created on : Jan 29, 2022, 10:17:12 PM
    Author     : midni
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Student</title>
        <link href="../../css/EditStudentAdminStyle.css" rel="stylesheet" type="text/css"/>
        <%
            String studentID = request.getAttribute("studentID").toString();
            Account acc = (Account) request.getSession().getAttribute("account");
        %>
    </head>
    <body>
        <header>
            <a href="../../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: <%=acc.getDisplayName()%> | <a href="../../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="../../admin/home">Home</a>
            <a href="../../admin/subject">Subject</a>
            <a href="../../admin/class">Class</a>
            <a href="../../admin/teacher">Teacher</a>
            <a href="../../admin/student">Student</a>
            <a href="#">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="#">Total Account</a>
        </div>

        <div id="student">
            <form action="../student/edit" method="post">
                <input type="hidden" name="studentID" value="<%=studentID%>">
                <table>
                    <tr>
                        <th colspan="2">Edit Student</th>
                    </tr>
                    <tr>
                        <td><span>Student ID:</span></td>
                        <td><input name="studentID" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Student Name:</span></td>
                        <td><input name="studentName" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Student Gender:</span></td>
                        <td>
                            <input type="radio" name="studentGender" value="1">Male
                            <input type="radio" name="studentGender" value="0">Female
                        </td>
                    </tr>
                    <tr>
                        <td><span>Student Address:</span></td>
                        <td><input name="studentAddress" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Student Email:</span></td>
                        <td><input name="studentEmail" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Student Phone:</span></td>
                        <td><input name="studentPhone" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Date of Birth:</span></td>
                        <td><input type="date" name="studentDOB" class="input-box"></td>
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
                        <td colspan="2"><input type="submit" id="submit-btn" value="Save"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
