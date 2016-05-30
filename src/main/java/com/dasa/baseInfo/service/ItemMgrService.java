package com.dasa.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.baseInfo.dao.ItemMgrDAO;

public class ItemMgrService extends SqlSessionDaoSupport  implements ItemMgrDAO{


	@Override
	public List<HashMap<String, String>> selectGroupMgrList() throws Exception {
		return getSqlSession().selectList("selectGroupMgrList");
	}

	@Override
	public List<HashMap<String, String>> selectItemMgrList(String groupCode) throws Exception {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("GROUP_CODE", groupCode);
		System.out.println("=============TEST : "+getSqlSession().selectList("selectItemMgrList", param));
		return getSqlSession().selectList("selectItemMgrList", param);
	}
	
	
	
}
