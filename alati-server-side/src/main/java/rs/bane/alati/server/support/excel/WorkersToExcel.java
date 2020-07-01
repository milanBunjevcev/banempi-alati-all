package rs.bane.alati.server.support.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.convert.converter.Converter;

import rs.bane.alati.server.model.Worker;

public class WorkersToExcel implements Converter<List<Worker>, XSSFWorkbook> {

	@Override
	public XSSFWorkbook convert(List<Worker> workerList) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();

		Row row;
		Cell cell;
		int currentRow = 0;

		for (int i = 0; i < workerList.size(); i++) {
			Worker worker = workerList.get(i);
			int currentColumn = 0;

			row = sheet.createRow(currentRow);

			cell = row.createCell(currentColumn++);
			cell.setCellValue(i + 1);
			cell = row.createCell(currentColumn++);
			cell.setCellValue(worker.getLastName());
			cell = row.createCell(currentColumn++);
			cell.setCellValue(worker.getName());
			cell = row.createCell(currentColumn++);
			cell.setCellValue(worker.getContractType().toString());
		}

		return workbook;
	}

}
