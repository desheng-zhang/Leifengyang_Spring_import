package com.atguigu.test;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.service.BookService;
import com.atguigu.service.MulService;

public class TxTest {
	
	ApplicationContext ioc = new ClassPathXmlApplicationContext("tx.xml");

	/**
	 * 有事务的业务逻辑，容器中保存的是这个业务逻辑的代理对象
	 * @throws FileNotFoundException
	 * 
	 * 
	 * multx(){
	 * 		//REQUIRED
	 * 		A(){
	 * 			//REQUIRES_NEW
	 * 			B(){}
	 * 			//REQUIRED
	 * 			c(){}
	 * 		}
	 * 		
	 * 		//REQUIRES_NEW
	 * 		D(){
	 * 			DDDD()//  REQUIRES_NEW不崩，REQUIRED崩
	 * 			//REQUIRED
	 * 			E(){
	 * 				//REQUIRES_NEW
	 * 				F(){
	 * 					//10/0（E崩，G崩，D崩，A,C崩）
	 * 				}
	 * 			}
	 * 			//REQUIRES_NEW
	 * 			G(){}
	 * 		}
	 * 
	 * 
	 * 		10/0(B成功，D整个分支下全部成功)
	 * 		任何处崩，已经执行的REQUIRES_NEW都会成功；
	 * 
	 * 		如果是REQUIRED；事务的属性都是继承于大事务的；
	 * 		而propagation=Propagation.REQUIRES_NEW可以调整
	 * 		默认：REQUIRED；
	 * 
	 * 		REQUIRED：将之前事务用的connection传递给这个方法使用；
	 * 		REQUIRES_NEW：这个方法直接使用新的connection；
	 * }
	 * 
	 */
	@Test
	public void test() throws FileNotFoundException {
		BookService bookService = ioc.getBean(BookService.class);
		
		//MulService bean = ioc.getBean(MulService.class);
		//bean.mulTx();
		
		//bookService.checkout("Tom", "ISBN-001");
		//int price = bookService.getPrice("ISBN-001");
		//System.out.println("读取到的数据："+price);
		//System.out.println(bookService.getClass());
		
		//效果都没改(相当于回滚了)，虽然mulTx的两个方法都开新车
		//bookService.mulTx();
		
		System.out.println(bookService.getClass());
		//如果是MulService --mulTx()---调用bookService两个方法
		//BookService---mulTx()--直接调用两个方法
		
		/***
		 * MulServiceProxy.mulTx(){
		 * 		bookServiceProxy.checkout();
		 * 		bookServiceProxy.updatePrice();
		 * }
		 * 
		 * 
		 * 本类方法的嵌套调用就只是一个事务;
		 * BookServiceProxy.mulTx(){
		 * 		checkout();
		 * 		updatePrice();
		 * 		//相当于
		 * 		bookDao.updateStock(isbn);
		 * 		int price = bookDao.getPrice(isbn);
		 * 		bookDao.updateBalance(username, price);
		 * 
		 * 		bookDao.updatePrice(isbn, price);
		 * }
		 */
		
		
	}

}
