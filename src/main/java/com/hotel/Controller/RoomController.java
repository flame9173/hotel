package com.hotel.Controller;

import com.google.gson.Gson;
import com.hotel.Entity.Room;
import com.hotel.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/Room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/BookRoom/{idcard}/{name}/{phone}/{number}",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String Book(@PathVariable("idcard") String idcard, @PathVariable("name") String name, @PathVariable("phone") String phone, @PathVariable("number") int number){
        return roomService.BookRoom(idcard,name,phone,number);
    }

    @RequestMapping(value = "/CheckInBookRoom/{roomnumber}/{discount}",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String CheckIn(@PathVariable("roomnumber") int roomnumber,@PathVariable("discount") float discount){
        return roomService.CheckInRoom(roomnumber,discount);
    }

    @RequestMapping(value = "/CheckInRoom/{idcard}/{name}/{phone}/{number}/{discount}",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String CheckInNoBook(@PathVariable("idcard") String idcard, @PathVariable("name") String name, @PathVariable("phone") String phone, @PathVariable("number") int number, @PathVariable("discount") float discount){
        return roomService.CheckInRoomNBook(idcard,name,phone,number,discount);
    }

    @RequestMapping(value = "/ContinueToLive/{roomnumber}/{newendtime}",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String ContinueToLive(@PathVariable("roomnumber") int roomnumber,@PathVariable("newendtime") int day){
        return roomService.CTlive(roomnumber,day);
    }

    @RequestMapping(value = "/ChangeRoom/{roomnumber1}/{roomnumber2}",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String ChangeRoom(@PathVariable("roomnumber1") int oldnumber,@PathVariable("roomnumber2") int newnumber){
        return roomService.ChangeRoom(oldnumber,newnumber);
    }

    @RequestMapping(value = "/CheckOut/{roomnumber}",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String CheckOut(@PathVariable("roomnumber") int number){
        return roomService.CheckOut(number);
    }

    @RequestMapping(value = "/QueryAllRooms",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String GetAllRooms(){
        List<Room> rooms = roomService.getRoomList();
        Gson gson = new Gson();
        String str = gson.toJson(rooms);
        return str;
    }

    @RequestMapping(value = "/QueryAllLiveRooms",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String GetAllLiveRooms(){
        List<Room> rooms = roomService.getNoUseRoomList();
        Gson gson = new Gson();
        String str = gson.toJson(rooms);
        return str;
    }
}
