
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
        //return this.users;
        ArrayList<AssetItem> assetItems = new ArrayList<AssetItem>();
        ArrayList<Asset> uniqueAssets = new ArrayList<Asset>();
        assetItems = assetItemRepository.findAll();
        System.out.println("assetItems: " + assetItems);
        List<String> names = new ArrayList<>();
        
        
        for (AssetItem item : assetItems) {
            names.add(item.getItemName());
            Asset uniqueAsset = new Asset (item.getItemName());
            uniqueAssets.add(uniqueAsset);
        }
        
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
