package com.problem.protfolio.repo;

import com.problem.protfolio.message.SecurityEvent;
import com.problem.protfolio.schema.SecurityStaticData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityEventRepo extends JpaRepository<SecurityEvent, String> {
}
