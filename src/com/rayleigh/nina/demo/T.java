package com.rayleigh.nina.demo;

import com.rayleigh.nina.controller.FileTool;

public class T {     

    public static void main(String[] args) {     
            // TODO Auto-generated method stub    
                 MSWordManager ms=new MSWordManager(true);     

//                 ms.createNewDocument();   
                 ms.openDocument("D:\\文件模板.doc");	
                 ms.replaceText("单个文件模板", "模具工作零件的计算及初绘总装配图");
                 ms.moveDown(1);

//                 ms.setFont(true, false, false, "0,0,0,0", "12", "微软雅黑");
//                 ms.insertText("*a");
//                 ms.typeParagraph();
//                 
//                 ms.setFont(true, false, false, "1,0,0,0", "12", "宋体");
//                 ms.insertText("*b");
//                 ms.typeParagraph();
                 
//                 ms.setFont(true, false, false, "0,0,0,0", "12", "微软雅黑");
//                 ms.insertText("*c");
//                 ms.typeParagraph();
                 

//                ms.getParagraphFromAnotherDoc("D:\\1.docx", 6);
//                 ms.setTitleStyle("标题 3");
//                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 1);
//                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 2);
                 ms.copyTableFromAnotherDoc("D:\\1.docx", 1);
                 ms.moveEnd();
//                 ms.typeParagraph();
                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 2);
                 ms.copyParagraphFromAnotherDoc("D:\\1.docx", 3);
//                 ms.insertText("1．选择图标建立点");
//                 ms.typeParagraph();
//                 ms.setFont(false, false, false, "0,0,0,0", "12", "Times New Roman");
//                 ms.insertText("1）该弯曲件凸、凹模横向尺寸的决定该弯曲件的尺寸23是标注为内形尺寸的，所以应以凸模为基准件，先确定凸模尺寸，然后再决定凹模尺寸");
//                 
//                 ms.setFont(true, false, false, "0,0,0,0", "32", "微软雅黑");
////                 ms.savePath("D:\\1.docx");
//                 ms.save();
                 
//
//                 ms.close();     
//                 ms.closeDocument();     
    }     

}