package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

	@Query(value = "SELECT C.* FROM ("
			+ "SELECT A.* FROM "
			+ "(SELECT MIN(ID) AS ID, TASK FROM TASKDETAIL WHERE TO_USER = :id GROUP BY TASK) B " 
			+ "JOIN TASK A on A.ID = B.TASK "
			+ "UNION "
			+ "SELECT TASK.* FROM TASK "
			+ "WHERE CREATE_USER = :id) C "
			+ "ORDER BY C.ID",  nativeQuery = true)
	public List<Task> getAllTasksByUserId(@Param("id") Long id);
	
	@Query(value = "SELECT T.* FROM "
		+	"( " 
		+	"			  SELECT TASK, MAX(ID) AS ID  "
		+	"			  FROM TASKDETAIL  "
		+	"			  GROUP BY TASK "
		+	"			) B "
		+	"			JOIN TASKDETAIL A on A.ID = B.ID "
		+	"			JOIN TASK T on T.ID = A.TASK "
		+	"			WHERE A.TO_ORG = ? "
			+ " ",  nativeQuery = true)
	public List<Task> findAllActiveTasksByApplicationCurrentOrg(Long id);
}
