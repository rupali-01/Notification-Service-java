package NotificationService.Repository.ElasticSearch;

import NotificationService.Entity.ElasticSearchDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticSearchRepo extends ElasticsearchRepository<ElasticSearchDoc, String> {
    @Query("{\"bool\":{\"must\":[{\"match\":{\"phone_number\":\"?0\"}},{\"range\":{\"created_at\":{\"gte\":\"?1\",\"lte\":\"?2\"}}}]}}")
    Page<ElasticSearchDoc> findSmsByStartAndEndDate(String phoneNumber, String startTime, String endTime, Pageable pageable);

    @Query("{\"match_phrase\":{\"message\":{\"query\":\"?0\"}}}")
    Page<ElasticSearchDoc> findSmsContainingText(String text, Pageable pageable);
}
