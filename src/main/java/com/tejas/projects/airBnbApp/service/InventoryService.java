package com.tejas.projects.airBnbApp.service;

import com.tejas.projects.airBnbApp.entity.Room;

public interface InventoryService {

        void initializedRoomForYear(Room room);

        void deleteFutureInventories(Room room);
}
