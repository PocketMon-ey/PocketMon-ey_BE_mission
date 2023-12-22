package com.pocketmoney.mission.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pocketmoney.mission.model.*;

@Service
public interface MissionService {
    List<Mission> selecAllMissions(StatusParam statusParam) throws Exception;
    Mission selectMission(int missionId) throws Exception;
    Mission insertMission(Mission mission) throws Exception;
    int updateStatusS(int id) throws Exception;
    int updateStatusA(int id) throws Exception;
    int updateStatusF(int id) throws Exception;
}
