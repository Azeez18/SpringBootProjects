package demo;


import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.PagedResult;

public class BDSSmartSheetDataModel {

    private Long bdsSmartSheetId;
    private String bdsNumber;

    private String bdsSmartSheetName;

    private PagedResult<Attachment> bdsAttachmentDetails;

    public BDSSmartSheetDataModel(Long bdsSmartSheetId, String bdsNumber, String bdsSmartSheetName, PagedResult<Attachment> bdsAttachmentDetails) {
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

    public PagedResult<Attachment> getBdsAttachmentDetails() {
        return bdsAttachmentDetails;
    }

    public void setBdsAttachmentDetails(PagedResult<Attachment> bdsAttachmentDetails) {
        this.bdsAttachmentDetails = bdsAttachmentDetails;
    }
}
