/***********************************************************************
 * Module:  Topic.java
 * Author:  ProBook1
 * Purpose: Defines the Class Topic
 ***********************************************************************/

package com.enspy.malaika.social.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Entity
public class Topic {

   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idTopic", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
   private long idTopic;

   private String title;

   private String link;

   private String image;

   private int likeCount;

   private int dislikeCount;

   @OneToMany
   public List<ChatMessage> chatMessage;

   @ManyToOne
   @JoinTable(
           name = "topic_community",
           joinColumns = @JoinColumn(name = "idTopic"),
           inverseJoinColumns = @JoinColumn(name = "idCommunity"))
   private Community community;
   
   
   /** default getter */
   public List<ChatMessage> getChatMessage() {
      if (chatMessage == null)
         chatMessage = new ArrayList<ChatMessage>();
      return chatMessage;
   }
   
   /** default iterator getter */
   public Iterator getIteratorChatMessage() {
      if (chatMessage == null)
         chatMessage = new ArrayList<ChatMessage>();
      return chatMessage.iterator();
   }
   
   /** default setter
     * @param newChatMessage */
   public void setChatMessage(java.util.Collection<ChatMessage> newChatMessage) {
      removeAllChatMessage();
      for (java.util.Iterator iter = newChatMessage.iterator(); iter.hasNext();)
         addChatMessage((ChatMessage)iter.next());
   }
   
   /** default add
     * @param newChatMessage */
   public void addChatMessage(ChatMessage newChatMessage) {
      if (newChatMessage == null)
         return;
      if (this.chatMessage == null)
         this.chatMessage = new ArrayList<ChatMessage>();
      if (!this.chatMessage.contains(newChatMessage))
         this.chatMessage.add(newChatMessage);
   }
   
   /** default remove
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