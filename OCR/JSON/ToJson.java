package Tessocr;
import java.lang.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

import net.sf.json.JSONObject;
public class ToJson {
	//public static String s="�й�\nnb����\n����";
		public JSONObject ToChangeJson(String s){
			String[] ss=s.split("\n");
		    /*for (int i = 0; i < ss.length; i++)
	         System.out.println(ss[i]);
	         */
		    /*Map<String, String> map = new TreeMap<String, String>(
		    		new Comparator<String>() {
		    			public int compare(String obj1, String obj2) {
	                // ��������
	                return obj1.compareTo(obj2);
	               // return obj2.compareTo(obj1);//����
		    		   }
	               }
		    );
		    for (int i = 0; i < ss.length; i++){
		    	map.put("��"+(i+1)+"��", ss[i]);
		    }
		    Set<String> keySet = map.keySet();
	        java.util.Iterator<String> iter = keySet.iterator();
	        while (iter.hasNext()) {
	            String key = iter.next();
	            System.out.println(key + ":" + map.get(key));
	        }
	        */
	        
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < ss.length; i++){
		    	map.put("��"+(i+1)+"��", ss[i]);
		    }
		   JSONObject jsonObject = JSONObject.fromObject(map);  
		   //System.out.println("Json����:"+jsonObject);
		   return jsonObject;
		    
			
			
		}
		

}
