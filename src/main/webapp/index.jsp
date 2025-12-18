<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Plant" %>
<%@ page import="model.Warehouse" %>

<h2>Plants in Warehouse</h2>

<%
    List<Plant> plants = (List<Plant>) request.getAttribute("plants");
    if (plants != null && !plants.isEmpty()) {
%>
<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Plant Name</th>
        <th>Quantity</th>
        <th>Warehouse</th>
        <th>Location</th>
    </tr>
    <%
        for (Plant p : plants) {
            Warehouse warehouse = p.getWarehouse();
    %>
    <tr>
        <td><%= p.getId() %></td>
        <td><strong><%= p.getName() %></strong></td>
        <td><%= p.getQuantity() %></td>
        <td>
            <% if (warehouse != null) { %>
            <%= warehouse.getName() %>
            <% } else { %>
            <em>No warehouse</em>
            <% } %>
        </td>
        <td>
            <% if (warehouse != null) { %>
            <%= warehouse.getLocation() %>
            <% } %>
        </td>
    </tr>
    <%
        }
    %>
</table>

<h3>Details:</h3>
<ul>
    <%
        for (Plant p : plants) {
            Warehouse warehouse = p.getWarehouse();
    %>
    <li>
        <strong><%= p.getName() %></strong> - <%= p.getQuantity() %> units
        <% if (warehouse != null) { %>
        (stored in: <%= warehouse.getName() %> at <%= warehouse.getLocation() %>)
        <% } %>
    </li>
    <%
        }
    %>
</ul>
<%
} else {
%>
<p>No plants available.</p>
<%
    }
%>