import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Maps class times to their respective final exam schedules.
 */
public class ClassTimeMapper {
    private List<ScheduleEntry> scheduleEntries;

    /**
     * Constructs a ClassTimeMapper and loads the exam schedule from a file.
     *
     * @param filePath the path to the file containing the exam schedule
     */
    public ClassTimeMapper(String filePath) {
        scheduleEntries = new ArrayList<>();
        loadExamSchedule(filePath);
    }

    /**
     * Represents an entry in the schedule mapping class times to final exam details.
     */
    public static class ScheduleEntry {
        String classTime;
        String classDays;
        String finalsTime;
        String finalsDay;
        String finalsMonth;
        int finalsYear;

        /**
         * Constructs a ScheduleEntry with the specified details.
         *
         * @param classTime   the class time
         * @param classDays   the class days
         * @param finalsTime  the finals time
         * @param finalsDay   the finals day
         * @param finalsMonth the finals month
         * @param finalsYear  the finals year
         */
        ScheduleEntry(String classTime, String classDays, String finalsTime,
                      String finalsDay, String finalsMonth, int finalsYear) {
            this.classTime = classTime;
            this.classDays = classDays;
            this.finalsTime = finalsTime;
            this.finalsDay = finalsDay;
            this.finalsMonth = finalsMonth;
            this.finalsYear = finalsYear;
        }
    }

    /**
     * Loads the exam schedule from the specified file.
     *
     * Reads each line of the file, splits it into components, and creates
     * a ScheduleEntry for valid lines. Handles incorrect formats gracefully.
     *
     * @param filePath the path to the file containing the schedule
     */
    private void loadExamSchedule(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into components based on spaces
                String[] parts = line.trim().split(" ");

                if (parts.length >= 11) {
                    try {
                        // Parse each component of the schedule entry
                        String classTime = parts[0] + " to " + parts[2];
                        String classDays = parts[3];
                        String finalsTime = parts[4] + " to " + parts[6];
                        String finalsDay = parts[7];
                        String finalsMonth = parts[8];
                        int finalsYear = Integer.parseInt(parts[9]);

                        // Add the parsed entry to the list
                        scheduleEntries.add(new ScheduleEntry(classTime, classDays, finalsTime, finalsDay, finalsMonth, finalsYear));
                    } catch (Exception e) {
                        // Handle errors during parsing of a line
                        System.err.println("Error parsing line: " + line);
                        e.printStackTrace();
                    }
                } else {
                    // Handle lines with invalid formats
                    System.err.println("Invalid format in line: " + line);
                }
            }
        } catch (IOException e) {
            // Handle errors during file reading
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all unique class days from the schedule.
     *
     * Iterates through the schedule entries to collect unique days
     * and returns them as a sorted set.
     *
     * @return a sorted set of unique class days
     */
    public Set<String> getAllDays() {
        Set<String> uniqueDays = new TreeSet<>();
        for (ScheduleEntry entry : scheduleEntries) {
            uniqueDays.add(entry.classDays);
        }
        return uniqueDays;
    }

    /**
     * Retrieves all time periods for a given class day.
     *
     * Filters the schedule entries to find time periods that match the
     * specified day.
     *
     * @param day the class day to retrieve time periods for
     * @return a list of time periods for the specified day
     */
    public List<String> getTimePeriods(String day) {
        List<String> timePeriods = new ArrayList<>();
        for (ScheduleEntry entry : scheduleEntries) {
            if (entry.classDays.equals(day)) {
                timePeriods.add(entry.classTime);
            }
        }
        return timePeriods;
    }

    /**
     * Finds the final exam schedule for a given class day and time.
     *
     * Searches through the schedule entries to find an entry matching the
     * specified class day and time.
     *
     * @param classDay  the class day
     * @param classTime the class time
     * @return the schedule entry for the specified class day and time, or null if not found
     */
    public ScheduleEntry findFinalsPeriod(String classDay, String classTime) {
        for (ScheduleEntry entry : scheduleEntries) {
            if (entry.classDays.equals(classDay) && entry.classTime.equals(classTime)) {
                return entry;
            }
        }
        return null;
    }
}
