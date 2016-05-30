package com.dasa.baseInfo.dao;

import java.util.HashMap;
import java.util.List;

/**
 * 품목관리1
 * @author  김재성
 * @since   2015-08-29
 * @version 1.0
 */
public interface ItemMgrDAO {

	/**
	 * 분류관리 분류 목록
	 * @param code
	 * @return List<CommonCodeTreeVo>
	 */
	public List<HashMap<String, String>> selectGroupMgrList()throws Exception;
	
	/**
	 * 품목관리 분류 목록
	 * @param code
	 * @return List<CommonCodeTreeVo>
	 */
	public List<HashMap<String, String>> selectItemMgrList(String groupCode)throws Exception;
}
