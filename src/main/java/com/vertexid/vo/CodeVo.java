package com.vertexid.vo;



/**
 * @Class명: CommonCodeVo
 * @작성일: 2014. 9. 18
 * @작성자: 김진호
 * @설명: 공통코드 VO 
 */
public class CodeVo {
	
	private String c_code;				// 코드
	private String c_name;				// 코드명
	private String c_desc;				// 코드설명
	private String c_order;				// 코드 순서
	private String c_ext1;				// 코드 순서
	private String c_ext2;				// 코드 순서
	private String c_ext3;				// 코드 순서
	private int depth;				// 코드 레벨 
	private String c_parent_code;		// 부모코드
	private String c_parent_code_name;	// 부모코드명
	private String c_is_system_code;	// 시스템코드여부
	private String c_is_use;			// 사용여부
	private String c_in_date; 			// 등록자
	private String c_up_date; 			// 수정자
	private String c_in_id; 			// 등록일
	private String c_up_id;				// 수정일
	private String child_cnt;			// 하위코드 수
	
	private String c_code_new;			// 하위코드 수
	
	public String getC_code_new() {
		return c_code_new;
	}
	public void setC_code_new(String c_code_new) {
		this.c_code_new = c_code_new;
	}
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_desc() {
		return c_desc;
	}
	public void setC_desc(String c_desc) {
		this.c_desc = c_desc;
	}
	public String getC_order() {
		return c_order;
	}
	public void setC_order(String c_order) {
		this.c_order = c_order;
	}
	public String getC_ext1() {
		return c_ext1;
	}
	public void setC_ext1(String c_ext1) {
		this.c_ext1 = c_ext1;
	}
	public String getC_ext2() {
		return c_ext2;
	}
	public void setC_ext2(String c_ext2) {
		this.c_ext2 = c_ext2;
	}
	public String getC_ext3() {
		return c_ext3;
	}
	public void setC_ext3(String c_ext3) {
		this.c_ext3 = c_ext3;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getC_parent_code() {
		return c_parent_code;
	}
	public void setC_parent_code(String c_parent_code) {
		this.c_parent_code = c_parent_code;
	}
	public String getC_parent_code_name() {
		return c_parent_code_name;
	}
	public void setC_parent_code_name(String c_parent_code_name) {
		this.c_parent_code_name = c_parent_code_name;
	}
	public String getC_is_system_code() {
		return c_is_system_code;
	}
	public void setC_is_system_code(String c_is_system_code) {
		this.c_is_system_code = c_is_system_code;
	}
	public String getC_is_use() {
		return c_is_use;
	}
	public void setC_is_use(String c_is_use) {
		this.c_is_use = c_is_use;
	}
	public String getC_in_date() {
		return c_in_date;
	}
	public void setC_in_date(String c_in_date) {
		this.c_in_date = c_in_date;
	}
	public String getC_up_date() {
		return c_up_date;
	}
	public void setC_up_date(String c_up_date) {
		this.c_up_date = c_up_date;
	}
	public String getC_in_id() {
		return c_in_id;
	}
	public void setC_in_id(String c_in_id) {
		this.c_in_id = c_in_id;
	}
	public String getC_up_id() {
		return c_up_id;
	}
	public void setC_up_id(String c_up_id) {
		this.c_up_id = c_up_id;
	}
	public String getChild_cnt() {
		return child_cnt;
	}
	public void setChild_cnt(String child_cnt) {
		this.child_cnt = child_cnt;
	}
	
	

}
