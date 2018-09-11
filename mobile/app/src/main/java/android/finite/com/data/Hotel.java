package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "hotels")
public class Hotel {
    @PrimaryKey
    private int hotelId;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="nameOriginal")
    private String nameOriginal;
    @ColumnInfo(name="address")
    private String address;
    @ColumnInfo(name="addressOriginal")
    private String addressOriginal;
    @ColumnInfo(name="latitudeDegree")
    private double latitudeDeg;
    @ColumnInfo(name="longitudeDegree")
    private double longitudeDeg;
    @ColumnInfo(name="phone")
    private String phoneNumber;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOriginal() {
        return nameOriginal;
    }

    public void setNameOriginal(String nameOriginal) {
        this.nameOriginal = nameOriginal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressOriginal() {
        return addressOriginal;
    }

    public void setAddressOriginal(String addressOriginal) {
        this.addressOriginal = addressOriginal;
    }

    public double getLatitudeDeg() {
        return latitudeDeg;
    }

    public void setLatitudeDeg(double latitudeDeg) {
        this.latitudeDeg = latitudeDeg;
    }

    public double getLongitudeDeg() {
        return longitudeDeg;
    }

    public void setLongitudeDeg(double longitudeDeg) {
        this.longitudeDeg = longitudeDeg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
