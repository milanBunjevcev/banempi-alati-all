package rs.bane.alati.server.support.excel;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Production;

@Component
public class DailyProductionToExcel implements Converter<List<Production>, XSSFWorkbook> {

	@Override
	public XSSFWorkbook convert(List<Production> source) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();

		
		
		return workbook;
	}
}
