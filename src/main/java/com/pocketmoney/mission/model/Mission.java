package com.pocketmoney.mission.model;

import lombok.Data;

@Data
public class Mission {
	private int id;
	private String name;
	private int reward;
	private int status;
	private int childId;
}