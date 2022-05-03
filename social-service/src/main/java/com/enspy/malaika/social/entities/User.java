/***********************************************************************
 * Module:  User.java
 * Author:  ProBook1
 * Purpose: Defines the Class Auditor
 ***********************************************************************/

package com.enspy.malaika.social.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="User")
public class User {

   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idUser", columnDefinition = "VARCHAR(255)", updatable = false, nullable = false)
   private String idUser;

   @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
   @JoinTable(
           name = "auditor_abonnement",
           joinColumns = @JoinColumn(name = "idAuditor"),
           inverseJoinColumns = @JoinColumn(name = "idAbonnement"))
   @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
   public Abonnement abonnement;

   @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
   private String password;

   @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
   public Country country;

   @JsonIgnore
   private LocalDate createdAt;

   private String image;
   private String name;
   private String bio;
   private String tel;
   private String email;


   @ManyToMany(cascade = {CascadeType.ALL }, fetch = FetchType.LAZY)
   @JoinTable(
           name = "auditor_discussions",
           joinColumns = @JoinColumn(name = "idAuditor"),
           inverseJoinColumns = @JoinColumn(name = "idDiscussion"))
   @SortComparator()
   public List<Discussion> discussions;

   @ManyToMany
   @JoinTable(
           name = "auditor_friends",
           joinColumns = @JoinColumn(name = "idAuditor"),
           inverseJoinColumns = @JoinColumn(name = "idAuditor"))
   public List<User> friends;


   @ManyToMany(cascade = {CascadeType.ALL }, fetch = FetchType.LAZY)
   @JoinTable(
           name = "auditor_communities",
           joinColumns = @JoinColumn(name = "idAuditor"),
           inverseJoinColumns = @JoinColumn(name = "idCommunity"))
   public List<Community> communities;


   public List<Discussion> getDiscussions() {

      if (discussions == null)
         discussions = new ArrayList<Discussion>();
      return discussions;

   }

   public Iterator getIteratorDiscussion() {

      if (discussions == null)
         discussions = new ArrayList<Discussion>();
      return discussions.iterator();
   }

   /**
    * default setter
    * @param newDiscussions
    *
    * */
   public void setDiscussions(List<Discussion> newDiscussions) {

      this.discussions = newDiscussions;
   }
   
   /**
    * default add
    * @param newDiscussion
    *
    * */
   public void addDiscussion(Discussion newDiscussion) {

      if (newDiscussion == null)
         return;
      if (this.discussions == null)
         this.discussions = new ArrayList<Discussion>();
      if (!this.discussions.contains(newDiscussion))
         this.discussions.add(newDiscussion);
   }

   
   /**
    * default remove
    * @param oldDiscussion
    *
    * */
   public void removeDiscussion(Discussion oldDiscussion) {

      if (oldDiscussion == null)
         return;
      if (this.discussions != null && ! this.discussions.isEmpty() )
         if (this.discussions.contains(oldDiscussion))
            this.discussions.remove(oldDiscussion);
   }

   /**
    * default remove
    * @param index
    *
    * */
   public void removeDiscussionByIndex(int index) {

      if (this.discussions != null && ! this.discussions.isEmpty())
         if (this.discussions.get(index) != null )
            this.discussions.remove(index);
   }
   
   /** default removeAll */
   public void removeAllDiscussions() {
      if (discussions != null)
         discussions.clear();
   }

   /** default getter */
   public List<User> getFriends() {

      if (friends == null)
         friends = new ArrayList<User>();
      return friends;
   }

   /** default iterator getter */
   public Iterator getIteratorAuditorB() {

      if (friends == null)
         friends = new ArrayList<User>();
      return friends.iterator();
   }

   /** default setter
    * @param newFriends */
   public void setFriends(List<User> newFriends) {

      this.friends = newFriends;
   }

   /** default add
    * @param newAuditor */
   public void addFriend(User newAuditor) {

      if (newAuditor == null)
         return;
      if (this.friends == null)
         this.friends = new ArrayList<User>();
      if (!this.friends.contains(newAuditor))
         this.friends.add(newAuditor);
   }

   /**
    * default remove
    * @param oldAuditor
    *
    * */
   public void removeFriend(User oldAuditor) {
      if (oldAuditor == null)
         return;
      if (this.friends != null)
         if (this.friends.contains(oldAuditor))
            this.friends.remove(oldAuditor);
   }

   /**
    * default remove
    * @param index
    *
    * */
   public void removeFriend(int index) {

      if (this.friends != null)
         if (this.friends.get(index) != null )
            this.friends.remove(index);
   }

   /** default removeAll */
   public void removeAllFriends() {
      if (friends != null)
         friends.clear();
   }






   /**  default getter */
   public List<Community> getCommunities() {
      if (communities == null)
         communities = new ArrayList<Community>();
      return communities;
   }
   
   /** default iterator getter */
   public Iterator getIteratorCommunity() {
      if (communities == null)
         communities = new ArrayList<Community>();
      return communities.iterator();
   }
   
   /**
    *  default setter
    * @param newCommunities
    *
    * */
   public void setCommunity(List<Community> newCommunities) {
      removeAllCommunities();
      this.communities = newCommunities;
   }
   
   /**
    * default add
    * @param newCommunity
    *
    * */
   public void addCommunity(Community newCommunity) {
      if (newCommunity == null)
         return;
      if (this.communities == null)
         this.communities = new ArrayList<Community>();
      if (!this.communities.contains(newCommunity))
         this.communities.add(newCommunity);
   }
   
   /**
    * default remove
    * @param oldCommunity
    *
    * */
   public void removeCommunity(Community oldCommunity) {
      if (oldCommunity == null)
         return;
      if (this.communities != null)
         if (this.communities.contains(oldCommunity))
            this.communities.remove(oldCommunity);
   }

   /**
    * default remove
    * @param index
    *
    * */
   public void removeCommunity(int index) {

      if (this.communities != null)
         if (this.communities.get(index) != null )
            this.communities.remove(index);
   }
   
   /** default removeAll */
   public void removeAllCommunities() {
      if (communities != null)
         communities.clear();
   }


}