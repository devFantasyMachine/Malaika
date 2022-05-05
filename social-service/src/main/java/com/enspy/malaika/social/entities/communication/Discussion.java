/***********************************************************************
 * Module:  Discussion.java
 * Author:  ProBook1
 * Purpose: Defines the Class Discussion
 ***********************************************************************/

package com.enspy.malaika.social.entities.communication;


import com.enspy.malaika.social.entities.actor.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
@Entity
public class Discussion implements Comparable<Discussion> {


   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idDiscussion", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
   private String idDiscussion;

   @Column(name = "CREATED_AT")
   private LocalDate createdAt;

   private long participantCount;

   @Enumerated(value=EnumType.STRING)
   @Column(name = "DISCUSSION_TYPE",updatable = false, nullable = false)
   private DiscussionType type;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinTable(
           name = "discussion_messages",
           joinColumns = @JoinColumn(name = "idDiscussion"),
           inverseJoinColumns = @JoinColumn(name = "idMessage"))
   @SortComparator(MessageComparator.class)
   public SortedSet<Message> messages = new TreeSet<>();

   @OneToMany
   private Set<User> participants;


   /** @pdGenerated default getter */
   public SortedSet<Message> getMessages() {
      if (messages == null)
         messages = new TreeSet<Message>();
      return messages;
   }

   /** @pdGenerated default setter
     * @param newChatMessage */
   public void setMessages(TreeSet<Message> newChatMessage) {
      removeAllChatMessage();
      this.messages = newChatMessage;
   }
   
   /** @pdGenerated default add
     * @param newChatMessage */
   public void addMessage(Message newChatMessage) {
      if (newChatMessage == null)
         return;
      if (this.messages == null)
         this.messages = new TreeSet<Message>();
      if (!this.messages.contains(newChatMessage))
         this.messages.add(newChatMessage);
   }
   
   /** @pdGenerated default remove
     * @param oldChatMessage */
   public void removeMessage(Message oldChatMessage) {
      if (oldChatMessage == null)
         return;
      if (this.messages != null)
         if (this.messages.contains(oldChatMessage))
            this.messages.remove(oldChatMessage);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllChatMessage() {
      if (messages != null)
         messages.clear();
   }

   @Override
   public int compareTo(Discussion o) {

      return messages.first().getCreatedDate().compareTo( o.createdAt );

   }


}


