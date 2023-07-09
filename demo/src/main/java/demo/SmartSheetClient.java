package demo;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.SmartsheetFactory;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.SourceInclusion;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
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

    public static void main(String[] args) {
        getSmartSheetData();
    }
    public static void getSmartSheetData() {

        Smartsheet smartsheet = SmartsheetFactory.createDefaultClient();

// Sample 2: Specify pagination parameter 'includeAll'
        PaginationParameters parameters = new PaginationParameters()
                .setIncludeAll(true);

// Specify 'include' parameter with value of "SOURCE", and 'includeAll' parameter with value of 'true'
            PagedResult<Sheet> sheets = null;
            PagedResult<Attachment> attachments = null;
            List<BDSSmartSheetDataModel> bdsSmartSheetDataModelList = null;
            try {
                sheets = smartsheet.sheetResources().listSheets(EnumSet.of(SourceInclusion.SOURCE), parameters, null);
                System.out.println("Total Sheets Count:::->"+sheets.getTotalCount());
                if (sheets != null && sheets.getTotalCount() > 0 && !CollectionUtils.isEmpty(sheets.getData())) {
                    bdsSmartSheetDataModelList = new ArrayList<>();
                    for (Sheet sheetData : sheets.getData()) {
                        if (sheetData.getName()!=null && sheetData.getName().startsWith("BDS Overview - BDS-")) {
                            System.out.println("Sheet Name::->"+sheetData.getName());
                            String bdsNumber = sheetData.getName().replace("BDS Overview - ","").trim();

                            // Omit pagination
                            attachments = smartsheet.sheetResources().attachmentResources().listAttachments(
                                    sheetData.getId(),               // long sheetId
                                    parameters                             // PaginationParameters
                            );
                            /*if(attachments!=null && !CollectionUtils.isEmpty(attachments.getData())) {
                                for (Attachment attachment : attachments.getData()) {
                                    System.out.println("attachment::->" + attachment.toString());
                                }
                            }*/
                            bdsSmartSheetDataModelList.add(new BDSSmartSheetDataModel(sheetData.getId(), bdsNumber, sheetData.getName(), attachments));
                        }
                    }

                }
            } catch (SmartsheetException e) {
                throw new RuntimeException(e);
            }

    }
}
