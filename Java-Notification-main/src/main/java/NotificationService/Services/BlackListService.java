package NotificationService.Services;

import java.util.Set;


public interface BlackListService {
    void addToBlacklist(String[] phoneNumber);

    void removeFromBlacklist(String[] phoneNumber);
    boolean isBlacklisted(String phoneNumber);

    Set<Object> getBlacklistedNumbers();
}
