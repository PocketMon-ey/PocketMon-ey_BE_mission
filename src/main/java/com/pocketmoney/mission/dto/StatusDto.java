package com.pocketmoney.mission.dto;

import lombok.Data;

@Data
public class StatusDto {
    private int id;
    private int status;

    public StatusDto(){
        status = 0;
    }
}
