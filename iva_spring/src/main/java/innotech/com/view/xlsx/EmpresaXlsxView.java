package innotech.com.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import innotech.com.entididades.Empresa;

//@Component("empresa/ver.xlsx")
@Component
public class EmpresaXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"empresa_view.xlsx\"");
		Empresa empresa = (Empresa) model.get("datos");
		Sheet sheet = workbook.createSheet("Empresa Spring");
		
		MessageSourceAccessor mensajes =  getMessageSourceAccessor();
		
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		
		cell.setCellValue("Datos de la Empresa");
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue( " Id : " +empresa.getId() );
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue( " Nombre: " +empresa.getNombre() );
		
		sheet.createRow(3).createCell(0).setCellValue("Direccion: " + empresa.getDireccion());
		
	}
	

}
