package dev.akuniutka.debttracker.dao;

import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(Long id);
    T save(T t);
}
