package com.example.kafkademo.kafka;

import com.example.kafkademo.Repository.ProStatisticsRepository;
import com.example.kafkademo.playload.ProStatistics;
import com.example.kafkademo.playload.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private final ProStatisticsRepository proStatisticsRepository;

    public KafkaConsumer(ProStatisticsRepository proStatisticsRepository) {
        this.proStatisticsRepository = proStatisticsRepository;
    }

    @KafkaListener(topics = "statistics",groupId = "myGroup")
    public void consume(Statistics statistics){

        LOGGER.info(String.format("%s",statistics.toString()));

        Optional<ProStatistics> existingStatistics = proStatisticsRepository.findByMaKho(statistics.getMaKho());
        int totalQuantity = statistics.getSoLuongSP();

        if (existingStatistics.isPresent()) {
            // Nếu tồn tại, cập nhật
            ProStatistics proStatistics = existingStatistics.get();
            proStatistics.setSoLuongSP(proStatistics.getSoLuongSP() + totalQuantity);
            proStatisticsRepository.save(proStatistics);
        } else {
            // Nếu không tồn tại, thêm
            ProStatistics newProStatistics = new ProStatistics();
            newProStatistics.setMaKho(statistics.getMaKho());
            newProStatistics.setSoLuongSP(totalQuantity);
            newProStatistics.setNgayThongKe(LocalDate.now());
            proStatisticsRepository.save(newProStatistics);
        }
    }
}
