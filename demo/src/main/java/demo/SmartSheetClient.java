package demo;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.SmartsheetFactory;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.SourceInclusion;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Component
public class SmartSheetClient {

    static {
        // These lines enable logging to the console
        System.setProperty("Smartsheet.trace.parts", "RequestBodySummary,ResponseBodySummary");
        System.setProperty("Smartsheet.trace.pretty", "false");
    }
    static final String ACCESS_TOKEN = "";


    public static void main(String[] args) {

        List<BDSSmartSheetDataModel> response = getSmartSheetData();
        System.out.println("response::->"+response);

        Attachment attachment = getSmartSheetAttachmentLink(response.get(0).getBdsSmartSheetId(),response.get(0).getBdsAttachmentDetails().get(0).getSmartSheetAttachment().getId());
        System.out.println("attachment::->"+attachment);
    }
    public static List<BDSSmartSheetDataModel> getSmartSheetData() {
        PagedResult<Sheet> sheets = null;
        PagedResult<Attachment> attachments = null;
        List<BDSSmartSheetDataModel> bdsSmartSheetDataModelList = null;

        Smartsheet smartsheet = SmartsheetFactory.createDefaultClient(ACCESS_TOKEN);
        // Specify pagination parameter 'includeAll'
        PaginationParameters parameters = new PaginationParameters()
                .setIncludeAll(true);

            try {
                // Specify 'include' parameter with value of "SOURCE", and 'includeAll' parameter with value of 'true'
                sheets = smartsheet.sheetResources().listSheets(EnumSet.of(SourceInclusion.SOURCE), parameters, null);
                System.out.println("Total Sheets Count:::->"+sheets.getTotalCount());
                if (sheets != null && sheets.getTotalCount() > 0 && !CollectionUtils.isEmpty(sheets.getData())) {
                    bdsSmartSheetDataModelList = new ArrayList<>();
                    for (Sheet sheetData : sheets.getData()) {
                        if (sheetData.getName()!=null && sheetData.getName().startsWith("BDS Overview - BDS-")) {
                            String bdsNumber = sheetData.getName().replace("BDS Overview - ","").trim();
                            System.out.println("Sheet Name::->"+sheetData.getName()+"::BDSNumber::"+bdsNumber+"::SheetID::"+sheetData.getId());
                            // Omit pagination
                            attachments = smartsheet.sheetResources().attachmentResources().listAttachments(
                                    sheetData.getId(),               // long sheetId
                                    parameters                             // PaginationParameters
                            );

                            String bdsAttachmentLink;
                            List<BDSAttachments> bdsAttachmentList = null;
                            if(attachments!=null && !CollectionUtils.isEmpty(attachments.getData())) {
                                bdsAttachmentList = new ArrayList();
                                for (Attachment attachment : attachments.getData()) {
                                    bdsAttachmentLink = "http://localhost:8080/downloadBDSAttachment?smartsheetid="+sheetData.getId()+"&&bdsAttachmentId="+attachment.getId();
                                    bdsAttachmentList.add(new BDSAttachments(bdsAttachmentLink, attachment));
                                    System.out.println("bdsAttachmentLink::->" + bdsAttachmentLink);
                                    System.out.println("attachment::->" + attachment.toString());
                                }
                            }
                            bdsSmartSheetDataModelList.add(new BDSSmartSheetDataModel(sheetData.getId(), bdsNumber, sheetData.getName(), bdsAttachmentList));
                            break;
                        }
                    }

                }
            } catch (SmartsheetException e) {
                throw new RuntimeException(e);
            }
            return bdsSmartSheetDataModelList;

    }

    public static Attachment getSmartSheetAttachmentLink(long sheetId, long attachmentId) {
        Smartsheet smartsheet = SmartsheetFactory.createDefaultClient(ACCESS_TOKEN);
        Attachment attachment;
        try {
             attachment = smartsheet.sheetResources().attachmentResources().getAttachment(
                     sheetId,       // long sheetId
                     attachmentId        // long attachmentId
            );
        } catch (SmartsheetException e) {
            throw new RuntimeException(e);
        }

        return attachment;
    }


}
