package NotificationService.Controller;

import NotificationService.Entity.Requests.SmsRequest;
import NotificationService.Entity.Requests.SmsRequestDto;
import NotificationService.Entity.Responses.SmsResponseDto;
import NotificationService.Repository.Jpa.SmsRequestRepository;
import NotificationService.Services.NotificationService;
import NotificationService.Utils.ErrorResponse;
import NotificationService.Validator.PhoneNumberValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Slf4j
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SmsRequestRepository smsRequestRepository;
   @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @PostMapping("/sms/send")
    public ResponseEntity<Object> sendSMS(@RequestBody SmsRequestDto requestDto) {
        try {
            PhoneNumberValidator.validate(requestDto);
            SmsResponseDto responseDto = notificationService.sendSMS(requestDto);
            return ResponseEntity.ok(responseDto);
        }  catch (IllegalArgumentException e)
        {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse("INVALID_REQUEST","phone_number is mandatory"));
        }
    }

    @GetMapping("/sms/{requestid}")
    public ResponseEntity<?> getSmsById(@PathVariable String requestid)
    {
   try {
       Optional<SmsRequest> smsRequest = notificationService.GetSmsByRequestId(requestid);
       if (smsRequest.isEmpty())
        throw new BadRequestException("Id Not Found!!");
       else {;
           return ResponseEntity.status(200).body(smsRequest.get());
       }
   }catch (BadRequestException e)
   {
       log.error(e.getMessage());
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
               new ErrorResponse("INVALID_REQUEST", "request_id not found"));
   }

    }
}
