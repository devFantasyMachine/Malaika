/***********************************************************************
 * Module:  Auditor.java
 * Author:  ProBook1
 * Purpose: Defines the Class Auditor
 ***********************************************************************/

package com.enspy.malaika.social.entities.actor;



import com.enspy.malaika.social.entities.communication.AuditorCommunity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="Auditor")
public class Auditor extends User implements Serializable {

   @ManyToMany
   @JoinTable(
           name = "FRIENDS",
           joinColumns = @JoinColumn(name = "auditor1_id"),
           inverseJoinColumns = @JoinColumn(name = "auditor2_id"))
   public Set<Auditor> friends = new HashSet<>();


   @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
   @ManyToMany(mappedBy="fans")
   Set<Artist> artistsSuivi;

   @ManyToMany(cascade = {CascadeType.ALL }, fetch = FetchType.LAZY)
   @JoinTable(
           name = "auditor_communities",
           joinColumns = @JoinColumn(name = "auditor_id"),
           inverseJoinColumns = @JoinColumn(name = "community_id"))
   public Set<AuditorCommunity> communities;


   public Set<Auditor> getFriends() {

      if (friends == null)
         friends = new HashSet<>();
      return friends;
   }

   public void setFriends(Set<Auditor> newFriends) {

      this.friends = newFriends;
   }

   public void addFriend(Auditor newAuditor) {

      if (newAuditor == null)
         return;
      if (this.friends == null)
         this.friends = new HashSet<>();
      if (!this.friends.contains(newAuditor))
         this.friends.add(newAuditor);
   }

   public void removeFriend(Auditor oldAuditor) {
      if (oldAuditor == null)
         return;
      if (this.friends != null)
         if (this.friends.contains(oldAuditor))
            this.friends.remove(oldAuditor);
   }

   public void removeAllFriends() {
      if (friends != null)
         friends.clear();
   }


   /**  default getter */
   public Set<AuditorCommunity> getCommunities() {
      if (communities == null)
         communities = new HashSet<>();
      return communities;
   }

   public void setCommunity(Set<AuditorCommunity> newCommunities) {
      removeAllCommunities();
      this.communities = newCommunities;
   }
   
   public void addCommunity(AuditorCommunity newCommunity) {
      if (newCommunity == null)
         return;
      if (this.communities == null)
         this.communities = new HashSet<>();
      if (!this.communities.contains(newCommunity))
         this.communities.add(newCommunity);
   }
   
   public void removeCommunity(AuditorCommunity oldCommunity) {
      if (oldCommunity == null)
         return;
      if (this.communities != null)
         if (this.communities.contains(oldCommunity))
            this.communities.remove(oldCommunity);
   }

   /** default removeAll */
   public void removeAllCommunities() {
      if (communities != null)
         communities.clear();
   }


}