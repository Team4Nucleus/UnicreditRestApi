package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicredit.cap.model.TaskStatus;


public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

	public TaskStatus getTaskStatusByCode(String code);
}
