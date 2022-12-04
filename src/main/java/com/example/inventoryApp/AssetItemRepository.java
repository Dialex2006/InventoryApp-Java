
package com.example.inventoryApp;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;


interface NamesOnly {
  String getName();
}

public interface AssetItemRepository extends JpaRepository<AssetItem, Long> {
    @Override
    ArrayList<AssetItem> findAll();
    ArrayList<AssetItem> findByItemName(String name);
    //ArrayList<AssetItem> findDistinctByItemNameNotIn();
    AssetItem findByUnitId(int id);
    AssetItem findBySerialNumber(String serialNumber);
    Long deleteByItemName(String name);
    
}

