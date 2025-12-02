package NotificationService.Entity.Requests;

public class StartAndEnd {

    private String startDate;
    private String endDate;
    private String phoneNumber;
    private int page;
    private int size;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
// Constructors, getters, and setters

    public StartAndEnd() {
        // Default constructor
    }

    public StartAndEnd(String startDate, String endDate, String phoneNumber, int page, int size) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.phoneNumber = phoneNumber;
        this.page = page;
        this.size = size;
    }


// Getters and setters...

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
