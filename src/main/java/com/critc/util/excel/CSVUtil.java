package com.critc.util.excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.critc.util.web.WebUtil;

/**
 * 导出CSV文件
 * @author chykong
 *
 */
public class CSVUtil {
	private static Logger logger = LoggerFactory.getLogger("sysLog");

	/**
	   * 生成为CVS文件 
	   * @param outPutPath 
	   * @param fileName 要导出生成的文件名
	   * @return
	   */
	public static File createCSVFile(String outPutPath, String fileName, String[][] data) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
			File file = new File(outPutPath);
			if (!file.exists()) {
				file.mkdir();
			}
			//定义文件名格式并创建
			csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {
					csvFileOutputStream.write(WebUtil.getSafeStr(data[i][j]));
					if (j != data[i].length - 1)
						csvFileOutputStream.write(",");
				}
				csvFileOutputStream.newLine();
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvFile;
	}

	/**
	 * 删除单个文件
	 * @param filePath
	 *     文件目录路径
	 * @param fileName
	 *     文件名称
	 */
	public static void deleteFile(String filePath, String fileName) {
		File file = new File(filePath + "/" + fileName);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 测试数据
	 * @param args
	 */
	public static void main(String[] args) {

		String[][] data = new String[2][2];
		data[0][0] = "序号";
		data[0][1] = "内容";

		data[1][0] = "1";
		data[1][1] = "测试";

		String path = "c:/export/";
		String fileName = "文件导出";
		File file = CSVUtil.createCSVFile(path, fileName, data);
		String fileName2 = file.getName();
		System.out.println("文件名称：" + fileName2);
	}
}
