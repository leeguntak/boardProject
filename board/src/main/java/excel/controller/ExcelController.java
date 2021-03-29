package excel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import board.bean.BoardTableDTO;
import board.service.BoardService;



@Controller
@RequestMapping(value="excel")
public class ExcelController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/excelDownload", method=RequestMethod.GET)
	public void excelDownload(HttpServletResponse response) throws IOException{

		List<BoardTableDTO> boardTableList = boardService.selectAll();
		
		//셀생성
		XSSFWorkbook objWorkBook = new XSSFWorkbook();
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("첫번째시트"); //워크시트 생성
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		
		XSSFFont font = objWorkBook.createFont();
		font.setFontHeightInPoints((short)9);
		font.setFontName("맑은고딕");

		//Header
		//헤더 스타일 지정
		CellStyle headStyle = wb.createCellStyle();
		//데이터 가운데 정렬
		headStyle.setAlignment(HorizontalAlignment.CENTER);
			//경계선
	   	headStyle.setBorderTop(BorderStyle.THIN);
	   	headStyle.setBorderBottom(BorderStyle.THIN);
	   	headStyle.setBorderLeft(BorderStyle.THIN);
	   	headStyle.setBorderRight(BorderStyle.THIN);
	   		// 배경색은 연두색
	   	headStyle.setFillForegroundColor(HSSFColorPredefined.LIGHT_GREEN.getIndex());
	   	headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		row = sheet.createRow(rowNum++); //1행 생성
		row.setHeight((short) (0*150));
		//1행에 데이터 삽입
		cell = row.createCell(0);
		cell.setCellValue("글번호");
		cell = row.createCell(1);
		cell.setCellValue("제목");
		cell = row.createCell(2);
		cell.setCellValue("조회수");
		cell = row.createCell(3);
		cell.setCellValue("작성일");
		
		//테이블 바디 스타일 지정
		CellStyle bodyStyle = wb.createCellStyle();
		//데이터 가운데 정렬
		bodyStyle.setAlignment(HorizontalAlignment.CENTER);
			//경계선
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		
		//Body
		for(BoardTableDTO boardTableDTO : boardTableList) {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue(boardTableDTO.getSeq());
			cell = row.createCell(1);
			cell.setCellValue(boardTableDTO.getSubject());
			cell = row.createCell(2);
			cell.setCellValue(boardTableDTO.getHit());
			cell = row.createCell(3);
			cell.setCellValue(boardTableDTO.getLogtime());
			//mem_id추가해야함
		}
		
		/*
		for(int i=0; i<6; i++) {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue(i);
			cell = row.createCell(1);
			cell.setCellValue(i);
			cell = row.createCell(2);
			cell.setCellValue(i);
			cell = row.createCell(3);
			cell.setCellValue(i);
			cell = row.createCell(4);
			cell.setCellValue(i);
		}
		//이렇게하면 여기서 쓴거 처럼 0~4 출력
		 */
		
		
		//컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");
		
		// Excel File Output
        wb.write(response.getOutputStream());
        wb.close();

	}

}
