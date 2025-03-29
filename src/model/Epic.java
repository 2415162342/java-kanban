package model;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Subtask> subtasks;

    public Epic(String name, String text) {
        super(name, text);
        this.subtasks = new ArrayList<>();
    }

    public ArrayList<Subtask> getSubtasks() {

        return subtasks;
    }
}
