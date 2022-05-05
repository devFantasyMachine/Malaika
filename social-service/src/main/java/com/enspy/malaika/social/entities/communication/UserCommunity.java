package com.enspy.malaika.social.entities.communication;

import com.enspy.malaika.social.entities.actor.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@MappedSuperclass
public class UserCommunity {


    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="community_id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String idCommunity;

    private String name;
    private String description;
    private long nbrAbonnes;
    private String image;
    private String link;
    private LocalDateTime createdAt;
    private CommunityType type;

    @OneToMany(mappedBy = "community")
    private Set<Topic> topics;

    @ManyToMany
    private Set<User> members;

    public Set<User> getMembers() {

        if (members == null)
            members = new HashSet<>();
        return members;
    }

    /** default setter
     * @param members */
    public void setMembers(Set<User> members) {

        this.members = members;
    }

    /** default add
     * @param member */
    public void addMember(User member) {

        if (member == null)
            return;
        if (this.members == null)
            this.members = new HashSet<>();
        if (!this.members.contains(member))
            this.members.add(member);
    }

    public void removeMember(User member) {
        if (member == null)
            return;
        if (this.members != null)
            if (this.members.contains(member))
                this.members.remove(member);
    }

    /** default removeAll */
    public void removeAllMembers() {
        if (members != null)
            members.clear();
    }


}
