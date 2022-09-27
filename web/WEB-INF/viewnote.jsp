<%-- 
    Document   : viewnote
    Created on : 25-Sep-2022, 2:41:51 PM
    Author     : pandawr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Note Keeper</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>View Note</h2>
        <p><b>Title:</b> ${note.title}</p> 
        <p><b>Contents:</b> 
            <br> ${note.content} </p>
        <a href="note?edit">Edit</a>
    </body>
</html>
