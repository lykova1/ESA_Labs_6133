package servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PlantService;
import java.io.IOException;

@WebServlet("/plants")
public class PlantServlet extends HttpServlet {
    @EJB
    private PlantService service;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("plants", service.findAll());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}