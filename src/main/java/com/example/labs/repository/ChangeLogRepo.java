package com.example.labs.repository;

import com.example.labs.model.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeLogRepo extends JpaRepository<ChangeLog, Long> {
}
