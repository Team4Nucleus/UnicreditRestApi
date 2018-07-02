package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicredit.cap.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	
}
