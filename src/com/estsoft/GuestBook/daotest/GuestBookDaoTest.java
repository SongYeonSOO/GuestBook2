package com.estsoft.GuestBook.daotest;

import java.util.List;

import com.estsoft.DB.MySQLWebDBConnection;
import com.estsoft.GuestBook.dao.GuestBookDao;
import com.estsoft.GuestBook.vo.GuestBookVo;


public class GuestBookDaoTest {
	public static void main(String[] args) {

		getInsertTest();		
		getDeleteTest();
		getListTest();

	}
	public static void getInsertTest(){
		GuestBookVo vo = new GuestBookVo();
		vo.setName("YS");
		vo.setMessage("HELLOWEB");
		vo.setPasswd("1234");
		GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());

		dao.insert(vo);
	}
	public static void getListTest(){
		GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());
		List<GuestBookVo> list = dao.getList();
		for(GuestBookVo vo : list){
			System.out.println(vo);
		}
	}
	public static void getDeleteTest(){
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(3L);
		vo.setPasswd("1234");		
		GuestBookDao dao = new GuestBookDao(new MySQLWebDBConnection());
		dao.delete(vo);
	}

}
