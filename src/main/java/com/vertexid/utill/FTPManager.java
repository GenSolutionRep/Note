package com.vertexid.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Value;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 서버와 연결하여 파일을 업로드하고, 다운로드한다.
 *
 * @author haneulnoon
 * @since 2009-09-10
 *
 */
public class FTPManager{
    private Session session = null;
    private Channel channel = null;
    private ChannelSftp channelSftp = null;

    @Value("#{sys_info['ftp_host']}")
    String ftp_host;
    
    @Value("#{sys_info['ftp_user_name']}")
    String ftp_userName;
    
    @Value("#{sys_info['ftp_password']}")
    String ftp_password;
    
    @Value("#{sys_info['ftp_port']}")
    int ftp_port;
    
    
    public void connect() {
        JSch jsch = new JSch();
        try {
        	//System.out.println("ftp_host : " +ftp_host);
        	//System.out.println("ftp_port : " +ftp_port);
        	//System.out.println("ftp_userName : " +ftp_userName);
        	//System.out.println("ftp_password : " +ftp_password);
            session = jsch.getSession(ftp_userName, ftp_host, ftp_port);
            session.setPassword(ftp_password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            channel = session.openChannel("sftp");
            channel.connect();
            //System.out.println("연결성공!!");
        } catch (JSchException e) {
        	System.out.println("연결실패!!");
            e.printStackTrace();
        }
        channelSftp = (ChannelSftp) channel;

    }
    
    
    //하나 파일을 업로드 >  dir 저장시킬 경로(서버) , file 저장할 파일
    public void upload(String dir, File file) throws SftpException, IOException, FileNotFoundException {
    	

  	  String[] folders = dir.split("/");
  	  channelSftp.cd("/");
  	  for (int i = 1; i < folders.length; i++) {
  		  String folder = folders[i];
//  		  System.out.println("make folders: " + folder);
  	      try {
  	    	  channelSftp.cd(folder);
  	      } catch (SftpException e) {
  	    	  channelSftp.mkdir(folder);
  	    	  channelSftp.cd(folder);
  	      }
  	  }

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            //System.out.println("원격지 저장위치"+ dir);
            channelSftp.cd(dir);
            channelSftp.put(in, file.getName());
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
            	if(in != null){
            		in.close();
            		disconnection();
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 서버와의 연결을 끊는다.
    public void disconnection() {
        channel.disconnect();
        session.disconnect();
        channelSftp.exit();
    }
}

