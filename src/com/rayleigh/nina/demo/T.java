package com.rayleigh.nina.demo;

import com.rayleigh.nina.controller.FileTool;

public class T {     

    public static void main(String[] args) {     
            // TODO Auto-generated method stub    
                 MSWordManager ms=new MSWordManager(true);     

//                 ms.createNewDocument();   
                 ms.openDocument("D:\\�ļ�ģ��.docx");	
                 ms.replaceText("�����ļ�ģ��", "ģ�߹�������ļ��㼰������װ��ͼ");
                 ms.moveDown(1);

//                 ms.setFont(true, false, false, "0,0,0,0", "12", "΢���ź�");
//                 ms.insertText("*a");
//                 ms.typeParagraph();
//                 
//                 ms.setFont(true, false, false, "1,0,0,0", "12", "����");
//                 ms.insertText("*b");
//                 ms.typeParagraph();
                 
//                 ms.setFont(true, false, false, "0,0,0,0", "12", "΢���ź�");
//                 ms.insertText("*c");
//                 ms.typeParagraph();
                 

//                ms.getParagraphFromAnotherDoc("D:\\1.docx", 6);
//                 ms.setTitleStyle("���� 3");
//                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 1);
//                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 2);
//                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 37);
//                 ms.copyTableFromAnotherDoc("D:\\1.docx", 2);//35-2-1=32,  3*8   // 3*11
//                 ms.moveEnd();
                 ms.setTableStyle();
//                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 38);
//                 String tmp = ms.getParagraphFromAnotherDoc("D:\\1.docx", 36);
//                 ms.insertText(tmp);
//                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 104);
//                 for (int i = 1; i <= 5; i++) {
//                	 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 105 + i);
//				}
//                 for (int i = 1; i <= 5; i++) {
//                	 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 110 + i);
//				}
//                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 48);//102-48 = 54
//                 ms.copyTableFromAnotherDoc("D:\\1.docx", 3);
//                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 102); 
//                 ms.typeParagraph();
//                 ms.insertText("1��ѡ��ͼ�꽨����");
//                 ms.typeParagraph();
//                 ms.setFont(false, false, false, "0,0,0,0", "12", "Times New Roman");
//                 ms.insertText("1����������͹����ģ����ߴ�ľ������������ĳߴ�23�Ǳ�עΪ���γߴ�ģ�����Ӧ��͹ģΪ��׼������ȷ��͹ģ�ߴ磬Ȼ���پ�����ģ�ߴ�");
//                 
//                 ms.setFont(true, false, false, "0,0,0,0", "32", "΢���ź�");
////                 ms.savePath("D:\\1.docx");
//                 ms.save();
                 
//
//                 ms.close();     
//                 ms.closeDocument();     
    }     

}