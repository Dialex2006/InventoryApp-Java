
package com.example.inventoryApp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AssetItemService {
    
    @Autowired
    private AssetItemRepository assetItemRepository;
    
    public AssetItem findItemById (int unitId) {
        System.out.println("Checking ID: " + unitId);
        return this.assetItemRepository.findById(unitId);
    }
    
    
    
    public AssetItem findItemBySerialNumber (String serialNumber) {
        return this.assetItemRepository.findBySerialNumber(serialNumber);
    }

    
    public void addAssetItem (AssetItem item) {
        this.assetItemRepository.save(item);
    }
    
    public void setAssetItem (AssetItem item) {
        this.assetItemRepository.save(item);
    }
    
    
    
    public List<AssetItem> getAllItems() {
        return assetItemRepository.findAll();
        
    }
    
    public ArrayList<AssetItem> getAllByName(String name) {
        return assetItemRepository.findByItemName(name);
        
        
    }
    
}
