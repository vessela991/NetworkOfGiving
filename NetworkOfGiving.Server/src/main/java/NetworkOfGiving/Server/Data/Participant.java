package NetworkOfGiving.Server.Data;

import com.sun.istack.NotNull;
import org.aspectj.weaver.loadtime.Agent;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "participant")
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name="charityId", nullable=false)
    private Charity charity;

    public Participant() {
    }

    public Participant(User user, Charity charity) {
        this.user = user;
        this.charity = charity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Charity getCharity() {
        return charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }
}
