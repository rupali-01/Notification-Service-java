package NotificationService.Entity.Responses;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class BlackListResponseDto {
    private Set<Object> data;

    public BlackListResponseDto(Set<Object> data) {
        this.data = data;
    }

    public BlackListResponseDto() {
    }

    public Set<Object> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = Collections.singleton(data);
    }
}
