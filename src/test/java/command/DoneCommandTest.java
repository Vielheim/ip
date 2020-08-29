package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import duke.command.DoneCommand;
import duke.exception.InvalidTaskIndexException;
import duke.exception.TaskDoneException;
import duke.logic.UiManager;
import duke.task.DukeTask;
import stub.DukeTaskStub;
import stub.TaskListStub;


public class DoneCommandTest {
    @Test
    public void testDone() throws InvalidTaskIndexException, TaskDoneException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DoneCommand command = new DoneCommand(0);
        DukeTask stub = new DukeTaskStub();

        TaskListStub taskListStub = new TaskListStub();
        taskListStub.addToList(stub);

        command.execute(taskListStub, new UiManager(), null);

        String expected = "Alright! I'll mark this task as done!\n"
                + "[✓] Testing DukeTaskStub\n"
                + "You now have 1 task\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testInvalidDone() {
        DukeTask stub = new DukeTaskStub();

        TaskListStub taskListStub = new TaskListStub();
        taskListStub.addToList(stub);

        DoneCommand lessThanZeroCommand = new DoneCommand(-1);
        DoneCommand moreThanIndexCommand = new DoneCommand(100);

        assertThrows(InvalidTaskIndexException.class, (
        ) -> lessThanZeroCommand.execute(taskListStub, new UiManager(), null));
        assertThrows(InvalidTaskIndexException.class, (
        ) -> moreThanIndexCommand.execute(taskListStub, new UiManager(), null));

    }
}
