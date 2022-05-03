/***********************************************************************
 * Module:  Abonnement.java
 * Author:  ProBook1
 * Purpose: Defines the Class Abonnement
 ***********************************************************************/

package com.enspy.malaika.social.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="Abonnement")
public class Abonnement {

   @Id
   @GenericGenerator(name = "uuid2", strategy = "uuid2")
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
   @Column(name="idAbonnement", columnDefinition = "VARCHAR(255)", updatable = false, nullable = false)
   private String idAbonnement;

   @Column(name = "at")
   private LocalDate at;

   @Column(name = "type")
   private AbonnType type;

   @OneToOne(mappedBy="abonnement")
   @Column(name = "subject")
   private Auditor subject;


   private Abonnement(Auditor subject,LocalDate at, AbonnType type) {
      this.at = at;
      this.subject = subject;
      this.type = type;
   }

   public static Abonnement build(Auditor subject, AbonnType type){

      switch (type){

         case PREMIUM: return new Abonnement(subject, LocalDate.now(), AbonnType.PREMIUM);
         case PREMIUM_PLUS: return new Abonnement(subject,LocalDate.now(), AbonnType.PREMIUM_PLUS);
         default: return new Abonnement(subject, LocalDate.now(), AbonnType.FREE);
      }
   }

   public static Abonnement build(Auditor subject){

      return new Abonnement(subject, LocalDate.now(), AbonnType.FREE);
   }

   public String getIdAbonnement() {
      return idAbonnement;
   }

   public void setIdAbonnement(String idAbonnement) {
      this.idAbonnement = idAbonnement;
   }

   public LocalDate getAt() {
      return at;
   }

   public void setAt(LocalDate at) {
      this.at = at;
   }

   public AbonnType getType() {
      return type;
   }

   public void setType(AbonnType type) {
      this.type = type;
   }


}