package com.pocketmoney.mission.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pocketmoney.mission.dto.MissionDto;
import com.pocketmoney.mission.dto.MissionIdDto;
import com.pocketmoney.mission.dto.RejectDto;
import com.pocketmoney.mission.dto.StatusDto;
import com.pocketmoney.mission.model.*;

@Service
public interface MissionService {
    List<Mission> selecAllMissions(StatusDto statusParam) throws Exception;
    Mission selectMission(int id) throws Exception;
    Mission insertMission(MissionDto missionDto) throws Exception;
    Mission updateStatusS(MissionIdDto missionIdDto) throws Exception;
    Mission updateStatusA(MissionIdDto missionIdDto) throws Exception;
    Mission updateStatusF(RejectDto rejectDto) throws Exception;
}
