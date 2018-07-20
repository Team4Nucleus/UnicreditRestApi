package com.unicredit.cap.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.TaskDetail;;

public interface TaskDetailRepository extends JpaRepository<TaskDetail, Long> {

	
	@Query(value = "SELECT * FROM TASKDETAIL WHERE TASK = ? AND ROWNUM = 1 ORDER BY FROM_DATE DESC",  nativeQuery = true)
	public TaskDetail getLastDetailOfTask(Long id);
	
}
