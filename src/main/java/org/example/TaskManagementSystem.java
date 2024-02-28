package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Тестовое задание.

public class TaskManagementSystem {
    // Хранилище задач, где ключ - ID пользователя, а значение - список его задач
    private Map<String, List<Task>> taskMap = new HashMap<>();

    // Метод для создания новой задачи
    public void createTask(String userId, Task task) {
        // Проверяем, существует ли пользователь в хранилище задач
        if (!taskMap.containsKey(userId)) {
            taskMap.put(userId, new ArrayList<>()); // Если нет, создаем новый список задач для него
        }
        List<Task> userTasks = taskMap.get(userId); // Получаем список задач пользователя
        userTasks.add(task); // Добавляем новую задачу в список
        taskMap.put(userId, userTasks); // Обновляем хранилище задач
    }

    // Метод для получения списка задач по ID пользователя
    public List<Task> getTasksByUserId(String userId) {
        return taskMap.getOrDefault(userId, new ArrayList<>()); // Возвращаем список задач пользователя или пустой список, если пользователь не найден
    }

    // Метод для редактирования задачи
    public void editTask(String userId, int taskId, Task updatedTask) {
        List<Task> userTasks = taskMap.getOrDefault(userId, new ArrayList<>()); // Получаем список задач пользователя
        if (taskId >= 0 && taskId < userTasks.size()) { // Проверяем, существует ли задача с указанным ID
            userTasks.set(taskId, updatedTask); // Обновляем задачу в списке
            taskMap.put(userId, userTasks); // Обновляем хранилище задач
        }
    }

    // Метод для удаления задачи
    public void deleteTask(String userId, int taskId) {
        List<Task> userTasks = taskMap.getOrDefault(userId, new ArrayList<>()); // Получаем список задач пользователя
        if (taskId >= 0 && taskId < userTasks.size()) { // Проверяем, существует ли задача с указанным ID
            userTasks.remove(taskId); // Удаляем задачу из списка
            taskMap.put(userId, userTasks); // Обновляем хранилище задач
        }
    }

    // Главный метод для тестирования
    public static void main(String[] args) {
        TaskManagementSystem taskManagementSystem = new TaskManagementSystem();

        // Создаем несколько задач
        Task task1 = new Task("Task 1", "Description 1", Task.Status.IN_PROGRESS, Task.Priority.HIGH, "User1", "User2");
        Task task2 = new Task("Task 2", "Description 2", Task.Status.TODO, Task.Priority.MEDIUM, "User1", "User3");
        Task task3 = new Task("Task 3", "Description 3", Task.Status.DONE, Task.Priority.LOW, "User2", "User1");

        // Добавляем задачи в систему
        taskManagementSystem.createTask("User1", task1);
        taskManagementSystem.createTask("User1", task2);
        taskManagementSystem.createTask("User2", task3);

        // Получаем задачи пользователя User1
        List<Task> user1Tasks = taskManagementSystem.getTasksByUserId("User1");
        System.out.println("User1's tasks:");
        for (Task task : user1Tasks) {
            System.out.println(task);
        }

        // Редактируем задачу
        Task updatedTask = new Task("Task 2 Updated", "Updated Description", Task.Status.IN_PROGRESS, Task.Priority.HIGH, "User1", "User3");
        taskManagementSystem.editTask("User1", 1, updatedTask);

        // Удаляем задачу
        taskManagementSystem.deleteTask("User2", 0);
    }
}

// Класс задачи
class Task {
    // Возможные статусы задачи
    public enum Status {TODO, IN_PROGRESS, DONE}
    // Возможные приоритеты задачи
    public enum Priority {LOW, MEDIUM, HIGH}

    private String title; // Заголовок задачи
    private String description; // Описание задачи
    private Status status; // Статус задачи
    private Priority priority; // Приоритет задачи
    private String author; // Автор задачи
    private String assignee; // Исполнитель задачи
    private String assignee2; // Для проверки пуша

    // Конструктор задачи
    public Task(String title, String description, Status status, Priority priority, String author, String assignee) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        this.assignee = assignee;
    }

    // Переопределенный метод toString для вывода информации о задаче
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", author='" + author + '\'' +
                ", assignee='" + assignee + '\'' +
                '}';
    }


}