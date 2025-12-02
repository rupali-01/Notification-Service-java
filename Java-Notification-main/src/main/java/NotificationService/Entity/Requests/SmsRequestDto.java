package NotificationService.Entity.Requests;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SmsRequestDto {
        private String phoneNumber;
        private String message;
        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getMessage() {
            return message;
        }

    public SmsRequestDto() {
    }
    public SmsRequestDto(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public void setMessage(String message) {
            this.message = message;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SmsRequestDto that = (SmsRequestDto) o;
            return Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(phoneNumber, message);
        }
    }


