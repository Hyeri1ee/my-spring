package hyeri.dbpool.tester.controller;

import hyeri.dbpool.config.DatasourceConfig;
import hyeri.dbpool.tester.TravelCandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/travel")
public class TravelCandController {
    private final TravelCandService travelCandService;
    private final DatasourceConfig datasourceConfig;

    public TravelCandController(TravelCandService travelCandService, DatasourceConfig datasourceConfig) {
        this.travelCandService = travelCandService;
        this.datasourceConfig = datasourceConfig;
    }

    @GetMapping("/info/insert")
    @ResponseBody
    public String testInsert() {
        long timeTaken = travelCandService.insertLATicket(100);
        String poolStats = datasourceConfig.getConnectionPoolStats(datasourceConfig.mongoClient());

        return "100개 데이터 삽입 시간: " + timeTaken + " ms\n" + "커넥션 풀 상태: " + poolStats;
    }
}
