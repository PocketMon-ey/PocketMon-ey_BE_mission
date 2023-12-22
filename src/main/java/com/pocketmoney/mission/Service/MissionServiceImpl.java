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
    public List<Mission> selecAllMissions(StatusParam statusParam) throws Exception {
        return missionDao.selecAllMissions(statusParam);
    }

    @Override
    public Mission selectMission(int missionId) throws Exception {
        return missionDao.selectMission(missionId);
    }

    @Override
    public int updateStatusS(int id) throws Exception {
        return missionDao.updateStatusS(id);
    }
    @Override
    public int updateStatusA(int id) throws Exception {
        return missionDao.updateStatusA(id);
    }
    @Override
    public int updateStatusF(int id) throws Exception {
        return missionDao.updateStatusF(id);
    }

    @Override
    public Mission insertMission(Mission mission) throws Exception {
        missionDao.insertMission(mission);
        StatusParam statusParam = new StatusParam();
        statusParam.setId(mission.getId());
        statusParam.setStatus(0);
        return missionDao.selecAllMissions(statusParam).get(0);
    }
    
}
