package com.example.sara.loggit;

/**
 * Created by Muhammad on 5/2/2018.
 */

public class Trip {

   public int Id;
    private String Leavingtime;
    private int Status;
    private  String trip_id;

    public String gettripid() {
        return trip_id ;
    }

    public String getLeavingtime() {
        return Leavingtime;
    }

    public int getStatus() {
        return Status;
    }


    public Trip(int id,String leavingtime,int status , String trip_id
    ) {
        Id = id;
        Leavingtime = leavingtime;
        Status = status;
        trip_id =trip_id;
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "Id='" + Id + '\'' +
                ", Leavingtime='" + Leavingtime + '\'' +
                ", Status='" + Status + '\'' +
                ", trip_id='" + trip_id + '\'' +
                '}';
    }
}
