package com.waterhub.waterhub.APIhelper;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Merupakan class untuk mengatur deserialisasi tipe data tanggal dari database
 *
 * @author Faza Zulfika P P
 * @version 1.0
 * @since 20 Desember 2017
 */
public class DateDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String date = json.getAsString();
        String[] timeZones = TimeZone.getAvailableIDs();
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yy hh:mm a", Locale.getDefault());

        for (String timeZoneId : timeZones) {
            formatter.setTimeZone(TimeZone.getTimeZone(timeZoneId));

            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                Log.e("Deserialize Date", "deserialize: Error - " + e.getMessage());
            }
        }

        return null;
    }
}
