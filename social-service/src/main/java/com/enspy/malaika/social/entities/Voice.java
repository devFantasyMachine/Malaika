/***********************************************************************
 * Module:  Voice.java
 * Author:  ProBook1
 * Purpose: Defines the Class Voice
 ***********************************************************************/

package com.enspy.malaika.social.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Voice extends Content {

   private int duration;

   @Lob
   @Column(name = "data", columnDefinition="MEDIUMBLOB" , updatable = true, nullable = false)
   private byte[] data;

   private String title;

   public Voice() {
      super();
   }

   public Voice(int duration, byte[] data, String titre) {
      super();
      this.duration = duration;
      this.data = data;
      this.title = titre;

   }

   public Voice(String idContent, int shareCount, int duration, byte[] data, String titre) {
      super(idContent, shareCount);
      this.duration = duration;
      this.data = data;
      this.title = titre;
   }

   public int getDuration() {
      return duration;
   }

   public void setDuration(int duration) {
      this.duration = duration;
   }

   public byte[] getData() {
      return data;
   }

   public void setData(byte[] data) {
      this.data = data;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }
}