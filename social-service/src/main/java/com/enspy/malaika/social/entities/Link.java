/***********************************************************************
 * Module:  Link.java
 * Author:  ProBook1
 * Purpose: Defines the Class Link
 ***********************************************************************/

package com.enspy.malaika.social.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Link extends Content {

   private String linkValue;

}