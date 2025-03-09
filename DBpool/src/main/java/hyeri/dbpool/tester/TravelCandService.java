package hyeri.dbpool.tester;

import hyeri.dbpool.tester.entity.TravelCand;
import hyeri.dbpool.tester.repository.TravelCandRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TravelCandService {

    private final TravelCandRepository travelCandRepository;
    private final int threadPoolSize;

    public TravelCandService(TravelCandRepository travelCandRepository, @Value("${spring.data.mongodb.connection-pool-size:1}") int threadPoolSize) {
        this.travelCandRepository = travelCandRepository;
        this.threadPoolSize = threadPoolSize;
    }

    public long insertLATicket(int amount) {

        ExecutorService executorService = Executors.newFixedThreadPool(this.threadPoolSize);

        List<Future<?>> futures = new ArrayList<>();

        Instant start = Instant.now();//=====================start time

        for (int i = 0; i < amount; i++) {
            final int index = i;
            futures.add(executorService.submit(() -> {
                TravelCand travelCand = new TravelCand("L.A. Ticket " + index);
                travelCandRepository.save(travelCand);
            }));
        }


        // 모든 작업이 끝날 때까지 대기
        futures.forEach(f -> {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Instant end = Instant.now(); //=====================end time


        executorService.shutdown(); // 스레드 풀 종료

        return Duration.between(start, end).toMillis();
    }
}
