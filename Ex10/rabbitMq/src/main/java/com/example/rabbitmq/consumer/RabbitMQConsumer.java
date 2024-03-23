package com.example.rabbitmq.consumer;

import com.example.rabbitmq.Repository.StatisticsRepository;
import com.example.rabbitmq.model.ProductStatistics;
import com.example.rabbitmq.model.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
    private final StatisticsRepository statisticsRepository;

    @Autowired
    public RabbitMQConsumer(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(ProductStatistics productStatistics){
        LOGGER.info(String.format("%s", productStatistics.toString()));
        Optional<Statistics> existingStatistics = statisticsRepository.findByMaKho(productStatistics.getMaKho());
        int totalQuantity = productStatistics.getSoLuongSP();

        if (existingStatistics.isPresent()) {
            // Nếu tồn tại, cập nhật
            Statistics statistics = existingStatistics.get();
            statistics.setSoLuongSP(statistics.getSoLuongSP() + totalQuantity);
            statisticsRepository.save(statistics);
        } else {
            // Nếu không tồn tại, thêm
            Statistics newStatistics = new Statistics();
            newStatistics.setMaKho(productStatistics.getMaKho());
            newStatistics.setSoLuongSP(totalQuantity);
            newStatistics.setNgayThongKe(LocalDate.now());
            statisticsRepository.save(newStatistics);
        }

    }
}
