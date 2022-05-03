/***********************************************************************
 * Module:  ChatMessage.java
 * Author:  ProBook1
 * Purpose: Defines the Class ChatMessage
 ***********************************************************************/

package com.enspy.malaika.social.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"father"})
@Entity
public class ChatMessage {


   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idMessage", columnDefinition = "VARCHAR(255)", updatable = false, nullable = false)
   private String idMessage;

   private long position;

   private MessageType type;

   private LocalDate createdDate;

   private boolean isReceived;

   private boolean lu;

   @ManyToOne(fetch = FetchType.LAZY)
   public User sender;

   @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
   @ManyToOne(fetch = FetchType.LAZY)
   public ChatMessage father;

   @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
   @OneToMany(mappedBy = "father", cascade = {CascadeType.ALL }, fetch = FetchType.LAZY)
   public List<ChatMessage> son;

   @ManyToOne
   public Content content;

}