package android.finite.com.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.finite.com.data.Country;

import java.util.List;

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries")
    List<Country> getAll();

    @Query("SELECT * FROM countries WHERE countryId = :id")
    Country getCountryById(int id);

    //@Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
    //User findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from countries")
    int countUsers();

    @Insert
    void insertAll(Country... users);

    @Delete
    void delete(Country user);

}
