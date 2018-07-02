package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicredit.cap.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
