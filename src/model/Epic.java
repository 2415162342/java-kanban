package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private final List<Subtask> subtasks;

    public Epic(String name, String text) {
        super(name, text);
        this.subtasks = new ArrayList<>();
    }

    public List<Subtask> getSubtasks() {

        return subtasks;
    }
}
