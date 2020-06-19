package NetworkOfGiving.Server.Data;

import com.sun.istack.NotNull;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="donation")
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal amount= new BigDecimal("0.0");

    @ManyToOne
    @NotNull
    @JoinColumn(name="userId", nullable=false)
    private User user;


    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "charityId", nullable = false)
    private Charity charity;

    public Donation() {
    }

    public Donation(BigDecimal amount, User user, Charity charity) {
        this.amount = amount;
        this.user = user;
        this.charity = charity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
