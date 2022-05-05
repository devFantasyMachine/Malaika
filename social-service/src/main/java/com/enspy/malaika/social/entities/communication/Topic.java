/***********************************************************************
 * Module:  ArtistTopic.java
 * Author:  ProBook1
 * Purpose: Defines the Class Topic
 ***********************************************************************/

package com.enspy.malaika.social.entities.communication;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Topic {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name="idTopic", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private String idTopic;

    private String title;

    private String link;

    private String image;

    private int likeCount;

    private int dislikeCount;

    @OneToMany
    public List<Message> chatMessages;

    @ManyToOne
    @JoinTable(
            name = "topic_community",
            joinColumns = @JoinColumn(name = "idTopic"),
            inverseJoinColumns = @JoinColumn(name = "idCommunity"))
    private UserCommunity community;


    /** default getter */
    public List<Message> getChatMessages() {
        if (chatMessages == null)
            chatMessages = new ArrayList<Message>();
        return chatMessages;
    }

    /** default setter
     * @param newChatMessage */
    public void setChatMessage(List<Message> newChatMessage) {
        removeAllChatMessage();
        this.chatMessages = newChatMessage;
    }

    /** default add
     * @param newChatMessage */
    public void addChatMessage(Message newChatMessage) {
        if (newChatMessage == null)
            return;
        if (this.chatMessages == null)
            this.chatMessages = new ArrayList<Message>();
        if (!this.chatMessages.contains(newChatMessage))
            this.chatMessages.add(newChatMessage);
    }

    /** default remove
     * @param oldChatMessage */
    public void removeChatMessage(Message oldChatMessage) {
        if (oldChatMessage == null)
            return;
        if (this.chatMessages != null)
            if (this.chatMessages.contains(oldChatMessage))
                this.chatMessages.remove(oldChatMessage);
    }

    /** @pdGenerated default removeAll */
    public void removeAllChatMessage() {
        if (chatMessages != null)
            chatMessages.clear();
    }

}