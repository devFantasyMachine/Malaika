/***********************************************************************
 * Module:  Country.java
 * Author:  ProBook1
 * Purpose: Defines the Class Country
 ***********************************************************************/

package com.enspy.malaika.social.entities.actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"icCountry", "name"})
@Entity
@Table(name = "COUNTRY")
public class Country {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "COUNTRY_ID")
   private int countryId;

   @Column(unique = true, name = "COUNTRY_NAME")
   private String countryName;

   @OneToMany
   private Set<User> members;

   @Enumerated(value=EnumType.STRING)
   @Column(name = "CONTINENT")
   private Continent continent;

   public Country(String countryName,  Continent continent) {
      this.countryName = countryName;
      this.continent = continent;
   }


}

