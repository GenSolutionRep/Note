package com.gensolution.activity.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class ActivityFixingOddVo {
	private String main_innb;
	private String dfo_innb;
	private String dfop_innb;
	private String cm_code;
	private String om_code;
	private String sm_code;
    private String dfop_flag;
    private String dfop_img_url;
    private String dfop_partclr_matter;
    
    private String base_de;
    private String delete_at;
	private String regist_man;
	private String regist_de;
	private String updt_man;
	private String updt_de;
	
	private MultipartFile[] files;//첨부파일
	private MultipartFile file;
	private String em_no;
//	private List<Map<String,String>> jsondata;
//	private String params;
	private String paramArr1;

	
	public String getMain_innb() {
		if(main_innb == null || main_innb.equals("")){
			main_innb = null;
		}
		return main_innb;
	}
	public void setMain_innb(String main_innb) {
		this.main_innb = main_innb;
	}
	public String getParamArr1() {
		return paramArr1;
	}
	public void setParamArr1(String paramArr1) {
		this.paramArr1 = paramArr1;
	}
	public String getDfo_innb() {
		
		return dfo_innb;
	}
	public void setDfo_innb(String dfo_innb) {
		this.dfo_innb = dfo_innb;
	}
	public String getDfop_innb() {
		if(dfop_innb == null || dfop_innb.equals("")){
			dfop_innb = null;
		}
		return dfop_innb;
	}
	public void setDfop_innb(String dfop_innb) {
		this.dfop_innb = dfop_innb;
	}
	public String getCm_code() {
		return cm_code;
	}
	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}
	public String getOm_code() {
		return om_code;
	}
	public void setOm_code(String om_code) {
		this.om_code = om_code;
	}
	public String getSm_code() {
		return sm_code;
	}
	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}
	public String getDfop_flag() {
		return dfop_flag;
	}
	public void setDfop_flag(String dfop_flag) {
		this.dfop_flag = dfop_flag;
	}
	public String getDfop_img_url() {
		return dfop_img_url;
	}
	public void setDfop_img_url(String dfop_img_url) {
		this.dfop_img_url = dfop_img_url;
	}
	public String getDfop_partclr_matter() {
		return dfop_partclr_matter;
	}
	public void setDfop_partclr_matter(String dfop_partclr_matter) {
		this.dfop_partclr_matter = dfop_partclr_matter;
	}
	public String getBase_de() {
		return base_de;
	}
	public void setBase_de(String base_de) {
		this.base_de = base_de;
	}
	public String getDelete_at() {
		return delete_at;
	}
	public void setDelete_at(String delete_at) {
		this.delete_at = delete_at;
	}
	public String getRegist_man() {
		return regist_man;
	}
	public void setRegist_man(String regist_man) {
		this.regist_man = regist_man;
	}
	public String getRegist_de() {
		return regist_de;
	}
	public void setRegist_de(String regist_de) {
		this.regist_de = regist_de;
	}
	public String getUpdt_man() {
		return updt_man;
	}
	public void setUpdt_man(String updt_man) {
		this.updt_man = updt_man;
	}
	public String getUpdt_de() {
		return updt_de;
	}
	public void setUpdt_de(String updt_de) {
		this.updt_de = updt_de;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
