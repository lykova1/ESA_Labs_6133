<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Plant" %>
<%@ page import="model.Warehouse" %>
<%@ page import="java.util.List" %>

<%
    Plant plant = (Plant) request.getAttribute("plant");
    List<Warehouse> warehouses = (List<Warehouse>) request.getAttribute("warehouses");

    if (plant == null) {
        response.sendRedirect(request.getContextPath() + "/plants");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Plant</title>
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
            max-width: 600px;
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

        .btn-back {
            display: inline-flex;
            align-items: center;
            padding: 10px 20px;
            background-color: #6c757d;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: 500;
            transition: background-color 0.3s;
        }

        .btn-back:hover {
            background-color: #5a6268;
        }

        /* Form Card */
        .form-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 25px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #333;
        }

        .form-control {
            width: 100%;
            padding: 12px 15px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
            transition: border-color 0.3s;
        }

        .form-control:focus {
            outline: none;
            border-color: #4CAF50;
            box-shadow: 0 0 0 3px rgba(76, 175, 80, 0.1);
        }

        /* Select styling */
        .select-wrapper {
            position: relative;
        }

        .select-wrapper::after {
            content: "▼";
            position: absolute;
            right: 15px;
            top: 50%;
            transform: translateY(-50%);
            pointer-events: none;
            color: #666;
        }

        select.form-control {
            appearance: none;
            background-color: white;
        }

        /* Button Group */
        .button-group {
            display: flex;
            gap: 15px;
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #eee;
        }

        .btn-update, .btn-cancel {
            flex: 1;
            padding: 14px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s;
            text-align: center;
            text-decoration: none;
        }

        .btn-update {
            background-color: #4CAF50;
            color: white;
        }

        .btn-update:hover {
            background-color: #45a049;
        }

        .btn-cancel {
            background-color: #6c757d;
            color: white;
        }

        .btn-cancel:hover {
            background-color: #5a6268;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .header {
                flex-direction: column;
                align-items: flex-start;
                gap: 15px;
            }

            .btn-back {
                align-self: flex-start;
            }

            .button-group {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Edit Plant</h1>
        <a href="<%= request.getContextPath() %>/plants" class="btn-back">
            ← Back to Plants List
        </a>
    </div>

    <div class="form-card">
        <form action="plants" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= plant.getId() %>">

            <div class="form-group">
                <label for="name">Plant Name</label>
                <input type="text" id="name" name="name"
                       value="<%= plant.getName() %>" required
                       class="form-control">
            </div>

            <div class="form-group">
                <label for="quantity">Quantity</label>
                <input type="number" id="quantity" name="quantity"
                       value="<%= plant.getQuantity() %>" required min="0"
                       class="form-control">
            </div>

            <div class="form-group">
                <label for="warehouseId">Warehouse</label>
                <div class="select-wrapper">
                    <select id="warehouseId" name="warehouseId" class="form-control">
                        <option value="">-- No Warehouse --</option>
                        <%
                            if (warehouses != null) {
                                Long currentWarehouseId = plant.getWarehouse() != null ?
                                        plant.getWarehouse().getId() : null;

                                for (Warehouse w : warehouses) {
                                    String selected = "";
                                    if (currentWarehouseId != null &&
                                            currentWarehouseId.equals(w.getId())) {
                                        selected = "selected";
                                    }
                        %>
                        <option value="<%= w.getId() %>" <%= selected %>>
                            <%= w.getName() %> (<%= w.getLocation() %>)
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
            </div>

            <div class="button-group">
                <button type="submit" class="btn-update">
                    Update Plant
                </button>
                <a href="<%= request.getContextPath() %>/plants" class="btn-cancel">
                    Cancel
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>