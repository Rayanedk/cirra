package com.example.test.repository;

import com.example.test.model.FooterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterInfoRepository extends JpaRepository<FooterInfo, Long> {
}
