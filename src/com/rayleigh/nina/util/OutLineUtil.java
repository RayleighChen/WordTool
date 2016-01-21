package com.rayleigh.nina.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.rayleigh.nina.bean.DocBean;
import com.rayleigh.nina.bean.OutlineBean;

public class OutLineUtil {
	private ArrayList<OutlineBean> outlins;
	
	
	public ArrayList<OutlineBean> readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            outlins = new ArrayList<OutlineBean>();
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                OutlineBean outline = new OutlineBean();
                outline.setName(tempString.trim());
                outlins.add(outline);
                line++;
            }
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return process();
    }
	
	public ArrayList<OutlineBean> process() {
		String parent = null;
		int numOfLevel1 = 0;
		int numOfLevel2 = 0;
		for (int i = 0; i < outlins.size(); i++) {

			if(i + 1 == outlins.size()){
				outlins.get(i).setFold(false);
				outlins.get(i).setFile(true);
				numOfLevel2++;
				if (numOfLevel2 < 10) {
					outlins.get(i).setName("0"+numOfLevel2+" "+outlins.get(i).getName().substring(1, outlins.get(i).getName().length()));
				} else {
					outlins.get(i).setName(numOfLevel2+" "+outlins.get(i).getName().substring(1, outlins.get(i).getName().length()));
				}
				outlins.get(i).setParent(parent);
				break;
			}
			outlins.get(i).setLevel(outlins.get(i).getName().substring(0));
			if (outlins.get(i).getName().substring(0, 1).equals("1")) {
				numOfLevel1++;
				outlins.get(i).setFold(true);
				outlins.get(i).setFile(false);
				if (outlins.get(i).getName().substring(1, outlins.get(i).getName().length()).equals("附录")) {
					outlins.get(i).setName(outlins.get(i).getName().substring(1, outlins.get(i).getName().length()));
				} else {
					outlins.get(i).setName("第"+numOfLevel1+"章 "+outlins.get(i).getName().substring(1, outlins.get(i).getName().length()));
				}
				
				parent = outlins.get(i).getName();
				numOfLevel2 = 0;
			}
			if (outlins.get(i).getName().substring(0, 1).equals("2")) {
				outlins.get(i).setFold(false);
				if (outlins.get(i + 1).getName().substring(0, 1).equals("3")) {
					outlins.get(i).setFile(false);
					outlins.get(i).setName("xx " + outlins.get(i).getName().substring(1, outlins.get(i).getName().length()));
					
				} else {
					outlins.get(i).setFile(true);
					numOfLevel2++;
					if (numOfLevel2 < 10) {
						outlins.get(i).setName("0"+numOfLevel2+" "+outlins.get(i).getName().substring(1, outlins.get(i).getName().length()));
					} else {
						outlins.get(i).setName(numOfLevel2+" "+outlins.get(i).getName().substring(1, outlins.get(i).getName().length()));
					}
				}				
				
				outlins.get(i).setParent(parent);
			}
			if (outlins.get(i).getName().substring(0, 1).equals("3")) {
				outlins.get(i).setFold(false);
				outlins.get(i).setFile(true);
				numOfLevel2++;
				if (numOfLevel2 < 10) {
					outlins.get(i).setName("0"+numOfLevel2+" "+outlins.get(i).getName().substring(1, outlins.get(i).getName().length()));
				} else {
					outlins.get(i).setName(numOfLevel2+" "+outlins.get(i).getName().substring(1, outlins.get(i).getName().length()));
				}
				outlins.get(i).setParent(parent);
			}
		}
		
		return outlins;
	}
	
	public static void main(String[] args) {
		DocBean docBean = new DocBean();
		XmlUtil xmlUtil = new XmlUtil(docBean);
		xmlUtil.getIndexOfBean(0);
		String filename = docBean.getRoot().substring(0, docBean.getRoot().lastIndexOf('/')) + "/outline.txt";
		ArrayList<OutlineBean> outlins = new OutLineUtil().readFileByLines(filename);
		for (int i = 0; i < outlins.size(); i++) {
			System.err.println(outlins.get(i).getName());
		}
	}
}
