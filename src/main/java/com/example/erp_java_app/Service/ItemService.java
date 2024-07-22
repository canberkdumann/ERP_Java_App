package com.example.erp_java_app.Service;

import com.example.erp_java_app.DTO.ItemDto;
import com.example.erp_java_app.Model.Item;
import com.example.erp_java_app.Repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public List<ItemDto> getAllItems() {

        return itemRepo.findAll().stream()
                .map(this::itemDto)
                .collect(Collectors.toList());
    }

    public double getTotalItemValue() {
        return itemRepo.findAll().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum();
    }

    public double getAverageItemValue() {
        List<Item> items = itemRepo.findAll();
        double totalItemValue = getTotalItemValue();
        int totalQuantity = items.stream()
                .mapToInt(Item::getQuantity)
                .sum();
        return totalQuantity == 0 ? 0 : totalItemValue / totalQuantity;
    }

    public Map<Integer, Double> getAverageItemValuesByOrderNumbers(List<Integer> orderNumbers) {
        List<Item> items = itemRepo.findAll();
        return orderNumbers.stream().collect(Collectors.toMap(
                orderNumber -> orderNumber,
                orderNumber -> {
                    List<Item> filteredItems = items.stream()
                            .filter(item -> item.getOrderNumber() == orderNumber)
                            .toList();
                    double totalItemValue = filteredItems.stream()
                            .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                            .sum();
                    int totalQuantity = filteredItems.stream()
                            .mapToInt(Item::getQuantity)
                            .sum();
                    return totalQuantity == 0 ? 0 : totalItemValue / totalQuantity;
                }
        ));
    }

    public Map<Integer, Integer> getTotalQuantitiesByItemNumbers() {
        return itemRepo.findAll().stream()
                .collect(Collectors.groupingBy(
                        Item::getItemNumber,
                        Collectors.summingInt(Item::getQuantity)
                ));
    }

    private ItemDto itemDto(Item item) {

        return ItemDto.builder()
                .itemId(item.getItemId())
                .orderNumber(item.getOrderNumber())
                .itemNumber(item.getItemNumber())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .build();
    }

}