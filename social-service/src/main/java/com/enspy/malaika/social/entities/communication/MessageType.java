/***********************************************************************
 * Module:  MessageType.java
 * Author:  ProBook1
 * Purpose: Defines the Class MessageType
 ***********************************************************************/

package com.enspy.malaika.social.entities.communication;


public enum MessageType {

   VOICE("VOICE"), TEXT("TEXT"), IMAGE("IMAGE"), VIDEO("TEXT"),
   LINK("LINK");

   private final String value;

   MessageType(String val) {

      this.value = val;
   }

}