package android.finite.com.timetrack.db.dataaccess;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.finite.com.timetrack.db.data.Customer;

import java.util.List;

@Dao
public interface CustomerDao {
    @Query("SELECT * FROM customer")
    List<Customer> getAll();

    @Query("SELECT * FROM customer WHERE customerId == :customerId")
    Customer getCustomerById(int customerId);
}
