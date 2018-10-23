<%@page import="hu.adamkiss.hoeweek7.service.dto.TechnologyDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editor</title>
    </head>
    <body>
        <h1>Technology editor</h1>
        <% TechnologyDto dbu=(TechnologyDto)request.getAttribute("dbu"); %>
        <form action="Update" method="POST">
            Id: <%= dbu.getId() %> <input type="hidden" name="id" value="<%= dbu.getId() %>"><br/>
            LevelName: <input type="text" name="tech_name" value="<%= dbu.getName() %>"><br/>
            FromLevel: <input type="text" name="tech_desc" value="<%= dbu.getDescription() %>"><br/>
            UntilLevel: <input type="number" name="tech_level" value="<%= dbu.getEmpireLevel() %>"><br/>
            <input type="submit" value="Save">
        </form>
    </body>
</html>