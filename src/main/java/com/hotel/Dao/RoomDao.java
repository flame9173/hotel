package com.hotel.Dao;

import com.hotel.Entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomDao {
    Room queryByNumber(int rnumber);
    void BookRoom(@Param(value = "idcard") String idcard,@Param(value = "customername") String customername,@Param(value = "customerphone") String customerphone,@Param(value = "rnumber") int rnumber,@Param(value = "rstatus") int rstatus);
    void UseBookRoom(@Param(value = "rnumber") int rnumber,@Param(value = "starttime") String starttime,@Param(value = "endtime") String endtime,@Param(value = "discount") float discount,@Param(value = "rstatus") int rstatus);
    void UseNoBookRoom(@Param(value = "idcard") String idcard,@Param(value = "customername") String customername,@Param(value = "customerphone") String customerphone,@Param(value = "rnumber") int rnumber,@Param(value = "starttime") String starttime,@Param(value = "endtime") String endtime,@Param(value = "rstatus") int rstatus,@Param(value = "discount") float discount);
    void ContinueToLive(@Param(value = "rnumber") int rnumber,@Param(value = "endtime") String endtime);
    List<Room> queryAll();
    List<Room> queryCanBeUsed();
}
