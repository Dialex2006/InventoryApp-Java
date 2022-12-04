package com.example.inventoryApp.service;

import java.util.ArrayList;
import java.util.List;

import com.example.inventoryApp.model.AssetUnit;
import com.example.inventoryApp.repository.AssetUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AssetUnitService {

    @Autowired
    private AssetUnitRepository AssetUnitRepository;

    public AssetUnit findItemById(int unitId) {

//        for (Quest q : this.quests) {
//            if (q.getName().equals(name)) {
//                return q;
//            }
//        }
//        return quests.get(0);
        System.out.println("Checking ID: " + unitId);
        return this.AssetUnitRepository.findById(unitId);
    }


    public AssetUnit findItemBySerialNumber(String serialNumber) {

        return this.AssetUnitRepository.findBySerialNumber(serialNumber);
    }


    public void addAssetUnit(AssetUnit item) {
        this.AssetUnitRepository.save(item);
    }

    public void setAssetUnit(AssetUnit item) {
        this.AssetUnitRepository.save(item);
    }


    public List<AssetUnit> getAllItems() {
        //return this.heroes;
        return AssetUnitRepository.findAll();

    }

    public ArrayList<AssetUnit> getAllByName(String name) {
        //return this.heroes;
        return AssetUnitRepository.findByItemName(name);


    }

}
