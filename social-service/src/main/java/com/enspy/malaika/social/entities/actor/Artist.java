/***********************************************************************
 * Module:  Artist.java
 * Author:  ProBook1
 * Purpose: Defines the Class Artist
 ***********************************************************************/

package com.enspy.malaika.social.entities.actor;


import com.enspy.malaika.social.entities.communication.ArtistCommunity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Artist extends User implements Serializable {

   @Column(name = "ARTIST_TYPE" , updatable = false, nullable = false)
   private ArtistType artistType;

   @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
   @JoinTable(
           name = "FANS",
           joinColumns = @JoinColumn(name = "ARTIST_ID"),
           inverseJoinColumns = @JoinColumn(name = "AUDITOR_ID"))
   Set<Auditor> fans;

   @Lob
   @Column(name="ARTIST_STORY")
   private String artistStory;

   @ManyToMany(cascade = {CascadeType.ALL }, fetch = FetchType.LAZY)
   @JoinTable(
           name = "artists_communities",
           joinColumns = @JoinColumn(name = "ARTIST_ID"),
           inverseJoinColumns = @JoinColumn(name = "COMMUNITY_ID"))
   public Set<ArtistCommunity> communities;


   /**  default getter */
   public Set<ArtistCommunity> getCommunities() {
      if (communities == null)
         communities = new HashSet<>();
      return communities;
   }

   public void setCommunity(Set<ArtistCommunity> newCommunities) {
      removeAllCommunities();
      this.communities = newCommunities;
   }

   public void addCommunity(ArtistCommunity newCommunity) {
      if (newCommunity == null)
         return;
      if (this.communities == null)
         this.communities = new HashSet<>();
      if (!this.communities.contains(newCommunity))
         this.communities.add(newCommunity);
   }

   public void removeCommunity(ArtistCommunity oldCommunity) {
      if (oldCommunity == null)
         return;
      if (this.communities != null)
         if (this.communities.contains(oldCommunity))
            this.communities.remove(oldCommunity);
   }

   public void removeAllCommunities() {
      if (communities != null)
         communities.clear();
   }

   public Set<Auditor> getFans() {
      return fans;
   }

   public void setFans(Set<Auditor> fans) {
      this.fans = fans;
   }

   public void addFan(Auditor newFan) {
      if (newFan == null)
         return;
      if (this.fans == null)
         this.fans = new HashSet<Auditor>();
      if (!this.fans.contains(newFan))
         this.fans.add(newFan);
   }


}