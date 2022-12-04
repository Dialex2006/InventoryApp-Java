package com.example.inventoryApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.inventoryApp.model.Asset;
import com.example.inventoryApp.model.AssetUnit;
import com.example.inventoryApp.repository.AssetUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {
    
    @Autowired
    private AssetUnitRepository AssetUnitRepository;
    
    public List<String> getAssetsList() {
        //return this.users;
        ArrayList<AssetUnit> AssetUnits = new ArrayList<AssetUnit>();
        ArrayList<Asset> uniqueAssets = new ArrayList<Asset>();
        AssetUnits = AssetUnitRepository.findAll();
        System.out.println("AssetUnits: " + AssetUnits);
        List<String> names = new ArrayList<>();
        
//        for (AssetUnit item : AssetUnits) {
//            names.add(item.getItemName());
//            Asset uniqueAsset = new Asset (item.getItemName());
//            uniqueAssets.add(uniqueAsset);
//        }
        
        names = names.stream().distinct().collect(Collectors.toList());
        System.out.println("names: " + names);

        for (String name : names) {
            Asset uniqueAsset = new Asset (name);
            uniqueAssets.add(uniqueAsset);
            System.out.println("uniqueAssets : " + uniqueAssets);
        }
        
        return names;
    }
}
