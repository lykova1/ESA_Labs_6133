package servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Plant;
import model.Warehouse;
import service.PlantService;
import service.WarehouseService;

import java.io.IOException;

@WebServlet("/plants")
public class PlantServlet extends HttpServlet {
    @EJB
    private PlantService plantService;

    @EJB // Добавил инъекцию WarehouseService
    private WarehouseService warehouseService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("plants", plantService.findAll());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            // Обработка удаления
            Long id = Long.parseLong(req.getParameter("id"));
            plantService.delete(id);
        } else {
            // Обработка добавления
            String name = req.getParameter("name");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String warehouseIdStr = req.getParameter("warehouseId");

            Plant plant = new Plant();
            plant.setName(name);
            plant.setQuantity(quantity);

            if (warehouseIdStr != null && !warehouseIdStr.trim().isEmpty()) {
                Long warehouseId = Long.parseLong(warehouseIdStr);

                Warehouse warehouse = warehouseService.findById(warehouseId);
                if (warehouse != null) {
                    plant.setWarehouse(warehouse);
                }
            }

            plantService.add(plant);
        }

        resp.sendRedirect(req.getContextPath() + "/plants");
    }
}