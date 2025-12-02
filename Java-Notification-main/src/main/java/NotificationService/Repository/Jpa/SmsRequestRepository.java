package NotificationService.Repository.Jpa;

import NotificationService.Entity.Requests.SmsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsRequestRepository extends JpaRepository<SmsRequest,Integer> {
    Optional<SmsRequest> findSmsRequestByrequestId(String requestid);

}
