/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package J1.L.P0005;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Log implements Serializable {
    String id;
    Date time;
    String funtion;
    public static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss-dd/MM/yyyy");

    public Log(String id, String funtion) {
        this.id = id;
        this.time = new Date();
        this.funtion = funtion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFuntion() {
        return funtion;
    }

    public void setFuntion(String funtion) {
        this.funtion = funtion;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        Log.sdf = sdf;
    }

    @Override
    public String toString() {
        return sdf.format(time) +"-"+ id + "-" + funtion;
    }
    
    
}
