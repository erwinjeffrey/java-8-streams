package org.javainaction.lambdas.datechapter12;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.util.Locale;

public class DateMain {
    public static void main(String[] args) {

        LocalDate date  = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        System.out.println("Year: " + year + " Month: " + month + " day: " + day);
        System.out.println("DayOfWeek: " + dow + " len: " + len + " boolean: " + leap);

        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);

        System.out.println("Reading LocalDate values using a TemporalField");
        int year2 = today.get(ChronoField.YEAR);
        int month2 = today.get(ChronoField.MONTH_OF_YEAR);
        int day2 = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println("year: " + year2 + " Month: " + month2 + " day: " + day2);

        System.out.println("Creating a LocalTime and reading its values");
        LocalTime time = LocalTime.of(13,45,20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        LocalDate date2 = LocalDate.parse("2014-03-18");
        LocalTime time2 = LocalTime.parse("13:45:20");

        System.out.println("Creating a LocalDateTime directly or by combining a date and a time");

        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18,13,45,20);
        LocalDateTime dt2 = LocalDateTime.of(date2, time2);
        LocalDateTime dt3 = date2.atTime(13,45,20);
        LocalDateTime dt4 = date2.atTime(time2);
        LocalDateTime dt5 = time2.atDate(date2);

        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(date1);

        System.out.println("Instant: " + Instant.now());
        Instant.ofEpochSecond(3);

        System.out.println("Defining a Duration or a Period");
        Duration d1 = Duration.between(time1, time2);
        Duration d2 = Duration.between(dt1, dt2);
        Duration d3 = Duration.between(Instant.ofEpochSecond(3),Instant.now());

        Period tenDays = Period.between(LocalDate.of(2014,3,8),
                LocalDate.of(2014,3,18));
        System.out.println("days: " + tenDays.getDays());

        System.out.println("Creating Durations and Periods");
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays2 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2,6,1);

        System.out.println("Manipulating the attributes of a LocalDate in an absolute way");
        LocalDate date3 = LocalDate.of(2014, 3, 18);
        LocalDate date4 = date3.withYear(2011);
        LocalDate date5 = date4.withDayOfMonth(25);
        LocalDate date6 = date5.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println(date6.getYear());

        System.out.println("Manipulating the attributes of a LocalDate in a relative way");
        LocalDate date7 = LocalDate.of(2014,3,18);
        LocalDate date8 = date7.plusWeeks(1);
        LocalDate date9 = date8.minusYears(3);
        LocalDate date10 = date9.plus(6, ChronoUnit.MONTHS);

       LocalDate date11 = LocalDate.of(2014,3,18);
       date11 = date11.with(ChronoField.MONTH_OF_YEAR, 9);
       date11 = date11.plusYears(2).minusDays(10);
       date11.withYear(2011);
       System.out.println(date11);

        System.out.println(" Using the predifined TemporalAdjusters");
        LocalDate date13 = LocalDate.of(2014,3,18);
        LocalDate date14 = date13.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date15 = date14.with(TemporalAdjusters.lastDayOfMonth());

        System.out.println("Implementing a custom TemporalAdjuster: ");
        LocalDate localDate = LocalDate.of(2020,9,7);
        localDate = localDate.with(new NextWorkingDay());
        System.out.println(localDate);

        System.out.println("Implementing a custom TemporalAdjuster with Lambda: ");
        LocalDate localDate1 = LocalDate.of(2020,9,7);
        localDate1 = localDate1.with(temporal -> {
            DayOfWeek doweek =
                    DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if(doweek == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if(doweek == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(localDate1);

        System.out.println("Using ofDateAdjuster");
        TemporalAdjuster nextWorkingDay2 = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek doweek =
                            DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                            int dayToAdd = 1;
                            if(doweek == DayOfWeek.FRIDAY) dayToAdd = 3;
                            if (doweek == DayOfWeek.SATURDAY) dayToAdd = 2;
                            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                }
        );
        LocalDate localDate2 = LocalDate.now();
        localDate2 = localDate2.with(nextWorkingDay2);
        System.out.println(localDate2);

        System.out.println("Printing and parsing date-time objects ");
        LocalDate date16 = LocalDate.of(2014,3,18);
        String s1 = date16.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(s1);
        String s2 = date16.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s2);

        LocalDate localDate3 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate localDate4 = localDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(localDate4.toString());

        System.out.println("Creating a DateTimeFormatter from a pattern: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate5 = LocalDate.of(2014,3,18);
        String formattedDate = localDate5.format(formatter);
        LocalDate localDate6 = LocalDate.parse(formattedDate,formatter);
        System.out.println(formattedDate);

        System.out.println("Creating a localized DateTimeFormatter");
        DateTimeFormatter italianFormatter =
                DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate localDate7 = LocalDate.of(2014,3,18);
        String formattedDate2 = date.format(italianFormatter);
        System.out.println(formattedDate2);
        LocalDate localDate8 = LocalDate.parse(formattedDate2, italianFormatter);
        System.out.println(localDate8);

        System.out.println("Building a DateTimeFormatter");
        DateTimeFormatter italianFormatter2 =  new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(".")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        LocalDate localDate9 = LocalDate.now();
        System.out.println( localDate9.format(italianFormatter));


        System.out.println("Working with different time zones and calendars");
        ZoneId  romeZone = ZoneId.of("Europe/Rome");

        LocalDate localDate10 = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);

        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18,13,45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);

        System.out.println(dateTime.toString());

        System.out.println("Fixed offset from UTC/Greenwich");

        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
        LocalDateTime dateTime1 = LocalDateTime.now();
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime1, newYorkOffset);
        System.out.println(dateTimeInNewYork.toString());


    }
}
