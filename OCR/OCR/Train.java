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
    	//��������ʹ��classpathĿ¼�µ�ѵ������
    	
    	//instance.setLanguage("chi_sim");//���Ŀ�ʶ�����ֱȽ�׼ȷ
    	
    	//Set the tessdata path
    	//instance.setDatapath("d:\\Tesseract-OCR\\tessdata");
    	//instance.setDatapath("E:\\Tesseract-OCR\\tessdata");
    	instance.setDatapath(path);//�����ֿ��·��
    	instance.setLanguage("chi_sim");//����Ϊ���������ֿ�
    	try {
    	    String result = instance.doOCR(imageFile);//ʶ�����result�����һ�����з�
    	    //System.out.println("û��ȥ��result��ĩβ�Ļ��з���ʶ��Ľ��:");
    	    //System.out.print(result);
    	    //String Finalresult=result.substring(0,result.length()-1);//��ȥresultĩβ�Ļ��з�
    	    //System.out.println("ȥ��ĩβ�Ļ��з���ʶ��Ľ��:");
    	    //System.out.print(Finalresult);
    	    //System.out.println("####################################################");
    	    //System.out.println("תΪJSON����:");
    	    //ToJson json=new ToJson();
    	    //JSONObject jsonObject =json.ToChangeJson(result);
    	    //JSONObject jsonObject =json.ToChangeJson(Finalresult);
    	   // return json.ToChangeJson(result);
    	    String[] ss=result.split("\n");
    	    Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < ss.length; i++){
		    	ss[i]="��"+(i+1)+"��:"+ss[i];
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
    	//��������ʹ��classpathĿ¼�µ�ѵ������
    	instance.setDatapath("xinziku/tessdata");
    	instance.setLanguage("chi_sim");//���Ŀ�ʶ�����ֱȽ�׼ȷ
    	//instance.setLanguage("eng1+chi_sim");
    	//Set the tessdata path
    	//instance.setDatapath("d:\\Tesseract-OCR\\tessdata");
    	
    	try {
    	    String result = instance.doOCR(imageFile);//ʶ�����result�����һ�����з�
    	    System.out.println("û��ȥ��result��ĩβ�Ļ��з���ʶ��Ľ��:");
    	    System.out.print(result);
    	    //String Finalresult=result.substring(0,result.length()-1);//��ȥresultĩβ�Ļ��з�
    	    //System.out.println("ȥ��ĩβ�Ļ��з���ʶ��Ľ��:");
    	    //System.out.print(Finalresult);
    	    System.out.println("####################################################");
    	    System.out.println("תΪJSON����:");
    	    ToJson json=new ToJson();
    	    json.ToChangeJson(result);
    	    //json.ToChangeJson(Finalresult);
    	} catch (TesseractException e) {
    	    System.err.println(e.getMessage());
    	}
    }*/
    
}  