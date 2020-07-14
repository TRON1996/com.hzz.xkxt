package com.hzz.xkxt.util;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class util {
	
	/**
	 * 32位MD5加密
	 * @param str 待加密字符串
	 * @return 32位MD5加密字符串
	 * @throws NoSuchAlgorithmException
	 */
	public static String UseMD5(String str) throws NoSuchAlgorithmException  {
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		BigInteger bigInteger=new BigInteger(1, md.digest());
		return bigInteger.toString(16);
	}
	
	/**
	 * 获取系统当前日期时间
	 * @return 系统当前日期时间
	 */
	public static String getNowTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return df.format(System.currentTimeMillis()); 
	}
	
	/**
	* 用于将异常对象e中的错误信息构建为一个String字
	符串，该字符串
	* 将用于输出到日志文件当中去
	* @param e 异常对象
	* @return 异常对象中的异常信息字符串
	*/
	public static String getExceptionMsg(Throwable e){
		//将异常对象中的异常记录转换为记录数组
		StackTraceElement[] s = e.getStackTrace();
		StringBuffer sb = new StringBuffer();//从数组中取出异常信息，构建为一个字符串
		for(int i=0;i<s.length;i++){
			sb.append("\r\n");
			sb.append(s[i]);
		}
		return sb.toString();
	}
	
	public static Object convertNull(Object obj){
		if(obj==null)
			return "";
		return obj;
	}
	/**
	 * 文件上传工具
	 * @param servlet Servlet对象
	 * @param request request对象
	 * @param dirname 上传目标文件夹
	 * @param fileName 上传后文件名
	 * @throws Exception 异常
	 */
	public static String fileupload(HttpServlet servlet,
			HttpServletRequest request, String dirname, String fileName)
			throws Exception {
		 // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(1024 * 1024 * 3);
        String a=System.getProperty("java.io.tmpdir");
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);         
        // 设置最大文件上传值
        upload.setFileSizeMax(1024 * 1024 * 40);         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(1024 * 1024 * 50);        
        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = servlet.getServletContext().getRealPath("/")  + dirname;
       
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        } 
      
        // 解析请求的内容提取文件数据
        @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField()) {
                	
                	String fName = new File(item.getName()).getName();
                	String type=fName.substring(fName.indexOf("."), fName.length());
                	
                	if(fileName.equals("")){
                		fileName = new File(item.getName()).getName();
                	}else{
                		fileName=fileName+type;
                	}
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    if(storeFile.exists()){
                    	storeFile.delete();
                    }
                    item.write(storeFile);                        
                }
            }
        }
        return fileName;
   
	}
	public static String getNow(){
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH);
		if(month<=8){
			return (year-1)+"-"+year+"学年下学期";
		}else{
			return year+"-"+(year+1)+"学年上学期";
		}
	}
	//测试
	public static void main(String[] args) {
		System.out.println(getNowTime());
	}

}
