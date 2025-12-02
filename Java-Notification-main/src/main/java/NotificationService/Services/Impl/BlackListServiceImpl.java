package NotificationService.Services.Impl;

import NotificationService.Entity.BlackList;
import NotificationService.Repository.Jpa.BlackListRepository;
import NotificationService.Services.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BlackListServiceImpl implements BlackListService {
private static final String BLACKLIST_KEY = "blacklist";
@Autowired
BlackListRepository blackListRepository;
    @Autowired
    private BlackListRepository blacklistedNumbers;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public BlackListServiceImpl(BlackListRepository blackListRepository, BlackListRepository blacklistedNumbers, RedisTemplate<String, Object> redisTemplate) {
        this.blackListRepository = blackListRepository;
        this.blacklistedNumbers = blacklistedNumbers;
        this.redisTemplate = redisTemplate;
    }

    public void addToBlacklist(String[] phoneNumber) {
        for(String s:phoneNumber) {
            if(!isBlacklisted(s)) {

                redisTemplate.opsForSet().add(BLACKLIST_KEY, s);

            }
            if(!blacklistedNumbers.existsById(s))
                blacklistedNumbers.save(new BlackList(s));
        }
    }

    public void removeFromBlacklist(String[] phoneNumber) {
        for(String s:phoneNumber) {
            if (isBlacklisted(s)) {
                redisTemplate.opsForSet().remove(BLACKLIST_KEY, s);
                blacklistedNumbers.deleteById(s);
            }
        }
    }

    public Set<Object> getBlacklistedNumbers() {
        return redisTemplate.opsForSet().members(BLACKLIST_KEY);
    }

    public boolean isBlacklisted(String phoneNumber) {
        if(Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(BLACKLIST_KEY, phoneNumber)))
            return true;
        return blackListRepository.existsById(phoneNumber);

    }


}
