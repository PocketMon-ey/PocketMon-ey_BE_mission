package com.pocketmoney.mission.model;

import lombok.Data;

@Data
public class StatusParam {
    public StatusParam(int id, int status) {
        this.id = id;
        this.status = status;
    }
    private int id;
    private int status;
}
