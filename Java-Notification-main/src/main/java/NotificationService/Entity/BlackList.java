package NotificationService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="BlackList")
public class BlackList {
    @Id
    private String phoneNumbers;
    public BlackList() {
    }

    public BlackList(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
