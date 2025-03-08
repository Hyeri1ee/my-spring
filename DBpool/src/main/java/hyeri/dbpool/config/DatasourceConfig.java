package hyeri.dbpool.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.internal.MongoClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {

    // 커넥션 풀 크기를 변수로 저장
    @Value("${spring.data.mongodb.connection-pool-size:10}")
    private int maxConnectionPoolSize;

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://root:1234@travel.mu8gr.mongodb.net/travel");

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder ->
                        builder.maxSize(maxConnectionPoolSize) // 커넥션 풀 크기 설정
                )
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public int mongoConnectionPoolSize() {
        // 커넥션 풀 크기를 반환
        return maxConnectionPoolSize;
    }

    // 커넥션 풀의 상태를 반환하는 메서드
    public String getConnectionPoolStats(MongoClient mongoClient) {
        if (mongoClient instanceof MongoClientImpl) {
            MongoClientImpl clientImpl = (MongoClientImpl) mongoClient;
            // 커넥션 풀 상태 정보 조회
            return clientImpl.getClusterDescription().toString();
        }
        return "커넥션 풀 상태를 조회할 수 없습니다.";
    }
}
