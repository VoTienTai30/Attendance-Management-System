<%-- 
    Document   : edit_class
    Created on : Jan 28, 2022, 10:47:41 AM
    Author     : midni
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Class</title>
        <link href="../../css/EditClassAdminStyle.css" rel="stylesheet" type="text/css"/>
        <%
            int classID = Integer.parseInt(request.getAttribute("classID").toString());
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

        <div id="class">
            <form action="../class/edit" method="post">
                <input type="hidden" name="classID" value="<%=classID%>">
                <table>
                    <tr>
                        <th colspan="2">Edit Class</th>
                    </tr>
                    <tr>
                        <td><span>Class Name:</span></td>
                        <td><input name="className" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" id="submit-btn" value="Save"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
