package io.isolve.photobooth.util;


import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by isuru on 3/17/16.
 */
public class IdUtil {

    private static final DateTime missionStartDate = new DateTime(2016, 1, 1, 0, 0);


    private final AtomicLong counter = new AtomicLong();

    private static final int base = (int) Math.pow(2, 12);

    private static List<String> randomLetters = Arrays.asList(
            "g", "h", "i", "j" ,
            "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z");

    /**
     * Apptizer mission day since January 1st 2016
     * */
    public String missionDayId(DateTime dateTime) {
        return replaceEmptyString(String.format("%-3s", Integer.toHexString(Days.daysBetween(missionStartDate, dateTime).getDays() + 1)));
    }

    public String missionDayId() {
        return replaceEmptyString(String.format("%-3s", Integer.toHexString(Days.daysBetween(missionStartDate, DateTime.now()).getDays() + 1)));
    }

    public String timInHex() {
        return replaceEmptyString(String.format("%-5s", Integer.toHexString(DateTime.now().secondOfDay().get())));
    }

    public String uniquePartForSec() {
        return replaceEmptyString(String.format("%-3s", Long.toHexString(counter.incrementAndGet() % base)));
    }

    private String replaceEmptyString(String stringWithEmptySpace) {
        if(stringWithEmptySpace.contains(" ")) {
            return replaceEmptyString(
                    stringWithEmptySpace.replaceFirst(" ", randomLetters.get(ThreadLocalRandom.current().nextInt(0, randomLetters.size())))
            );
        }
        return stringWithEmptySpace;
    }

    public String uniqeId() {
        return this.uniquePartForSec() + this.timInHex() + this.missionDayId();
    }
}