package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicredit.cap.model.Organization;


public interface OrganizationRepository  extends JpaRepository<Organization, Long> {

}
