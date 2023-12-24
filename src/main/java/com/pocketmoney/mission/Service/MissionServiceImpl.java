package com.pocketmoney.mission.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocketmoney.mission.dao.MissionDao;
import com.pocketmoney.mission.dto.AchievementRateDto;
import com.pocketmoney.mission.dto.CreditRateDto;
import com.pocketmoney.mission.dto.MissionDto;
import com.pocketmoney.mission.dto.MissionIdDto;
import com.pocketmoney.mission.dto.MissionStatusDto;
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

    @Override
    public CreditRateDto selectCreditRate(int childId) throws Exception {
        List<MissionStatusDto> missionStatusDto = missionDao.selectStatus(childId);
        int approveCnt = 0;
        int addCnt = 0;
        int rejectCnt = 0;
        for (MissionStatusDto ms : missionStatusDto) {
            if (ms.getStatus() == 0) {
                addCnt += ms.getCnt();
            } else if (ms.getStatus() == 2) {
                approveCnt += ms.getCnt();
            } else if (ms.getStatus() == 3) {
                rejectCnt += ms.getCnt();
            }
            addCnt += ms.getCnt();
        }
        CreditRateDto creditRateDto = new CreditRateDto();
        int cr = 500 - (addCnt * 20) - (rejectCnt * 40) + (approveCnt * 50);
        if (cr < 0) {
            creditRateDto.setCreditRate(0);
            ;
        } else if (cr > 1000) {
            creditRateDto.setCreditRate(1000);
            ;
        } else {
            creditRateDto.setCreditRate(cr);
        }
        return creditRateDto;
    }

    @Override
    public AchievementRateDto selectAchievementRate(int childId) throws Exception {
        List<MissionStatusDto> missionStatusDto = missionDao.selectStatus(childId);
        int approveCnt = 0;
        int addCnt = 0;
        for (MissionStatusDto ms : missionStatusDto) {
            if (ms.getStatus() == 0) {
                addCnt += ms.getCnt();
            } else if (ms.getStatus() == 2) {
                approveCnt += ms.getCnt();
            }
            addCnt += ms.getCnt();
        }
        AchievementRateDto achievementRateDto = new AchievementRateDto();
        if (addCnt == 0) {
            achievementRateDto.setAchievementRate(0);
        } else {
            int ar = Math.round(approveCnt / addCnt * 100);
            achievementRateDto.setAchievementRate(ar);
        }
        return achievementRateDto;
    }

}
