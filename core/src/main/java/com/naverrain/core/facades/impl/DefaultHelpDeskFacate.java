package com.naverrain.core.facades.impl;

import com.naverrain.persistence.enteties.SupportTicket;
import com.naverrain.persistence.utils.comparator.CustomSupportTicketsComparator;
import com.naverrain.core.facades.HelpDeskFacade;

import java.util.PriorityQueue;
import java.util.Queue;

public class DefaultHelpDeskFacate implements HelpDeskFacade {

    private Queue<SupportTicket> tickets;

    {
        tickets = new PriorityQueue<>(new CustomSupportTicketsComparator());
    }

    @Override
    public void addNewSupportTicket(SupportTicket supportTicket) {
        tickets.offer(supportTicket);
    }

    @Override
    public SupportTicket getNextSupportTicket() {
        return tickets.poll();
    }

    @Override
    public int getNumberOfTickets() {
        return tickets.size();
    }
}
