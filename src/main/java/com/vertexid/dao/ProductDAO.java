package com.vertexid.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vertexid.vo.ProductVo;

public interface ProductDAO {
	
	/**
	 * 트리 리스트
	 * @param String om_code
	 * @return List<AuthorityMenuVo>
	 */
	public List<ProductVo> getTreeList()  throws SQLException;
	
	/**
	 * 해당 노드 & 하위 메뉴 List
	 * @param String pm_code
	 * @return List<ProductVo>
	 */
	public List<ProductVo> selectList(String pm_code)  throws SQLException;
	
	/**
	 * 해당 품목의 row data
	 * @param String pm_code
	 * @return List<ProductVo>
	 */
	public ProductVo selectRow(String pm_code)  throws SQLException;
	
	/**
	 * 해당 트리의 첫 node의 품목코드 조회
	 * @return List<ProductVo>
	 */
	public String selectFirstTree()  throws SQLException;
	
	/**
	 * (콤보)depthList
	 * @return List<ProductVo>
	 */

	public List<ProductVo> selectDepthList(HashMap hashMap)  throws SQLException;
	
	/**
	 * 해당 품목 Insert
	 * @param ProductVo ProductVo
	 * @return String
	 */
	public int insertProduct(ProductVo vo)  throws SQLException;
	
	/**
	 * 해당 품목 Update
	 * @param ProductVo ProductVo
	 * @return String
	 */
	public int updateProduct(ProductVo vo)  throws SQLException;

	/**
	 * 해당 품목 Delete
	 * @param HashMap hashMap
	 * @return String
	 */
	public int deleteProduct(HashMap hashMap)  throws SQLException;
	
	/**
	 * 코드유효성 검사
	 * @param String pm_code
	 * @return ProductVo
	 */
	public ProductVo checkProductCode(String pm_code)  throws SQLException;

	/**
	 * 모든코드 가져오기
	 * @param 
	 * @return ProductVo
	 */
	public List<ProductVo> productListAll() throws SQLException;
	

}
