package NotificationService.Controller;

import NotificationService.Entity.ElasticSearchDoc;
import NotificationService.Repository.ElasticSearch.ElasticSearchRepo;
import NotificationService.Utils.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/v1/sms")
@Slf4j
public class ElasticSearchController {
     ElasticSearchRepo elasticSearchRepo;
    @Autowired
    public ElasticSearchController(ElasticSearchRepo elasticSearchRepo) {
        this.elasticSearchRepo = elasticSearchRepo;
    }

        @GetMapping("/findByTimeRange")
        public ResponseEntity<?> findByPhoneNumberAndTimeRange(@RequestParam String phoneNumber,
                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
            Page<ElasticSearchDoc> result = null;
            try {
                Pageable pageable = PageRequest.of(page, size);
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                String startTimestamp = startTime.format(formatter);
                String endTimestamp = endTime.format(formatter);
                result = elasticSearchRepo.findSmsByStartAndEndDate(phoneNumber, startTimestamp, endTimestamp, pageable);
                if (result.isEmpty()) {
                    log.error("no documet is found with given params");
                    ErrorResponse errorResponse = new ErrorResponse("INVALID REQUEST","DOCUMENT NOT FOUND");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
                }
                return ResponseEntity.ok(result.getContent());
            }catch (Exception e) {
            log.error(e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("INVALID REQUEST","INTERNAL_SERVER_ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping("/findByText")
    public ResponseEntity<?> searchSmsMessages(@RequestParam String searchText, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ElasticSearchDoc> smsMessages = elasticSearchRepo.findSmsContainingText(searchText,pageable);
            if(smsMessages==null)
                throw new IOException("not found");
            if (smsMessages.isEmpty()) {
                log.error("no document is found with given params");
                ErrorResponse errorResponse = new ErrorResponse("INVALID REQUEST","DOCUMENT NOT FOUND");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            return ResponseEntity.ok(smsMessages.getContent());
        } catch (IOException e) {
            log.error(e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("INVALID REQUEST","INTERNAL SERVER ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}