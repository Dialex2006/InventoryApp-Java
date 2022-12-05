
package com.example.inventoryApp;

import java.util.ArrayList;
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
    Long deleteByItemName(String name);
    
}

