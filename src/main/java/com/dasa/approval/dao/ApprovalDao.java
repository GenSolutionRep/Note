package com.dasa.approval.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.approval.vo.ApprovalVo;
import com.dasa.employee.vo.EmployeeVo;
import com.vertexid.vo.NaviVo;

public interface ApprovalDao {
	
	/*결재처리***********************************************************************/
	/**
	 * 결재상신 리스트 카운트 조회
	 * @return List<ApprovalVo>
	 */
	public NaviVo selectApprovalListCount(NaviVo naviVo) throws SQLException;

	/**
	 * 반려 리스트 조회
	 * @return List<ApprovalVo>
	 */
	public List<ApprovalVo> selectRejectList(String am_code)  throws SQLException;
	
	/**
	 * 결재상신 리스트 조회
	 * @return List<ApprovalVo>
	 */
	public List<ApprovalVo> selectApprovalList(NaviVo naviVo)  throws SQLException;
	
	/**
	 * 결재상신 row 조회
	 * @return List<ApprovalDtlVo>
	 */
	public ApprovalVo selectApprovalRow(ApprovalVo vo)  throws SQLException;
	
	/**
	 * 결재상신 save(프로시저)
	 * @param ApprovalVo hashMap
	 * @return List<AuthorityMenuVo>
	 */
	public void saveApproval(ApprovalVo vo)  throws SQLException;
	
	/**
	 * 첨부파일번호 update
	 * @return List<ApprovalDtlVo>
	 */
	public int amNoUpdate(ApprovalVo vo)  throws SQLException;
	
	/**
	 * 중복 체크 getCheckCount
	 * @return List<ApprovalDtlVo>
	 */
	public int getCheckCount(ApprovalVo vo)  throws SQLException;

}
