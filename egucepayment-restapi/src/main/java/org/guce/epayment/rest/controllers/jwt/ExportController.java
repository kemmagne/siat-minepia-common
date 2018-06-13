package org.guce.epayment.rest.controllers.jwt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.transfer.entities.TransferOrder;
import org.guce.epayment.transfer.services.TransferOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tadzotsa
 */
@RestController
@RequestMapping("jwt/report")
public class ExportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    private CoreService coreService;
    @Autowired
    private TransferOrderService transferOrderService;

    @RequestMapping(path = "transfer/orders/pdf/{type}/{period}", method = RequestMethod.GET)
    public void exportTransferOrdersPdf(@PathVariable("type") int type, @PathVariable("period") int period,
            @RequestHeader(RestConstants.LOGIN) String userLogin, @RequestHeader(RestConstants.LOCALE) String locale,
            HttpServletResponse response) {
        export(type, period, userLogin, locale, response, RestConstants.MEDIA_TYPE_PDF, "export.pdf", true);
    }

    @RequestMapping(path = "transfer/orders/excel/{type}/{period}", method = RequestMethod.GET)
    public void exportTransferOrdersExcel(@PathVariable("type") int type, @PathVariable("period") int period,
            @RequestHeader(RestConstants.LOGIN) String userLogin, @RequestHeader(RestConstants.LOCALE) String locale,
            HttpServletResponse response) {
        export(type, period, userLogin, locale, response, RestConstants.MEDIA_TYPE_EXCEL, "export.xlsx", false);
    }

    private void export(int type, int period, String userLogin, String locale, HttpServletResponse response,
            String contentType, String fileName, boolean pdf) {

        try {
            final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();
            final Partner userPartner = connectedUser.getPartner();
            final Partner partnerParent = userPartner.getParent();
            final String bankCode = partnerParent == null ? userPartner.getCode() : partnerParent.getCode();
            final List<TransferOrder> transferOrders = (List<TransferOrder>) transferOrderService
                    .findTransferOrdersPeriodically(type, period, bankCode, 0, 0, false);

            response.reset();
            response.resetBuffer();
            response.setContentType(contentType);
            response.setHeader(RestConstants.CONTENT_DISPOSITION, String.format(RestConstants.FILE_DOWNLOAD_HEADER_VALUE_FORMAT, fileName));

            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            if (pdf) {
                printFromJasper(transferOrders, locale, outputStream);
            } else {
                printExcel(transferOrders, locale, outputStream);
            }
            outputStream.close();
            response.setContentLength(outputStream.size());

            final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            IOUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
            response.flushBuffer();
        } catch (JRException | IOException ex) {
            LOGGER.error(null, ex);
            try {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
            } catch (IOException ex1) {
                LOGGER.error(null, ex1);
            }
        }
    }

    private void printFromJasper(List<TransferOrder> transferOrders, String locale, OutputStream output) throws JRException, IOException {

        final JRDataSource source = new JRBeanCollectionDataSource(transferOrders);
        final Map<String, Object> params = new HashMap<>();

        params.put(JRParameter.REPORT_LOCALE, locale);
        params.put(JRParameter.REPORT_RESOURCE_BUNDLE, RestUtils.getResourceBundle(locale));

        final JasperPrint jasperPrint = JasperFillManager.fillReport(new ClassPathResource("print/periodic_to.jasper").getInputStream(), params, source);

        JasperExportManager.exportReportToPdfStream(jasperPrint, output);
    }

    private void printExcel(List<TransferOrder> transferOrders, String locale, OutputStream output) throws IOException {

        final XSSFWorkbook workbook = new XSSFWorkbook();
        final XSSFSheet sheet = workbook.createSheet("Etats Ordres de virement");
        Row row;
        Cell cell;
        int rowNum = 0, colNum = 0;
        final ResourceBundle resourceBundle = RestUtils.getResourceBundle(locale);

        // titre
        final CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();

        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        headerCellStyle.setFont(font);
        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);

        row = sheet.createRow(rowNum++);
        // n° ligne
        cell = row.createCell(colNum++);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue("#");
        // reference
        cell = row.createCell(colNum++);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(resourceBundle.getString("reference"));
        // mode de paiment
        cell = row.createCell(colNum++);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(resourceBundle.getString("payment.mode"));
        // contribuable
        cell = row.createCell(colNum++);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(resourceBundle.getString("tax.payer"));
        // date
        cell = row.createCell(colNum++);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue("Date");
        // Montant
        cell = row.createCell(colNum++);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(resourceBundle.getString("amount"));
        //
        CellStyle bodyCellStyle = sheet.getWorkbook().createCellStyle();
        font = sheet.getWorkbook().createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(false);
        bodyCellStyle.setFont(font);
        bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag(locale));
        for (TransferOrder transferOrder : transferOrders) {
            // init
            row = sheet.createRow(rowNum++);
            colNum = 0;
            //
            cell = row.createCell(colNum++);
            cell.setCellStyle(bodyCellStyle);
            cell.setCellValue(rowNum - 1);
            //
            cell = row.createCell(colNum++);
            cell.setCellStyle(bodyCellStyle);
            cell.setCellValue(transferOrder.getReference());
            //
            cell = row.createCell(colNum++);
            cell.setCellStyle(bodyCellStyle);
            cell.setCellValue(transferOrder.getMode().getLabel());
            //
            cell = row.createCell(colNum++);
            cell.setCellStyle(bodyCellStyle);
            cell.setCellValue(transferOrder.getTaxPayer().getName());
            //
            cell = row.createCell(colNum++);
            cell.setCellStyle(bodyCellStyle);
            cell.setCellValue(transferOrder.getValidationDate().format(DateTimeFormatter.ofPattern(resourceBundle.getString("date.time.format"))));
            //
            cell = row.createCell(colNum++);
            cell.setCellStyle(bodyCellStyle);
            cell.setCellValue(numberFormat.format(transferOrder.getAmount()));
        }
        for (int index = 0; index < colNum; index++) {
            sheet.autoSizeColumn(index);
        }
        workbook.write(output);
    }

}
