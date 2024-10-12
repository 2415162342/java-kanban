import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int nextId = 1;

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void addNewTask(Task task) {
        task.setId(nextId());
        tasks.put(task.getId(), task);
    }

    public void addNewEpic(Epic epic) {
        epic.setId(nextId());
        epics.put(epic.getId(), epic);
    }

    public void addNewSubtask(Subtask subtask) {
        subtask.setId(nextId());
        subtasks.put(subtask.getId(), subtask);
        Epic epicList = epics.get(subtask.getEpId());
        epicList.getSubIds().add(subtask.getId());
    }

    public Task getIdTask(int id) {
        return tasks.get(id);
    }

    public Epic getIdEpic(int id) {
        return epics.get(id);
    }

    public Subtask getIdSubtask(int id) {
        return subtasks.get(id);
    }

    public void deleteIdTask(int id) {
        tasks.remove(id);
    }

    public void deleteIdEpic(int id) {
        Epic epic = epics.get(id);
        for (Integer idSubtask : epic.getSubIds()) {
            subtasks.remove(idSubtask);
        }
        epics.remove(id);
    }

    public void deleteAllTask() {
        tasks.clear();
    }

    public void deleteAllEpic() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubtask() {
        subtasks.clear();
        for(Epic epic : epics.values()) {
            epic.getSubIds().clear();
            updateStatusEpic(epic);
        }
    }

    public void deleteIdSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        int epicId = subtask.getEpId();
        Epic epic = epics.get(epicId);
        subtasks.remove(id);
        epic.getSubIds().remove(Integer.valueOf(id));

        updateStatusEpic(epic);
    }

    public ArrayList<Subtask> printSubtasksByIdEpic(int id) {
        ArrayList<Subtask> subtaskList = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpId() == id) {
                subtaskList.add(subtask);
            }
        }
        return subtaskList;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        updateStatusEpic(epic);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic updateEpic = epics.get(subtask.getEpId()) ;
        updateStatusEpic(updateEpic);
    }

    private int nextId() {
        return nextId++;
    }

    private void updateStatusEpic(Epic epic) {

        int countStatusNew = 0;
        for (Integer subId : epic.getSubIds()) {
            Subtask subtask = subtasks.get(subId);
            if (subtask.getStatus().equals(Status.NEW)) {
                countStatusNew++;
            }
        }

        int countStatusDone = 0;
        for (Integer list : epic.getSubIds()) {
            Subtask subtask = subtasks.get(list);
            if (subtask.getStatus().equals(Status.DONE)) {
                countStatusDone++;
            }
        }

        if (epic.getSubIds().isEmpty() || epic.getSubIds().size() == countStatusNew) {
            epic.setStatus(Status.NEW);
        } else if (epic.getSubIds().size() == countStatusDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

}
