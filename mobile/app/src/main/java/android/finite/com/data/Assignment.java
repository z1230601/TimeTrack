package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "assignments", foreignKeys = @ForeignKey(entity = Hotel.class,
        parentColumns = "hotelId",
        childColumns = "hotelId",
        onDelete = CASCADE), indices = {@Index("assignmentId"),@Index("hotelId")})
public class Assignment {
    @PrimaryKey(autoGenerate=true)
    private int assignmentId;
    @ColumnInfo(name="from")
    private Date fromDate;
    @ColumnInfo(name="to")
    private Date toDate;
    @ColumnInfo(name="tasks")
    private String taskDescription;
    @ColumnInfo(name="done")
    private String doneTasks;
    @ColumnInfo(name="flights")
    private List<Integer> flights;
    @ColumnInfo(name="hotelId")
    private int hotelId;
    @ColumnInfo(name="workingPackage")
    private String workingPackage;

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(String doneTasks) {
        this.doneTasks = doneTasks;
    }

    public List<Integer> getFlights() {
        return flights;
    }

    public void setFlights(List<Integer> flights) {
        this.flights = flights;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getWorkingPackage() {
        return workingPackage;
    }

    public void setWorkingPackage(String workingPackage) {
        this.workingPackage = workingPackage;
    }
}
