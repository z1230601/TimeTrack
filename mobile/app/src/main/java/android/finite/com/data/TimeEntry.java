package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
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
    private Date date;
    @ColumnInfo(name="startTime")
    private Timestamp start;
    @ColumnInfo(name="endTime")
    private Timestamp end;
    @ColumnInfo(name="type")
    private Type type;
    @ColumnInfo(name="assignmentId")
    private int assignmentId;

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
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
