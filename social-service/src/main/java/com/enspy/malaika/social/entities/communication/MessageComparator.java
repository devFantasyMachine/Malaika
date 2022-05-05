package com.enspy.malaika.social.entities.communication;

import java.util.Comparator;

public class MessageComparator implements Comparator<Message> {

    @Override
    public int compare(Message o1, Message o2) {
        return o1.compareTo(o2);
    }
}
