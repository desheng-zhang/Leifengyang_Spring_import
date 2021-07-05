package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MulService {
	
	private BookService bookService;
	
	
	public void mulTx(){
		bookService.checkout("Tom", "ISBN-001");
		
		bookService.updatePrice("ISBN-002", 998);
		
	}

}
