package com.example.rabbitmq.Repository;

import com.example.rabbitmq.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    // Các phương thức để thao tác với bảng Statistics
    Optional<Statistics> findByMaKho(String maKho);
}
