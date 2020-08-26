package duke.task;

import duke.CommonString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends DukeTask {
    private final LocalDateTime datetime;

    public DeadlineTask(String description, LocalDateTime datetime) {
        super(description);
        this.datetime = datetime;
    }

    public String getDatetime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(CommonString.DUKE_DATETIME_FORMAT.toString());
        return datetime.format(df);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getDatetime());
    }
}