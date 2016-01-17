package com.rayleigh.nina.demo;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class TestDocPage {
	
	
	 public static void main (String [] args) throws Exception {
		 
		   String filePath = "E:\\test.doc";
		   
		   ActiveXComponent word=new ActiveXComponent("Word.Application");
		   
		   word.setProperty("Visible", new Variant(false)); 
		   
		   Dispatch documents=word.getProperty("Documents").toDispatch();
		   
		   Dispatch wordFile=Dispatch.invoke(documents, "Open", Dispatch.Method, new Object[]{filePath,new Variant(true),new Variant(false)}, new int[1]).toDispatch();	
	
		   Dispatch paragraphs=Dispatch.get(wordFile, "Paragraphs").toDispatch();
           
           int paraCount=Dispatch.get(paragraphs, "Count").getInt();
           
          for(int i=0;i<paraCount;++i){
	
	           Dispatch paragraph=Dispatch.call(paragraphs, "Item",new Variant(i+1)).toDispatch();
	           
	           int outline=Dispatch.get(paragraph, "OutlineLevel").getInt();
	           
	           if(outline<=9){
	        	   
		           System.out.println("大纲等级："+outline);
		           System.out.println("\n");
		           Dispatch paraRange=Dispatch.get(paragraph, "Range").toDispatch();
		
		           System.out.println("标题名称："+Dispatch.get(paraRange, "Text").toString());
		
		           int pages = Integer.parseInt(Dispatch.call(paraRange,"information",1).toString());
		           
		           System.out.println("标题页码："+pages);
		           System.out.println("\n");
	      
	           }
          
           }
         
		   Dispatch.call(wordFile, "Close", new Variant(true));
		   Dispatch.call(word, "Quit");
	   }
	   
}
