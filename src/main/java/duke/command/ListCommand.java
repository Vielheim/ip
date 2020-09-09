package duke.command;
import java.util.stream.IntStream;

import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;


/**
 * Represents a List Command by the user.
 * It prints out the list of user's <code>DukeTask</code>
 * to the user using <code>UIManager</code>
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public boolean getExitStatus() {
        return false;
    }

    /**
     * Prints all <code>DukeTask</code> for the user.
     * If the task is a form of GUI command, sets response to the result instead.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @param isGuiTask      <code>boolean</code> object to denote GUI task
     */
    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager,
                        StorageManager storageManager, boolean isGuiTask) {
        assert uiManager != null : "ListCommand must have a uiManager";
        assert taskList != null : "ListCommand must have a taskList";
        if (isGuiTask) {
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < taskList.getSize(); i++) {
                output.append(uiManager.getNumberedTask(taskList.getTaskList().get(i), i)).append("\n");
            }
            output.append("\n").append(uiManager.getTaskStatus(taskList.getSize()));
            response = output.toString();
        } else {
            IntStream.range(0, taskList.getSize())
                    .forEach(i -> uiManager.printNumberedTask(taskList.getTaskList().get(i), i));
            System.out.println();
            uiManager.printTaskStatus(taskList.getSize());
        }
    }
}
