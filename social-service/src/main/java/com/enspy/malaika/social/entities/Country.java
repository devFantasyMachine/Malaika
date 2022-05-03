/***********************************************************************
 * Module:  Country.java
 * Author:  ProBook1
 * Purpose: Defines the Class Country
 ***********************************************************************/

package com.enspy.malaika.social.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"thumbnail"})
@Entity
public class Country {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idPays;
   private String name;
   private String thumbnail;
   private Continent continent;

   public Country(String name, String thumbnail, Continent continent) {
      this.name = name;
      this.thumbnail = thumbnail;
      this.continent = continent;
   }

   public int getIdPays() {
      return idPays;
   }

   public void setIdPays(int idPays) {
      this.idPays = idPays;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getThumbnail() {
      return thumbnail;
   }

   public void setThumbnail(String thumbnail) {
      this.thumbnail = thumbnail;
   }

   public Continent getContinent() {
      return continent;
   }

   public void setContinent(Continent continent) {
      this.continent = continent;
   }
}