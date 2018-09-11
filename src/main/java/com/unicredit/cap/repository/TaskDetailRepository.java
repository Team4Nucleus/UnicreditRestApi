package com.unicredit.cap.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.TaskDetail;
import com.unicredit.cap.model.TaskTimeConsument;;

public interface TaskDetailRepository extends JpaRepository<TaskDetail, Long> {

	
	@Query(value = "SELECT A.* FROM "
			+ "( SELECT MAX(ID) AS ID FROM TASKDETAIL WHERE TASK = ? ) B "
			+ "JOIN TASKDETAIL A on A.ID = B.ID",  nativeQuery = true)
	public TaskDetail getLastDetailOfTask(Long id);
	
	
	@Query(value = "SELECT A.* FROM "
			+ "( SELECT MIN(ID) AS ID FROM TASKDETAIL WHERE TASK = ? ) B "
			+ "JOIN TASKDETAIL A on A.ID = B.ID",  nativeQuery = true)
	public TaskDetail getFirstDetailOfTask(Long id);
	
}
