package com.compny.order.service;

import com.compny.component.ResDTO;
import com.compny.order.dto.EmptyTimeDto;
import com.compny.order.dto.OrderReqDto;
import com.compny.order.dto.OrderResDto;
import com.compny.order.dto.OrderUpdDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    ResDTO createOrder(OrderReqDto dto);

    OrderResDto getOrderById(Long id);

    List<OrderResDto> getAllOrdersByClientId(LocalDate date);

    List<OrderResDto> getAllOrdersByDoctorId(String doctorId, LocalDate date);

    List<EmptyTimeDto> getAllEmptyTimeByDoctorId(String doctorId, LocalDate date);

    ResDTO updateOrder(OrderUpdDto dto);

    ResDTO deleteOrder(Long id);

}
