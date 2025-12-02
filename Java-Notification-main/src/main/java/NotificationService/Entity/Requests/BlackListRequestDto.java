package NotificationService.Entity.Requests;

import org.springframework.stereotype.Service;

@Service
public class BlackListRequestDto {
    private String[] phoneNumbers;

    public BlackListRequestDto(String[] phonenumbers) {
        this.phoneNumbers = phonenumbers;
    }

    public BlackListRequestDto() {
    }
    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phonenumbers) {
        this.phoneNumbers = phonenumbers;
    }
}
