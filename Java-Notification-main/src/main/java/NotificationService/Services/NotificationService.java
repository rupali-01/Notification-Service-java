package NotificationService.Services;

import NotificationService.Entity.Requests.SmsRequest;
import NotificationService.Entity.Requests.SmsRequestDto;
import NotificationService.Entity.Responses.SmsResponseDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NotificationService {
    SmsResponseDto sendSMS(SmsRequestDto requestDto);
    Optional<SmsRequest> GetSmsByRequestId(String id);
    Boolean existsById(int id);
}
