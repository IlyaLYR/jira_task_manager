package com.vsu.odinaev.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaginatedTasksResponse {

    @NotNull
    private List<@Valid TaskResponse> items = new ArrayList<>();

    @NotNull
    private Meta meta;

    // Геттеры и сеттеры
    public List<@Valid TaskResponse> getItems() {
        return items;
    }

    public void setItems(List<@Valid TaskResponse> items) {
        this.items = items;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    // Удобные методы для добавления/удаления элементов (опционально)
    public PaginatedTasksResponse addItemsItem(TaskResponse item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        return this;
    }

    public PaginatedTasksResponse removeItemsItem(TaskResponse item) {
        if (item != null && this.items != null) {
            this.items.remove(item);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginatedTasksResponse that = (PaginatedTasksResponse) o;
        return Objects.equals(items, that.items) &&
                Objects.equals(meta, that.meta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, meta);
    }

    @Override
    public String toString() {
        return "PaginatedTasksResponse{" +
                "items=" + items +
                ", meta=" + meta +
                '}';
    }

    // Вложенный класс Meta (можно вынести отдельно, но для целостности оставим здесь)
    public static class Meta {
        private Integer page;
        private Integer limit;
        private Integer total;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Meta meta = (Meta) o;
            return Objects.equals(page, meta.page) &&
                    Objects.equals(limit, meta.limit) &&
                    Objects.equals(total, meta.total);
        }

        @Override
        public int hashCode() {
            return Objects.hash(page, limit, total);
        }

        @Override
        public String toString() {
            return "Meta{" +
                    "page=" + page +
                    ", limit=" + limit +
                    ", total=" + total +
                    '}';
        }
    }
}