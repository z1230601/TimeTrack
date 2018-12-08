package android.finite.com.timetrack.db.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

import java.util.Currency;

@Entity(tableName = "countries")
public class Country {
    @PrimaryKey
    private int countryId;
    @ColumnInfo(name="countryName")
    String name = "";
    @ColumnInfo(name="centerposition")
    LatLng center = null;
    @ColumnInfo(name = "nationalCurrency")
    Currency currency = null;

    public Country() {}
    @Ignore
    public Country(int id, String name, LatLng center, Currency nationalcurrency) {
        this.countryId = id;
        this.name = name;
        this.center = center;
        this.currency = nationalcurrency;
    }


    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
