package com.vertexid.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.vertexid.dao.ProductDAO;
import com.vertexid.vo.ProductVo;


public class ProductService extends SqlSessionDaoSupport  implements ProductDAO {
	
	@Override
	public List<ProductVo> getTreeList() throws SQLException {
		String isql_string = (String) getSqlSession().selectOne("product.getSQL_productList");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("isql_string", isql_string);
		return getSqlSession().selectList("product.productList", map);
	}
	
	@Override
	public List<ProductVo> selectList(String pm_code)	throws SQLException {
		return getSqlSession().selectList("product.selectProductList", pm_code);
	}

	@Override
	public ProductVo selectRow(String pm_code) throws SQLException {
		return getSqlSession().selectOne("product.selectProductRow", pm_code);
	}
	
	@Override
	public String selectFirstTree() throws SQLException {
		return getSqlSession().selectOne("product.selectFirstTreeProduct");
	}
	
	@Override
	public List<ProductVo> selectDepthList(HashMap hashMap) throws SQLException {
		return getSqlSession().selectList("product.selectDepthList", hashMap);
	}
	
	@Override
	public int insertProduct(ProductVo vo) throws SQLException {
		return getSqlSession().insert("product.insertProduct", vo);
	}
	
	@Override
	public int updateProduct(ProductVo vo) throws SQLException {
		return getSqlSession().update("product.updateProduct", vo);
	}
	
	@Override
	public int deleteProduct(HashMap map) throws SQLException {
		return getSqlSession().update("product.deleteProduct", map);
	}
	
	@Override
	public ProductVo checkProductCode(String pm_code) throws SQLException {
		return getSqlSession().selectOne("product.checkProductCode", pm_code);
	}

	@Override
	public List<ProductVo> productListAll() throws SQLException {
		return getSqlSession().selectList("product.productListAll");
	}
}
