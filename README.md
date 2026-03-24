# Java Finals and Secret Santa Apps

This repository contains Java Swing applications for managing finals schedules and Secret Santa assignments.

## Applications

1. **Tommie Finals App** (`TommieFinalsApp.java`): Main launcher with welcome screen and toolkit selection.
2. **Finals Interpreter** (`FinalsInterpreterApp.java`): Find finals exam dates and times based on class schedules.
3. **Secret Santa** (`SecretSantaApp.java`): Manage friends and assign Secret Santa matches.

## How to Run

### Quickest Way (Pre-compiled JAR)
Simply download and **double-click** `TommieFinalsApp.jar` or run:
```
java -jar TommieFinalsApp.jar
```

### From Source
1. Ensure you have Java JDK installed (version 8 or higher).
2. Clone the repository.
3. Navigate to the `src/` directory.
4. Compile all Java files:
   ```
   javac *.java
   ```
5. Run the main application:
   ```
   java TommieFinalsApp
   ```

This will launch the GUI application where you can access both toolkits.

## Data Files

- `ExamSchedule.txt`: Contains the finals schedule data used by the Finals Interpreter.