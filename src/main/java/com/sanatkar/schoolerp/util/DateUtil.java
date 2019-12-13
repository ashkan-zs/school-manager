package com.sanatkar.schoolerp.util;


import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author amirsam bahador
 * @version 1.0
 */
public class DateUtil implements Serializable {
    private int irYear;
    private int irMonth;
    private int irDay;
    private int gYear;
    private int gMonth;
    private int gDay;
    private int juYear;
    private int juMonth;
    private int juDay;
    private int leap;
    private int JDN;
    private int march;


    public DateUtil() {
        Calendar calendar = new GregorianCalendar();
        setGregorianDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
    }


    public DateUtil(int year, int month, int day) {
        setGregorianDate(year, month, day);
    }

    public String getNumbericDate(int date) {
        if (date < 10) {
            return "0" + date;
        }
        return String.valueOf(date);
    }

    public int getPersianYear() {
        return irYear;
    }

    public int getPersianMonth() {
        return irMonth;
    }

    public int getPersianDay() {
        return irDay;
    }

    public int getGregorianYear() {
        return gYear;
    }

    public int getGregorianMonth() {
        return gMonth;
    }

    public int getGregorianDay() {
        return gDay;
    }

    public int getJulianYear() {
        return juYear;
    }

    public int getJulianMonth() {
        return juMonth;
    }

    public int getJulianDay() {
        return juDay;
    }

    public String getPersianDate() {
        return (irYear + "/" + irMonth + "/" + irDay);
    }

    public String getGregorianDate() {
        return (gYear + "/" + gMonth + "/" + gDay);
    }

    public String getJulianDate() {
        return (juYear + "/" + juMonth + "/" + juDay);
    }

    public String getWeekDayStr() {
        String weekDayStr[] = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"};
        return (weekDayStr[getDayOfWeek()]);
    }

    public String toString() {
        return (getWeekDayStr() +
                ", Gregorian:[" + getGregorianDate() +
                "], Julian:[" + getJulianDate() +
                "], Persian:[" + getPersianDate() + "]");
    }

    public int getDayOfWeek() {
        return (JDN % 7);
    }

    public void nextDay() {
        JDN++;
        JDNToPersian();
        JDNToJulian();
        JDNToGregorian();
    }

    public void nextDay(int days) {
        JDN += days;
        JDNToPersian();
        JDNToJulian();
        JDNToGregorian();
    }

    public void previousDay() {
        JDN--;
        JDNToPersian();
        JDNToJulian();
        JDNToGregorian();
    }

    public void previousDay(int days) {
        JDN -= days;
        JDNToPersian();
        JDNToJulian();
        JDNToGregorian();
    }

    public void setPersianDate(int year, int month, int day) {
        irYear = year;
        irMonth = month;
        irDay = day;
        JDN = PersianDateToJDN();
        JDNToPersian();
        JDNToJulian();
        JDNToGregorian();
    }

    public void setGregorianDate(int year, int month, int day) {
        gYear = year;
        gMonth = month;
        gDay = day;
        JDN = gregorianDateToJDN(year, month, day);
        JDNToPersian();
        JDNToJulian();
        JDNToGregorian();
    }

    public void setJulianDate(int year, int month, int day) {
        juYear = year;
        juMonth = month;
        juDay = day;
        JDN = julianDateToJDN(year, month, day);
        JDNToPersian();
        JDNToJulian();
        JDNToGregorian();
    }

    private void PersianCalendar() {

        int Breaks[] =
                {-61, 9, 38, 199, 426, 686, 756, 818, 1111, 1181,
                        1210, 1635, 2060, 2097, 2192, 2262, 2324, 2394, 2456, 3178};
        int jm, N, leapJ, leapG, jp, j, jump;
        gYear = irYear + 621;
        leapJ = -14;
        jp = Breaks[0];

        j = 1;
        do {
            jm = Breaks[j];
            jump = jm - jp;
            if (irYear >= jm) {
                leapJ += (jump / 33 * 8 + (jump % 33) / 4);
                jp = jm;
            }
            j++;
        } while ((j < 20) && (irYear >= jm));
        N = irYear - jp;

        leapJ += (N / 33 * 8 + ((N % 33) + 3) / 4);
        if (((jump % 33) == 4) && ((jump - N) == 4))
            leapJ++;

        leapG = gYear / 4 - ((gYear / 100 + 1) * 3 / 4) - 150;
        march = 20 + leapJ - leapG;

        if ((jump - N) < 6)
            N = N - jump + ((jump + 4) / 33 * 33);
        leap = (((N + 1) % 33) - 1) % 4;
        if (leap == -1)
            leap = 4;
    }

    public boolean IsLeap(int irYear1) {

        int Breaks[] =
                {-61, 9, 38, 199, 426, 686, 756, 818, 1111, 1181,
                        1210, 1635, 2060, 2097, 2192, 2262, 2324, 2394, 2456, 3178};
        int jm, N, leapJ, leapG, jp, j, jump;
        gYear = irYear1 + 621;
        leapJ = -14;
        jp = Breaks[0];

        j = 1;
        do {
            jm = Breaks[j];
            jump = jm - jp;
            if (irYear1 >= jm) {
                leapJ += (jump / 33 * 8 + (jump % 33) / 4);
                jp = jm;
            }
            j++;
        } while ((j < 20) && (irYear1 >= jm));
        N = irYear1 - jp;

        leapJ += (N / 33 * 8 + ((N % 33) + 3) / 4);
        if (((jump % 33) == 4) && ((jump - N) == 4))
            leapJ++;

        leapG = gYear / 4 - ((gYear / 100 + 1) * 3 / 4) - 150;
        march = 20 + leapJ - leapG;

        if ((jump - N) < 6)
            N = N - jump + ((jump + 4) / 33 * 33);
        leap = (((N + 1) % 33) - 1) % 4;
        if (leap == -1)
            leap = 4;
        if (leap == 4 || leap == 0)
            return true;
        else
            return false;

    }

    private int PersianDateToJDN() {
        PersianCalendar();
        return (gregorianDateToJDN(gYear, 3, march) + (irMonth - 1) * 31 - irMonth / 7 * (irMonth - 7) + irDay - 1);
    }

    private void JDNToPersian() {
        JDNToGregorian();
        irYear = gYear - 621;
        PersianCalendar();
        int JDN1F = gregorianDateToJDN(gYear, 3, march);
        int k = JDN - JDN1F;
        if (k >= 0) {
            if (k <= 185) {
                irMonth = 1 + k / 31;
                irDay = (k % 31) + 1;
                return;
            } else
                k -= 186;
        } else {
            irYear--;
            k += 179;
            if (leap == 1)
                k++;
        }
        irMonth = 7 + k / 30;
        irDay = (k % 30) + 1;
    }

    private int julianDateToJDN(int year, int month, int day) {
        return (year + (month - 8) / 6 + 100100) * 1461 / 4 + (153 * ((month + 9) % 12) + 2) / 5 + day - 34840408;
    }

    private void JDNToJulian() {
        int j = 4 * JDN + 139361631;
        int i = ((j % 1461) / 4) * 5 + 308;
        juDay = (i % 153) / 5 + 1;
        juMonth = ((i / 153) % 12) + 1;
        juYear = j / 1461 - 100100 + (8 - juMonth) / 6;
    }

    private int gregorianDateToJDN(int year, int month, int day) {
        int jdn = (year + (month - 8) / 6 + 100100) * 1461 / 4 + (153 * ((month + 9) % 12) + 2) / 5 + day - 34840408;
        jdn = jdn - (year + 100100 + (month - 8) / 6) / 100 * 3 / 4 + 752;
        return (jdn);
    }

    private void JDNToGregorian() {
        int j = 4 * JDN + 139361631;
        j = j + (((((4 * JDN + 183187720) / 146097) * 3) / 4) * 4 - 3908);
        int i = ((j % 1461) / 4) * 5 + 308;
        gDay = (i % 153) / 5 + 1;
        gMonth = ((i / 153) % 12) + 1;
        gYear = j / 1461 - 100100 + (8 - gMonth) / 6;
    }
}
