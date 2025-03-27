import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Integer> subtasks;

    public Epic(String name, String text) {
        super(name, text);
        this.subtasks = new ArrayList<>();
    }

    public ArrayList<Integer> getSubtasks() {
        return subtasks;
    }
}
