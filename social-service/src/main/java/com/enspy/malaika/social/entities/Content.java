/***********************************************************************
 * Module:  Content.java
 * Author:  ProBook1
 * Purpose: Defines the Class Content
 ***********************************************************************/

package com.enspy.malaika.social.entities;


import javax.persistence.Entity;

@Entity
public class Content {

   private Long idContent;

   private String text;

   private int shareCount;

}