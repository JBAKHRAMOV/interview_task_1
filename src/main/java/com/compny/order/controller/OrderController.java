package com.compny.order.controller;

import com.compny.component.ResDTO;
import com.compny.order.dto.EmptyTimeDto;
import com.compny.order.dto.OrderReqDto;
import com.compny.order.dto.OrderResDto;
import com.compny.order.dto.OrderUpdDto;
import com.compny.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Qualifier("order-service")
    private final OrderService orderService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResDTO> createOrder(@RequestBody @Valid OrderReqDto dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderResDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/get-all-by-client-id/{date}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResDto>> getAllOrdersByClientId(@PathVariable LocalDate date) {
        return ResponseEntity.ok(orderService.getAllOrdersByClientId(date));
    }

    @GetMapping("/get-all-by-doctor-id/{doctorId}/{date}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResDto>> getAllOrdersByDoctorId(@PathVariable String doctorId, @PathVariable LocalDate date) {
        return ResponseEntity.ok(orderService.getAllOrdersByDoctorId(doctorId, date));
    }

    @GetMapping("/get-all-empty-time-by-doctor-id/{doctorId}/{date}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<EmptyTimeDto>> getAllEmptyTimeByDoctorId(@PathVariable String doctorId, @PathVariable LocalDate date) {
        return ResponseEntity.ok(orderService.getAllEmptyTimeByDoctorId(doctorId, date));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResDTO> updateOrder(@RequestBody @Valid OrderUpdDto dto) {
        return ResponseEntity.ok(orderService.updateOrder(dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResDTO> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }
}
