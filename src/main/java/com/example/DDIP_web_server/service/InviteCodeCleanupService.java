package com.example.DDIP_web_server.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DDIP_web_server.repository.InviteCodeRepository;

import java.time.LocalDateTime;

@Service
public class InviteCodeCleanupService {

    private final InviteCodeRepository inviteCodeRepository;

    public InviteCodeCleanupService(InviteCodeRepository inviteCodeRepository) {
        this.inviteCodeRepository = inviteCodeRepository;
    }

    @Scheduled(cron = "0 */5 * * * ?")  // 5분마다 실행
    @Transactional
    public void deleteExpiredInviteCodes() {
        LocalDateTime expirationThreshold = LocalDateTime.now().minusMinutes(5);  // 5분 지난 코드 삭제
        inviteCodeRepository.deleteByCreatedAtBefore(expirationThreshold);
        System.out.println("Expired invite codes deleted.");
    }
}
