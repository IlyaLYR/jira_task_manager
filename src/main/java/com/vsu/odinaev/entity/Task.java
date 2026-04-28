package com.vsu.odinaev.entity;

import com.vsu.odinaev.model.TaskStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

/**
 * Задача в рамках проекта.
 *
 * <p>Хранится в таблице {@code tasks}. Поле {@code number} уникально
 * в пределах одного проекта (ограничение {@code uk_task_project_number})
 * и генерируется автоматически сервисом при создании задачи.</p>
 *
 * <p>Набор возможных статусов задачи определён перечислением {@link com.vsu.odinaev.model.TaskStatus}.
 * Начальный статус при создании — {@code TODO}.</p>
 */
@Entity
@Table(
        name = "tasks",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_task_project_number",
                columnNames = {"project_id", "number"}
        ),
        indexes = {
                @Index(name = "idx_tasks_project_status", columnList = "project_id, status"),
                @Index(name = "idx_tasks_assignee", columnList = "assignee_id"),
                @Index(name = "idx_tasks_author", columnList = "author_id"),
                @Index(name = "idx_tasks_status", columnList = "status")
        }
)
public class Task extends PanacheEntityBase {

    /**
     * Уникальный идентификатор задачи (UUID, генерируется автоматически).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    /**
     * Порядковый номер задачи в рамках проекта (генерируется сервисом).
     */
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    public String number;

    /**
     * Заголовок задачи. Обязательное поле, максимум 255 символов.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    public String title;

    /**
     * Подробное описание задачи. Необязательное поле, хранится как TEXT.
     */
    @Column(columnDefinition = "TEXT")
    public String description;

    /**
     * Текущий статус задачи. По умолчанию — {@code TODO}.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    public TaskStatus status = TaskStatus.TODO;

    /**
     * Пользователь, создавший задачу. Обязательное поле.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    public User author;

    /**
     * Пользователь, которому назначена задача. Может быть {@code null}.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    public User assignee;

    /**
     * Проект, к которому относится задача. Обязательное поле.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    public Project project;

    /**
     * Дата и время создания задачи (заполняется автоматически, не изменяется).
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    public Instant createdAt;

    /**
     * Дата и время последнего обновления задачи (заполняется автоматически).
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    public Instant updatedAt;
}