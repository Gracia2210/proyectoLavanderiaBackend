package pe.edu.usmp.lavanderia.app.service.impl;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import pe.edu.usmp.lavanderia.app.config.Auth;
import pe.edu.usmp.lavanderia.app.repository.ReporteRepository;
import pe.edu.usmp.lavanderia.app.request.ReporteRequest;
import pe.edu.usmp.lavanderia.app.response.ClienteResponse;
import pe.edu.usmp.lavanderia.app.response.ConfiguracionGlobalResponse;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ReporteResponse;
import pe.edu.usmp.lavanderia.app.service.ConfiguracionService;
import pe.edu.usmp.lavanderia.app.service.ReporteService;
import pe.edu.usmp.lavanderia.app.utils.Constantes;
import pe.edu.usmp.lavanderia.app.utils.UtilResource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private Auth auth;

    private final ReporteRepository reporteRepository;
    private final ConfiguracionService configuracionService;

    public ReporteServiceImpl(ReporteRepository reporteRepository, ConfiguracionService configuracionService) {
        this.reporteRepository = reporteRepository;
        this.configuracionService = configuracionService;
    }

    @Override
    public List<ReporteResponse> listaIngresoTotalesPeriodo(ReporteRequest datos) {
        return reporteRepository.listaIngresoTotalesPeriodo(datos);
    }

    @Override
    public List<ReporteResponse> listaServicioSolicitado(ReporteRequest datos) {
        return reporteRepository.listaServicioSolicitado(datos);
    }

    @Override
    public List<ReporteResponse> listaEstadosPagos(ReporteRequest datos) {
        return reporteRepository.listaEstadosPagos(datos);
    }

    @Override
    public List<ReporteResponse> listaEstadosPagosDetalle(ReporteRequest datos) {
        return reporteRepository.listaEstadosPagosDetalle(datos);
    }

    @Override
    public List<ReporteResponse> listaClientesFrecuentes(ReporteRequest datos) {
        return reporteRepository.listaClientesFrecuentes(datos);
    }

    @Override
    public List<ReporteResponse> listaMedioPagosMonto(ReporteRequest datos) {
        return reporteRepository.listaMedioPagosMonto(datos);
    }

    @Override
    public List<ReporteResponse> listaIngresoUsuario(ReporteRequest datos) {
        return reporteRepository.listaIngresoUsuario(datos);
    }

    @Override
    public ListModelResponse<ReporteResponse> listarDeudores(ReporteRequest datos) {
        ListModelResponse<ReporteResponse> resp = new ListModelResponse<>();
        List<ReporteResponse> lista = reporteRepository.listarDeudores(datos);
        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se han encontrado pagos pendientes");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se han encontrado pagos pendientes");
        }
        return resp;
    }

    @Override
    public String exportarReportesPDF(ReporteRequest datos) throws Exception {
        HashMap<String, Object> mapParametros = new HashMap<>();
        String logoBase64 = Base64.getEncoder()
                .encodeToString(IOUtils.toByteArray(getClass().getResourceAsStream("/static/images/logo.png")));
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fechaFormateada = formatoFecha.format(fechaActual);
        ConfiguracionGlobalResponse config= configuracionService.configuracionGlobal();
        mapParametros.put("logo",config.getImagen()!=null?Base64.getEncoder().encodeToString(config.getImagen()): logoBase64);
        mapParametros.put("nombreSistema", config.getNombre());
        mapParametros.put("fechaActual", fechaFormateada + " hrs");
        mapParametros.put("usuario", auth.nombreCompleto());
        mapParametros.put("titulo", "titulo");
        DateTimeFormatter formatoOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicioConvertida = LocalDate.parse(datos.getInicio(), formatoOriginal);
        LocalDate fechaFinConvertida = LocalDate.parse(datos.getFin(), formatoOriginal);
        String fechaInicioFormateada = fechaInicioConvertida.format(nuevoFormato);
        String fechaFinFormateada = fechaFinConvertida.format(nuevoFormato);
        mapParametros.put("fechaInicio", fechaInicioFormateada);
        mapParametros.put("fechaFin", fechaFinFormateada);
        List<Map<String, Object>> listDataMap = new ArrayList<>();
        String archivoReporte="";
        switch (datos.getReporte()){
            case "1":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaIngresoTotalesPeriodo(datos));
                archivoReporte="ReporteIngresoTotalPeriodo";
                break;
            case "2":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaServicioSolicitado(datos));
                archivoReporte="ReporteServiciosMasSolicitados";
                break;
            case "3":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaEstadosPagosDetalle(datos));
                archivoReporte="ReporteEstadoPagos";
                break;
            case "4":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaClientesFrecuentes(datos));
                archivoReporte="ReporteClientesFrecuentes";
                break;
            case "5":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaMedioPagosMonto(datos));
                archivoReporte="ReporteMetodoPagoMasIngreso";
                break;
            case "6":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaIngresoUsuario(datos));
                archivoReporte="ReporteUsuariosMasIngreso";
                break;
            case "7":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listarDeudores(datos));
                archivoReporte="ReporteDeudas";
                break;
        }
        InputStream jrxmlStream = new ClassPathResource("/static/"+archivoReporte+".jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listDataMap);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapParametros, dataSource);
        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.exportReport();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        String base64PDF = Base64.getEncoder().encodeToString(pdfBytes);
        return base64PDF;
    }
    public byte[] exportarReportesExcel(ReporteRequest datos) throws Exception {
        HashMap<String, Object> mapParametros = new HashMap<>();
        String logoBase64 = Base64.getEncoder()
                .encodeToString(IOUtils.toByteArray(getClass().getResourceAsStream("/static/images/logo.png")));
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fechaFormateada = formatoFecha.format(fechaActual);
        ConfiguracionGlobalResponse config= configuracionService.configuracionGlobal();
        mapParametros.put("logo",config.getImagen()!=null?Base64.getEncoder().encodeToString(config.getImagen()): logoBase64);
        mapParametros.put("nombreSistema", config.getNombre());
        mapParametros.put("fechaActual", fechaFormateada + " hrs");
        mapParametros.put("usuario", auth.nombreCompleto());
        mapParametros.put("titulo", "titulo");
        DateTimeFormatter formatoOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicioConvertida = LocalDate.parse(datos.getInicio(), formatoOriginal);
        LocalDate fechaFinConvertida = LocalDate.parse(datos.getFin(), formatoOriginal);
        String fechaInicioFormateada = fechaInicioConvertida.format(nuevoFormato);
        String fechaFinFormateada = fechaFinConvertida.format(nuevoFormato);
        mapParametros.put("fechaInicio", fechaInicioFormateada);
        mapParametros.put("fechaFin", fechaFinFormateada);

        List<Map<String, Object>> listDataMap = new ArrayList<>();

        String archivoReporte="";
        switch (datos.getReporte()){
            case "1":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaIngresoTotalesPeriodo(datos));
                archivoReporte="ReporteIngresoTotalPeriodo";
                break;
            case "2":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaServicioSolicitado(datos));
                archivoReporte="ReporteServiciosMasSolicitados";
                break;
            case "3":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaEstadosPagosDetalle(datos));
                archivoReporte="ReporteEstadoPagos";
                break;
            case "4":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaClientesFrecuentes(datos));
                archivoReporte="ReporteClientesFrecuentes";
                break;
            case "5":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaMedioPagosMonto(datos));
                archivoReporte="ReporteMetodoPagoMasIngreso";
                break;
            case "6":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listaIngresoUsuario(datos));
                archivoReporte="ReporteUsuariosMasIngreso";
                break;
            case "7":
                listDataMap = UtilResource.convertirDtoAMap(reporteRepository.listarDeudores(datos));
                archivoReporte="ReporteDeudas";
                break;
        }
        InputStream jrxmlStream = new ClassPathResource("/static/"+archivoReporte+".jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listDataMap);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapParametros, dataSource);
        JRXlsxExporter exporter = new JRXlsxExporter();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        return byteArrayOutputStream.toByteArray();
    }
}
