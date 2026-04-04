package com.example.hirehub.service.schedulerService;

import com.example.hirehub.repository.JobPostingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobPostSchedulerServiceImpl implements JobPostingSchedulerService {
    JobPostingRepository jobPostingRepository;

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void checkAndExpireJob() {
        jobPostingRepository.updateExpiredJobs(LocalDateTime.now());
        log.info("Expired job postings have been updated by the system: " + LocalDateTime.now());
    }
}
