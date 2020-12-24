package innotech.com.Controladores;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import innotech.com.entididades.Empresa;
import innotech.com.miscelaneos.EmpresaExcelExporter;
import innotech.com.services.EmpresaServiceImp;


 
@Controller
public class EmpresaExcel {
 
   
    @Autowired
    private EmpresaServiceImp empresaServ;
     
     
    @GetMapping("/emp/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=empresas_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
       
        List<Empresa> listEmpresa = empresaServ.findAll();
         
        EmpresaExcelExporter excelExporter = new EmpresaExcelExporter(listEmpresa);
         
        excelExporter.export(response);    
    }  
 
}
