package com.example.inventoryApp.repository;

import java.util.ArrayList;

import com.example.inventoryApp.model.AssetItem;
import org.springframework.data.jpa.repository.JpaRepository;

interface NamesOnly {
    String getName();
}

public interface AssetItemRepository extends JpaRepository<AssetItem, Integer> {
    @Override
    ArrayList<AssetItem> findAll();

    ArrayList<AssetItem> findByItemName(String name);

    AssetItem findByUnitId(int unitId);

    AssetItem findById(int id);

    AssetItem findBySerialNumber(String serialNumber);

    void deleteById(int id);
}
