package NotificationService.Entity.Responses;

import org.springframework.stereotype.Service;

@Service
public class SmsResponseDto {
    private String status;
    private String message;
    private String requestId;

    public SmsResponseDto() {
    }

    public SmsResponseDto(String status, String message, String requestId) {
        this.status = status;
        this.message = message;
        this.requestId = requestId;
    }

    public SmsResponseDto(String failed, String failedToSendSms) {
        this.status=failed;
        this.message=failedToSendSms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
