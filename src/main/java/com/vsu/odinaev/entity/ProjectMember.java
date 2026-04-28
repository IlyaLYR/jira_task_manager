package com.vsu.odinaev.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

/**
 * Участник проекта — связующая таблица между {@link User} и {@link Project}.
 *
 * <p>Хранится в таблице {@code project_members}. Пара {@code (project_id, user_id)}
 * уникальна: один пользователь не может состоять в одном проекте дважды
 * (ограничение {@code uk_project_member}).</p>
 *
 * <p>Поле {@code role} описывает роль пользователя в проекте (например,
 * {@code "member"}, {@code "admin"}). По умолчанию — {@code "member"}.</p>
 */
@Entity
@Table(
        name = "project_members",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_project_member",
                columnNames = {"project_id", "user_id"}
        ),
        indexes = {
                @Index(name = "idx_pm_project", columnList = "project_id"),
                @Index(name = "idx_pm_user", columnList = "user_id")
        }
)
public class ProjectMember extends PanacheEntityBase {

    /**
     * Уникальный идентификатор записи об участии (UUID, генерируется автоматически).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    /**
     * Проект, в котором состоит пользователь.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    public Project project;

    /**
     * Пользователь — участник проекта.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    /**
     * Роль пользователя в проекте. По умолчанию — {@code "member"}.
     */
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    public String role = "member";

    /**
     * Дата и время вступления пользователя в проект (заполняется автоматически, не изменяется).
     */
    @CreationTimestamp
    @Column(name = "joined_at", nullable = false, updatable = false)
    public Instant joinedAt;
}