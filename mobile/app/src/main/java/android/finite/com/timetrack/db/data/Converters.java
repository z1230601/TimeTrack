package android.finite.com.timetrack.db.data;

import android.arch.persistence.room.TypeConverter;
import android.finite.com.utility.Tuple;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
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

    @TypeConverter
    public static LatLng stringToLatLng(String latLong) {
        String[] splitted = latLong.split("|");
        if(splitted.length != 2) {
            try {
                return new LatLng(Double.parseDouble(splitted[0]), Double.parseDouble(splitted[1]));
            } catch(Exception e){}
        }
        return new LatLng(0,0);

    }

    @TypeConverter
    public static String LatLongToString(LatLng coordinates) {
        return coordinates.latitude + "|" + coordinates.longitude;
    }

    @TypeConverter
    public static Currency stringToCurrnecy(String currencyCode) {
        return Currency.getInstance(currencyCode);
    }

    @TypeConverter
    public static String CurrencyToString(Currency data ) {
        return data.getCurrencyCode();
    }

    @TypeConverter
    public static String breaksToString(List<Tuple<Date, Date>> breaks) {
        String entry = "";
        for(int i=0; i < breaks.size(); i++) {
            entry += breaks.get(i).first.getTime() + "," + breaks.get(i).second.getTime();
            if(i != breaks.size() - 1) {
                entry += "|";
            }
        }
        return entry;
    }

    @TypeConverter
    public static List<Tuple<Date, Date>> stringToBreaks(String data) {
        List<Tuple<Date, Date>> ret = new ArrayList<Tuple<Date, Date>>();
        List<String> tuples = new ArrayList<String>(Arrays.asList(data.split("|")));
        for(String dataTuple : tuples){
            String[] singleData = dataTuple.split(",");
            if(singleData.length == 2) {
                Date start = new Date(Long.parseLong(singleData[0]));
                Date end = new Date(Long.parseLong(singleData[1]));
                ret.add(new Tuple<Date, Date>(start, end));
            }
        }
        return ret;
    }

}
