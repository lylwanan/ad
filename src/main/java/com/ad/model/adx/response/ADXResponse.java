package com.ad.model.adx.response;

import lombok.Data;

import java.util.List;

@Data
public class ADXResponse {
    private String id;
    private String bidid;
    private List<SeatBid> seatbid;
}

