package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Plant;
import java.util.List;

@Stateless
public class PlantService {
    @PersistenceContext(unitName = "plantPU")
    private EntityManager em;

    public List<Plant> findAll() {
        return em.createQuery("SELECT p FROM Plant p", Plant.class)
                .getResultList();
    }

    public void add(Plant plant) {
        em.persist(plant);
    }

    public void delete(Long id) {
        Plant p = em.find(Plant.class, id);
        if (p != null) {
            em.remove(p);
        }
    }

    public Plant findById(Long id) {
        return em.find(Plant.class, id);
    }

    public void update(Plant plant) {
        em.merge(plant);
    }
}