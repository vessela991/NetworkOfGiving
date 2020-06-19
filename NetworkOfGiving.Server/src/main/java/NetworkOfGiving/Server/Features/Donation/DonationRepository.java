package NetworkOfGiving.Server.Features.Donation;

import NetworkOfGiving.Server.Data.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Integer> {
    List<Donation> findByCharityId(int id);
    List<Donation> findByUserId(int id);
}
