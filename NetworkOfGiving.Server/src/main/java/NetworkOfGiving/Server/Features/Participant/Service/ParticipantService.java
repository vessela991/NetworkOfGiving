package NetworkOfGiving.Server.Features.Participant.Service;

import NetworkOfGiving.Server.Data.Participant;
import NetworkOfGiving.Server.Features.Participant.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public List<Participant> getParticipantsByCharityId(int id){
        return participantRepository.findByCharityId(id);
    }
    public Participant participate(Participant participant){
        return participantRepository.save(participant);
    }
}
