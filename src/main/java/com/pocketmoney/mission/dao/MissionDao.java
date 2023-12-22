package com.pocketmoney.mission.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pocketmoney.mission.model.Mission;
import com.pocketmoney.mission.model.StatusParam;

@Mapper
public interface MissionDao {
    List<Mission> selecAllMissions(Integer status) throws SQLException;
    Mission selectMission(int id) throws SQLException;
    int insertMission(Mission mission) throws SQLException;
    int updateStatus(StatusParam statusParam) throws SQLException;
}