package com.hotel.Service;

import com.hotel.Entity.Room;

import java.util.List;

public interface RoomService {
    String BookRoom(String idcard,String customername,String customerphone,int rnumber);
    String CheckInRoom(int number,float discount);
    String CheckInRoomNBook(String idcard,String customername,String customerphone,int rnumber,float discount);
    String CTlive(int number,int day);
    String ChangeRoom(int oldnumber,int newnumber);
    String CheckOut(int number);
    List<Room> getRoomList();
    List<Room> getNoUseRoomList();
}
