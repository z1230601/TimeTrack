package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "times", foreignKeys = @ForeignKey(entity = Assignment.class,
    parentColumns = "assignmentId", childColumns = "assignmentId", onDelete = CASCADE),
    indices = {@Index("timeId"), @Index("assignmentId")})
public class TimeEntry {
    public enum Type {
        TRAVEL_PASSIV,
        TRAVLE_ACTIVE,
        WORK;

        public static Type fromOrdinal(int type) {
            for(Type typeEnumartion : values()) {
                if(typeEnumartion.ordinal() == type){
                    return typeEnumartion;
                }
            }
            return WORK;
        }
    };
    @PrimaryKey
    private int timeId;
    @ColumnInfo(name="entrydate")
    private Date entrydate;
    @ColumnInfo(name="startTime")
    private Date start;
    @ColumnInfo(name="endTime")
    private Date end;
    @ColumnInfo(name="type")
    private Type type;
    @ColumnInfo(name="assignmentId")
    private int assignmentId;

    public TimeEntry() {

    }

    @Ignore
    public TimeEntry(int timeId, Date entrydate, Date start, Date end, Type type, int assignmentId) {
        this.timeId = timeId;
        this.entrydate = entrydate;
        this.start = start;
        this.end = end;
        this.type = type;
        this.assignmentId = assignmentId;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }
}
