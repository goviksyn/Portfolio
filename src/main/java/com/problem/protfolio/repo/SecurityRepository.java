package com.problem.protfolio.repo;

import com.problem.protfolio.schema.SecurityStaticData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityStaticData, String> {
}
