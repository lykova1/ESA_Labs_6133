<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Plant" %>
<%@ page import="model.Warehouse" %>
<%@ page import="service.WarehouseService" %>

<!DOCTYPE html>
<html>
<head>
    <title>Plants Management</title>
</head>
<body>
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
        <th>Actions</th>
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
        <td>
            <form action="plants" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= p.getId() %>">
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>

<%
} else {
%>
<p>No plants available.</p>
<%
    }
%>

<h3>Add New Plant</h3>
<form action="plants" method="post">
    <input type="text" name="name" placeholder="Plant Name" required><br><br>
    <input type="number" name="quantity" placeholder="Quantity" required><br><br>
    <input type="number" name="warehouseId" placeholder="Warehouse ID (optional)"><br><br>
    <button type="submit">Add Plant</button>
</form>

</body>
</html>