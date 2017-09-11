package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		 String uuidName="";
		try{
			//1.��������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(100*1024);
			factory.setRepository(new File(this.getServletContext().getRealPath("WEB-INF/temp")));
			//2.�����ļ��ϴ�������
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			
			//--����Ƿ�����ȷ���ļ��ϴ���
			if(!fileUpload.isMultipartContent(request)){
				throw new RuntimeException("������ȷ�ı������ϴ�!");
			}
			//--�����ļ��ϴ��Ĵ�С����
//			fileUpload.setFileSizeMax(1024*1024*100);//�����ļ�������10M
//			fileUpload.setSizeMax(1024*1024*100);//�ܴ�С������100M
			
			//--���ñ��뼯,����ϴ��ļ�������������
			fileUpload.setHeaderEncoding("utf-8");
			
			//--�����ļ��ϴ�����
			fileUpload.setProgressListener(new ProgressListener(){
				Long beginTime = System.currentTimeMillis();
				public void update(long bytesRead, long contentLength, int items) {
					BigDecimal br = new BigDecimal(bytesRead).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
					BigDecimal cl = new BigDecimal(contentLength).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
					System.out.print("��ǰ��ȡ���ǵ�"+items+"���ϴ���,�ܴ�С"+cl+"KB,�Ѿ���ȡ"+br+"KB");
					//ʣ���ֽ���
					BigDecimal ll = cl.subtract(br);
					System.out.print("ʣ��"+ll+"KB");
					//�ϴ��ٷֱ�
					BigDecimal per = br.multiply(new BigDecimal(100)).divide(cl,2,BigDecimal.ROUND_HALF_UP);
					System.out.print("�Ѿ����"+per+"%");
					//�ϴ���ʱ
					Long nowTime = System.currentTimeMillis();
					Long useTime = (nowTime - beginTime)/1000;
					System.out.print("�Ѿ���ʱ"+useTime+"��");
					//�ϴ��ٶ�
					BigDecimal speed = new BigDecimal(0);
					if(useTime!=0){
						speed = br.divide(new BigDecimal(useTime),2,BigDecimal.ROUND_HALF_UP);
					}
					System.out.print("�ϴ��ٶ�Ϊ"+speed+"KB/S");
					//����ʣ��ʱ��
					BigDecimal ltime = new BigDecimal(0);
					if(!speed.equals(new BigDecimal(0))){
						ltime = ll.divide(speed,0,BigDecimal.ROUND_HALF_UP);
					}
					System.out.print("����ʣ��ʱ��Ϊ"+ltime+"��");
					
					System.out.println();
				}
				
			});
			
			//3.�����ļ��ϴ����������request
			List<FileItem> list = fileUpload.parseRequest(request);
			//4.�������е�FileItem
			for(FileItem item : list){
				if(item.isFormField()){
					//��ǰ��һ����ͨ���ֶ���
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					System.out.println(name+":"+value);
				}else{
					//��ǰ��һ���ļ��ϴ���
					String filename = item.getName();
					//String uuidName = UUID.randomUUID().toString()+"_"+filename;
					String uuidName1 = makeFileName( filename);
					uuidName=uuidName1;
					int hash = uuidName.hashCode();
					String hashStr = Integer.toHexString(hash);
					char [] hss = hashStr.toCharArray();
					String path = this.getServletContext().getRealPath("WEB-INF/upload");
					/*for(char c : hss){
						path+="/"+c;
					}
					new File(path).mkdirs();
					*/
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(new File(path,uuidName));
					
					IOUtils.In2Out(in, out);
					IOUtils.close(in, out);
					
					//--ɾ����ʱ�ļ�
					item.delete();
				}
			}
		}catch (FileSizeLimitExceededException e) {
			response.getWriter().write("�����ļ�������10M,�ܴ�С������100M!");
			return;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		File f=new File(this.getServletContext().getRealPath("/WEB-INF/upload")+"\\"+uuidName);
	    System.out.println(this.getServletContext().getRealPath("/WEB-INF/upload")+"\\"+uuidName);
	    request.setAttribute("File",f);
	    request.setAttribute("Filename",uuidName);
	    request.getRequestDispatcher("/OutServlet").forward(request, response);
	
	
	}

	 private String makeFileName(String filename){ //2.jpg
		  //Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
			 return (new Date().getTime() + "_" + filename);
		 }  
		 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

