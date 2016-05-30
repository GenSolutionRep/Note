package com.dasa.login.service;

import java.sql.SQLException;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.login.dao.LoginDao;
import com.dasa.login.vo.LoginVo;

/**
 * @파일명: LoginService.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 로그인 Service
 */
public class LoginService extends SqlSessionDaoSupport implements LoginDao {
	
	@Override
	public LoginVo getSystemDate(LoginVo loginVo) throws SQLException {
		return (LoginVo) getSqlSession().selectOne("login.getSystemDate", loginVo);
	}

	@Override
	public LoginVo checkLogin(LoginVo loginVo) throws SQLException {
		return (LoginVo) getSqlSession().selectOne("login.checkLogin", loginVo);
	}
	
	@Override
	public int setUniqueId(LoginVo loginVo) throws SQLException {
		return getSqlSession().update("login.setUniqueId", loginVo);
	}
	
	@Override
	public int setPushId(LoginVo loginVo) throws SQLException {
		return getSqlSession().update("login.setPushId", loginVo);
	}
	
	@Override
	public LoginVo login(LoginVo loginVo) throws SQLException {
		return (LoginVo) getSqlSession().selectOne("login.login", loginVo);		
	}

}
