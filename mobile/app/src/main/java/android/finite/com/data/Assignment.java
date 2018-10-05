package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.finite.com.timetrack.view.cards.LinesCardDataProvider;
import android.finite.com.utility.TextLayout;
import android.finite.com.utility.Tuple;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "assignments",
        foreignKeys = {
//            @ForeignKey(entity = Hotel.class,
//                        parentColumns = "hotelId",
//                        childColumns = "hotelId",
//                        onDelete = CASCADE),
            @ForeignKey(entity = Project.class,
                        parentColumns = "projectId",
                        childColumns = "projectId",
                        onDelete = CASCADE)
        },
        indices = {@Index("assignmentId"),
                //@Index("hotelId"),
                @Index("projectId")})
public class Assignment extends LinesCardDataProvider {
    @PrimaryKey(autoGenerate=true)
    private int assignmentId;
    @ColumnInfo(name="shortName")
    private String shortName;
    @ColumnInfo(name="shortDescription")
    private String shortDescription;
    @ColumnInfo(name="from")
    private Date fromDate;
    @ColumnInfo(name="to")
    private Date toDate;
    @ColumnInfo(name="tasks")
    private String taskDescription;
    @ColumnInfo(name="done")
    private String doneTasks;
//    @ColumnInfo(name="flights")
//    private List<Integer> flights;
//    @ColumnInfo(name="hotelId")
//    private int hotelId;
    @ColumnInfo(name="workingPackage")
    private String workingPackage;

    @ColumnInfo(name="projectId")
    private int parentProject;

    public Assignment() {

    }

    @Ignore
    public Assignment(int assignmentId, String shortName, String shortDescription, Date fromDate, Date toDate, String taskDescription,
                      String doneTasks, String workingPackage, int parentProject) {
        this.assignmentId = assignmentId;
        this.shortName = shortName;
        this.shortDescription = shortDescription;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.taskDescription = taskDescription;
        this.doneTasks = doneTasks;
        this.workingPackage = workingPackage;
        this.parentProject = parentProject;
    }

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

//    public List<Integer> getFlights() {
//        return flights;
//    }
//
//    public void setFlights(List<Integer> flights) {
//        this.flights = flights;
//    }
//
//    public int getHotelId() {
//        return hotelId;
//    }
//
//    public void setHotelId(int hotelId) {
//        this.hotelId = hotelId;
//    }

    public String getWorkingPackage() {
        return workingPackage;
    }

    public void setWorkingPackage(String workingPackage) {
        this.workingPackage = workingPackage;
    }

    public int getParentProject() {
        return parentProject;
    }

    public void setParentProject(int parentProject) {
        this.parentProject = parentProject;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    @Override
    public Tuple<TextLayout, String> getHeadLine() {
        return new Tuple<>(TextLayout.LARGE, this.taskDescription);
    }

    @Override
    public Tuple<TextLayout, List<String>> getContentLines() {
        List<String> lines = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM");
        lines.add(format.format(this.fromDate) + " - " + format.format(this.toDate));
        SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
        if(this.toDate.getYear() == this.fromDate.getYear()){
            lines.add(yearFormat.format(this.toDate));
        }else {
            lines.add(yearFormat.format(this.fromDate) + "/" + yearFormat.format(this.toDate));
        }
        lines.add(this.taskDescription);
        return new Tuple<>(TextLayout.SMALL, lines);
    }

    @Override
    public Tuple<TextLayout, String> getFootNote() {
        return new Tuple<>(TextLayout.MEDIUM, this.workingPackage);
    }

    @Override
    public String toString() {
        return this.shortName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
