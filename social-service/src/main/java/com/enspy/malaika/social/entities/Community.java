/***********************************************************************
 * Module:  Community.java
 * Author:  ProBook1
 * Purpose: Defines the Class Community
 ***********************************************************************/

package com.enspy.malaika.social.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Community {

   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idCommunity", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
   private String idCommunity;

   private String name;
   private String description;
   private long nbrAbonnes;
   private String image;
   private String link;
   private LocalDateTime createdAt;
   private CommunityType type;

   @OneToMany(mappedBy = "community")
   public List<Topic> topics;


   public List<User> members;



   /** default getter */
   public List<User> getMembers() {

      if (members == null)
         members = new ArrayList<User>();
      return members;
   }

   /** default iterator getter */
   public Iterator getIteratorMembers() {

      if (members == null)
         members = new ArrayList<User>();
      return members.iterator();
   }

   /** default setter
    * @param members */
   public void setMembers(List<User> members) {

      this.members = members;
   }

   /** default add
    * @param member */
   public void addMember(User member) {

      if (member == null)
         return;
      if (this.members == null)
         this.members = new ArrayList<User>();
      if (!this.members.contains(member))
         this.members.add(member);
   }

   /**
    * default remove
    * @param member
    *
    * */
   public void removeMember(User member) {
      if (member == null)
         return;
      if (this.members != null)
         if (this.members.contains(member))
            this.members.remove(member);
   }

   /**
    * default remove
    * @param index
    *
    * */
   public void removeMember(int index) {

      if (this.members != null)
         if (this.members.get(index) != null )
            this.members.remove(index);
   }

   /** default removeAll */
   public void removeAllMembers() {
      if (members != null)
         members.clear();
   }



   /**
    * @Getter
    *
    * */
   public List<Topic> getTopics() {
      if (topics == null)
         topics = new ArrayList<Topic>();
      return topics;
   }
   
   public Iterator getIteratorTopic() {
      if (topics == null)
         topics = new ArrayList<Topic>();
      return topics.iterator();
   }
   
   /**
    * @Setter
    * @param newTopic
    *
    * */
   public void setTopics(List<Topic> newTopic) {
      removeAllTopic();
      this.topics = newTopic;
   }
   
   /**
    * default add
    * @param newTopic
    *
    * */
   public void addTopic(Topic newTopic) {
      if (newTopic == null)
         return;
      if (this.topics == null)
         this.topics = new ArrayList<Topic>();
      if (!this.topics.contains(newTopic))
         this.topics.add(newTopic);
   }
   
   /**
    * default remove
    * @param oldTopic
    *
    * */
   public void removeTopic(Topic oldTopic) {
      if (oldTopic == null)
         return;
      if (this.topics != null)
         if (this.topics.contains(oldTopic))
            this.topics.remove(oldTopic);
   }
   
   /**
    * default removeAll
    *
    * */
   public void removeAllTopic() {
      if (topics != null)
         topics.clear();
   }


}