package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicredit.cap.model.ClientBusinesPartner;

public interface ClientBusinesPartnerRepository extends JpaRepository<ClientBusinesPartner, Long> {

	public ClientBusinesPartner getClientBusinesPartnerByJib(String jib);
	
}
