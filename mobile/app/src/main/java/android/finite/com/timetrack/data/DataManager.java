package android.finite.com.timetrack.data;

public class DataManager {
    private static DataManager instance = null;

    private DataManager() {

    }

    public static DataManager get() {
        if(instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
}
