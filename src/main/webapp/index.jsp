<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Plant" %>

<h2>Plants</h2>

<ul>
    <%
        List<Plant> plants = (List<Plant>) request.getAttribute("plants");
        for (Plant p : plants) {
    %>
    <li><%= p.getName() %> — <%= p.getQuantity() %></li>
    <%
        }
    %>
</ul>
