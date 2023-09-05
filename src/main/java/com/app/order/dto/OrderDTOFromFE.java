package com.app.order.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOFromFE {
    @JsonAlias("foodItemsList")
    private List<FoodItemsDTO> foodItemsDTOList;
    private Integer userId;
    private Restaurant restaurant;
}
