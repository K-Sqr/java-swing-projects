/**
 * Represents a class period with its time and days.
 */
public class ClassPeriod {
    private String time;
    private String days;

    /**
     * Constructs a ClassPeriod instance with the specified time and days.
     *
     * @param time the time of the class period
     * @param days the days of the class period
     */
    public ClassPeriod(String time, String days) {
        this.time = time;
        this.days = days;
    }

    /**
     * Returns the time of the class period.
     *
     * @return the time of the class period
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns the days of the class period.
     *
     * @return the days of the class period
     */
    public String getDays() {
        return days;
    }
}

