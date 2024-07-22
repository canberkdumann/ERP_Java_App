package com.example.erp_java_app.Controller;

import com.example.erp_java_app.DTO.ItemDto;
import com.example.erp_java_app.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/totalItemValue")                        //Üç siparişteki malların toplam tutarının çıktısını verir!
    public double getTotalItemValue() {
        return itemService.getTotalItemValue();
    }

    @GetMapping("/averageItemValue")                      //Üç siparişteki bütün malların ortalama fiyatını bulur!
    public double getAverageItemValue() {
        return itemService.getAverageItemValue();
    }

    @GetMapping("/averageItemValuesByOrderNumbers")       //Üç siparişteki bütün malların tek tek mal bazlı ortalama fiyatını bulur!
    public Map<Integer, Double> getAverageItemValuesByOrderNumbersDefault() {
        List<Integer> defaultOrderNumbers = Arrays.asList(1000, 1001, 1002);
        return itemService.getAverageItemValuesByOrderNumbers(defaultOrderNumbers);
    }

    @GetMapping("/totalQuantitiesByItemNumbers")         //Tek tek mal bazlı, malların hangi siparişlerde kaç adet olduğunun çıktısını verir!
    public Map<Integer, Integer> getTotalQuantitiesByItemNumbers() {
        return itemService.getTotalQuantitiesByItemNumbers();
    }

}
