package NotificationService.Controller;

import NotificationService.Entity.Requests.BlackListRequestDto;
import NotificationService.Services.BlackListService;
import NotificationService.Utils.ErrorResponse;
import NotificationService.Utils.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1")
@Slf4j
public class BlackListController {
    @Autowired
    private BlackListService blackListService;

    @Autowired
    public BlackListController(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    @PostMapping("/blacklist")
    public ResponseEntity<Object> addToBlacklist(@RequestBody BlackListRequestDto blacklistRequestDto) {
        try {
            if(blacklistRequestDto.getPhoneNumbers().length==0)
                throw new BadRequestException("Provide Numbers");
            blackListService.addToBlacklist(blacklistRequestDto.getPhoneNumbers());
            log.info("successfully blacklisted numbers");
            return ResponseEntity.ok(new SuccessResponse("Successfully blacklisted"));
        } catch (BadRequestException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse("BAD_REQUEST", "Provide mobile numbers"));
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorResponse("INTERNAL_ERROR", "An internal error occurred"));
        }
    }
    @DeleteMapping("/blacklist")
    public ResponseEntity<Object> removeFromBlacklist(@RequestBody BlackListRequestDto blacklistRequestDto) {
        try {
            blackListService.removeFromBlacklist(blacklistRequestDto.getPhoneNumbers());
            log.info("successfully whitelisted numbers");
            return ResponseEntity.ok(new SuccessResponse("Successfully whitelisted"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorResponse("INTERNAL_ERROR", "An internal error occurred"));
        }
    }

    @GetMapping("/blacklist")
    public ResponseEntity<Object> getBlacklistedNumbers() {
        try {

            Set<Object> responseDto = blackListService.getBlacklistedNumbers();
            log.info("successfully fetched blacklisted numbers");
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorResponse("INTERNAL_ERROR", "An internal error occurred"));
        }
    }

}
