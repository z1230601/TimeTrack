package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "customer")
public class Customer {
    @PrimaryKey(autoGenerate = true)
    private int customerId;
    @ColumnInfo(name= "name")
    private String name;
    @ColumnInfo(name="address")
    private String address;
    @ColumnInfo(name="nameOriginal")
    private String nameOriginal;
    @ColumnInfo(name="adrressOriginal")
    private String addressOriginal;
    @ColumnInfo(name="phoneNumber")
    private String phoneNumber;

    public int getCustomerId() {return customerId;}
    public void setCustomerId(int customerId) {this.customerId = customerId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getNameOriginal() {return nameOriginal;}
    public void setNameOriginal(String nameOriginal) {this.nameOriginal = nameOriginal;}

    public String getAddressOriginal() {return addressOriginal;}
    public void setAddressOriginal(String addressOriginal) {this.addressOriginal = addressOriginal;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
