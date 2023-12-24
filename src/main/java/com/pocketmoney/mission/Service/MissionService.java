package com.pocketmoney.mission.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pocketmoney.mission.dto.AchievementRateDto;
import com.pocketmoney.mission.dto.CreditRateDto;
import com.pocketmoney.mission.dto.MissionDto;
import com.pocketmoney.mission.dto.MissionIdDto;
import com.pocketmoney.mission.dto.RejectDto;
import com.pocketmoney.mission.dto.StatusDto;
import com.pocketmoney.mission.model.*;

@Service
public interface MissionService {
    List<Mission> selectAllMissions(int childId) throws Exception;
    List<Mission> selectAllMissionsByStatus(StatusDto statusParam) throws Exception;
    Mission selectMission(int id) throws Exception;
    CreditRateDto selectCreditRate(int childId) throws Exception;
    AchievementRateDto selectAchievementRate(int childId) throws Exception;
    Mission insertMission(MissionDto missionDto) throws Exception;
    Mission updateStatusS(MissionIdDto missionIdDto) throws Exception;
    Mission updateStatusA(MissionIdDto missionIdDto) throws Exception;
    Mission updateStatusF(RejectDto rejectDto) throws Exception;
}
