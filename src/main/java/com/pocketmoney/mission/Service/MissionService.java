package com.pocketmoney.mission.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pocketmoney.mission.model.*;

@Service
public interface MissionService {
    List<Mission> selecAllMissions(Integer status) throws Exception;
    Mission selectMission(int missionId) throws Exception;
    Mission insertMission(Mission mission) throws Exception;
    int updateStatus(StatusParam statusParam) throws Exception;
}
