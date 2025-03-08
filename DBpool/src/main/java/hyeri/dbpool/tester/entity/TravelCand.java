package hyeri.dbpool.tester.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "travel_cands")
public class TravelCand {
    @Id
    private String id;
    private String ticketName;

    public TravelCand(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getId() { return id; }
    public String getTicketName() { return ticketName; }
}
