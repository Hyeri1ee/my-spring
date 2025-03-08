package hyeri.dbpool.tester.repository;

import hyeri.dbpool.tester.entity.TravelCand;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Document(collection = "travel_cands")
public interface TravelCandRepository extends MongoRepository<TravelCand, String> {

}
