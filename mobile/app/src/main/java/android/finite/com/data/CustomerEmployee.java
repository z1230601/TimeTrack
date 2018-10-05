package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "customeremployees", foreignKeys = @ForeignKey(entity = Customer.class,
        parentColumns = "customerId",
        childColumns = "employedBy",
        onDelete = CASCADE), indices = {@Index("customerEmployeeId"), @Index("employedBy")})
public class CustomerEmployee {
    @PrimaryKey
    private int customerEmployeeId;
    @ColumnInfo(name="firstname")
    private String firstName;
    @ColumnInfo(name="lastname")
    private String lastName;
    @ColumnInfo(name="nameoriginal")
    private String nameOriginal;
    @ColumnInfo(name="phoneNumber")
    private String phoneNumber;
    @ColumnInfo(name="employedBy")
    private int employedBy; // customer foreign key

    public int getCustomerEmployeeId() {
        return customerEmployeeId;
    }

    public void setCustomerEmployeeId(int customerEmployeeId) {
        this.customerEmployeeId = customerEmployeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameOriginal() {
        return nameOriginal;
    }

    public void setNameOriginal(String nameOriginal) {
        this.nameOriginal = nameOriginal;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getEmployedBy() {
        return employedBy;
    }

    public void setEmployedBy(int employedBy) {
        this.employedBy = employedBy;
    }
}
