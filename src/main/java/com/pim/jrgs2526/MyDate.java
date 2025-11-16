package com.pim.jrgs2526;


public class MyDate {

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";


    private int day;
    private Months month;
    private int year;
  
    public MyDate() { }

    public MyDate(int day, Months month, int year) {
      // validateYear(year);
      validateDay(day, month, year);

      this.day = day;
      this.month = month;
      this.year = year;
    }

    private boolean isLeapYear(int year) {
      return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    private void validateYear(int year) throws IllegalArgumentException {
      if (!isLeapYear(year)) {
        throw new IllegalArgumentException(ERR_INVALID_YEAR);
      }
    }

    private void validateDay(int day, Months month, int year) throws IllegalArgumentException {
      if (day < 1 || day > daysInMonth(month, year))
        throw new IllegalArgumentException(
                    month == Months.FEBRUARY && day == 29 ? ERR_INVALID_DATE : ERR_INVALID_DAY
            );
    }

    // Setters y Getterss
    
    public void setMonth(Months newMonth) throws IllegalArgumentException {
      if (day > daysInMonth(newMonth, year))
        throw new IllegalArgumentException(ERR_INVALID_MONTH);

      this.month = newMonth;
    }

    public void setDay(int newDay) throws IllegalArgumentException {
      if (newDay < 1 || newDay > daysInMonth(month, year))
        throw new IllegalArgumentException(ERR_INVALID_DAY);
    }

    public void setYear(int newYear) throws IllegalArgumentException {
      validateYear(newYear);

      if (this.day > daysInMonth(month, newYear))
        throw new IllegalArgumentException(ERR_INVALID_YEAR);
      this.year = newYear;
    }

    private int daysInMonth(Months month, int year) {
      switch (month) {
        case APRIL:   // Meses que tienen 30 creo
        case JUNE:
        case SEPTEMBER:
        case NOVEMBER:
          return 30;
        case FEBRUARY:
          return isLeapYear(year) ? 29 : 28;
        default:
          return 31;
      }
    }


    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth( int monthNumber ) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }

}
