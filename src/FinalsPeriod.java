/**
 * The FinalsPeriod class represents a specific finals period, 
 * including the time, day, month, and year of the exam.
 * This class extends the ClassPeriod class to include date-related details.
 */
public class FinalsPeriod extends ClassPeriod {
    private String day;
    private String month;
    private int year;

    /**
     * Constructs a FinalsPeriod with the specified time, day, month, and year.
     *
     * @param time  The time of the finals period.
     * @param day   The day of the finals period.
     * @param month The month of the finals period.
     * @param year  The year of the finals period.
     */
    public FinalsPeriod(String time, String day, String month, int year) {
        super(time, ""); // Calls the superclass constructor with time and an empty description.
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Returns the full date of the finals period in "Month Day, Year" format.
     *
     * @return The full date as a String.
     */
    public String getFullDate() {
        return month + " " + day + ", " + year;
    }

    /**
     * Returns the day of the finals period.
     *
     * @return The day as a String.
     */
    public String getDay() {
        return day;
    }

    /**
     * Returns the month of the finals period.
     *
     * @return The month as a String.
     */
    public String getMonth() {
        return month;
    }

    /**
     * Returns the year of the finals period.
     *
     * @return The year as an integer.
     */
    public int getYear() {
        return year;
    }
}
