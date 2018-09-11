package android.finite.com.data;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static List<Integer> stringToList(String value) {
        String valueSplitted[] = value.split(";");
        List<Integer> ret = new ArrayList<Integer>();
        for(String singleValue : valueSplitted) {
            ret.add(Integer.parseInt(singleValue));
        }
        return ret;
    }

    @TypeConverter
    public static String listToString(List<Integer> list) {
        String ret = "";
        for(int i =0; i < list.size(); i++){
            ret += list.get(i).toString();
            if(i != list.size()-1){
                ret += ";";
            }
        }
        return ret;
    }

    @TypeConverter
    public static TimeEntry.Type intToType(int type) {
        return TimeEntry.Type.fromOrdinal(type);
    }

    @TypeConverter
    public static int typeToInt(TimeEntry.Type type) {
        return (int) type.ordinal();
    }
}
