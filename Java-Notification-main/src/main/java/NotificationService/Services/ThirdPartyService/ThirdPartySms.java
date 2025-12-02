package NotificationService.Services.ThirdPartyService;

import NotificationService.Entity.ElasticSearchDoc;
import NotificationService.Entity.Requests.DocumentMapper;
import NotificationService.Entity.Requests.SmsRequest;
import NotificationService.Entity.Requests.SmsRequestDto;
import NotificationService.Repository.Jpa.SmsRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Slf4j
public class ThirdPartySms {
    @Autowired
    SmsRequest smsRequest;
    @Autowired
    SmsRequestRepository smsRequestRepository;
    @Autowired
    ElasticsearchRepository elasticsearchRepository;
    @Value("${api.imiconnect.url}")
    private String thirdPartyApiUrl;
    @Value("${api_key_value}")
    private String thirdPartyApiKey;
   @KafkaListener(topics = "Notifications" , groupId = "sms")
   public void listener(String Id)
   {
       int id=Integer.parseInt(Id);
       Optional<SmsRequest> optionalSmsRequest = smsRequestRepository.findById(id);

       if (optionalSmsRequest.isPresent()) {
           SmsRequest smsRequest = optionalSmsRequest.get();
           sendSmsViaThirdParty(smsRequest);
           smsRequestRepository.save(smsRequest);
           DocumentMapper documentMapper=new DocumentMapper();
           ElasticSearchDoc elasticSearchDoc=documentMapper.toDocument(smsRequest);
//           if(smsRequest.getStatus().equals("SENT"))
           elasticsearchRepository.save(elasticSearchDoc);
           log.info(smsRequest.toString());
       }
   }
    public void sendSmsViaThirdParty(SmsRequest smsRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Key", thirdPartyApiKey);

            SmsRequestDto smsRequestDto = new SmsRequestDto();
            smsRequestDto.setPhoneNumber(smsRequest.getPhone_number());
            smsRequestDto.setMessage(smsRequest.getMessage());

            HttpEntity<SmsRequestDto> requestEntity = new HttpEntity<>(smsRequestDto, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    thirdPartyApiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
              log.info("successfully sent from 3rd party");
                smsRequest.setStatus("SENT");
               smsRequest.setUpdatedAt(LocalDateTime.now());
            } else {
                log.error("not working at 3rd party");
                smsRequest.setUpdatedAt(LocalDateTime.now());
                smsRequest.setFailure_code(response.getStatusCode().toString());
                smsRequest.setFailure_comments("not working at 3rd party");
                smsRequest.setStatus("FAILED");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            smsRequest.setUpdatedAt(LocalDateTime.now());
            smsRequest.setFailure_code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            smsRequest.setFailure_comments(e.getLocalizedMessage());
            smsRequest.setStatus("FAILED");
        }
    }


}
