package com.rayleigh.nina.demo;

public class T {     

    public static void main(String[] args) {     
            // TODO Auto-generated method stub    
                 MSWordManager ms=new MSWordManager(true);     
//����һ��MSwordManager����,����������ʾWord����    
                 ms.createNewDocument();     
//����һ���µ�.doc�ļ�    
                 ms.insertText("Test jacob");     
//�����ı�    
                 ms.save("D:\\1.docx");     
//����.doc�ļ�    
                 ms.close();     
                 ms.closeDocument();     
    }     

}