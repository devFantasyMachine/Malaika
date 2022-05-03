/***********************************************************************
 * Module:  AbonnType.java
 * Author:  ProBook1
 * Purpose: Defines the Class AbonnType
 ***********************************************************************/

package com.enspy.malaika.social.entities;


public enum AbonnType {

   FREE("FREE"),
   PREMIUM("PREMIUM"),
   PREMIUM_PLUS("PREMIUM PLUS");

   private final String value;

   AbonnType(String value) {

      this.value = value;
   }

}