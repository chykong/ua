package com.critc.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * excel工具，读取2007文件格式
 * 
 * @author chykong
 * 
 */
public class Excel2007Util {

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
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		InputStream is = null;
		String[] title = null;
		try {
			is = new FileInputStream(fileName);
			wb = WorkbookFactory.create(is);
			sheet = wb.getSheetAt(0);
			row = sheet.getRow(titleIndex - 1);
			// 标题总列数
			int colNum = row.getPhysicalNumberOfCells();
			title = new String[colNum];
			for (int i = 0; i < colNum; i++) {
				title[i] = getCellFormatValue(row.getCell(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		InputStream is = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			is = new FileInputStream(fileName);
			wb = WorkbookFactory.create(is);
			sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();// 得到总行数
			row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells(); // 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = dataIndex - 1; i <= rowNum + dataIndex - 1; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					String[] values = new String[colNum];
					for (int j = 0; j < colNum; j++) {
						values[j] = getCellFormatValue(row.getCell(j)).trim();
					}
					list.add(values);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
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

	private String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) { // 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_BLANK:
				cellvalue = "";
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
				} else {
					double val = cell.getNumericCellValue();
					// String operaVal="";
					if (String.valueOf(val).toUpperCase().indexOf('E') > -1) {
						cellvalue = String.valueOf(new DecimalFormat("#.######").format(val));
					} else {
						cellvalue = String.valueOf(val);
					}
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cellvalue = cell.getRichStringCellValue().toString();
				break;
			default:
				cellvalue = "";// 默认的Cell值
			}
		}
		return cellvalue;
	}

	/**
	 * 写excel
	 */
	public void writeExcel() {
		try {
			FileOutputStream out = new FileOutputStream(new File("D://result.xlsx"));
			Workbook writeWB = new SXSSFWorkbook(500);
			Sheet writeSheet = writeWB.createSheet();

			for (int rr = 0; rr < 400000; rr++) {
				Row writeRow = writeSheet.createRow(rr);
				for (int cc = 0; cc < 10; cc++) {
					writeRow.createCell(cc).setCellValue("测试" + rr + "," + cc);
				}
			}
			writeWB.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CellStyle createBorderCellStyle(Workbook wb, short backgroundColor, short foregroundColor, short halign, Font font) {
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(halign);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setFillBackgroundColor(backgroundColor);
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs.setFont(font);
		cs.setBorderLeft(CellStyle.SOLID_FOREGROUND);
		cs.setBorderRight(CellStyle.SOLID_FOREGROUND);
		cs.setBorderTop(CellStyle.SOLID_FOREGROUND);
		cs.setBorderBottom(CellStyle.SOLID_FOREGROUND);
		return cs;
	}

	public CellStyle getDefaultTitleStyle(Workbook workbook) {
		Font fontTitle = createFont(workbook, HSSFFont.BOLDWEIGHT_BOLD, HSSFFont.COLOR_NORMAL, (short) 20);
		CellStyle style = createBorderCellStyle(workbook, HSSFColor.WHITE.index, HSSFColor.WHITE.index, HSSFCellStyle.ALIGN_CENTER, fontTitle);
		return style;
	}

	public Font createFont(Workbook wb, short boldweight, short color, short size) {
		Font font = wb.createFont();
		font.setBoldweight(boldweight);
		font.setColor(color);
		font.setFontHeightInPoints(size);
		return font;
	}

	/**
	 * 写excel
	 * 
	 * @param data
	 */
	public void writeExcel(String[][] data, String fileName, HttpServletResponse response) {
		// FileOutputStream out = null;
		OutputStream os = null;
		try {
			// / out = new FileOutputStream(new File("D://result.xlsx"));
			Workbook wb = new SXSSFWorkbook(500);
			Sheet writeSheet = wb.createSheet();
			Font fontTitle = wb.createFont();
			fontTitle.setFontHeightInPoints((short) 12);
			fontTitle.setFontName("宋体");
			fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
			fontTitle.setCharSet(Font.DEFAULT_CHARSET);
			CellStyle cellTitleStyle = wb.createCellStyle();
			cellTitleStyle.setFont(fontTitle);
			cellTitleStyle.setBorderBottom((short) 1);
			cellTitleStyle.setBorderLeft((short) 1);
			cellTitleStyle.setBorderRight((short) 1);
			cellTitleStyle.setBorderTop((short) 1);
			cellTitleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellTitleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

			Font fontData = wb.createFont();
			fontData.setFontHeightInPoints((short) 10);
			fontData.setFontName("宋体");
			CellStyle cellDataStyle = wb.createCellStyle();
			cellDataStyle.setFont(fontData);
			cellDataStyle.setBorderBottom((short) 1);
			cellDataStyle.setBorderLeft((short) 1);
			cellDataStyle.setBorderRight((short) 1);
			cellDataStyle.setBorderTop((short) 1);
			cellDataStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellDataStyle.setFillForegroundColor(HSSFColor.WHITE.index);

			// 写表头
			Row writeRow = writeSheet.createRow(0);
			for (int i = 0; i < data[0].length; i++) {
				Cell cell = writeRow.createCell(i);
				cell.setCellValue(data[0][i]);
				cell.setCellStyle(cellTitleStyle);
			}
			// 写数据内容
			for (int i = 1; i < data.length; i++) {
				Row dataRow = writeSheet.createRow(i);
				for (int j = 0; j < data[i].length; j++) {
					Cell cell = dataRow.createCell(j);
					cell.setCellValue(data[i][j]);
					cell.setCellStyle(cellDataStyle);
				}
			}

			response.setContentType("application/msexcel");
			response.reset();
			response.setHeader("content-disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1") + ".xlsx");
			System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.POILogger");
			os = response.getOutputStream();
			wb.write(os);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
					os = null;
				}
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入指定文件名的excel
	 * 
	 * @param data
	 * @param fileName
	 */
	public void writeExcel(String[][] data, String fileName) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(fileName));
			Workbook wb = new SXSSFWorkbook(500);
			Sheet writeSheet = wb.createSheet();
			Font fontTitle = wb.createFont();
			fontTitle.setFontHeightInPoints((short) 12);
			fontTitle.setFontName("宋体");
			fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
			fontTitle.setCharSet(Font.DEFAULT_CHARSET);
			CellStyle cellTitleStyle = wb.createCellStyle();
			cellTitleStyle.setFont(fontTitle);
			cellTitleStyle.setBorderBottom((short) 1);
			cellTitleStyle.setBorderLeft((short) 1);
			cellTitleStyle.setBorderRight((short) 1);
			cellTitleStyle.setBorderTop((short) 1);
			cellTitleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellTitleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

			Font fontData = wb.createFont();
			fontData.setFontHeightInPoints((short) 10);
			fontData.setFontName("宋体");
			CellStyle cellDataStyle = wb.createCellStyle();
			cellDataStyle.setFont(fontData);
			cellDataStyle.setBorderBottom((short) 1);
			cellDataStyle.setBorderLeft((short) 1);
			cellDataStyle.setBorderRight((short) 1);
			cellDataStyle.setBorderTop((short) 1);
			cellDataStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellDataStyle.setFillForegroundColor(HSSFColor.WHITE.index);

			// 写表头
			Row writeRow = writeSheet.createRow(0);
			for (int i = 0; i < data[0].length; i++) {
				Cell cell = writeRow.createCell(i);
				cell.setCellValue(data[0][i]);
				cell.setCellStyle(cellTitleStyle);
			}
			// 写数据内容
			for (int i = 1; i < data.length; i++) {
				Row dataRow = writeSheet.createRow(i);
				for (int j = 0; j < data[i].length; j++) {
					Cell cell = dataRow.createCell(j);
					cell.setCellValue(data[i][j]);
					cell.setCellStyle(cellDataStyle);
				}
			}

			wb.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeExcel(String[][] data, String srcFile, String file_name, HttpServletResponse response, int titleRows, int rowHeight) {
		OutputStream os = null;
		try {
			// Workbook wb = new SXSSFWorkbook(500);
			InputStream in = new FileInputStream(srcFile);
			Workbook work = new HSSFWorkbook(in);
			Sheet sheet = work.getSheetAt(0);

			// 数据样式
			Font fontData = work.createFont();
			fontData.setFontHeightInPoints((short) 10);
			fontData.setFontName("宋体");
			fontData.setBoldweight((short) 1);

			CellStyle cellDataStyle = work.createCellStyle();
			cellDataStyle.setFont(fontData);
			cellDataStyle.setBorderBottom((short) 1);
			cellDataStyle.setBorderLeft((short) 1);
			cellDataStyle.setBorderRight((short) 1);
			cellDataStyle.setBorderTop((short) 1);
			cellDataStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellDataStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			cellDataStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellDataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellDataStyle.setWrapText(true);

			Font fontDataBold = work.createFont();
			fontDataBold.setFontHeightInPoints((short) 10);
			fontDataBold.setFontName("宋体");
			fontDataBold.setBoldweight(Font.BOLDWEIGHT_BOLD);
			CellStyle cellDataStyleBold = work.createCellStyle();
			cellDataStyleBold.setFont(fontDataBold);
			cellDataStyleBold.setBorderBottom((short) 1);
			cellDataStyleBold.setBorderLeft((short) 1);
			cellDataStyleBold.setBorderRight((short) 1);
			cellDataStyleBold.setBorderTop((short) 1);
			cellDataStyleBold.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cellDataStyleBold.setFillForegroundColor(HSSFColor.WHITE.index);
			cellDataStyleBold.setAlignment(CellStyle.ALIGN_CENTER);
			cellDataStyleBold.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellDataStyleBold.setWrapText(true);

			// 得到行，并填充数据和表格样式
			for (int i = 0; i < data.length; i++) {
				// Row row = sheet.createRow(i + 1);// 得到行
				Row row = sheet.createRow(i + titleRows);// 得到行
				// row.setHeight((short) 1000);
				row.setHeight((short) rowHeight);
				for (int j = 0; j < data[i].length; j++) {
					Cell cell = row.createCell(j);// 得到第0个单元格
					cell.setCellValue(data[i][j]);// 填充值
					cell.setCellStyle(cellDataStyle);// 填充样式
				}
			}
			response.setContentType("application/msexcel");
			response.reset();
			response.setHeader("content-disposition", "attachment; filename=" + new String(file_name.getBytes("GBK"), "ISO-8859-1") + ".xls");
			System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.POILogger");
			os = response.getOutputStream();
			work.write(os);
			os.flush();
		} catch (FileNotFoundException e) {
			System.out.println("文件路径错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件输入流错误");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Excel2007Util excelUtil = new Excel2007Util();
		// String title[] =
		// excelUtil.readExcelTitle("E:\\eclipse3.6\\workspace\\SjdbPro\\WebRoot\\uploadFiles\\2013080176589.xls",
// 1);
		// for (String str : title)
		// System.out.println(str);
		//
		// List<String[]> list = excelUtil.readExcelContent("d:\\test.xlsx", 2);
		// for (String[] str : list) {
		// for (String s : str)
		// System.out.println(s);
		// }

		String[][] data = new String[3][2];
		data[0][0] = "ID";
		data[0][1] = "姓名";
		data[1][0] = "1";
		data[1][1] = "测试";
		data[2][0] = "2";
		data[2][1] = "测试2";
		// excelUtil.writeExcel(data);
	}
}
