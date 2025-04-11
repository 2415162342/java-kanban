package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

public interface TaskManager {

    void addTask(Task newTask);
    void addEpic(Epic newEpic);
    void addSubtask(Subtask newSubtask);

    void deleteTasks();
    void deleteEpics();
    void deleteSubtasks();

    void deleteIdTask(int id);
    void deleteIdEpic(int id);
    void deleteIdSubtask(int id);

    ArrayList<Subtask> printSubtaskByIdEpic(int id);

    void updateTask(Task task, Status newStatus);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask, Status newStatus);

}
