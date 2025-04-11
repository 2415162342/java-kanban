package manager;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    private int counter = 1;
    private final Map<Integer, Task> taskHashMap = new HashMap<>();
    private final Map<Integer, Epic> epicHashMap = new HashMap<>();
    private final Map<Integer, Subtask> subtaskHashMap = new HashMap<>();
    private final HistoryManager historyManager = new InMemoryHistoryManager();

    @Override
    public void addTask(Task newTask) {
        newTask.setId(counter++);
        taskHashMap.put(newTask.getId(), newTask);
    }
    @Override
    public void addEpic(Epic newEpic) {
        newEpic.setId(counter++);
        epicHashMap.put(newEpic.getId(), newEpic);
    }
    @Override
    public void addSubtask(Subtask newSubtask) {
        newSubtask.setId(counter++);
        Epic epic = epicHashMap.get(newSubtask.getEpicId());
        epic.getSubtasks().add(newSubtask);
        subtaskHashMap.put(newSubtask.getId(), newSubtask);
    }

    public List<Task> getTask() {
        return new ArrayList<>(taskHashMap.values());
    }

    public List<Epic> getEpic() {
        return new ArrayList<>(epicHashMap.values());
    }

    public List<Subtask> getSubtask() {
        return new ArrayList<>(subtaskHashMap.values());
    }

    @Override
    public void deleteTasks() {
        taskHashMap.clear();
    }

    @Override
    public void deleteEpics() {
        epicHashMap.clear();
        subtaskHashMap.clear();
    }

    @Override
    public void deleteSubtasks() {
        subtaskHashMap.clear();
        for (Epic epic : epicHashMap.values()) {
            epic.getSubtasks().clear();
            updateStatusEpic(epic);
        }
    }

    public Task getIdTask(int id) {
        historyManager.add(taskHashMap.get(id));
        return taskHashMap.get(id);
    }

    public Epic getIdEpic(int id) {
        historyManager.add(epicHashMap.get(id));
        return epicHashMap.get(id);
    }

    public Subtask getIdSubtask(int id) {
        historyManager.add(subtaskHashMap.get(id));
        return subtaskHashMap.get(id);
    }

    @Override
    public void deleteIdTask(int id) {
        taskHashMap.remove(id);
    }

    @Override
    public void deleteIdEpic(int id) {
        Epic epic = epicHashMap.get(id);
        for (Subtask sub : epic.getSubtasks()) {
            subtaskHashMap.remove(sub.getId());
        }
        epicHashMap.remove(id);
    }

    @Override
    public void deleteIdSubtask(int id) {
        Subtask subtask = subtaskHashMap.get(id);
        Epic epic = epicHashMap.get(subtask.getEpicId());
        System.out.println(epic.getSubtasks());
        epic.getSubtasks().remove(Integer.valueOf(id));
        subtaskHashMap.remove(id);
        updateStatusEpic(epic);
    }

    @Override
    public List<Subtask> printSubtaskByIdEpic(int id) {
        Epic epic = epicHashMap.get(id);
        return epic.getSubtasks();
    }

    @Override
    public void updateTask(Task task, Status newStatus) {
        task.setStatus(newStatus);
        taskHashMap.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epicHashMap.put(epic.getId(), epic);
        updateStatusEpic(epic);
    }

    @Override
    public void updateSubtask(Subtask subtask, Status newStatus) {
        subtask.setStatus(newStatus);
        subtaskHashMap.put(subtask.getId(), subtask);
        Epic updateEpic = epicHashMap.get(subtask.getEpicId());
        updateStatusEpic(updateEpic);
    }

    private void updateStatusEpic(Epic epic) {
        int countStatusNew = 0;
        for (Subtask sub : epic.getSubtasks()) {
            if (sub.getStatus().equals(Status.NEW)) {
                countStatusNew++;
            }
        }
        int countStatusDone = 0;
        for (Subtask sub : epic.getSubtasks()) {
            if (sub.getStatus().equals(Status.DONE)) {
                countStatusDone++;
            }
        }
        if (epic.getSubtasks().isEmpty() || epic.getSubtasks().size() == countStatusNew) {
            epic.setStatus(Status.NEW);
        } else if (epic.getSubtasks().size() == countStatusDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

}
