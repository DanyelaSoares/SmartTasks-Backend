package com.smarttasks.repository;

import com.smarttasks.model.Task;
import com.smarttasks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
