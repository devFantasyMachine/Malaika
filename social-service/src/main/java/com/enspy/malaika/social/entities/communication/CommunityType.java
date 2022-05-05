/***********************************************************************
 * Module:  CommunityType.java
 * Author:  ProBook1
 * Purpose: Defines the Class CommunityType
 ***********************************************************************/

package com.enspy.malaika.social.entities.communication;


public enum CommunityType {

   PRIVATE("PRIVATE"),
   PUBLIC("PUBLIC");

   private final String value;

   CommunityType(String aValue) {

      this.value = aValue;
   }

}