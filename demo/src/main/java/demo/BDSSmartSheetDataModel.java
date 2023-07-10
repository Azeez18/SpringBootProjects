package demo;


import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.PagedResult;

import java.util.List;

public class BDSSmartSheetDataModel {

    private Long bdsSmartSheetId;
    private String bdsNumber;

    private String bdsSmartSheetName;

    private List<BDSAttachments> bdsAttachmentDetails;

    public BDSSmartSheetDataModel(Long bdsSmartSheetId, String bdsNumber, String bdsSmartSheetName, List<BDSAttachments> bdsAttachmentDetails) {
        this.bdsSmartSheetId = bdsSmartSheetId;
        this.bdsNumber = bdsNumber;
        this.bdsSmartSheetName = bdsSmartSheetName;
        this.bdsAttachmentDetails = bdsAttachmentDetails;
    }

    public String getBdsNumber() {
        return bdsNumber;
    }

    public void setBdsNumber(String bdsNumber) {
        this.bdsNumber = bdsNumber;
    }

    public Long getBdsSmartSheetId() {
        return bdsSmartSheetId;
    }

    public void setBdsSmartSheetId(Long bdsSmartSheetId) {
        this.bdsSmartSheetId = bdsSmartSheetId;
    }

    public String getBdsSmartSheetName() {
        return bdsSmartSheetName;
    }

    public void setBdsSmartSheetName(String bdsSmartSheetName) {
        this.bdsSmartSheetName = bdsSmartSheetName;
    }

    public List<BDSAttachments> getBdsAttachmentDetails() {
        return bdsAttachmentDetails;
    }

    public void setBdsAttachmentDetails(List<BDSAttachments> bdsAttachmentDetails) {
        this.bdsAttachmentDetails = bdsAttachmentDetails;
    }
}
