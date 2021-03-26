package com.unicredit.cap.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.Holiday;

public interface HolidayRepository  extends JpaRepository<Holiday, Long> {

	
	@Query(value = "SELECT * FROM HOLIDAY WHERE HOLIDAYDATE = ? AND ID <> ?",  nativeQuery = true)
	public List<Holiday> findHolideyByDateAndOtherId(Date date, Long id);
	
	@Query(value = "SELECT * FROM HOLIDAY WHERE HOLIDAYDATE = ?",  nativeQuery = true)
	public List<Holiday> findHolideyByDate(Date date);
	
	
	@Query(value = "SELECT * FROM HOLIDAY WHERE EXTRACT (year from HOLIDAYDATE )  = ?",  nativeQuery = true)
	public List<Holiday> findHolideyByYear(int year);
}
