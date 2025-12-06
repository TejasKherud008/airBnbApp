package com.tejas.projects.airBnbApp.repository;

import com.tejas.projects.airBnbApp.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepsository extends JpaRepository<Inventory,Long> {
}
