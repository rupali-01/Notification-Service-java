package NotificationService.Entity.Requests;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Table(name = "sms_requests")
@Component
public class SmsRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name ="request_id",unique = true)
    private  String requestId;

    public String getrequestId() {
        return requestId;
    }

    public void setrequestId(String requestId) {
        this.requestId = requestId;
    }

    private String phone_number;
    private String message;
    private String status;
    private String failure_code;
    private String failure_comments;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    public SmsRequest() {

    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public SmsRequest(int id, String phone_number, String message, String status, String failure_code, String failure_comments, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.phone_number = phone_number;
        this.message = message;
        this.status = status;
        this.failure_code = failure_code;
        this.failure_comments = failure_comments;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getFailure_code() {
        return failure_code;
    }

    public void setFailure_code(String failure_code) {
        this.failure_code = failure_code;
    }

    public String getFailure_comments() {
        return failure_comments;
    }

    public void setFailure_comments(String failure_comments) {
        this.failure_comments = failure_comments;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdatedAt(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }


    @Override
    public String toString() {
        return "sms_requests{" +
                "id=" + id +
                ", phone_number=" + phone_number +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", failure_code='" + failure_code + '\'' +
                ", failure_comments='" + failure_comments + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
