package com.cbposter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocalUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalUtil.class);

    private static final DateTimeFormatter defaultDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
    private static final DateTimeFormatter defaultTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
    private static final DateTimeFormatter defaultDateTimeFormatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    private static volatile Locale localeFormat = null;

    private LocalUtil() {
    }

    public static String localeToText(Locale value) {
        if (value == null) {
            return "en"; 
        }
        return value.toString();
    }

    public static Locale textToLocale(String value) {
        if (!StringUtil.hasText(value)) {
            return Locale.ENGLISH;
        }

        if ("system".equals(value)) { 
            return getSystemLocale();
        }

        String[] val = value.split("_", 3); 
        String language = val.length > 0 ? val[0] : ""; 
        String country = val.length > 1 ? val[1] : ""; 
        String variant = val.length > 2 ? val[2] : ""; 

        return new Locale(language, country, variant);
    }

    public static Locale getSystemLocale() {
        String language = System.getProperty("user.language", "en");  
        String country = System.getProperty("user.country", "");  
        String variant = System.getProperty("user.variant", "");  
        return new Locale(language, country, variant);
    }

    public static Locale getLocaleFormat() {
        Locale l = LocalUtil.localeFormat;
        if (l == null) {
            l = Locale.getDefault();
        }
        return l;
    }

    public static void setLocaleFormat(Locale value) {
        LocalUtil.localeFormat = value;
    }

    public static NumberFormat getNumberInstance() {
        return NumberFormat.getNumberInstance(getLocaleFormat());
    }

    public static NumberFormat getIntegerInstance() {
        return NumberFormat.getIntegerInstance(getLocaleFormat());
    }

    public static NumberFormat getPercentInstance() {
        return NumberFormat.getPercentInstance(getLocaleFormat());
    }

    public static DateFormat getDateInstance(int style) {
        return DateFormat.getDateInstance(style, getLocaleFormat());
    }

    public static DateTimeFormatter getDateFormatter() {
        return defaultDateFormatter.withLocale(getLocaleFormat());
    }

    public static DateTimeFormatter getDateFormatter(FormatStyle style) {
        return DateTimeFormatter.ofLocalizedDate(style).withLocale(getLocaleFormat());
    }

    public static DateTimeFormatter getTimeFormatter() {
        return defaultTimeFormatter.withLocale(getLocaleFormat());
    }

    public static DateTimeFormatter getTimeFormatter(FormatStyle style) {
        return DateTimeFormatter.ofLocalizedTime(style).withLocale(getLocaleFormat());
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return defaultDateTimeFormatter.withLocale(getLocaleFormat());
    }

    public static DateTimeFormatter getDateTimeFormatter(FormatStyle style) {
        return DateTimeFormatter.ofLocalizedDateTime(style).withLocale(getLocaleFormat());
    }



    public static Date toLocalDate(TemporalAccessor temporal) {
        if (temporal != null) {
            try {
                TemporalAccessor t = temporal;
                if (temporal instanceof LocalDate) {
                    t = ((LocalDate) temporal).atStartOfDay(ZoneId.systemDefault());
                } else if (temporal instanceof LocalTime) {
                    t = ((LocalTime) temporal).atDate(LocalDate.ofEpochDay(0)).atZone(ZoneId.systemDefault());
                } else if (temporal instanceof LocalDateTime) {
                    t = ((LocalDateTime) temporal).atZone(ZoneId.systemDefault());
                }
                return Date.from(Instant.from(t));
            } catch (Exception e) {
                LOGGER.error("Date conversion", e); 
            }
        }
        return null;
    }

    public static Date[] toLocalDates(Object array) {
        if (array != null && array.getClass().isArray()) {
            Date[] dates = new Date[Array.getLength(array)];
            for (int i = 0; i < dates.length; i++) {
                dates[i] = toLocalDate((TemporalAccessor) Array.get(array, i));
            }
            return dates;
        }
        return null;
    }

    public static LocalDate toLocalDate(Date date) {
        if (date != null) {
            try {
                LocalDateTime datetime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                return datetime.toLocalDate();
            } catch (Exception e) {
                LOGGER.error("Date conversion", e); 
            }
        }
        return null;
    }

    public static LocalTime toLocalTime(Date date) {
        if (date != null) {
            try {
                LocalDateTime datetime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                return datetime.toLocalTime();
            } catch (Exception e) {
                LOGGER.error("Time conversion", e); 
            }
        }
        return null;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        if (date != null) {
            try {
                return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            } catch (Exception e) {
                LOGGER.error("DateTime conversion", e); 
            }
        }
        return null;
    }

    public static Date dateTime(Date date, Date time) {
        if (time == null) {
            return date;
        } else if (date == null) {
            return time;
        }
        Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(date);

        Calendar calendarB = Calendar.getInstance();
        calendarB.setTime(time);

        calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
        calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
        calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
        calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));

        return calendarA.getTime();
    }

    public static String formatDateTime(TemporalAccessor date) {
        if (date instanceof LocalDate) {
            return LocalUtil.getDateFormatter().format(date);
        } else if (date instanceof LocalTime) {
            return LocalUtil.getTimeFormatter().format(date);
        } else if (date instanceof LocalDateTime) {
            return LocalUtil.getDateTimeFormatter().format(date);
        } else if (date instanceof Instant) {
            return LocalUtil.getDateTimeFormatter().format(((Instant) date).atZone(ZoneId.systemDefault()));
        }
        return ""; 
    }


}
