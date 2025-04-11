package manager;

import model.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    ArrayList<Task> history = new ArrayList<>(10);

    public void add(Task task) {
        if(history.size() == 10) {
            history.removeFirst();
        }
        history.add(task);
    }

    public ArrayList<Task> getHistory() {
        return history;
    }
}
