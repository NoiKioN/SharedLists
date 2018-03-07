package com.nnightknights.sharedlists.list.database.TypeConverters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by nnigh on 3/7/2018.
 */

public class ListTypeCoverter {
    @TypeConverter
    public static Date dateFromTimestamp(Long date){
        return date == null ? null : new Date(date);
    }

    @TypeConverter
    public static Long timestampFromDate(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static boolean booleanFromBit(int value){
        return value == 1;
    }

    @TypeConverter
    public static int bitFromBoolean(boolean value){
        return value ? 1 : 0;
    }
}
