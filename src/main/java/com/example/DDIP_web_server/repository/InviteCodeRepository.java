//수정 후 삭제 요망
package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.InviteCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface InviteCodeRepository extends JpaRepository<InviteCode, Long> {
    // createdAt이 5분 이상 지난 초대 코드 삭제
    void deleteByCreatedAtBefore(LocalDateTime cutoffTime);

    Optional<InviteCode> findByCode(String code);
}
