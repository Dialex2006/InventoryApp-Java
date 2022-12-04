package com.example.inventoryApp.repository;

import java.util.ArrayList;

import com.example.inventoryApp.model.AssetUnit;
import org.springframework.data.jpa.repository.JpaRepository;


interface NamesOnly {
    String getName();
}

public interface AssetUnitRepository extends JpaRepository<AssetUnit, Long> {
    @Override
    ArrayList<AssetUnit> findAll();

    ArrayList<AssetUnit> findByItemName(String name);

    //ArrayList<AssetUnit> findDistinctByItemNameNotIn();
    AssetUnit findByUnitId(long unitId);

    AssetUnit findById(long id);

    AssetUnit findBySerialNumber(String serialNumber);

    Long deleteByItemName(String name);

}

