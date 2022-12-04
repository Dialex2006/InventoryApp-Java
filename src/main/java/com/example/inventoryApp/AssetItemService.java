
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

//        for (Quest q : this.quests) {
//            if (q.getName().equals(name)) {
//                return q;
//            }
//        }
//        return quests.get(0);
        return this.assetItemRepository.findByUnitId(unitId);
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
        //return this.heroes;
        return assetItemRepository.findAll();
        
    }
    
    public ArrayList<AssetItem> getAllByName(String name) {
        //return this.heroes;
        return assetItemRepository.findByItemName(name);
        
        
    }
    
}
