package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    @OneToMany(mappedBy = "warehouse")
    private List<Plant> plants;

    // Конструкторы
    public Warehouse() {}

    public Warehouse(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // Геттеры
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public List<Plant> getPlants() { return plants; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setPlants(List<Plant> plants) { this.plants = plants; }

    // Методы для управления связями
    public void addPlant(Plant plant) {
        plants.add(plant);
        plant.setWarehouse(this);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
        plant.setWarehouse(null);
    }

    // equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;
        Warehouse warehouse = (Warehouse) o;
        return id != null && id.equals(warehouse.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // toString
    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", plantsCount=" + (plants != null ? plants.size() : 0) +
                '}';
    }
}