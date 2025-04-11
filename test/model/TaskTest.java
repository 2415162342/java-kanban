package model;

import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    InMemoryTaskManager taskManager = new InMemoryTaskManager();
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    @Test
    void addNewTaskTest() {
        Task task = new Task("Task 1", "Text Task 1");
        taskManager.addTask(task);
        Task savedTask = taskManager.getIdTask(task.getId());

        Assertions.assertNotNull(task, "Задача не найдена.");
        Assertions.assertEquals(task, savedTask, "Задачи не совпадают.");

        List<Task> tasksList = taskManager.getTask();

        Assertions.assertNotNull(tasksList, "Задачи не возвращаются.");
        Assertions.assertEquals(1, tasksList.size(), "Неверное количество задач.");
        Assertions.assertEquals(task, tasksList.get(0), "Задачи не совпадают.");
    }

    @Test
    void addHistoryTaskTest() {
        Task task = new Task("Task 1", "Text Task 1");
        taskManager.addTask(task);

        historyManager.add(task);
        final List<Task> historyList = historyManager.getHistory();

        Assertions.assertNotNull(historyList, "После добавления задачи, история не должна быть пустой.");
        Assertions.assertEquals(1, historyList.size(), "После добавления задачи, история не должна быть пустой.");
    }

    @Test
    void deleteTasksTest() {
        Task task = new Task("Task 1", "Text Task 1");
        taskManager.addTask(task);
        List<Task> taskList = taskManager.getTask();

        Assertions.assertEquals(1, taskList.size(), "После добавления задачи, список задач не должен быть пустой.");

        taskManager.deleteTasks();
        List<Task> taskListAfterDelete = taskManager.getTask();
        Assertions.assertEquals(0, taskListAfterDelete.size(), "После удаления списка задач, список задач должен быть пустой.");
    }

    @Test
    void deleteIdTaskTest() {
        Task task = new Task("Task 1", "Text Task 1");
        taskManager.addTask(task);
        int idTask = task.getId();

        taskManager.deleteIdTask(idTask);

        List<Task> taskList = taskManager.getTask();
        Task savedTask = null;
        for(Task t : taskList) {
            if(t.getId() == idTask) {
                savedTask = t;
            }
        }
        Assertions.assertNull(savedTask, "После удаления задачи из списка, этой задачи в списке быть не должно.");
    }

    @Test
    void updateTaskTest() {
        Task task = new Task("Task 1", "Text Task 1");
        taskManager.addTask(task);

        Assertions.assertEquals(Status.NEW, task.getStatus(), "Статус задачи должен быть NEW");
        taskManager.updateTask(task, Status.IN_PROGRESS);
        Assertions.assertEquals(Status.IN_PROGRESS, task.getStatus(), "Статус задачи должен быть IN_PROGRESS");
    }
}