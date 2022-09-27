<%-- 
    Document   : editnote
    Created on : 25-Sep-2022, 2:42:05 PM
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
        <h2>Edit Note</h2>
        <form action="note" method="post">
            Title: <input type="text" name="title" value="${note.title}"><br>
            Contents: <textarea name="content" rows="7" cols="25">${note.content}</textarea><br>
            <button type="submit">Save</button>
        </form>
    </body>
</html>
