/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package J1.L.P0005;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Bank implements Serializable {
    String id;
    String name;
    long amount;

    public Bank(String id, String name, long amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    

    @Override
    public String toString() {
        return id+"-"+name+"-"+amount;
    }
    
}
