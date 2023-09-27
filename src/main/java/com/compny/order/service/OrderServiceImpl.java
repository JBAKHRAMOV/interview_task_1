package com.compny.order.service;

import com.compny.client.dto.ClientResDto;
import com.compny.client.service.ClientService;
import com.compny.component.ResDTO;
import com.compny.config.details.EntityDetails;
import com.compny.doctor.entity.DoctorEntity;
import com.compny.doctor.service.DoctorService;
import com.compny.doctor.work_time.dto.WorkTimeResDto;
import com.compny.doctor.work_time.service.WorkTimeService;
import com.compny.order.dto.EmptyTimeDto;
import com.compny.order.dto.OrderReqDto;
import com.compny.order.dto.OrderResDto;
import com.compny.order.dto.OrderUpdDto;
import com.compny.order.entity.OrderEntity;
import com.compny.order.repository.OrderRepository;
import com.compny.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service("order-service")
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DoctorService doctorService;
    private final WorkTimeService workTimeService;
    private final ClientService clientService;
    private final SmsService smsService;


    @Override
    public ResDTO createOrder(OrderReqDto dto) {

        if (checkIsTimeBusy(dto.getStartTime(), dto.getEndTime(), dto.getDate(), dto.getDoctorId())) {

            DoctorEntity doctor = doctorService.getDoctorEntityById(dto.getDoctorId());
            ClientResDto client = clientService.getClientById(dto.getClientId());

            OrderEntity order = new OrderEntity();
            order.setClientId(EntityDetails.getProfile().getId());
            order.setDoctor(doctor);
            order.setClinicId(EntityDetails.getProfile().getClinicId());
            order.setDate(dto.getDate());
            order.setStartTime(dto.getStartTime());
            order.setEndTime(dto.getEndTime());
            order.setDescription(dto.getDescription());
            orderRepository.save(order);

            String text = "Your order is confirmed. Meeting time: " + dto.getStartTime() + "-" + dto.getEndTime() + " " + dto.getDate();

            smsService.sendMsgToPhone(client.getPhone(), text);

            return new ResDTO("Order created");
        } else {
            return new ResDTO("Time is busy");
        }
    }

    @Override
    public OrderResDto getOrderById(Long id) {
        return entityToDto(getById(id));
    }

    @Override
    public List<OrderResDto> getAllOrdersByClientId( LocalDate date) {
        String clientId = EntityDetails.getProfile().getId();
        String clinicId = EntityDetails.getProfile().getClinicId();
        return orderRepository.findAllByClinicIdAndClientIdAndDate(clinicId, clientId, date)
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public List<OrderResDto> getAllOrdersByDoctorId(String doctorId, LocalDate date) {

        String clinicId = EntityDetails.getProfile().getClinicId();
        return orderRepository.findByClinicIdAndDoctorIdAndDate(clinicId, doctorId, date)
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public List<EmptyTimeDto> getAllEmptyTimeByDoctorId(String doctorId, LocalDate date) {

        List<EmptyTimeDto> emptyTimeList = new ArrayList<>();

        WorkTimeResDto workTime = workTimeService.getWorkTimeByDoctorIdAndDay(doctorId, date.getDayOfWeek().getValue());

        String clinicId = EntityDetails.getProfile().getClinicId();
        List<OrderEntity> timeList = orderRepository.findByClinicIdAndDoctorIdAndDate(clinicId, doctorId, date);


        timeList.sort(Comparator.comparing(OrderEntity::getStartTime));

        for (int i = 0; i <= timeList.size(); i++) {
            EmptyTimeDto emptyTime = new EmptyTimeDto();
            if (i == 0 && workTime.getStartTime().toNanoOfDay() - timeList.get(i).getStartTime().toNanoOfDay() != 0) {
                emptyTime.setStartTime(workTime.getStartTime());
                emptyTime.setEndTime(timeList.get(i).getStartTime());
                emptyTimeList.add(emptyTime);
            } else if (i == timeList.size() && workTime.getEndTime().toNanoOfDay() - timeList.get(i - 1).getEndTime().toNanoOfDay() > 0) {
                emptyTime.setStartTime(timeList.get(i - 1).getEndTime());
                emptyTime.setEndTime(workTime.getEndTime());
                emptyTimeList.add(emptyTime);
            } else if (timeList.get(i - 1).getEndTime().toNanoOfDay() - timeList.get(i).getStartTime().toNanoOfDay() < 0) {
                emptyTime.setStartTime(timeList.get(i - 1).getEndTime());
                emptyTime.setEndTime(timeList.get(i).getStartTime());
                emptyTimeList.add(emptyTime);

            }
        }

        return emptyTimeList;

    }

    @Override
    public ResDTO updateOrder(OrderUpdDto dto) {

        OrderEntity order = getById(dto.getId());

        if (checkIsTimeBusy(dto.getStartTime(), dto.getEndTime(), dto.getDate(), dto.getDoctorId())) {
            order.setStartTime(dto.getStartTime());
            order.setEndTime(dto.getEndTime());
            order.setDate(dto.getDate());
            orderRepository.save(order);
            return new ResDTO("Order updated");
        } else {
            return new ResDTO("Time is busy");
        }

    }

    @Override
    public ResDTO deleteOrder(Long id) {
        orderRepository.delete(getById(id));

        return new ResDTO("Order deleted");
    }

    private Boolean checkIsTimeBusy(LocalTime startTime, LocalTime endTime, LocalDate date, String doctorId) {
        String clinicId = EntityDetails.getProfile().getClinicId();
        return orderRepository.checkIfDoctorIsFree(doctorId, clinicId, date, startTime, endTime) == 0;
    }

    private OrderEntity getById(Long id) {
        String clinicId = EntityDetails.getProfile().getClinicId();
        return orderRepository.findByIdAndClinicId(id, clinicId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    private OrderResDto entityToDto(OrderEntity entity) {
        return new OrderResDto(
                entity.getId(),
                entity.getDescription(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getDate(),
                entity.getClientId(),
                entity.getDoctor().getId()
        );
    }
}
