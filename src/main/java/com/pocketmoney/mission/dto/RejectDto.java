package com.pocketmoney.mission.dto;

import lombok.Data;

@Data
public class RejectDto {
    private int id;
    private int status;
    private String rejectReason;
}
