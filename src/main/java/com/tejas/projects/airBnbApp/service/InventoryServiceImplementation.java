package com.tejas.projects.airBnbApp.service;

import com.tejas.projects.airBnbApp.entity.Inventory;
import com.tejas.projects.airBnbApp.entity.Room;
import com.tejas.projects.airBnbApp.repository.InventoryRepsository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InventoryServiceImplementation implements InventoryService {

    private final InventoryRepsository inventoryRepsository;

    @Override
    public void initializedRoomForYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for(; !today.isAfter(endDate);today = today.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepsository.save(inventory);
        }
    }

    @Override
    public void deleteFutureInventories(Room room) {
//        inventoryRepsository.delete();
    }
}
