package com.critc.util.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * excel工具，读取2003文件
 * 
 * @author chykong
 * 
 */
public class Excel2003Util {

	/**
	 * 读取excel
	 * 
	 * @param is
	 *            文件流传入参数
	 * @param titleIndex
	 *            excel文件头的行数,默认从第一行开始，对应excel应该是titleIndex-1
	 * @return
	 */
	public String[] readExcelTitle(String fileName, int titleIndex) {
		String[] title = null;
		InputStream is = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		try {
			is = new FileInputStream(fileName);
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			row = sheet.getRow(titleIndex - 1);
			// 标题总列数
			int colNum = row.getPhysicalNumberOfCells();
			System.out.println("colNum:" + colNum);
			title = new String[colNum];
			for (int i = 0; i < colNum; i++) {
				title[i] = getCellFormatValue(row.getCell(i));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return title;
	}

	/**
	 * * 读取Excel数据内容 *
	 * 
	 * @param InputStream
	 * @param dataIndex为数据行的起始行数
	 * @return List 包含单元格数据内容的Map对象
	 */
	public List<String[]> readExcelContent(String fileName, int dataIndex) {
		List<String[]> list = null;
		InputStream is = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		try {
			is = new FileInputStream("d:\\zm.xls");
			list = new ArrayList<String[]>();
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();// 得到总行数
			row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells(); // 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = dataIndex - 1; i < rowNum + dataIndex - 1; i++) {
				row = sheet.getRow(i);
				String[] values = new String[colNum];
				for (int j = 0; j < colNum; j++) {
					values[j] = getCellFormatValue(row.getCell(j)).trim();
				}
				list.add(values);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	/**
	 * * 根据HSSFCell类型设置数据 *
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) { // 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
				cellvalue = String.valueOf(cell.getNumericCellValue());
			case HSSFCell.CELL_TYPE_FORMULA: { // 判断当前的cell是否为Date
				cellvalue = String.valueOf(cell.getNumericCellValue());
				break;
			}
			case HSSFCell.CELL_TYPE_STRING: // 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:// 默认的Cell值
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	public static void main(String[] args) {
		Excel2003Util excelUtil = new Excel2003Util();
		String title[] = excelUtil.readExcelTitle("E:\\eclipse3.6\\workspace\\SjdbPro\\WebRoot\\uploadFiles\\2013073163877.xls", 1);
		for (String str : title)
			System.out.println(str);

		List<String[]> list = excelUtil.readExcelContent("d:\\zm.xls", 2);
		for (String[] str : list) {
			for (String s : str)
				System.out.println(s);
		}
	}
}
