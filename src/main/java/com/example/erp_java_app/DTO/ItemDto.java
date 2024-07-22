package com.example.erp_java_app.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemDto {

    int itemId;

    int orderNumber;

    int itemNumber;

    int quantity;

    double unitPrice;
}
