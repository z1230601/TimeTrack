package android.finite.com.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.finite.com.data.*;


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

    public static DataAccess getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), DataAccess.class, "T3-data")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
