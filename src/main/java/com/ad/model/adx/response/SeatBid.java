package com.ad.model.adx.response;

import lombok.Data;

import java.util.List;

@Data
public class SeatBid {
    private List<Bid> bid;
    private int group;
    private String seat;
}
