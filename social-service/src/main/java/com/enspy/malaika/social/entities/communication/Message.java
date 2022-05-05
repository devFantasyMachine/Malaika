/***********************************************************************
 * Module:  Message.java
 * Author:  ProBook1
 * Purpose: Defines the Class ChatMessage
 ***********************************************************************/

package com.enspy.malaika.social.entities.communication;


import com.enspy.malaika.social.entities.Content;
import com.enspy.malaika.social.entities.actor.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"father","son"})
@Entity
public class Message implements Comparable<Message> {


   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idMessage", columnDefinition = "VARCHAR(255)", updatable = false, nullable = false)
   private String idMessage;

   private MessageType type;

   private LocalDate createdDate;

   @ManyToOne(fetch = FetchType.LAZY)
   public User sender;

   @OneToMany
   public List<User> viewer;

   @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
   @ManyToOne(fetch = FetchType.LAZY)
   public Message father;

   @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
   @OneToMany(mappedBy = "father", cascade = {CascadeType.ALL }, fetch = FetchType.LAZY)
   public List<Message> son;

   @ManyToOne
   public Content content;

   @Override
   public int compareTo(Message o) {
      return createdDate.compareTo(o.getCreatedDate());
   }


   public String getIdMessage() {
      return this.idMessage;
   }

   public void setIdMessage(String idMessage) {
      this.idMessage = idMessage;
   }

   public MessageType getType() {
      return this.type;
   }

   public void setType(MessageType type) {
      this.type = type;
   }

   public LocalDate getCreatedDate() {
      return this.createdDate;
   }

   public void setCreatedDate(LocalDate createdDate) {
      this.createdDate = createdDate;
   }

   public User getSender() {
      return this.sender;
   }

   public void setSender(User sender) {
      this.sender = sender;
   }

   public Message getFather() {
      return this.father;
   }

   public void setFather(Message father) {
      this.father = father;
   }

   public List<Message> getSon() {
      return this.son;
   }

   public void setSon(List<Message> son) {
      this.son = son;
   }

   public Content getContent() {
      return this.content;
   }

   public void setContent(Content content) {
      this.content = content;
   }


}