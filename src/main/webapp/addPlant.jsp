<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Warehouse" %>

<%
    List<Warehouse> warehouses = (List<Warehouse>) request.getAttribute("warehouses");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Plant</title>
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

        .required {
            color: #dc3545;
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

        .error-message {
            color: #dc3545;
            font-size: 14px;
            margin-top: 5px;
            display: none;
        }

        .form-control.error {
            border-color: #dc3545;
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

        .btn-submit, .btn-reset {
            flex: 1;
            padding: 14px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-submit {
            background-color: #4CAF50;
            color: white;
        }

        .btn-submit:hover {
            background-color: #45a049;
        }

        .btn-reset {
            background-color: #f8f9fa;
            color: #495057;
            border: 2px solid #e9ecef;
        }

        .btn-reset:hover {
            background-color: #e9ecef;
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
        <h1>Add New Plant</h1>
        <a href="<%= request.getContextPath() %>/plants" class="btn-back">
            ← Back to Plants List
        </a>
    </div>

    <div class="form-card">
        <form action="add-plant" method="post" id="plantForm" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="name">Plant Name <span class="required">*</span></label>
                <input type="text" id="name" name="name" class="form-control"
                       placeholder="Enter plant name" required>
                <div class="error-message" id="nameError">Please enter a plant name</div>
            </div>

            <div class="form-group">
                <label for="quantity">Quantity <span class="required">*</span></label>
                <input type="number" id="quantity" name="quantity" class="form-control"
                       placeholder="0" min="0" required>
                <div class="error-message" id="quantityError">Please enter a valid quantity</div>
            </div>

            <div class="form-group">
                <label for="warehouseId">Warehouse</label>
                <div class="select-wrapper">
                    <select id="warehouseId" name="warehouseId" class="form-control">
                        <option value="">-- Select Warehouse (Optional) --</option>
                        <%
                            if (warehouses != null) {
                                for (Warehouse w : warehouses) {
                        %>
                        <option value="<%= w.getId() %>">
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
                <button type="submit" class="btn-submit">
                    Add Plant
                </button>
                <button type="reset" class="btn-reset">
                    Clear Form
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    function validateForm() {
        let isValid = true;
        const name = document.getElementById('name');
        const quantity = document.getElementById('quantity');
        const nameError = document.getElementById('nameError');
        const quantityError = document.getElementById('quantityError');

        // Reset errors
        name.classList.remove('error');
        quantity.classList.remove('error');
        nameError.style.display = 'none';
        quantityError.style.display = 'none';

        // Validate name
        if (!name.value.trim()) {
            name.classList.add('error');
            nameError.style.display = 'block';
            isValid = false;
        }

        // Validate quantity
        if (!quantity.value || quantity.value < 0) {
            quantity.classList.add('error');
            quantityError.style.display = 'block';
            isValid = false;
        }

        return isValid;
    }

    // Add real-time validation
    document.getElementById('name').addEventListener('blur', function() {
        if (!this.value.trim()) {
            this.classList.add('error');
            document.getElementById('nameError').style.display = 'block';
        }
    });

    document.getElementById('quantity').addEventListener('blur', function() {
        if (!this.value || this.value < 0) {
            this.classList.add('error');
            document.getElementById('quantityError').style.display = 'block';
        }
    });

    // Remove error when user starts typing
    document.getElementById('name').addEventListener('input', function() {
        if (this.value.trim()) {
            this.classList.remove('error');
            document.getElementById('nameError').style.display = 'none';
        }
    });

    document.getElementById('quantity').addEventListener('input', function() {
        if (this.value && this.value >= 0) {
            this.classList.remove('error');
            document.getElementById('quantityError').style.display = 'none';
        }
    });
</script>
</body>
</html>