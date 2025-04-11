package model;

import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    InMemoryTaskManager taskManager = new InMemoryTaskManager();
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    @Test
    void addNewEpicTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);
        Epic savedEpic = taskManager.getIdEpic(epic.getId());

        Assertions.assertNotNull(epic, "Задача не найдена.");
        Assertions.assertEquals(epic, savedEpic, "Задачи не совпадают.");

        ArrayList<Epic> epicList = taskManager.getEpic();

        Assertions.assertNotNull(epicList, "Задачи не возвращаются.");
        Assertions.assertEquals(1, epicList.size(), "Неверное количество задач.");
        Assertions.assertEquals(epic, epicList.get(0), "Задачи не совпадают.");
    }

    @Test
    void addHistoryEpicTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);

        historyManager.add(epic);
        final ArrayList<Task> historyList = historyManager.getHistory();

        Assertions.assertNotNull(historyList, "После добавления задачи, история не должна быть пустой.");
        Assertions.assertEquals(1, historyList.size(), "После добавления задачи, история не должна быть пустой.");
    }

    @Test
    void deleteEpicsTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);
        ArrayList<Epic> epicList = taskManager.getEpic();

        Assertions.assertEquals(1, epicList.size(), "После добавления задачи, список задач не должен быть пустой.");

        taskManager.deleteEpics();
        ArrayList<Epic> epicListAfterDelete = taskManager.getEpic();
        Assertions.assertEquals(0, epicListAfterDelete.size(), "После удаления списка задач, список задач должен быть пустой.");
    }

    @Test
    void deleteIdEpicTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);
        int idEpic = epic.getId();

        taskManager.deleteIdEpic(idEpic);

        ArrayList<Epic> epicList = taskManager.getEpic();
        Epic savedEpic = null;
        for(Epic e : epicList) {
            if(e.getId() == idEpic) {
                savedEpic = e;
            }
        }
        Assertions.assertNull(savedEpic, "После удаления задачи из списка, этой задачи в списке быть не должно.");
    }

    @Test
    void updateEpicTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask("Subtask 1", "Text Subtask 1", epic.getId());
        taskManager.addSubtask(subtask);

        Assertions.assertEquals(Status.NEW, epic.getStatus(), "Статус задачи должен быть NEW");

        taskManager.updateSubtask(subtask, Status.IN_PROGRESS);
        Assertions.assertEquals(Status.IN_PROGRESS, epic.getStatus(), "Статус задачи должен быть IN_PROGRESS");
    }
}