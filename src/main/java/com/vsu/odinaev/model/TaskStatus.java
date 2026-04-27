package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Перечисление статусов задачи.
 *
 * <p>Описывает жизненный цикл задачи на Kanban-доске. Переходы между
 * статусами выполняются через эндпоинт {@code POST /v1/tasks/{taskId}/status}.</p>
 *
 * <ul>
 *   <li>{@link #TODO}        — задача создана, ещё не взята в работу</li>
 *   <li>{@link #IN_PROGRESS} — задача выполняется</li>
 *   <li>{@link #REVIEW}      — задача на проверке кода</li>
 *   <li>{@link #TO_TEST}     — задача ожидает тестирования</li>
 *   <li>{@link #IN_TEST}     — задача тестируется</li>
 *   <li>{@link #DONE}        — задача завершена</li>
 * </ul>
 */
public enum TaskStatus {

    /**
     * Задача создана, ещё не взята в работу.
     */
    TODO("TODO"),

    /**
     * Задача находится в работе у исполнителя.
     */
    IN_PROGRESS("IN_PROGRESS"),

    /**
     * Задача передана на ревью кода.
     */
    REVIEW("REVIEW"),

    /**
     * Задача ожидает тестирования.
     */
    TO_TEST("TO_TEST"),

    /**
     * Задача находится на тестировании.
     */
    IN_TEST("IN_TEST"),

    /**
     * Задача выполнена и закрыта.
     */
    DONE("DONE");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    /**
     * Convert a String into String, as specified in the
     * <a href="https://download.oracle.com/otndocs/jcp/jaxrs-2_0-fr-eval-spec/index.html">See JAX RS 2.0 Specification, section 3.2, p. 12</a>
     */
    public static TaskStatus fromString(String s) {
        for (TaskStatus b : TaskStatus.values()) {
            // using Objects.toString() to be safe if value type non-object type
            // because types like 'int' etc. will be auto-boxed
            if (java.util.Objects.toString(b.value).equals(s)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected string value '" + s + "'");
    }

    @JsonCreator
    public static TaskStatus fromValue(String value) {
        for (TaskStatus b : TaskStatus.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}


