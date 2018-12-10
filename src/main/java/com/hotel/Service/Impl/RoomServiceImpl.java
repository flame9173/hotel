package com.hotel.Service.Impl;

import com.hotel.Dao.CompletelistDao;
import com.hotel.Dao.CustomerDao;
import com.hotel.Dao.RoomDao;
import com.hotel.Entity.Room;
import com.hotel.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service("RoomService")
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CompletelistDao completelistDao;

    public String BookRoom(String idcard,String customername,String customerphone,int rnumber){
        Room room = roomDao.queryByNumber(rnumber);
        if (room.getRstatus()==0){
            return "预订失败(已有人预订)";
        } else if (room.getRstatus()==1){
            return "预订失败(已有人入住)";
        } else {
            roomDao.BookRoom(idcard,customername,customerphone,rnumber,0);
            return "预订成功";
        }
    }

    public String CheckInRoom(int number,float discount){
        String starttime = getNowTime();
        String endtime = getAfterTime(1);
        roomDao.UseBookRoom(number,starttime,endtime,discount,1);

        Room room = roomDao.queryByNumber(number);
        String sex;
        int x = room.getIdcard().charAt(16)-'0';
        if (x%2==0) sex="女";else sex = "男";

        String str;
        int age;
        str = room.getIdcard().substring(6,10);
        int a = Integer.parseInt(str);
        age = 2018-a;

        customerDao.Register(room.getCustomername(),room.getCustomerphone(),room.getIdcard(),sex,age,room.getRnumber());
        return "入住成功";
    }

    public String CheckInRoomNBook(String idcard,String customername,String customerphone,int rnumber,float discount){
        String starttime = getNowTime();
        String endtime = getAfterTime(1);
        Room room = roomDao.queryByNumber(rnumber);
        if (room.getRstatus()==0){
            return "入住失败(已有人预订)";
        } else if (room.getRstatus()==1){
            return "入住失败(已有人入住)";
        } else {
            roomDao.UseNoBookRoom(idcard,customername,customerphone,rnumber,starttime,endtime,1,discount);

            room = roomDao.queryByNumber(rnumber);
            String sex;
            int x = room.getIdcard().charAt(16)-'0';
            if (x%2==0) sex="女";else sex = "男";

            String str;
            int age;
            str = room.getIdcard().substring(6,10);
            int a = Integer.parseInt(str);
            age = 2018-a;

            customerDao.Register(room.getCustomername(),room.getCustomerphone(),room.getIdcard(),sex,age,room.getRnumber());
            return "入住成功";
        }
    }

    public String CTlive(int number,int day){
        String newdate = getAfterTime(day);
        roomDao.ContinueToLive(number,newdate);
        return "续住成功：新的退房时间"+newdate;
    }

    public String ChangeRoom(int oldnumber,int newnumber){
        Room old = roomDao.queryByNumber(oldnumber);
        Room room = roomDao.queryByNumber(newnumber);
        if (room.getRstatus()==0){
            return "换房失败(已有人预订)";
        } else if (room.getRstatus()==1){
            return "换房失败(已有人入住)";
        } else {
            customerDao.ChangeRoom(old.getCustomername(),newnumber);
            roomDao.UseNoBookRoom(old.getIdcard(),old.getCustomername(),old.getCustomerphone(),newnumber,old.getStarttime(),old.getEndtime(),1,old.getDiscount());
            roomDao.UseNoBookRoom(null,null,null,oldnumber,null,null,-1,1);
            return "换房成功";
        }
    }

    public String CheckOut(int number){
        Room room = roomDao.queryByNumber(number);
        long jg = dateToStamp(room.getEndtime())-dateToStamp(room.getStarttime());
        long days = jg/86400000;
        if (days==0) days=1;
        float total = days*room.getUnitprice()*room.getDiscount();
        room.setTotalprice(total);

        completelistDao.insertbill(number,room.getCustomername(),room.getIdcard(),room.getTotalprice(),getNowTime()); //把账单写入Completelist

        String jsonResult = com.alibaba.fastjson.JSON.toJSONString(room);
        roomDao.UseNoBookRoom(null,null,null,number,null,null,-1,1);
        customerDao.Leave(number);
        return jsonResult;
    }

    public List<Room> getRoomList() {
        return roomDao.queryAll();
    }

    public List<Room> getNoUseRoomList(){
        return roomDao.queryCanBeUsed();
    }

    public String getNowTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public String getAfterTime(int d){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 12:00:00");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String afterdate = sdf.format(new Date(date.getTime() + (long)d*24 * 60 * 60 * 1000));
        return afterdate;
    }

    public static long dateToStamp(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
            return date.getTime();
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return -1;
        }
    }
}
