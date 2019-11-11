package com.ad.model.adx.request;

import lombok.Data;

import java.util.List;

@Data
public class Pmp {
    private int private_auction;
    private List<Deal> deals;
}
