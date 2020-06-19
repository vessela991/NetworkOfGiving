package NetworkOfGiving.Server.Features.Participant;

import NetworkOfGiving.Server.Data.Charity;
import NetworkOfGiving.Server.Data.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Integer> {
    List<Participant> findByCharityId(int id);
    List<Participant> findByUserId(int id);
}
