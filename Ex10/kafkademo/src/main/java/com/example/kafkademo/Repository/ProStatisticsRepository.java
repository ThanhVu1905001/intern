package com.example.kafkademo.Repository;


import com.example.kafkademo.playload.ProStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProStatisticsRepository extends JpaRepository<ProStatistics, Long> {
    // Các phương thức để thao tác với bảng Statistics
    Optional<ProStatistics> findByMaKho(String maKho);
}
