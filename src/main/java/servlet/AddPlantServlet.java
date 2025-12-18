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

@WebServlet("/add-plant")
public class AddPlantServlet extends HttpServlet {

    @EJB
    private PlantService plantService;

    @EJB
    private WarehouseService warehouseService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Загружаем список складов для выпадающего списка
        req.setAttribute("warehouses", warehouseService.findAll());
        req.getRequestDispatcher("/addPlant.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

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

        // Перенаправляем на главную страницу после успешного добавления
        resp.sendRedirect(req.getContextPath() + "/plants");
    }
}