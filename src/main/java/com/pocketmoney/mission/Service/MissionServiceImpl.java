package com.pocketmoney.mission.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocketmoney.mission.dao.MissionDao;
import com.pocketmoney.mission.model.Mission;
import com.pocketmoney.mission.model.StatusParam;

@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    MissionDao missionDao;

    @Override
    public List<Mission> selecAllMissions(Integer status) throws Exception {
        return missionDao.selecAllMissions(status);
    }

    @Override
    public Mission selectMission(int missionId) throws Exception {
        return missionDao.selectMission(missionId);
    }

    @Override
    public int updateStatus(StatusParam statusParam) throws Exception {
        return missionDao.updateStatus(statusParam);
    }

    @Override
    public Mission insertMission(Mission mission) throws Exception {
        missionDao.insertMission(mission);
        return missionDao.selecAllMissions(0).get(0);
    }
    
}
