package com.shopping.utils.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/***
 * @author lsf
 */
public class ExportExcel {

	public final static HSSFWorkbook exportMultiExcel(String fileName, String[] title, String[] feildNames,
                                                      List<HashMap<String, Object>> listContent) {
		List<String> titleList = Arrays.asList(title);
		List<String> feildList = Arrays.asList(feildNames);
		return exportMultiExcel(fileName, titleList, feildList, listContent);
	}

	public final static HSSFWorkbook exportMultiExcel(String fileName, List<String> titleList, List<String> feildList,
                                                      List<HashMap<String, Object>> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {

			// 创建excel
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(fileName);// 设置Sheet页

			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);// 设置单元格文字垂直居中

			// 设置列宽度
			for (int i = 0; i < titleList.size(); i++) {
				sheet.setColumnWidth((short) i, (short) 6000);
			}

			HSSFRow row = sheet.createRow((int) 0); // 标题行
			row.setHeightInPoints(25);

			// 标题行字体
			HSSFFont font = wb.createFont();
			font.setFontHeightInPoints((short) 14); // 字体大小
			font.setBold(true); // 粗体
			font.setFontName("黑体");
			style.setFont(font);
			for (int i = 0; i < titleList.size(); i++) {
				HSSFCell cell = row.createCell((short) i);
				cell.setCellValue(titleList.get(i));
				cell.setCellStyle(style);
			}

			// 内容字体样式
			HSSFCellStyle style2 = wb.createCellStyle();
			HSSFFont font2 = wb.createFont();
			font2.setFontHeightInPoints((short) 12); // 字体大小
//			font2.setBold(true); // 粗体
			font2.setFontName("仿宋_GB2312");
			style2.setVerticalAlignment(VerticalAlignment.CENTER);
			// 设置垂直居中
			style2.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
			style2.setFont(font2);

			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			for (int i = 0; i < listContent.size(); i++) {
				row = sheet.createRow(i + 1);
				row.setHeightInPoints(35);
				Map<String, Object> exportdata = listContent.get(i);
				for (int j = 0; j < feildList.size(); j++) {
					if (StringUtils.isNotBlank(feildList.get(j))) {
						HSSFCell cell = row.createCell((short) j);
						Object object = exportdata.get(feildList.get(j));
						if (object != null) {
							cell.setCellValue(object.toString());
							cell.setCellStyle(style2);
						}
					}
				}
			}

			return wb;

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return null;
	}

	/***************************************************************************
	 * @param fileName
	 *            EXCEL文件名称
	 * @param Title
	 *            EXCEL文件第一行列标题集合
	 * @param feildNames
	 *            从map取值的key集合
	 * @param listContent
	 *            EXCEL文件正文数据集合
	 * @return
	 */
	public final static String export_data(HttpServletResponse response,
                                           String fileName, String[] Title, String[] feildNames,
                                           List<HashMap<String, Object>> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 设置响应头
			response.setContentType("application/vnd.ms-excel;");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes(), "iso-8859-1") + ".xls");

			// 创建excel
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(fileName);// 设置Sheet页

			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);// 设置单元格文字居中
			style.setVerticalAlignment(VerticalAlignment.CENTER);// 设置单元格文字垂直居中

			// 设置列宽度
			for (int i = 0; i < Title.length; i++) {
				sheet.setColumnWidth((short) i, (short) 6000);
			}

			HSSFRow row = sheet.createRow((int) 0); // 标题行
			row.setHeightInPoints(25);

			// 标题行字体
			HSSFFont font = wb.createFont();
			font.setFontHeightInPoints((short) 14); // 字体大小
			font.setBold(true); // 粗体
			font.setFontName("黑体");
			style.setFont(font);
			for (int i = 0; i < Title.length; i++) {
				HSSFCell cell = row.createCell((short) i);
				cell.setCellValue(Title[i]);
				cell.setCellStyle(style);
			}

			// 内容字体样式
			HSSFCellStyle style2 = wb.createCellStyle();
			HSSFFont font2 = wb.createFont();
			font2.setFontHeightInPoints((short) 12); // 字体大小
			font2.setBold(true); // 粗体
			font2.setFontName("仿宋_GB2312");
			style2.setVerticalAlignment(VerticalAlignment.CENTER);
			// 设置垂直居中
			style2.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
			style2.setFont(font2);

			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			for (int i = 0; i < listContent.size(); i++) {
				row = sheet.createRow(i + 1);
				row.setHeightInPoints(35);
				Map<String, Object> exportdata = listContent.get(i);
				for (int j = 0; j < feildNames.length; j++) {
					if (StringUtils.isNotBlank(feildNames[j])) {
						HSSFCell cell = row.createCell((short) j);
						Object object = exportdata.get(feildNames[j]);
						if (object != null) {
							cell.setCellValue(object.toString());
							cell.setCellStyle(style2);
						}
					}
				}
			}

			// 将excel写入响应头
			OutputStream ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}

	/*public static String getValue(HSSFCell hssfCell) {
		if (hssfCell == null) {
			return "";
		} else if (hssfCell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == CellType.NUMERIC) {
			// String valueOf = String.valueOf(hssfCell.getNumericCellValue());
			int a = (int) hssfCell.getNumericCellValue();
			return String.valueOf(a);
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	public static String getValue(Cell cell) {
		if (Util.isNull(cell)) {
			return "";
		} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue()).split(".")[0];
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}*/

	public static void exportZipExcel(HttpServletResponse response , HttpServletRequest request
			, List<HashMap<String , Object>> resultList
			, String[] title , String[] feildNames , int fileShowCount) throws IOException {
		List<HSSFWorkbook> workbooks = new ArrayList<>();
		if (resultList == null || resultList.size() > 0) {
			return;
		}

		int index = resultList.size() % fileShowCount > 0 ? resultList.size() / fileShowCount + 1 : resultList.size() / fileShowCount;
		for (int i = 0; i < index; i++) {
			int fromIndex = i * fileShowCount;
			int toIndex = fromIndex + fileShowCount;


			List<HashMap<String, Object>> datas = resultList.subList(fromIndex, toIndex > resultList.size() ? resultList.size() : toIndex);

			HSSFWorkbook workbook = ExportExcel.exportMultiExcel("xls", title, feildNames, datas);
			if (workbook != null) {
				workbooks.add(workbook);
			}
		}

		exportZipExcel(response , request , workbooks);
	}

	public static void exportZipExcel(HttpServletResponse response , HttpServletRequest request
			, List<HSSFWorkbook> workbooks) throws IOException {



		List<String> fileNames = new ArrayList<>();// 用于存放生成的文件名称s

		setResponseHeader(response, "xls");

		for (HSSFWorkbook workbook : workbooks) {
			String file = request.getSession().getServletContext().getRealPath("") + "/" +
					getFileName() + "-" + System.currentTimeMillis() + ".xls";

			fileNames.add(file);

			FileOutputStream o = new FileOutputStream(file);
			workbook.write(o);
		}

		File zip = new File(request.getSession().getServletContext().getRealPath("")
				+ "/" + getFileName() + ".zip");// 压缩文件
		File srcfile[] = new File[fileNames.size()];
		for (int i = 0, n1 = fileNames.size(); i < n1; i++) {
			srcfile[i] = new File(fileNames.get(i));
		}
		ZipFiles(srcfile, zip);
		FileInputStream inStream = new FileInputStream(zip);
		byte[] buf = new byte[4096];
		int readLength;
		while (((readLength = inStream.read(buf)) != -1)) {
			response.getOutputStream().write(buf, 0, readLength);
		}
		inStream.close();
	}

	public static void exportZipExcel(HttpServletResponse response , String path
			, List<HSSFWorkbook> workbooks) throws IOException {



		List<String> fileNames = new ArrayList<>();// 用于存放生成的文件名称s

		setResponseHeader(response, "xls");

		for (HSSFWorkbook workbook : workbooks) {
			String file = path + "/" +
					getFileName() + "-" + System.currentTimeMillis() + ".xls";

			fileNames.add(file);

			FileOutputStream o = new FileOutputStream(file);
			workbook.write(o);
		}

		File zip = new File(path
				+ "/" + getFileName() + ".zip");// 压缩文件
		File srcfile[] = new File[fileNames.size()];
		for (int i = 0, n1 = fileNames.size(); i < n1; i++) {
			srcfile[i] = new File(fileNames.get(i));
		}
		ZipFiles(srcfile, zip);
		FileInputStream inStream = new FileInputStream(zip);
		byte[] buf = new byte[4096];
		int readLength;
		while (((readLength = inStream.read(buf)) != -1)) {
			response.getOutputStream().write(buf, 0, readLength);
		}
		inStream.close();
	}

	public static void setResponseHeader(HttpServletResponse response, String fileName) {
		try {

			response.reset();// 清空输出流
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("GB2312"), "8859_1")
					+ ".zip");
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
			response.setContentType("application/zip");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void ZipFiles(File[] srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getFileName(){
		// 文件名获取
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String f = format.format(date);
		return f;
	}

	public static String getValue(HSSFCell hssfCell) {
		if (hssfCell == null) {
			return "";
		} else if (hssfCell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == CellType.NUMERIC) {
			// String valueOf = String.valueOf(hssfCell.getNumericCellValue());
			int a = (int) hssfCell.getNumericCellValue();
			return String.valueOf(a);
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}