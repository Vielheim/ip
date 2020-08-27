package command;

import duke.command.Command;
import duke.exception.InvalidInstructionException;
import org.junit.jupiter.api.Test;
import stub.CommandChildStub;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    public void testGetExitStatus() {
        Command falseCommand = new CommandChildStub(false);
        Command trueCommand = new CommandChildStub(true);

        assertFalse(falseCommand.getExitStatus());
        assertTrue(trueCommand.getExitStatus());
    }

    // This test tests if the inherited execute() in ChildStub can execute properly or not.
    @Test
    public void testCommandExecute() throws InvalidInstructionException, IOException {
        Command testCommand = new CommandChildStub(true);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // After this all System.out.println() statements will come to outContent stream.
        // This is a generic test so the inputs are ignored.
        // Will test more in detail for the specific children of Command
        testCommand.execute(null, null, null);
        String expectedOutput = "Testing Command\n";

        // Do the actual assertion.
        assertEquals(expectedOutput, outContent.toString());
    }
}
