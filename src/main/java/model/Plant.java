package model;

import jakarta.persistence.*;

@Entity
@Table(name = "plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    // Конструкторы
    public Plant() {}

    public Plant(String name, int quantity, Warehouse warehouse) {
        this.name = name;
        this.quantity = quantity;
        this.warehouse = warehouse;
    }

    // Геттеры
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public Warehouse getWarehouse() { return warehouse; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }

    // equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plant)) return false;
        Plant plant = (Plant) o;
        return id != null && id.equals(plant.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // toString
    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", warehouse=" + (warehouse != null ? warehouse.getName() : "null") +
                '}';
    }
}