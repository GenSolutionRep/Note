package com.vertexid.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.vertexid.dao.CommonCodeDAO;
import com.vertexid.vo.CodeVo;

public class CommonCodeService extends SqlSessionDaoSupport  implements CommonCodeDAO{

	//트리조회
	@Override
	public List<CodeVo> codeTree(Map<String, String> map) throws SQLException {
//		String isql_string = (String) getSqlSession().selectOne("getSQL_codeTree", map);
//		map.put("isql_string", isql_string);
		return getSqlSession().selectList("codeTree", map);
	}
	
	//선택 코드리스트
	@Override
	public List<CodeVo> codeList(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("codeList",map);
	}

	//코드정보불러오기
	@Override
	public CodeVo codeView(Map<String, String> map) throws SQLException {
		return getSqlSession().selectOne("codeView",map);
	}

	//코드저장
	@Override 
	public int codeSave(CodeVo vo) throws SQLException {
		return getSqlSession().insert("codeSave", vo);
	}

	//코드삭제
	@Override
	public int codeDelete(CodeVo vo) throws SQLException {
		return getSqlSession().update("codeDelete", vo);
	}
	
	//코드 콤보박스
	@Override
	public List<CodeVo> codeComboBox(String c_parent_code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("c_parent_code", c_parent_code);
		return getSqlSession().selectList("codeComboBox", map);
	}
	
	//코드 콤보박스
	@Override
	public List<CodeVo> codeForCal(String c_parent_code, String c_ext1) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("c_parent_code", c_parent_code);
		map.put("c_ext1", c_ext1);
		return getSqlSession().selectList("codeForCal", map);
	}

}
