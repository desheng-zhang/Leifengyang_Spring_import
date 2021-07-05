package com.atguigu.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.dao.BookDao;

@Service
public class BookService {
	
	@Autowired
	BookDao bookDao;
	
	
	
	public void checkout(String username,String isbn){
		bookDao.updateStock(isbn);
		
		int price = bookDao.getPrice(isbn);
		bookDao.updateBalance(username, price);
		
		int i = 10/0;
		
	}
	
	
	public void updatePrice(String isbn,int price){
		bookDao.updatePrice(isbn, price);
	}
	
	
	
	public int getPrice(String isbn){
		return bookDao.getPrice(isbn);
	}
	
	
	public void mulTx(){
		
		checkout("Tom", "ISBN-001");
		
		updatePrice("ISBN-002", 998);
		
		//int i=10/0;
	}
	
	

}
