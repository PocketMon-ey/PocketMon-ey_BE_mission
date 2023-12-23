package com.pocketmoney.mission.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocketmoney.mission.dao.MissionDao;
import com.pocketmoney.mission.dto.MissionDto;
import com.pocketmoney.mission.dto.MissionIdDto;
import com.pocketmoney.mission.dto.RejectDto;
import com.pocketmoney.mission.dto.StatusDto;
import com.pocketmoney.mission.model.Mission;
import com.pocketmoney.mission.util.WebClientService;

@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    MissionDao missionDao;

    @Override
    public List<Mission> selectAllMissions(int childId) throws Exception {
        return missionDao.selectAllMissions(childId);
    }

    @Override
    public List<Mission> selectAllMissionsByStatus(StatusDto statusParam) throws Exception {
        return missionDao.selectAllMissionsByStatus(statusParam);
    }

    @Override
    public Mission selectMission(int id) throws Exception {
        return missionDao.selectMission(id);
    }

    @Override
    public Mission updateStatusS(MissionIdDto missionIdDto) throws Exception {
        missionDao.updateStatusS(missionIdDto);
        Mission mission = missionDao.selectMission(missionIdDto.getId());
        return mission;
    }
    @Override
    public Mission updateStatusA(MissionIdDto missionIdDto) throws Exception {
        WebClientService wcs = new WebClientService();
        Mission mission = missionDao.selectMission(missionIdDto.getId());
        int childId = mission.getChildId();
		int parentId = wcs.getParentId(childId);
        int price = mission.getReward();
		wcs.sendMoney(parentId, childId, price);
        missionDao.updateStatusA(missionIdDto);
        mission = missionDao.selectMission(missionIdDto.getId());
        return mission;
    }
    @Override
    public Mission updateStatusF(RejectDto rejectDto) throws Exception {
        missionDao.updateStatusF(rejectDto);
        return missionDao.selectMission(rejectDto.getId());
    }

    @Override
    public Mission insertMission(MissionDto mission) throws Exception {
        missionDao.insertMission(mission);
        StatusDto statusParam = new StatusDto();
        statusParam.setId(mission.getChildId());
        statusParam.setStatus(0);
        Mission returMission = missionDao.selectAllMissionsByStatus(statusParam).get(0);
        return returMission;
    }
    
}
