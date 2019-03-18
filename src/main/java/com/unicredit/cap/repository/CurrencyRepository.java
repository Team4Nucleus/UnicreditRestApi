package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unicredit.cap.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

	public Currency getCurrencyByCode(String code);
}
