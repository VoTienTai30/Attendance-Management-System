<%-- 
    Document   : edit_subject
    Created on : Jan 26, 2022, 11:00:36 PM
    Author     : midni
--%>

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
        <%
            int subjectID = Integer.parseInt(request.getAttribute("subjectID").toString());
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
            <a href="../../admin/schedule">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="#">Total Account</a>
        </div>

        <div id="subject">
            <form action="../subject/edit" method="post">
                <input type="hidden" name="subjectID" value="<%=subjectID%>">
                <table>
                    <tr>
                        <th colspan="2">Edit Subject</th>
                    </tr>
                    <tr>
                        <td><span>Subject Code:</span></td>
                        <td><input name="subjectCode" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Subject Name:</span></td>
                        <td><input name="subjectName" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Total Slot:</span></td>
                        <td><input name="totalSlot" type="number" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Semester:</span></td>
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
