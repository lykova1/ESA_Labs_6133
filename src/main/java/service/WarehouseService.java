package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Warehouse;
import java.util.List;

@Stateless
public class WarehouseService {
    @PersistenceContext(unitName = "plantPU")
    private EntityManager em; // Убрал static

    public List<Warehouse> findAll() {
        return em.createQuery("SELECT w FROM Warehouse w", Warehouse.class)
                .getResultList();
    }

    public void add(Warehouse warehouse) {
        em.persist(warehouse);
    }

    public void delete(Long id) {
        Warehouse w = em.find(Warehouse.class, id);
        if (w != null) {
            em.remove(w);
        }
    }

    public Warehouse findById(Long id) { // Убрал static
        return em.find(Warehouse.class, id);
    }

    public void update(Warehouse warehouse) {
        em.merge(warehouse);
    }
}