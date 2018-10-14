package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.finite.com.utility.Tuple;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;
import java.util.List;

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

    @ColumnInfo(name="breaks")
    private List<Tuple<Date, Date>> breaks;

    public TimeEntry() {

    }

    @Ignore
    public TimeEntry(int timeId, Date entrydate, Date start, Date end, Type type, int assignmentId, List<Tuple<Date, Date>> breaks) {
        this.timeId = timeId;
        this.entrydate = entrydate;
        this.start = start;
        this.end = end;
        this.type = type;
        this.assignmentId = assignmentId;
        this.breaks = breaks;
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


    public List<Tuple<Date, Date>> getBreaks() {
        return breaks;
    }

    public void setBreaks(List<Tuple<Date, Date>> breaks) {
        this.breaks = breaks;
    }

    public long getWorkDurationInSeconds() {
        return (this.end.getTime() - this.start.getTime())/1000  - getBreakDurationInSeconds();
    }


    public long getBreakDurationInSeconds() {
        long secondsBreak = 0;
        for(Tuple<Date, Date> breakEntry : this.breaks) {
            secondsBreak += (breakEntry.second.getTime() - breakEntry.first.getTime());
        }
        return secondsBreak/1000;
    }
}
