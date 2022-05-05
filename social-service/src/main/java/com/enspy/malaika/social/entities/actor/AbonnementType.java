/***********************************************************************
 * Module:  AbonnementType.java
 * Author:  ProBook1
 * Purpose: Defines the Class AbonnementType
 ***********************************************************************/

package com.enspy.malaika.social.entities.actor;


import java.io.Serializable;

public enum AbonnementType implements Serializable {

   FREE("FREE"),
   FAMILY("FAMILY"),
   PREMIUM("PREMIUM"),
   PREMIUM_PLUS("PREMIUM PLUS");

   private final String value;

   AbonnementType(String value) {
      this.value = value;
   }

}