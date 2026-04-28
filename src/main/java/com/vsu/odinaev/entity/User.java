package com.vsu.odinaev.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Пользователь системы.
 *
 * <p>Хранится в таблице {@code users}. Уникальность логина обеспечивается
 * ограничением базы данных и индексом {@code idx_users_login}.</p>
 *
 * <p>Использует паттерн Active Record Panache: запросы выполняются
 * непосредственно на классе ({@code User.find(...)}, {@code User.persist()}).</p>
 */
@Entity
@Table(name = "users", indexes = @Index(name = "idx_users_login", columnList = "login"))
public class User extends PanacheEntityBase {

    /**
     * Уникальный идентификатор пользователя (UUID, генерируется автоматически).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    /**
     * Уникальный логин пользователя. Используется для аутентификации и как {@code upn} в JWT.
     */
    @NotBlank
    @Size(max = 255)
    @Column(unique = true, nullable = false, length = 255)
    @Username
    public String login;

    /**
     * Хэш пароля, вычисленный алгоритмом bcrypt. Исходный пароль не хранится.
     */
    @NotBlank
    @Size(max = 255)
    @Column(name = "password_hash", nullable = false, length = 255)
    @Password
    public String passwordHash;

    /**
     * Дата и время создания записи (заполняется автоматически, не изменяется).
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    public Instant createdAt;

    /**
     * Дата и время последнего обновления записи (заполняется автоматически).
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    public Instant updatedAt;

    /**
     * Задачи, автором которых является данный пользователь.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Task> authoredTasks = new ArrayList<>();

    /**
     * Задачи, назначенные данному пользователю как исполнителю.
     */
    @OneToMany(mappedBy = "assignee")
    public List<Task> assignedTasks = new ArrayList<>();

    /**
     * Проекты, владельцем которых является данный пользователь.
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Project> ownedProjects = new ArrayList<>();

    /**
     * Участия данного пользователя в проектах (роль в каждом проекте).
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ProjectMember> projectMemberships = new ArrayList<>();
}