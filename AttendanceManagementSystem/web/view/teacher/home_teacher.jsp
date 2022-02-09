<%-- 
    Document   : home_teacher
    Created on : Feb 1, 2022, 11:54:15 AM
    Author     : midni
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="../css/HomeTeacherStyle.css" rel="stylesheet" type="text/css"/>
        <%Account acc = (Account) request.getSession().getAttribute("account");%>
    </head>
    <body>
        <header>
            <a href="../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: <%=acc.getDisplayName()%> | <a href="../logout">Log out</a> </div>
        </header>
        
    </body>
</html>
