/***********************************************************************
 * Module:  Artist.java
 * Author:  ProBook1
 * Purpose: Defines the Class Artist
 ***********************************************************************/

package com.enspy.malaika.social.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Artist extends User {


   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="id", columnDefinition = "VARCHAR(255)", updatable = false, nullable = false)
   private String idArtist;

   private String imageList;
   private String link;
   private String provenance;
   private String story;

}