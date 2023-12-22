package com.pocketmoney.mission.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocketmoney.mission.dao.MissionDao;
import com.pocketmoney.mission.dto.MissionDto;
import com.pocketmoney.mission.dto.MissionIdDto;
import com.pocketmoney.mission.dto.StatusDto;
import com.pocketmoney.mission.model.Mission;
import com.pocketmoney.mission.util.WebClientService;

@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    MissionDao missionDao;

    @Override
    public List<Mission> selecAllMissions(StatusDto statusParam) throws Exception {
        return missionDao.selecAllMissions(statusParam);
    }

    @Override
    public Mission selectMission(MissionIdDto missionId) throws Exception {
        return missionDao.selectMission(missionId);
    }

    @Override
    public Mission updateStatusS(MissionIdDto missionIdDto) throws Exception {
        missionDao.updateStatusS(missionIdDto);
        Mission mission = missionDao.selectMission(missionIdDto);
        return mission;
    }
    @Override
    public Mission updateStatusA(MissionIdDto missionIdDto) throws Exception {
        WebClientService wcs = new WebClientService();
        Mission mission = missionDao.selectMission(missionIdDto);
        int childId = mission.getChildId();
		int parentId = wcs.getParentId(childId);
        int price = mission.getReward();
		wcs.sendMoney(parentId, childId, price);
        missionDao.updateStatusA(missionIdDto);
        mission = missionDao.selectMission(missionIdDto);
        return mission;
    }
    @Override
    public Mission updateStatusF(MissionIdDto missionIdDto) throws Exception {
        missionDao.updateStatusF(missionIdDto);
        return missionDao.selectMission(missionIdDto);
    }

    @Override
    public Mission insertMission(MissionDto mission) throws Exception {
        missionDao.insertMission(mission);
        StatusDto statusParam = new StatusDto();
        statusParam.setId(mission.getChildId());
        statusParam.setStatus(0);
        Mission returMission = missionDao.selecAllMissions(statusParam).get(0);
        return returMission;
    }
    
}
