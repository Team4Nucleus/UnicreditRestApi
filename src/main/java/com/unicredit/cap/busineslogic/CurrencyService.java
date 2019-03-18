package com.unicredit.cap.busineslogic;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.List;
import com.unicredit.cap.repository.DbContext;

@Service
public class CurrencyService {

	@Autowired
	private DbContext db;
	
	@Autowired
	private Environment env;
	
	public List<Currency> getAllCurrencies() {
		
		return db.Currencies().findAll();
	}
	
	
	public Currency getCurrencyByCode(String code) {
		
		return db.Currencies().getCurrencyByCode(code);
	}
	
	public Currency saveNewCurrency(Currency currency) {
		
		if (currency.getCode().equals("") )
		throw new CapNotFoundException("Please set the Currency Code");
		
		db.Currencies().save(currency);
		return currency;
	}
	
	
	public Currency updateCurrency (Currency currency) {
		
		Currency current = db.Currencies().findOne(currency.getCode());
		
		current.setName(currency.getName());
		current.setExchangerate(currency.getExchangerate());
		
		db.Currencies().save(current);
		
		return current;
	}
	
}
