package android.finite.com.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.finite.com.data.Assignment;
import android.finite.com.data.User;

import java.util.List;

@Dao
public interface AssignmentDao {
    @Query("SELECT * FROM assignments")
    List<Assignment> getAll();

    //@Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
    //User findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from assignments")
    int countUsers();

    @Insert
    void insertAll(Assignment... users);

    @Delete
    void delete(Assignment user);
}

