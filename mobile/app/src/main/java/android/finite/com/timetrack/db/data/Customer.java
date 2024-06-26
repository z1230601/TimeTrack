package android.finite.com.timetrack.db.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

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

    public Customer() {}

    @Ignore
    public Customer(int id, String name, String address, String nameOriginal,
                    String addressOriginal, String phoneNumber) {
        this.customerId = id;
        this.name = name;
        this.address = address;
        this.nameOriginal = nameOriginal;
        this.addressOriginal = addressOriginal;
        this.phoneNumber = phoneNumber;
    }

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

    @Override
    public String toString() {
        return this.name;
    }
}
