package com.enspy.malaika.social.entities.actor;




import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"abonnementId" })
@ToString
@Entity
public class MalaikaAbonnement implements Serializable {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="abonnement_id", columnDefinition = "VARCHAR(255)",
            insertable = false, updatable = false, nullable = false)
    private String abonnementId;

    @Column(name = "subscribedAt")
    private LocalDateTime subscribedAt;

    @ManyToOne
    private User user;

    @Column(name = "type")
    private AbonnementType type;

    public MalaikaAbonnement(AbonnementType type, User user) {
        this.subscribedAt = LocalDateTime.now();
        this.user = user;
        this.type = type;
    }
}
