package android.finite.com.timetrack.db.dataaccess;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.finite.com.timetrack.db.data.TimeEntry;

import java.util.List;

@Dao
public interface TimeEntryDao {
    @Query("SELECT * FROM times WHERE assignmentId == :assignmentId")
    List<TimeEntry> getTimesByAssignmentId(int assignmentId);

    @Insert
    void saveEntry(TimeEntry entry);
}
