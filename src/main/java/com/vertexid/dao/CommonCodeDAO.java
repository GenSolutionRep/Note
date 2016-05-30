package com.vertexid.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.vertexid.vo.CodeVo;



/**
 * 공통 코드 관리
 * @author  김진호
 * @since   2014-09-29
 * @version 1.0
 */
public interface CommonCodeDAO {
	
	
	/**
	 * 공통코드 트리 목록
	 * @param code
	 * @return List<CommonCodeTreeVo>
	 */
	public List<CodeVo> codeTree(Map<String, String> map) throws SQLException;
	
	/**
	 * 공통코드 목록 조회
	 * @param cc_parent_code
	 * @return List<CommonCodeVo>
	 */
	public List<CodeVo> codeList(Map<String, String> map)throws SQLException;
	
	/**
	 * 공통코드 상세보기
	 * param code
	 * return CommonCodeVo
	 */
	public CodeVo codeView(Map<String, String> map)throws SQLException;
	
	/**
	 * 공통코드 등록
	 * @param CodeVo
	 * @return int
	 */
	public int codeSave(CodeVo vo)throws SQLException;
	
	/**
	 * 공통코드 수정
	 * @param CodeVo
	 * @return int
	 */
	public int codeDelete(CodeVo vo)throws SQLException;
	
	/**
	 * 공통코드 삭제
	 * @param CodeVo
	 * @return int
	 */
	
	/**
	 * 공통코드 콤보박스
	 * @param CodeVo
	 * @return List<CommonCodeVo> 
	 */
	public List<CodeVo> codeComboBox(String parent_code)throws SQLException;
	
	public List<CodeVo> codeForCal(String c_parent_code, String c_ext1) throws SQLException;
	


}
