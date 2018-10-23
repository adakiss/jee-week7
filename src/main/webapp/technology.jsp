<%@page import="java.util.List"%>
<%@page import="hu.adamkiss.hoeweek7.service.dto.TechnologyDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DBU Manager</title>
        <style>
             table, th, td {
                border: 1px solid black;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Technologies</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Technology name</th>
                <th>Technology descrption</th>
                <th>Empire level</th>
            </tr>
            <%for(TechnologyDto t : (List<TechnologyDto>)request.getAttribute("dbus")){ %>
            <tr>
                <td>
                    <%= t.getId() %>
                </td>
                <td>
                    <%= t.getName() %>
                </td>
                <td>
                    <%= t.getDescription()%>
                </td>
                <td>
                    <%= t.getEmpireLevel()%>
                </td>
                <td>
                    <form id="update<%= t.getId() %>" action="Update" method="GET">
                        <input type="hidden" name="id" value="<%= t.getId() %>" form="update<%= t.getId() %>">
                        <input type="submit" value="Update" form="update<%= t.getId() %>">
                    </form>
                    <form id="delete<%= t.getId() %>" action="Delete" method="POST">
                        <input type="hidden" name="id" value="<%= t.getId() %>" form="delete<%= t.getId() %>">
                        <input type="submit" value="Remove" form="delete<%= t.getId() %>">
                    </form>
                </td>
            </tr>
            <%}%>
            
            <tr>
                <form id="save" method="POST" action="Create">
                    <td></td>
                    <td>
                        <input type="text" name="tech_name" form="save">
                    </td>
                    <td>
                        <input type="text" name="tech_desc" form="save">
                    </td>
                    <td>
                        <input type="number" name="tech_level" form="save">
                    </td>
                    <td>
                        <input type="submit" value="Save" form="save">
                    </td>
                </form>    
            </tr>
            
        </table>
    </body>
</html>