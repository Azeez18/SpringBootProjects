package demo;

import com.smartsheet.api.models.Attachment;

public class BDSAttachments {
    private String bdsAttachmentLink;

    private Attachment smartSheetAttachment;

    public BDSAttachments(String bdsAttachmentLink, Attachment smartSheetAttachment) {
        this.bdsAttachmentLink = bdsAttachmentLink;
        this.smartSheetAttachment = smartSheetAttachment;
    }

    public String getBdsAttachmentLink() {
        return bdsAttachmentLink;
    }

    public void setBdsAttachmentLink(String bdsAttachmentLink) {
        this.bdsAttachmentLink = bdsAttachmentLink;
    }

    public Attachment getSmartSheetAttachment() {
        return smartSheetAttachment;
    }

    public void setSmartSheetAttachment(Attachment smartSheetAttachment) {
        this.smartSheetAttachment = smartSheetAttachment;
    }
}
