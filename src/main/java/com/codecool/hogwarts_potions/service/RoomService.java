package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.model.HouseType;
import com.codecool.hogwarts_potions.model.PetType;
import com.codecool.hogwarts_potions.model.Room;
import com.codecool.hogwarts_potions.repository.RoomRepository;
import com.codecool.hogwarts_potions.service.constants.RoomServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
       Iterable<Room> roomsIterator = roomRepository.findAll();
       List<Room> rooms = new ArrayList<>();
       roomsIterator.forEach(rooms::add);
       return rooms;
    }


    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public Room getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            return room.get();
        }else {
            throw new NoSuchElementException("There is no Room with ID: "+id);
        }

    }

    public void updateRoomById(Long id, Room updatedRoom) {
        Room roomToUpdate = getRoomById(id);
        if(roomToUpdate.getStudents() != null){
            roomToUpdate.setStudents(updatedRoom.getStudents());
        }
        if(roomToUpdate.getHouseType() != null){
            roomToUpdate.setHouseType(updatedRoom.getHouseType());
        }
        roomRepository.save(roomToUpdate);
    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getRoomsForRatOwners() {
        List<Room> allRooms = getAllRooms();
        return allRooms.stream()
                .filter(room -> room.getStudents().stream()
                        .allMatch(student -> student.getPetType() != PetType.CAT && student.getPetType() != PetType.OWL))
                .collect(Collectors.toList());
    }

    public Room findSuitableRoomForStudentByHouseType(HouseType houseType){
       List<Room> rooms = getAllRooms();

       try{
          return rooms.stream()
                .filter(room -> room.getHouseType().equals(houseType)
                        && room.getStudents().size() < RoomServiceConstants.NUMBER_OF_BED).findFirst().get();
       }catch (NoSuchElementException e){
           return  createAndRegisterRoom(houseType);
       }

    }

    private Room createAndRegisterRoom(HouseType houseType){
        Room room = new Room(houseType);
        addRoom(room);
        return room;
    }


}


//
//    rooms.stream()
//                    .filter(room -> room.getHouseType().equals(houseType)
//                            && room.getStudents().size() < RoomServiceConstants.NUMBER_OF_BED).findFirst().get();
