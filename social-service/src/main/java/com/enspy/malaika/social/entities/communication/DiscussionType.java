/***********************************************************************
 * Module:  MessageType.java
 * Author:  ProBook1
 * Purpose: Defines the Class MessageType
 ***********************************************************************/

package com.enspy.malaika.social.entities.communication;


public enum DiscussionType {

   GROUP("GROUP"), INBOX("INBOX");

   private final String value;

   DiscussionType(String val) {

      this.value = val;
   }

}