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

    @EJB
    private WarehouseService warehouseService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            // Показываем форму редактирования
            Long id = Long.parseLong(req.getParameter("id"));
            Plant plant = plantService.findById(id);

            if (plant != null) {
                req.setAttribute("plant", plant);
                req.setAttribute("warehouses", warehouseService.findAll());
                req.getRequestDispatcher("/editPlant.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/plants");
            }
        } else {
            // Показываем список растений
            req.setAttribute("plants", plantService.findAll());
            req.setAttribute("warehouses", warehouseService.findAll());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            // Обработка удаления
            Long id = Long.parseLong(req.getParameter("id"));
            plantService.delete(id);
        } else if ("update".equals(action)) {
            // Обработка обновления
            Long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String warehouseIdStr = req.getParameter("warehouseId");

            Plant plant = plantService.findById(id);
            if (plant != null) {
                plant.setName(name);
                plant.setQuantity(quantity);

                if (warehouseIdStr != null && !warehouseIdStr.trim().isEmpty()) {
                    Long warehouseId = Long.parseLong(warehouseIdStr);
                    Warehouse warehouse = warehouseService.findById(warehouseId);
                    plant.setWarehouse(warehouse);
                } else {
                    plant.setWarehouse(null);
                }

                plantService.update(plant);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/plants");
    }
}