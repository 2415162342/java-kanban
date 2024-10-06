import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();  //После проверки изменить на private
    private int nextId = 1;

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }

    public void addNewTask(Task task) {
        task.id = nextId++;
        task.status = Status.NEW;
        tasks.put(task.id, task);
    }

    public void addNewEpic(Epic epic) {
        epic.id = nextId++;
        epic.status = Status.NEW;
        epics.put(epic.id, epic);
    }

    public void addNewSubtask(Subtask subtask) {
        subtask.id = nextId++;
        subtasks.put(subtask.id, subtask);
        subtask.status = Status.NEW;
        Epic epicList = epics.get(subtask.epId);
        epicList.subIds.add(subtask.id);
    }

    public void printAllTasks() {
    ArrayList<Task> taskList = new ArrayList<>(tasks.values());
        System.out.println(taskList);

        for(int epId : epics.keySet()) {
            System.out.println(epics.get(epId));
            ArrayList<Subtask> subtaskList = new ArrayList<>();
            for(Subtask sub : subtasks.values()) {
                if(sub.epId == epId) {
                    subtaskList.add(subtasks.get(sub.id));
                }
            }
            System.out.println(subtaskList);
        }
    }

    public Task getIdTask(int id) {
        if(!tasks.containsKey(id)) {
            return null;
        } else {
            return tasks.get(id);
        }
    }

    public Epic getIdEpic(int id) {
        if(!epics.containsKey(id)) {
            return null;
        } else {
            return epics.get(id);
        }
    }

    public void deleteIdTask(int id) {
        if(!tasks.containsKey(id)) {
            System.out.println("Задачи с такие id " + id + " нет.");
        } else {
            tasks.remove(id);
            System.out.println("Задача удалена");
        }
    }

    public void deleteIdEpic(int id) {
        if(!epics.containsKey(id)) {
            System.out.println("Эпика с таким id " + id + " нет.");
        } else {
            epics.remove(id);
            System.out.println("Эпик удалён");
        }
    }


    public void deleteAllTasks() {
        if(tasks.size() + epics.size() + subtasks.size() == 0) {
            System.out.println("Список пуст");
        } else {
            tasks.clear();
            epics.clear();
            subtasks.clear();
            System.out.println("Все задачи удалены!");
        }
    }

    public void printSubtasksByIdEpic(int id) {
        Epic epicList = epics.get(id);
        for(Integer list : epicList.subIds) {
            System.out.println(subtasks.get(list));
        }
    }

    public void updateTask(Task task) {
        tasks.put(task.id, task);
    }

    public void updateEpic(int id) {
        if(!epics.containsKey(id)) {
            System.out.println("Эпика с таким id " + id + " нет.");
            }
        Epic epic = epics.get(id);

            int countStatusNew = 0;
            for(Integer list : epic.subIds) {
                Subtask subtask = subtasks.get(list);
                if(subtask.status.equals(Status.NEW)) {
                    countStatusNew++;
                }
            }

            int countStatusDone = 0;
            for(Integer list : epic.subIds) {
                Subtask subtask = subtasks.get(list);
                if(subtask.status.equals(Status.DONE)) {
                    countStatusDone++;
                }
            }

            if(epic.subIds.isEmpty() || epic.subIds.size() == countStatusNew) {
                epic.status = Status.NEW;
            } else if(epic.subIds.size() == countStatusDone) {
                epic.status = Status.DONE;
            } else {
                epic.status = Status.IN_PROGRESS;
            }
        }

}
