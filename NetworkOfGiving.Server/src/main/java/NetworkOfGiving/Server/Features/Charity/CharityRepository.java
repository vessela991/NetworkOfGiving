package NetworkOfGiving.Server.Features.Charity;

import NetworkOfGiving.Server.Data.Charity;
import NetworkOfGiving.Server.Data.Participant;
import NetworkOfGiving.Server.Data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository
public interface CharityRepository extends JpaRepository<Charity,Integer> {
    @Transactional
    int deleteByIdAndUser(@Param("id") int id, @Param("user") User user);
    Charity findByName(String name);
    Charity findByIdAndUser(@Param("id") int id, @Param("user") User user);
}
