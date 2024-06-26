package android.finite.com.timetrack.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.finite.com.timetrack.db.data.*;
import android.finite.com.timetrack.db.dataaccess.AssignmentDao;
import android.finite.com.timetrack.db.dataaccess.CountryDao;
import android.finite.com.timetrack.db.dataaccess.CustomerDao;
import android.finite.com.timetrack.db.dataaccess.CustomerEmployeeDao;
import android.finite.com.timetrack.db.dataaccess.HotelDao;
import android.finite.com.timetrack.db.dataaccess.ProjectDao;
import android.finite.com.timetrack.db.dataaccess.TimeEntryDao;
import android.finite.com.timetrack.db.dataaccess.UserDao;


@Database(entities = {Assignment.class, Country.class, Customer.class, CustomerEmployee.class, Hotel.class,
    Project.class, TimeEntry.class, User.class}, version=1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class DataAccess extends RoomDatabase {

    public static final String DATABASE_NAME = "localTimeTrack";

    public abstract AssignmentDao assignmentDao();
    public abstract CountryDao countryDao();
    public abstract CustomerDao customerDao();
    public abstract CustomerEmployeeDao customerEmployeeDao();
    public abstract HotelDao hotelDao();
    public abstract ProjectDao projectDao();
    public abstract TimeEntryDao timeEntryDao();
    public abstract UserDao userDao();

    private static DataAccess INSTANCE;

    public static DataAccess getAppDatabase() {
        return INSTANCE;
    }

    public static void initAppDatabase(Context context) {
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                DataAccess.class, "T3-data")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                //TODO: remove and make workers
                            .allowMainThreadQueries()
                            .build();
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
