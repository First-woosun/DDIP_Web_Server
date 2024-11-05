// InviteCodeService.java
package com.example.DDIP_web_server.service;

import com.example.DDIP_web_server.entity.InviteCode;
import com.example.DDIP_web_server.repository.InviteCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InviteCodeService {

    private final InviteCodeRepository inviteCodeRepository;

    public InviteCodeService(InviteCodeRepository inviteCodeRepository) {
        this.inviteCodeRepository = inviteCodeRepository;
    }

    public InviteCode saveInviteCode(String code) {
        InviteCode inviteCode = new InviteCode(code);
        return inviteCodeRepository.save(inviteCode);
    }

    public Optional<InviteCode> findInviteCode(String code) {
        return inviteCodeRepository.findByCode(code);
    }
}
