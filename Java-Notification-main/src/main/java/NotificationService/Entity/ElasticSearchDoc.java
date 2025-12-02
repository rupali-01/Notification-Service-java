package NotificationService.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

@Component
@Document(indexName = "sms_service-doc")
public class ElasticSearchDoc {
    @Id
    private String id;
    private String phone_number;
    private String message;
    private String status;
    private String failure_code;
    private String failure_comments;
    private String created_at;
    private String updated_at;
    public ElasticSearchDoc() {
    }

    public ElasticSearchDoc(String id, String phone_number, String message, String status, String failure_code, String failure_comments, String created_at, String updated_at) {
        this.id = id;
        this.phone_number = phone_number;
        this.message = message;
        this.status = status;
        this.failure_code = failure_code;
        this.failure_comments = failure_comments;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public ElasticSearchDoc(String id, String phone_number, String message, String status, String failure_code, String failure_comments, String created_at) {
        this.id = id;
        this.phone_number = phone_number;
        this.message = message;
        this.status = status;
        this.failure_code = failure_code;
        this.failure_comments = failure_comments;
        this.created_at = created_at;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdatedAt(String updated_at) {
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
