package com.enspy.malaika.social.entities;

import javax.persistence.*;

@Entity
public class Text extends Content {

    @Lob
    @Basic(fetch= FetchType.LAZY)
    @Column(name="value")
    private String value;
}
