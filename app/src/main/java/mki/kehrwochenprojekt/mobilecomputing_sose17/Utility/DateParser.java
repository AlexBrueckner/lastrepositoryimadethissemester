package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexb on 04.07.2017.
 */

public class DateParser {
    private static final Pattern dateFormat = Pattern.compile("([1-2]\\d{3})-([0-9][1-9]|1[0-2])-([0-2]\\d|3(?:0|1))(?:\\s|T)((?:0|1)\\d|2[0-3]):([0-5]\\d):([0-5]\\d).[0-9]+Z");
    private static Matcher m;

    public static Date parseDate(String dateString) {
        m = dateFormat.matcher(dateString);
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            if (m.matches()) {
                d = sdf.parse(dateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            d = null;
        } finally {
            return d;
        }


    }
}
