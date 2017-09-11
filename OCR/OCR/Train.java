package Tessocr;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sourceforge.tess4j.*;  
  
public class Train {  
	
	public String[] recognize(File imageFile,String path){//JSONObject
		//File imageFile = new File("e:\\wubiaoge3.JPG");
    	//File imageFile = new File("tessdataku/test1.JPG");
    	ITesseract instance = new Tesseract();

    	//In case you don't have your own tessdata, let it also be extracted for you
    	//这样就能使用classpath目录下的训练库了
    	
    	//instance.setLanguage("chi_sim");//中文库识别数字比较准确
    	
    	//Set the tessdata path
    	//instance.setDatapath("d:\\Tesseract-OCR\\tessdata");
    	//instance.setDatapath("E:\\Tesseract-OCR\\tessdata");
    	instance.setDatapath(path);//设置字库的路径
    	instance.setLanguage("chi_sim");//设置为简体中文字库
    	try {
    	    String result = instance.doOCR(imageFile);//识别完后，result后面会一个换行符
    	    //System.out.println("没有去掉result最末尾的换行符的识别的结果:");
    	    //System.out.print(result);
    	    //String Finalresult=result.substring(0,result.length()-1);//除去result末尾的换行符
    	    //System.out.println("去掉末尾的换行符的识别的结果:");
    	    //System.out.print(Finalresult);
    	    //System.out.println("####################################################");
    	    //System.out.println("转为JSON对象:");
    	    //ToJson json=new ToJson();
    	    //JSONObject jsonObject =json.ToChangeJson(result);
    	    //JSONObject jsonObject =json.ToChangeJson(Finalresult);
    	   // return json.ToChangeJson(result);
    	    String[] ss=result.split("\n");
    	    Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < ss.length; i++){
		    	ss[i]="第"+(i+1)+"行:"+ss[i];
		    }
			
			return ss;
    	} catch (TesseractException e) {
    		//JSONObject jsonObject =null;
    		 String[] ss={};
    		System.err.println(e.getMessage());
    		//return jsonObject;
    		return ss;
    	}
    
	}
	
  
	/* public static void main(String[] args) {  
    	File imageFile = new File("e:\\wubiaoge3.JPG");
    	//File imageFile = new File("tessdataku/test1.JPG");
    	ITesseract instance = new Tesseract();

    	//In case you don't have your own tessdata, let it also be extracted for you
    	//这样就能使用classpath目录下的训练库了
    	instance.setDatapath("xinziku/tessdata");
    	instance.setLanguage("chi_sim");//中文库识别数字比较准确
    	//instance.setLanguage("eng1+chi_sim");
    	//Set the tessdata path
    	//instance.setDatapath("d:\\Tesseract-OCR\\tessdata");
    	
    	try {
    	    String result = instance.doOCR(imageFile);//识别完后，result后面会一个换行符
    	    System.out.println("没有去掉result最末尾的换行符的识别的结果:");
    	    System.out.print(result);
    	    //String Finalresult=result.substring(0,result.length()-1);//除去result末尾的换行符
    	    //System.out.println("去掉末尾的换行符的识别的结果:");
    	    //System.out.print(Finalresult);
    	    System.out.println("####################################################");
    	    System.out.println("转为JSON对象:");
    	    ToJson json=new ToJson();
    	    json.ToChangeJson(result);
    	    //json.ToChangeJson(Finalresult);
    	} catch (TesseractException e) {
    	    System.err.println(e.getMessage());
    	}
    }*/
    
}  