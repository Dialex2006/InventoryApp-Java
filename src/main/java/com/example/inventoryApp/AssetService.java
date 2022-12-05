
package com.example.inventoryApp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetService {
    
    @Autowired
    private AssetItemRepository assetItemRepository;
    
    
    public List<String> getAssetsList() {
        ArrayList<AssetItem> assetItems = new ArrayList<AssetItem>();
        assetItems = assetItemRepository.findAll();
        List<String> names = new ArrayList<>();
        
        for (AssetItem item : assetItems) {
            names.add(item.getItemName());
        }
        
        names = names.stream().distinct().collect(Collectors.toList());
        System.out.println("names: " + names);
        
        return names; // returning just an array of unique asset names
        
        
    }
    
    
}
