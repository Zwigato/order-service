package com.app.order.service;

import com.app.order.OrderRepo;
import com.app.order.dto.OrderDTO;
import com.app.order.dto.OrderDTOFromFE;
import com.app.order.dto.UserDTO;
import com.app.order.entity.Order;
import com.app.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;

    public OrderDTO saveOrderInDB(OrderDTOFromFE  orderDetails) {
        Integer nextOrderID = sequenceGenerator.generateNextOrderId();

        UserDTO userDTO = findUserDetailsFromUserIdMS(orderDetails.getUserId());

        Order orderSavedInDB = orderRepo.save( new Order(nextOrderID, orderDetails.getFoodItemsDTOList(),orderDetails.getRestaurant(),userDTO ));
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderSavedInDB);
    }

    private UserDTO findUserDetailsFromUserIdMS(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/"+userId ,UserDTO.class);
    }
}
