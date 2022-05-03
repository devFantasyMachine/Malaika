/***********************************************************************
 * Module:  Discussion.java
 * Author:  ProBook1
 * Purpose: Defines the Class Discussion
 ***********************************************************************/

package com.enspy.malaika.social.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Discussion {


   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idDiscussion", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
   private String idDiscussion;

   private LocalDate createdAt;
   
   public List<ChatMessage> chatMessages;
   
   private User auditor1;

   private User auditor2;




   /** @pdGenerated default getter */
   public List<ChatMessage> getChatMessage() {
      if (chatMessages == null)
         chatMessages = new java.util.HashSet<ChatMessage>();
      return chatMessages;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorChatMessage() {
      if (chatMessage == null)
         chatMessage = new java.util.HashSet<ChatMessage>();
      return chatMessage.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newChatMessage */
   public void setChatMessage(java.util.Collection<ChatMessage> newChatMessage) {
      removeAllChatMessage();
      for (java.util.Iterator iter = newChatMessage.iterator(); iter.hasNext();)
         addChatMessage((ChatMessage)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newChatMessage */
   public void addChatMessage(ChatMessage newChatMessage) {
      if (newChatMessage == null)
         return;
      if (this.chatMessage == null)
         this.chatMessage = new java.util.HashSet<ChatMessage>();
      if (!this.chatMessage.contains(newChatMessage))
         this.chatMessage.add(newChatMessage);
   }
   
   /** @pdGenerated default remove
     * @param oldChatMessage */
   public void removeChatMessage(ChatMessage oldChatMessage) {
      if (oldChatMessage == null)
         return;
      if (this.chatMessage != null)
         if (this.chatMessage.contains(oldChatMessage))
            this.chatMessage.remove(oldChatMessage);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllChatMessage() {
      if (chatMessage != null)
         chatMessage.clear();
   }

}