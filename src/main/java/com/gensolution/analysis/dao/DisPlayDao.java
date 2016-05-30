package com.gensolution.analysis.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.analysis.vo.DisPlayVo;
import com.dasa.analysis.vo.DisplayGridVo;
import com.gensolution.activity.vo.ActivityGridVo;
import com.vertexid.vo.NaviVo;

public interface DisPlayDao {

	public List<DisPlayVo> displayList() throws SQLException;

	public List<DisPlayVo> displayPrdList(DisPlayVo vo) throws SQLException;

	public List<DisPlayVo> displayPrdItemList(DisPlayVo vo)  throws SQLException;

	public int displayPrdSave1(DisPlayVo vo)throws SQLException;
	public int displayPrdItemSave1(DisPlayVo vo)throws SQLException;
	
	
	public int displayPrdSave3(DisPlayVo vo)throws SQLException;
	public int displayPrdItemSave3(DisPlayVo vo)throws SQLException;
	
	public int displayPrdSave4(DisPlayVo vo)throws SQLException;
	public int displayPrdItemSave4(DisPlayVo vo)throws SQLException;
	
	public int displayPrdSave6(DisPlayVo vo)throws SQLException;
	public int displayPrdItemSave6(DisPlayVo vo)throws SQLException;
	
	public int displayPrdSave7(DisPlayVo vo)throws SQLException;
	public int displayPrdItemSave7(DisPlayVo vo)throws SQLException;


	public int displayPrdSave5(DisPlayVo vo) throws SQLException;

	
	
	@Transactional
	public int displayBatchArr(String type) throws SQLException, ParseException;
	
	@Transactional
	public int displayBatchPd(String type) throws SQLException,ParseException;
	
	@Transactional
	public int displayBatchBig(String type) throws SQLException,ParseException;
	
	@Transactional
	public int displayBatchTrt(String type) throws SQLException,ParseException;

	public DisplayGridVo displayArrList(NaviVo naviVo)throws SQLException;

	public NaviVo displayArrListCnt(NaviVo naviVo)throws SQLException;

	public NaviVo displayBigListCnt(NaviVo naviVo)throws SQLException;

	public DisplayGridVo displayBigList(NaviVo naviVo)throws SQLException;

	public NaviVo displayPdListCnt(NaviVo naviVo)throws SQLException;

	public DisplayGridVo displayPdList(NaviVo naviVo)throws SQLException;

	public NaviVo displayTrtListCnt(NaviVo naviVo)throws SQLException;

	public DisplayGridVo displayTrtList(NaviVo naviVo)throws SQLException;

	public DisplayGridVo displayExcelArrList(NaviVo naviVo) throws SQLException;

	public DisplayGridVo displayExcelBigList(NaviVo naviVo)throws SQLException;

	public DisplayGridVo displayExcelPdList(NaviVo naviVo)throws SQLException;

	public DisplayGridVo displayExcelTrtList(NaviVo naviVo)throws SQLException;

}
