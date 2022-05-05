/***********************************************************************
 * Module:  Content.java
 * Author:  ProBook1
 * Purpose: Defines the Class Content
 ***********************************************************************/

package com.enspy.malaika.social.entities;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@Entity
public class Content {

   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idContent", columnDefinition = "VARCHAR(255)", updatable = false, nullable = false)
   private String idContent;

   private int shareCount;

   public Content() {
      super();
   }

   public Content(String idContent, int shareCount) {
      this.idContent = idContent;
      this.shareCount = shareCount;
   }

   public String getIdContent() {
      return idContent;
   }

   public void setIdContent(String idContent) {
      this.idContent = idContent;
   }

   public void incrementShareCount() {
      this.shareCount = this.shareCount + 1;
   }

   public int getShareCount() {
      return shareCount;
   }

   public void setShareCount(int shareCount) {
      this.shareCount = shareCount;
   }





}




