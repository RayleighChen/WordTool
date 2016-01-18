package com.rayleigh.nina.demo;

import com.rayleigh.nina.controller.FileTool;

public class T {     

    public static void main(String[] args) {     
            // TODO Auto-generated method stub    
                 MSWordManager ms=new MSWordManager(true);     

                 ms.createNewDocument();     
 
                 ms.insertText("Test jacob");     

                 ms.save("D:\\1.docx");     

                 ms.close();     
                 ms.closeDocument();     
    }     

}