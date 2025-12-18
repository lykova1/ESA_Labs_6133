<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Plant" %>
<%@ page import="model.Warehouse" %>

<%
    List<Plant> plants = (List<Plant>) request.getAttribute("plants");
    int totalPlants = plants != null ? plants.size() : 0;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Plants Management</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            line-height: 1.6;
            padding: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 15px;
            border-bottom: 2px solid #4CAF50;
        }

        .header h1 {
            color: #333;
            font-size: 28px;
        }

        .btn-add {
            display: inline-flex;
            align-items: center;
            padding: 12px 24px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 600;
            transition: background-color 0.3s;
        }

        .btn-add:hover {
            background-color: #45a049;
        }

        /* Plants Table */
        .plants-table-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 25px;
            margin-bottom: 30px;
        }

        .table-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .table-header h2 {
            color: #333;
            font-size: 20px;
        }

        .plants-table {
            width: 100%;
            border-collapse: collapse;
        }

        .plants-table th {
            background-color: #f8f9fa;
            padding: 15px;
            text-align: left;
            font-weight: 600;
            color: #333;
            border-bottom: 2px solid #dee2e6;
        }

        .plants-table td {
            padding: 15px;
            border-bottom: 1px solid #e9ecef;
            vertical-align: middle;
        }

        .plants-table tbody tr:hover {
            background-color: #f8f9fa;
        }

        .plant-name {
            font-weight: 500;
            color: #333;
        }

        .warehouse-info {
            display: flex;
            flex-direction: column;
        }

        .warehouse-name {
            font-weight: 500;
            color: #333;
        }

        .warehouse-location {
            font-size: 0.9rem;
            color: #666;
            margin-top: 2px;
        }

        .quantity-badge {
            display: inline-block;
            padding: 5px 10px;
            background-color: #e7f5e7;
            color: #2e7d32;
            border-radius: 4px;
            font-weight: 500;
            font-size: 0.9rem;
        }

        /* Action Buttons */
        .action-buttons {
            display: flex;
            gap: 8px;
        }

        .btn-edit, .btn-delete {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            font-size: 0.9rem;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s;
        }

        .btn-edit {
            background-color: #e3f2fd;
            color: #1976d2;
        }

        .btn-edit:hover {
            background-color: #bbdefb;
        }

        .btn-delete {
            background-color: #ffebee;
            color: #d32f2f;
        }

        .btn-delete:hover {
            background-color: #ffcdd2;
        }

        /* Empty State */
        .empty-state {
            text-align: center;
            padding: 50px 20px;
            color: #666;
        }

        .empty-state h3 {
            font-size: 20px;
            margin-bottom: 10px;
        }

        .empty-state p {
            margin-bottom: 20px;
        }

        /* Summary */
        .summary {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-top: 20px;
        }

        .summary p {
            color: #666;
            font-size: 1rem;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .header {
                flex-direction: column;
                align-items: flex-start;
                gap: 15px;
            }

            .plants-table {
                display: block;
                overflow-x: auto;
            }

            .action-buttons {
                flex-direction: column;
            }

            .btn-edit, .btn-delete {
                width: 100%;
                margin-bottom: 5px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>🌿 Plants Management</h1>
        <a href="<%= request.getContextPath() %>/add-plant" class="btn-add">
            + Add New Plant
        </a>
    </div>

    <div class="plants-table-container">
        <div class="table-header">
            <h2>Plants List</h2>
        </div>

        <% if (plants != null && !plants.isEmpty()) { %>
        <table class="plants-table">
            <thead>
            <tr>
                <th>Plant</th>
                <th>Quantity</th>
                <th>Warehouse</th>
                <th>Location</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (Plant p : plants) {
                Warehouse warehouse = p.getWarehouse();
            %>
            <tr>
                <td>
                    <div class="plant-name"><%= p.getName() %></div>
                    <div style="font-size: 0.9rem; color: #999;">ID: <%= p.getId() %></div>
                </td>
                <td>
                    <span class="quantity-badge"><%= p.getQuantity() %> units</span>
                </td>
                <td>
                    <div class="warehouse-info">
                        <% if (warehouse != null) { %>
                        <span class="warehouse-name"><%= warehouse.getName() %></span>
                        <% } else { %>
                        <span style="color: #999;">Not assigned</span>
                        <% } %>
                    </div>
                </td>
                <td>
                    <div class="warehouse-info">
                        <% if (warehouse != null) { %>
                        <span class="warehouse-location"><%= warehouse.getLocation() %></span>
                        <% } else { %>
                        <span style="color: #999;">—</span>
                        <% } %>
                    </div>
                </td>
                <td>
                    <div class="action-buttons">
                        <form action="plants" method="get" style="display: inline;">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <button type="submit" class="btn-edit">Edit</button>
                        </form>

                        <form action="plants" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <button type="submit"
                                    class="btn-delete"
                                    onclick="return confirm('Delete <%= p.getName() %>?')">
                                Delete
                            </button>
                        </form>
                    </div>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } else { %>
        <div class="empty-state">
            <h3>No Plants Available</h3>
            <p>There are no plants in the system yet.</p>
            <a href="<%= request.getContextPath() %>/add-plant" class="btn-add">
                + Add First Plant
            </a>
        </div>
        <% } %>
    </div>

    <div class="summary">
        <p><strong>Total Plants:</strong> <%= totalPlants %></p>
        <% if (plants != null && !plants.isEmpty()) {
            int totalQuantity = 0;
            for (Plant p : plants) {
                totalQuantity += p.getQuantity();
            }
        %>
        <p><strong>Total Quantity:</strong> <%= totalQuantity %> units</p>
        <% } %>
    </div>
</div>
</body>
</html>