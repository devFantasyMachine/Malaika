/***********************************************************************
 * Module:  Discussion.java
 * Author:  ProBook1
 * Purpose: Defines the Class Discussion
 ***********************************************************************/

package com.enspy.malaika.social.entities.communication;


import com.enspy.malaika.social.entities.actor.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;


@Entity
public class Discussion implements Comparable<Discussion> {


   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idDiscussion", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
   private String idDiscussion;

   private LocalDate createdAt;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinTable(
           name = "discussions",
           joinColumns = @JoinColumn(name = "idDiscussion"),
           inverseJoinColumns = @JoinColumn(name = "idMessage"))
   @SortComparator(MessageComparator.class)
   public SortedSet<Message> chatMessages = new TreeSet<>();

   @OneToOne
   private User user;


   /** @pdGenerated default getter */
   public SortedSet<Message> getMessages() {
      if (chatMessages == null)
         chatMessages = new TreeSet<Message>();
      return chatMessages;
   }

   /** @pdGenerated default setter
     * @param newChatMessage */
   public void setMessages(TreeSet<Message> newChatMessage) {
      removeAllChatMessage();
      this.chatMessages = newChatMessage;
   }
   
   /** @pdGenerated default add
     * @param newChatMessage */
   public void addMessage(Message newChatMessage) {
      if (newChatMessage == null)
         return;
      if (this.chatMessages == null)
         this.chatMessages = new TreeSet<Message>();
      if (!this.chatMessages.contains(newChatMessage))
         this.chatMessages.add(newChatMessage);
   }
   
   /** @pdGenerated default remove
     * @param oldChatMessage */
   public void removeMessage(Message oldChatMessage) {
      if (oldChatMessage == null)
         return;
      if (this.chatMessages != null)
         if (this.chatMessages.contains(oldChatMessage))
            this.chatMessages.remove(oldChatMessage);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllChatMessage() {
      if (chatMessages != null)
         chatMessages.clear();
   }

   @Override
   public int compareTo(Discussion o) {

      return chatMessages.first().getCreatedDate().compareTo( o.createdAt );

   }


}


