package com.pocketmoney.mission.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pocketmoney.mission.dto.MissionDto;
import com.pocketmoney.mission.dto.MissionIdDto;
import com.pocketmoney.mission.dto.MissionStatusDto;
import com.pocketmoney.mission.dto.RejectDto;
import com.pocketmoney.mission.dto.StatusDto;
import com.pocketmoney.mission.model.Mission;

@Mapper
public interface MissionDao {
    List<Mission> selectAllMissions(int childId) throws SQLException;
    List<Mission> selectAllMissionsByStatus(StatusDto statusParam) throws SQLException;
    Mission selectMission(int id) throws SQLException;
    List<MissionStatusDto> selectStatus(int childId) throws SQLException;
    int insertMission(MissionDto missionDto) throws SQLException;
    int updateStatusS(MissionIdDto missionIdDto) throws SQLException;
    int updateStatusA(MissionIdDto missionIdDto) throws SQLException;
    int updateStatusF(RejectDto rejectDto) throws SQLException;
}