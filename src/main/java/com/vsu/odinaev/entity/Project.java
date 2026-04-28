package com.vsu.odinaev.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Проект — контейнер для задач и участников.
 *
 * <p>Хранится в таблице {@code projects}. Каждый проект принадлежит одному
 * владельцу ({@link User}), который создаёт его через API.</p>
 *
 * <p>При удалении проекта каскадно удаляются все его задачи ({@link Task})
 * и записи об участниках ({@link ProjectMember}).</p>
 */
@Entity
@Table(name = "projects", indexes = @Index(name = "idx_projects_owner", columnList = "owner_id"))
public class Project extends PanacheEntityBase {

    /**
     * Уникальный идентификатор проекта (UUID, генерируется автоматически).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    /**
     * Название проекта. Обязательное поле, максимум 255 символов.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    public String name;

    /**
     * Описание проекта. Необязательное поле, хранится как TEXT.
     */
    @Column(columnDefinition = "TEXT")
    public String description;

    /**
     * Владелец проекта — пользователь, создавший его.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    public User owner;

    /**
     * Дата и время создания проекта (заполняется автоматически, не изменяется).
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    public Instant createdAt;

    /**
     * Дата и время последнего обновления проекта (заполняется автоматически).
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    public Instant updatedAt;

    /**
     * Список задач проекта. Каскадно удаляются при удалении проекта.
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Task> tasks = new ArrayList<>();

    /**
     * Список участников проекта. Каскадно удаляются при удалении проекта.
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ProjectMember> members = new ArrayList<>();
}