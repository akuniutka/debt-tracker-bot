package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findAllByUserId(Long userId);
}