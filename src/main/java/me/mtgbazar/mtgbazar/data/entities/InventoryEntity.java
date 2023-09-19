//package me.mtgbazar.mtgbazar.data.entities;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity(name = "inventory")
//public class InventoryEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long inventoryId;
//    @OneToMany(mappedBy = "inventory")
//    private List<CardEntity> cards;
//
//    public void setInventoryId(Long inventoryId) {
//        this.inventoryId = inventoryId;
//    }
//
//    public Long getInventoryId() {
//        return inventoryId;
//    }
//}
