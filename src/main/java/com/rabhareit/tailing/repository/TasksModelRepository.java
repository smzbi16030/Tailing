package com.rabhareit.tailing.repository;

import com.rabhareit.tailing.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksModelRepository extends JpaRepository<TaskModel, Long> {
}
