package com.dasa.login.dao;

import java.sql.SQLException;

import com.dasa.login.vo.LoginVo;

/**
 * @파일명: LoginDao.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 로그인 Dao
 */
public interface LoginDao {
	
	//시스템 시간
	public LoginVo getSystemDate(LoginVo loginVo)throws SQLException;
	
	//로그인 체크
	public LoginVo checkLogin(LoginVo loginVo) throws SQLException;

	//디바이스 고유번호 저장
	public int setUniqueId(LoginVo loginVo) throws SQLException;
	
	//디바이스 PUSH ID 저장
	public int setPushId(LoginVo loginVo) throws SQLException;

	//로그인
	public LoginVo login(LoginVo loginVo) throws SQLException;

}
