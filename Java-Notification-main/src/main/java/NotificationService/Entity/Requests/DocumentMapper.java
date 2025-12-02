package NotificationService.Entity.Requests;

import NotificationService.Entity.ElasticSearchDoc;
import NotificationService.Entity.Requests.SmsRequest;

public class DocumentMapper {
    public ElasticSearchDoc toDocument(SmsRequest smsRequest) {
        if (smsRequest == null) {
            return null;
        }
        String id =  smsRequest.getrequestId();
        String phoneNumber = smsRequest.getPhone_number();
        String message = smsRequest.getMessage();
        String status = smsRequest.getStatus();
        String failureCode = smsRequest.getFailure_code();
        String failureComments = smsRequest.getFailure_comments();
        String createdAt = smsRequest.getCreated_at().toString(); // Assuming createdAt is already in ISO 8601 format
        String updatedAt = smsRequest.getUpdated_at().toString(); // Assuming updatedAt is already in ISO 8601 format
        return new ElasticSearchDoc(id, phoneNumber, message, status, failureCode, failureComments, createdAt, updatedAt);
    }
}
