package NotificationService.Services.Impl;

import NotificationService.Entity.Requests.SmsRequest;
import NotificationService.Entity.Requests.SmsRequestDto;
import NotificationService.Entity.Responses.SmsResponseDto;
import NotificationService.Repository.Jpa.SmsRequestRepository;
import NotificationService.Services.BlackListService;
import NotificationService.Services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SmsRequestRepository smsRequestRepository;
    @Autowired
    private KafkaTemplate<String, String > kafkaTemplate;
    @Autowired
    private BlackListService blacklistService;
    @Autowired
    public NotificationServiceImpl(SmsRequestRepository smsRequestRepository, BlackListService blacklistService) {
        this.smsRequestRepository = smsRequestRepository;
        this.blacklistService = blacklistService;
    }
    public SmsResponseDto sendSMS(SmsRequestDto requestDto) {
       
        if (blacklistService.isBlacklisted(requestDto.getPhoneNumber())) {
            return new SmsResponseDto("BLACKLISTED", "Phone number is blacklisted");
        }
         SmsRequest savedRequest = saveSmsRequest(requestDto);
        sendNotificationToKafka(savedRequest);
        return new SmsResponseDto("SUCCESS", "Successfully Sent", savedRequest.getrequestId());
    }
    private void sendNotificationToKafka(SmsRequest smsRequest) {
        try {
            String notificationMessage = "New SMS: " + smsRequest.getMessage();
            System.out.println(smsRequest.getId());
            kafkaTemplate.send("Notifications",Integer.valueOf(smsRequest.getId()).toString());
        } catch (Exception e) {
           log.info(e.getMessage());
        }
    }
    @Override
    public Optional<SmsRequest> GetSmsByRequestId(String id) {
        return smsRequestRepository.findSmsRequestByrequestId(id);
    }
    @Override
    public Boolean existsById(int id) {
        return smsRequestRepository.existsById(id);
    }

    private SmsRequest saveSmsRequest(SmsRequestDto requestDto) {
        SmsRequest smsRequest = new SmsRequest();
        UUID uuid = UUID.randomUUID();
        smsRequest.setrequestId(uuid.toString());
        smsRequest.setPhoneNumber(requestDto.getPhoneNumber());
        smsRequest.setMessage(requestDto.getMessage());
        smsRequest.setStatus("PENDING"); // Set an initial status
        smsRequest.setCreatedAt(LocalDateTime.now());
        return smsRequestRepository.save(smsRequest);
    }

    private void updateSmsRequestStatus(SmsRequest smsRequest, boolean smsSent) {
        if (smsSent) {
            smsRequest.setStatus("SENT");
        } else {
            smsRequest.setStatus("FAILED");
            smsRequest.setFailure_code("failed at 3rd party");
            smsRequest.setFailure_comments("check the api ");
        }
        smsRequest.setUpdatedAt(LocalDateTime.now());
        smsRequestRepository.save(smsRequest);
    }
}
