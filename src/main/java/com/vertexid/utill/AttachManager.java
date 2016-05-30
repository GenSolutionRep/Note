package com.vertexid.utill;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.SftpException;
import com.vertexid.dao.AttachDAO;
import com.vertexid.vo.AttachVo;



/**
 * @Class명: FileManager
 * @작성일: 2014. 9. 18
 * @작성자: 김진호
 * @설명: 파일관리자
 */
public class AttachManager {

	private AttachVo vo ;
	private static final String SP = File.separator;	// 서버 구분자
	private static final String BASEFOLDER = "upload";		// 기본폴더명
	private long miliSec;									// 자동삭제 시간
	private boolean autoDel = false;						// 자동삭제 Flag
	
	@Autowired(required = true)
	private HttpServletRequest request;
	
	@Autowired(required = true)
	private HttpSession session;
	
	@Autowired
	private AttachDAO dao;
	
	@Autowired
	private FTPManager FTP;
	
	
	//파일 업도르 한개
	public int updateFile(MultipartFile file, int file_m_seq, String type)throws Exception{
		return file == null ? -1 : saveFile(file,file_m_seq,  type);
	}
	//파일 업로드 멀티  0 = 신규
	public int updateFile(MultipartFile[] files) throws Exception{
		return files == null ? -1 : saveFiles(files,0); // 0 = 신규
	}
	//파일 업로드 멀티  0 != 기존
	public int updateFile(MultipartFile[] files , int file_m_seq) throws Exception{
		return files == null ? -1 : saveFiles(files,file_m_seq); 
	}
	
	//2015.10.29 by zzz2613
	public int updateFile(MultipartFile[] files , int file_m_seq, String em_no, String type) throws Exception{
		return files == null ? -1 : saveFiles(files,file_m_seq, em_no, type); 
	}
	
	//파일 업로드 멀티  0 != 기존
//	public int updateMergeFile(MultipartFile[] files, int[] file_m_seq) throws Exception {
//	 	return files == null ? -1 : mergeFiles(files,file_m_seq);
//	}
//	public int updateMergeFile(MultipartFile file, int file_m_seq) throws Exception {
//		return file == null ? -1 : mergeFile(file,file_m_seq);
//	}
	
	//파일 임시저장
//	public String updateTempFile(MultipartFile files) throws Exception{
//		this.autoDel = false;
//		return files == null ? "-1" : uploadServer(files,"temp");
//	}
	//사진미리보기 임시저장
	public String updatePrevViewFile(MultipartFile file) throws Exception{
		this.autoDel = false;
		return file == null ? "-1" : uploadServer(file,"prev");
	}
	
	//파일 임시저장 자동삭제
	public String updateTempFile(MultipartFile files,long miliSec) throws Exception{
		this.miliSec = miliSec;
		this.autoDel = true;
		return files == null ? "-1" : uploadServer(files,"temp");
	}
	
	//모바일 이미지업로드 - Return imageUrl
/*	public String uploadImageFile(MultipartFile file) throws Exception{
		return file == null ? "-1" : uploadServer(file,"activity");
	}*/

	//모바일 이미지업로드 - Return imageUrl
	public ArrayList<String> uploadImageFiles(MultipartFile[] files) throws Exception{
		ArrayList<String> url_arr = new ArrayList<String>();
		for (MultipartFile file :files) {
			if(file.getBytes().length > 0){
				url_arr.add(uploadServer(file,"files"));
				//System.out.println("용량 byte : "+file.getBytes().length);
			}else{
				System.out.println("파일은 있지만 용량이 없음 byte : "+file.getSize());
			}
		}
		
		return url_arr;
	}
	
	//모바일 이미지업로드 - Return imageUrl
	public String uploadImageFile(MultipartFile file) throws Exception{
		String file_path = "";
		if(file.getBytes().length > 0){
			file_path = uploadServer(file,"fixingOdd");
			//System.out.println("용량 byte : "+file.getBytes().length);
		}else{
			System.out.println("파일은 있지만 용량이 없음 byte : "+file.getSize());
		}
		return file_path;
	}
	
	//파일 삭제
	public void deleteFile(String fullPath) throws Exception{
		Thread tpd = new Thread(new TempFileDelete(fullPath,0));
		tpd.start();
	}
	
	
	private int saveFiles(MultipartFile[] files ,int file_m_seq) throws Exception{
		String userId = (String) session.getAttribute("login_no");
		//System.out.println("userId : " + userId);
		List<AttachVo> voList = new ArrayList<AttachVo>();
		
		for (MultipartFile file :files) {
			if(file.getBytes().length > 0){
				vo = new AttachVo();
				vo.setRegist_man(userId);
				vo.setAi_size(file.getSize());
				vo.setAi_nm( file.getOriginalFilename());
				vo.setAi_path(uploadServer(file,"files"));
				voList.add(vo);
				//System.out.println("용량 byte : "+file.getBytes().length);
			}else{
				System.out.println("파일은 있지만 용량이 없음 byte : "+file.getSize());
			}
		}
		return dao.attachInsertMulti(voList, userId, file_m_seq);
	}
	private int saveFile(MultipartFile file ,int file_m_seq, String type) throws Exception{
		String userId = (String) session.getAttribute("login_no");
		//System.out.println("userId : " + userId);
		List<AttachVo> voList = new ArrayList<AttachVo>();
		
		if(file.getBytes().length > 0){
			vo = new AttachVo();
			vo.setRegist_man(userId);
			vo.setAi_size(file.getSize());
			vo.setAi_nm( file.getOriginalFilename());
			vo.setAi_path(uploadServer(file,"files"));
			voList.add(vo);
			//System.out.println("용량 byte : "+file.getBytes().length);
		}else{
			System.out.println("파일은 있지만 용량이 없음 byte : "+file.getSize());
		}
		return dao.attachInsertMulti(voList, userId, file_m_seq,type);
	}
	
	//2015.10.29 by zzz2613
	private int saveFiles(MultipartFile[] files ,int file_m_seq, String em_no, String type) throws Exception{
		List<AttachVo> voList = new ArrayList<AttachVo>();
		for (MultipartFile file :files) {
			if(file.getBytes().length > 0){
				vo = new AttachVo();
				vo.setRegist_man(em_no);
				vo.setAi_size(file.getSize());
				vo.setAi_nm( file.getOriginalFilename());
				String path = uploadServer(file,"files");
				vo.setAi_path(path);
				if(!path.equals("")){
					voList.add(vo);
				}
				//System.out.println("용량 byte : "+file.getBytes().length);
			}else{
				System.out.println("파일은 있지만 용량이 없음 byte : "+file.getSize());
			}
		}
		return dao.attachInsertMulti(voList, em_no, file_m_seq, type);
	}
	
	
	private String uploadServer(MultipartFile file, String folder){
		
		String date = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		String dirY  = new SimpleDateFormat("yyyy").format(new Date()); 
		String dirM  = new SimpleDateFormat("MM").format(new Date()); 
		String dirD  = new SimpleDateFormat("dd").format(new Date()); 
		

		String oFileName = file.getOriginalFilename();
		//System.out.println("oFileName : " +oFileName);
		String sFileName = date +(oFileName.substring(oFileName.lastIndexOf(".")));
		//String root = request.getSession().getServletContext().getRealPath("/");
		String root = "/";
		String filePath = "";
		filePath = root +BASEFOLDER+SP+folder +SP+ dirY+SP+ dirM+SP+ dirD ;
		String dbPath = "/"+BASEFOLDER+"/"+folder+"/"+dirY+"/"+dirM+"/"+dirD;
		if(folder.equals("temp")){
			filePath = root +BASEFOLDER+SP+folder;
		}

		FileOutputStream fos = null;
		String fullPath = "";
		String dbfullPath = "";
		
		try {
			sFileName = checkFile(filePath,sFileName);
			fullPath = filePath + SP + sFileName;
			dbfullPath = dbPath +"/"+ sFileName;
			checkDir(filePath);
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream(fullPath);
			fos.write(fileData);

		} catch (Exception e) {
			System.out.println("파일 저장 실패 : "+fullPath);
			dbfullPath ="";
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(autoDel){
			//System.out.println("임시저장 파일 삭제 : "+fullPath);
			Thread tpd = new Thread(new TempFileDelete(fullPath,miliSec));
			tpd.start();
			autoDel = false;
		}
        
		if(folder.equals("temp")){
			return fullPath;
		}else if(folder.equals("prev")){
			return dbfullPath;
		}else{
			Thread ftp = new Thread(new ftpSharing(dbPath, filePath+SP+sFileName));
			ftp.start();
			
			return dbfullPath;
		}
	}
	class ftpSharing implements Runnable{
		private String dir;
		private String ufile;
		public ftpSharing(String dir, String ufile){
			this.dir =dir;
			this.ufile = ufile;
		}

		@Override
		public void run() {
			int forCnt = 1;
			while (true) {
				try {
					Thread.sleep(2000);
					boolean run = ftpSharingUpload(dir,ufile);
					forCnt ++;
					if(forCnt == 10){
						System.out.println("ftp 파일 업로드실패 종료!" + ufile);
						break;
					}else if( run){
						System.out.println("ftp 파일 업로드성공 종료!" + ufile);
						break;
					}else{
						System.out.println("ftp 파일 업로드실패" + forCnt+" 번째 재시도");
						
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					FTP.disconnection();
				}
			}
		}
		private boolean ftpSharingUpload(String dir, String ufile){
			boolean run = true;
			FTP.connect();
	        try {
	        	FTP.upload(dir, new File(ufile));
			} catch (FileNotFoundException e) {
				run = false;
				FTP.disconnection();
				e.printStackTrace();
			} catch (SftpException e) {
				run = false;
				FTP.disconnection();
				e.printStackTrace();
			} catch (IOException e) {
				run = false;
				FTP.disconnection();
				e.printStackTrace();
			}finally{
				FTP.disconnection();
			}
	        return run;
		}
	}
	private void ftpFileUpload(String dir, String ufile){
			FTP.connect();
	        try {
	        	FTP.upload(dir, new File(ufile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (SftpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				FTP.disconnection();
			}
	}

	private void checkDir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	private String checkFile(String path, String fileNm){
		String tempFileName =  fileNm.substring(0,fileNm.lastIndexOf("."));
		String tempFileEx =  fileNm.substring(fileNm.lastIndexOf("."));
		String tempFullName =  tempFileName+tempFileEx;
		int cnt = 1;
		while (true) {
			File f = new File(path+SP+tempFullName);
			if (f.isFile()) {
				//System.out.println(tempFullName+ "와 중복된 파일명이 있습니다.");
				tempFullName = tempFileName+"("+cnt+")"+tempFileEx;
				cnt++;
			}else {
				//System.out.println(tempFullName + "는 중복된 파일명이 없습니다.");
				break;
			}
	    }
	    return tempFullName;
	}
	class TempFileDelete  implements Runnable{
		
		private String filePath;
		private long miliSec;
		public TempFileDelete(String filePath,long miliSec){
			this.filePath = filePath;
			this.miliSec = miliSec;
		}
		
		@Override
		public void run() {
			try {
				
				Thread.sleep(miliSec);
				File f = new File(filePath);
			    if (f.delete()) {
			      //System.out.println("삭제 성공");
			    } else {
			      System.err.println("삭제 실패 ");
			    }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public FileSystemResource downloadFile(int file_d_seq ,HttpServletResponse response) throws Exception {
		vo = dao.attachItem(file_d_seq);
		//System.out.println("vo : " + vo.toString());
	    String webPath = vo.getAi_path();
	    String ogriFilename = vo.getAi_nm();
		//String root = request.getSession().getServletContext().getRealPath("/");
		String root = "/";
	    //System.out.println("fullPath : " + webPath);
	    String downPath = (root+webPath).replace("/", SP );
	    //System.out.println("downPath : " + downPath);
	    
	    File file = new File(downPath);
	    
	    String userAgent = request.getHeader("User-Agent");
	    //System.out.println("userAgent : " + userAgent);
	    String ext = ogriFilename.substring(ogriFilename.lastIndexOf(".") + 1).toUpperCase(); // 확장자를 대문자로
	    ogriFilename = ogriFilename.substring(0, ogriFilename.lastIndexOf(".") + 1) + ext;
	    //System.out.println("다운로드파일명 : "+ogriFilename);
	    if ((userAgent.indexOf("Chrome") > -1 || userAgent.indexOf("Safari") > -1  ) && userAgent.indexOf("Android") < 0 ) { //크롬Safari
	      ogriFilename =  Encoder.utfToiso(ogriFilename);
	    } else { // 나머지
	      ogriFilename =  URLEncoder.encode(ogriFilename, "UTF-8").replaceAll("\\+", "%20");
	    }
		//System.out.println("다운로드파일명 : "+ogriFilename);
		response.setHeader("Content-Type", "application/force-download");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + ogriFilename+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.setHeader("Accept-Ranges", "bytes");
	    return new FileSystemResource(file);
	}
	
	//2015.12.03 by zzz2613
	public FileSystemResource downloadFile_apk(int file_d_seq ,HttpServletResponse response) throws Exception {
		vo = dao.attachItem_apk(file_d_seq);
		//System.out.println("vo : " + vo.toString());
	    String webPath = vo.getAi_path();
	    String ogriFilename = vo.getAi_nm();
		//String root = request.getSession().getServletContext().getRealPath("/");
		String root = "/";
	    //System.out.println("fullPath : " + webPath);
	    String downPath = (root+webPath).replace("/", SP );
	    //System.out.println("downPath : " + downPath);
	    
	    File file = new File(downPath);
	    
	    String userAgent = request.getHeader("User-Agent");
	    //System.out.println("userAgent : " + userAgent);
	    String ext = ogriFilename.substring(ogriFilename.lastIndexOf(".") + 1).toUpperCase(); // 확장자를 대문자로
	    ogriFilename = ogriFilename.substring(0, ogriFilename.lastIndexOf(".") + 1) + ext;
	    //System.out.println("다운로드파일명 : "+ogriFilename);
	    if ((userAgent.indexOf("Chrome") > -1 || userAgent.indexOf("Safari") > -1  ) && userAgent.indexOf("Android") < 0 ) { //크롬Safari
	      ogriFilename =  Encoder.utfToiso(ogriFilename);
	    } else { // 나머지
	      ogriFilename =  URLEncoder.encode(ogriFilename, "UTF-8").replaceAll("\\+", "%20");
	    }
		//System.out.println("다운로드파일명 : "+ogriFilename);
		response.setHeader("Content-Type", "application/force-download");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + ogriFilename+"\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    response.setHeader("Accept-Ranges", "bytes");
	    return new FileSystemResource(file);
	}
	
	public List<AttachVo> attachList(int am_no)throws Exception {
		return dao.attachList(am_no);
	}
//	public AttachVo attachItem(int ai_no)throws Exception {
//		return dao.attachItem(ai_no);
//	}
	public int attachDelete(int am_no) throws Exception{
		return dao.attachDelete(am_no);
	}
	
	
	

}
