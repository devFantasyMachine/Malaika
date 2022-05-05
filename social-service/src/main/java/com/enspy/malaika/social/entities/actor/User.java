package com.enspy.malaika.social.entities.actor;


import com.enspy.malaika.social.entities.communication.Discussion;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "USER")
@Inheritance(strategy= InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="USER_ID", insertable = false, updatable = false, nullable = false)
    private String userId;

    @Column(name="USER_PSEUDO",unique = true, updatable = false, nullable = false)
    private String userPseudo;

    @Column(name="USER_NAME",nullable = false, length = 30)
    private String userName;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @Column(name="USER_PASSWORD", nullable = false, length = 30)
    private String userPassword;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @Column(name="USER_TEL",unique = true, nullable = false, length = 15)
    private String userTel;

    @Lob
    @Column(name = "USER_PICTURE" ,nullable = false, columnDefinition = "MEDIUMBLOB")
    private byte[] userImage;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Column(name = "USER_COUNTRY", updatable = false)
    private Country userCountry;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @Column(name = "USER_CREATED_AT",nullable = false)
    private LocalDateTime userCreatedAt;

    @Column(name = "USER_LAST_VIEW",nullable = false)
    private LocalDateTime userLastView;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_BIO")
    private String userBio;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Discussion> discussions = new HashSet<>();


    public User(String userPseudo, String userName,
                String userPassword, String userTel,
                byte[] userImage, Country userCountry,
                String userEmail, String userBio) {
        this.userPseudo = userPseudo;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userTel = userTel;
        this.userImage = userImage;
        this.userCountry = userCountry;
        this.userEmail = userEmail;
        this.userBio = userBio;
        this.userCreatedAt = LocalDateTime.now();
        this.userLastView = LocalDateTime.now();
    }

    public Set<Discussion> getDiscussions() {

        return discussions;
    }

    public void setDiscussions(Set<Discussion> newDiscussions) {

        this.discussions = newDiscussions;
    }

    public void addDiscussion(Discussion newDiscussion) {

        if (newDiscussion == null)
            return;
        if (this.discussions == null)
            this.discussions = new TreeSet<Discussion>();
        if (!this.discussions.contains(newDiscussion))
            this.discussions.add(newDiscussion);
    }

    public void removeDiscussion(Discussion oldDiscussion) {

        if (oldDiscussion == null)
            return;
        if (this.discussions != null && ! this.discussions.isEmpty() )
            if (this.discussions.contains(oldDiscussion))
                this.discussions.remove(oldDiscussion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userPseudo, user.userPseudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userPseudo);
    }
}
