/***********************************************************************
 * Module:  MessageType.java
 * Author:  ProBook1
 * Purpose: Defines the Class MessageType
 ***********************************************************************/

package com.enspy.malaika.social.entities;


public enum MessageType {
   VOICE("VOICE"),
   LINK("LINK");

   private final String value;

   MessageType(String val) {

      this.value = val;
   }

}