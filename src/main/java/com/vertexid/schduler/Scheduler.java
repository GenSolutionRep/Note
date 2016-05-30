package com.vertexid.schduler;

import java.io.File;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.dasa.analysis.vo.AttandanceVo;
import com.gensolution.analysis.dao.DisPlayDao;
import com.vertexid.dao.SchedulerDAO;

@Controller
public class Scheduler {
	
	@Autowired
	private SchedulerDAO dao;
	
	@Autowired
	private DisPlayDao dao2;
	
	@Value("#{sys_info['host_name']}")
    String host_name;
	
	//사원근태현황
	@Scheduled(cron = "00 00 05 * * *")
	public void pr_attandance() throws Exception {
		if(host_name.equals("was1")){
			AttandanceVo p_vo  = new  AttandanceVo();
			
			Calendar c = Calendar.getInstance(); 
			c.add(Calendar.DATE, -1);
			//String.valueOf(c.get(Calendar.YEAR)) + "년 ";
			//String.valueOf(c.get(Calendar.MONTH)) + "월 ";
			
			 /*
			 * 익월 
			 */
			  Calendar nextMonthCal = Calendar.getInstance();  
			  nextMonthCal.add(Calendar.MONTH, +1);
			  nextMonthCal.set(Calendar.DATE, nextMonthCal.getActualMaximum(Calendar.DATE));  
			  int p_next_year = nextMonthCal.get(Calendar.YEAR); 
			  int p_next_month = nextMonthCal.get(Calendar.MONTH)+1;
			  
			  p_vo.setEa_pre_yy(String.valueOf(p_next_year));
			  p_vo.setEa_pre_mm(String.valueOf(p_next_month));
			  
			  /*
			   * 현재월
			   */
			  Calendar cal = Calendar.getInstance();  
			  cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
			  int p_year = cal.get(Calendar.YEAR); // 현재월
			  int p_month = cal.get(Calendar.MONTH)+1; // 현재월
			  
			  p_vo.setEa_yy(String.valueOf(p_year));
			  p_vo.setEa_mm(String.valueOf(p_month));
			  
			  /*
			   * 전월 
			   */
			  Calendar prevMonthCal = Calendar.getInstance();  
			  prevMonthCal.add(Calendar.MONTH, -1);
			  prevMonthCal.set(Calendar.DATE, prevMonthCal.getActualMaximum(Calendar.DATE));  
			  int p_pre_year = prevMonthCal.get(Calendar.YEAR); // 전월
			  int p_pre_month = prevMonthCal.get(Calendar.MONTH)+1; // 전월
			  
			  p_vo.setEa_pre_yy(String.valueOf(p_pre_year));
			  p_vo.setEa_pre_mm(String.valueOf(p_pre_month));
			  
			if(c.get(Calendar.DATE)>=16){
				p_vo.setEa_yy(String.valueOf(p_next_year));
				if(p_next_month<10)
					p_vo.setEa_mm("0"+String.valueOf(p_next_month));
				else
					p_vo.setEa_mm(String.valueOf(p_next_month));
				
				p_vo.setEa_pre_yy(String.valueOf(p_year));
				if(p_month<10)
					p_vo.setEa_pre_mm("0"+String.valueOf(p_month));
				else
					p_vo.setEa_pre_mm(String.valueOf(p_month));
				
			}else{
				p_vo.setEa_yy(String.valueOf(p_year));
				if(p_month<10)
					p_vo.setEa_mm("0"+String.valueOf(p_month));
				else
					p_vo.setEa_mm(String.valueOf(p_month));
				
				p_vo.setEa_pre_yy(String.valueOf(p_pre_year));
				if(p_pre_month<10)
					p_vo.setEa_pre_mm("0"+String.valueOf(p_pre_month));
				else
					p_vo.setEa_pre_mm(String.valueOf(p_pre_month));
			}
			  
			/*System.out.println("*** c.get(Calendar.DATE) : " + c.get(Calendar.DATE) );
			System.out.println("*** p_vo.getEa_yy() : " + p_vo.getEa_yy() );
			System.out.println("*** p_vo.getEa_mm() : " + p_vo.getEa_mm() );
			System.out.println("*** p_vo.getEa_pre_yy() : " + p_vo.getEa_pre_yy() );
			System.out.println("*** p_vo.getEa_pre_mm() : " + p_vo.getEa_pre_mm() );
			*/
			dao.sp_summary_emp_attandance(p_vo);
		}		
		return;
	}
	
//	@Scheduled(cron = "01 * * * * *")
//	@Scheduled(cron = "01 13 17 * * *")
	@Scheduled(cron = "01 10 00 * * *")
	public void displayBatchArr() throws Exception {
		if(host_name.equals("was1")){
			System.out.println("통계 배치 시작 >  현재 진열 줄수");
			int cnt = dao2.displayBatchArr("4");
			System.out.println("등록성공 : " + cnt);
		}
		return;
	}
//	@Scheduled(cron = "01 * * * * *")
//	@Scheduled(cron = "01 14 17 * * *")
	@Scheduled(cron = "30 11 00 * * *")
	public void displayBatchTrt() throws Exception {
		if(host_name.equals("was1")){
			System.out.println("통계 배치 시작 >  TRT 취급품목 현황 ");
			int cnt = dao2.displayBatchTrt("5");
			System.out.println("등록성공 : " + cnt);
		}
		return;
	}
//	@Scheduled(cron = "01 * * * * *")
//	@Scheduled(cron = "01 15 17 * * *")
	@Scheduled(cron = "00 13 00 * * *")
	public void displayBatchBig() throws Exception {
		if(host_name.equals("was1")){
			System.out.println("통계 배치 시작 >  보조 진열 현황 ");
			int cnt = dao2.displayBatchBig("6");
			System.out.println("등록성공 : " + cnt);
		}
		return;
	}
//	@Scheduled(cron = "01 * * * * *")
//	@Scheduled(cron = "01 16 17 * * *")
	@Scheduled(cron = "30 13 00 * * *")
	public void displayBatchPd() throws Exception {
		if(host_name.equals("was1")){
			System.out.println("통계 배치 시작 >  PD매대 진열 현황 ");
			int cnt = dao2.displayBatchPd("7");
			System.out.println("등록성공 : " + cnt);
		}
		return;
	}
	
	
	// 
	@Scheduled(cron = "0 56 15 * * *")
	public void deleteFile(){
//		String fullPath = "";
//		//파일 삭제
//		Thread tpd = new Thread(new TempFileDelete(fullPath,0));
//		tpd.start();
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
				
				File f = new File(filePath);
			    if (f.delete()) {
			      System.out.println("삭제 성공");
			    } else {
			      System.err.println("삭제 실패 ");
			    }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
