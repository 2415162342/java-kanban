package model;

import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    InMemoryTaskManager taskManager = new InMemoryTaskManager();
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    @Test
    void addNewSubtaskTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Subtask 1", "Text Subtask 1", epic.getId());
        taskManager.addSubtask(subtask);

        Subtask savedSubtask = taskManager.getIdSubtask(subtask.getId());

        Assertions.assertNotNull(subtask, "Задача не найдена.");
        Assertions.assertEquals(subtask, savedSubtask, "Задачи не совпадают.");

        List<Subtask> subtaskList = taskManager.getSubtask();

        Assertions.assertNotNull(subtaskList, "Задачи не возвращаются.");
        Assertions.assertEquals(1, subtaskList.size(), "Неверное количество задач.");
        Assertions.assertEquals(subtask, subtaskList.get(0), "Задачи не совпадают.");
    }

    @Test
    void addHistorySubtaskTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask("Subtask 1", "Text Subtask 1", epic.getId());

        historyManager.add(subtask);
        final List<Task> historyList = historyManager.getHistory();

        Assertions.assertNotNull(historyList, "После добавления задачи, история не должна быть пустой.");
        Assertions.assertEquals(1, historyList.size(), "После добавления задачи, история не должна быть пустой.");
    }

    @Test
    void deleteSubtasksTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask("Subtask 1", "Text Subtask 1", epic.getId());
        taskManager.addSubtask(subtask);

        List<Subtask> subtaskList = taskManager.getSubtask();

        Assertions.assertEquals(1, subtaskList.size(), "После добавления задачи, список задач не должен быть пустой.");

        taskManager.deleteEpics();
        List<Epic> subtaskListAfterDelete = taskManager.getEpic();
        Assertions.assertEquals(0, subtaskListAfterDelete.size(), "После удаления списка задач, список задач должен быть пустой.");
    }

    @Test
    void deleteIdSubtaskTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask("Subtask 1", "Text Subtask 1", epic.getId());
        taskManager.addSubtask(subtask);
        int idSubtask = subtask.getId();

        taskManager.deleteIdSubtask(idSubtask);

        List<Subtask> subtaskList = taskManager.getSubtask();
        Subtask savedSubtask = null;
        for(Subtask s : subtaskList) {
            if(s.getId() == idSubtask) {
                savedSubtask = s;
            }
        }
        Assertions.assertNull(savedSubtask, "После удаления задачи из списка, этой задачи в списке быть не должно.");
    }

    @Test
    void updateSubtaskTest() {
        Epic epic = new Epic("Epic 1", "Text Epic 1");
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask("Subtask 1", "Text Subtask 1", epic.getId());
        taskManager.addSubtask(subtask);

        Assertions.assertEquals(Status.NEW, subtask.getStatus(), "Статус задачи должен быть NEW");
        taskManager.updateSubtask(subtask, Status.IN_PROGRESS);
        Assertions.assertEquals(Status.IN_PROGRESS, subtask.getStatus(), "Статус задачи должен быть IN_PROGRESS");
    }

}