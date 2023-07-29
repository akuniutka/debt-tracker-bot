package dev.akuniutka.debttracker.dao;

public interface Dao<T> {
    T save(T t);
}
