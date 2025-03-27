import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private int counter = 1;
    private final HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private final HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    private final HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();

    public void addTask(Task newTask) {
        newTask.setId(counter++);
        taskHashMap.put(newTask.getId(), newTask);
    }

    public void addEpic(Epic newEpic) {
        newEpic.setId(counter++);
        epicHashMap.put(newEpic.getId(), newEpic);
    }

    public void addSubtask(Subtask newSubtask) {
        newSubtask.setId(counter++);
        Epic epic = epicHashMap.get(newSubtask.getEpicId());
        epic.getSubtasks().add(newSubtask.getId());
        subtaskHashMap.put(newSubtask.getId(), newSubtask);
    }

    public ArrayList<Task> getTask() {
        return new ArrayList<>(taskHashMap.values());
    }

    public ArrayList<Epic> getEpic() {
        return new ArrayList<>(epicHashMap.values());
    }

    public ArrayList<Subtask> getSubtask() {
        return new ArrayList<>(subtaskHashMap.values());
    }

    public void deleteTasks() {
        taskHashMap.clear();
    }

    public void deleteEpics() {
        epicHashMap.clear();
        subtaskHashMap.clear();
    }

    public void deleteSubtasks() {
        subtaskHashMap.clear();
        for (Epic epic : epicHashMap.values()) {
            epic.getSubtasks().clear();
            updateStatusEpic(epic);
        }
    }

    public Task getIdTask(int id) {
        return taskHashMap.get(id);
    }

    public Epic getIdEpic(int id) {
        return epicHashMap.get(id);
    }

    public Subtask getIdSubtask(int id) {
        return subtaskHashMap.get(id);
    }


    public void deleteIdTask(int id) {
        taskHashMap.remove(id);
    }

    public void deleteIdEpic(int id) {
        Epic epic = epicHashMap.get(id);
        for (int sub : epic.getSubtasks()) {
            subtaskHashMap.remove(sub);
        }
        epicHashMap.remove(id);
    }

    public void deleteIdSubtask(int id) {
        Subtask subtask = subtaskHashMap.get(id);
        Epic epic = epicHashMap.get(subtask.getEpicId());
        System.out.println(epic.getSubtasks());
        epic.getSubtasks().remove(Integer.valueOf(id));
        subtaskHashMap.remove(id);
        updateStatusEpic(epic);
    }

    public ArrayList<Subtask> printSubtaskByIdEpic(int id) {
        Epic epic = epicHashMap.get(id);
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (int sub : epic.getSubtasks()) {
            subtasksList.add(subtaskHashMap.get(sub));
        }
        return subtasksList;
    }

    public void updateTask(Task task, Status newStatus) {
        task.setStatus(newStatus);
        taskHashMap.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epicHashMap.put(epic.getId(), epic);
        updateStatusEpic(epic);
    }

    public void updateSubtask(Subtask subtask, Status newStatus) {
        subtask.setStatus(newStatus);
        subtaskHashMap.put(subtask.getId(), subtask);
        Epic updateEpic = epicHashMap.get(subtask.getEpicId());
        updateStatusEpic(updateEpic);
    }


    private void updateStatusEpic(Epic epic) {
        int countStatusNew = 0;
        for (int subId : epic.getSubtasks()) {
            Subtask subtask = subtaskHashMap.get(subId);
            if (subtask.getStatus().equals(Status.NEW)) {
                countStatusNew++;
            }
        }
        int countStatusDone = 0;
        for (int list : epic.getSubtasks()) {
            Subtask subtask = subtaskHashMap.get(list);
            if (subtask.getStatus().equals(Status.DONE)) {
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
