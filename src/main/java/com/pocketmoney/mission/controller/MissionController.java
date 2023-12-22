package com.pocketmoney.mission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pocketmoney.mission.Service.MissionService;
import com.pocketmoney.mission.dto.MissionDto;
import com.pocketmoney.mission.dto.MissionIdDto;
import com.pocketmoney.mission.dto.RejectDto;
import com.pocketmoney.mission.dto.StatusDto;
import com.pocketmoney.mission.model.Mission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mission")
@Api(tags = "MISSION")
public class MissionController {

	@Autowired
	private MissionService missionService;

	@ApiOperation(value = "전체 미션 조회")
	@GetMapping(value={"/list/{childId}/{status}", "/list/{childId}"})
	public ResponseEntity<List<Mission>> getAllMissions(@PathVariable int childId, @PathVariable(required = false) Integer status) throws Exception {
		StatusDto statusParam = new StatusDto();
		statusParam.setId(childId);
		if (status != null) {
			statusParam.setStatus(status);
		}else{
			status = 0;
		}
		List<Mission> missions = missionService.selecAllMissions(statusParam);
		return new ResponseEntity<List<Mission>>(missions, HttpStatus.OK);
	}

	@ApiOperation(value = "미션 상세 조회")
	@GetMapping(value = "{missionId}")
	public ResponseEntity<Mission> getMissionById(@PathVariable int missionId) throws Exception {
		Mission mission = missionService.selectMission(missionId);
		return new ResponseEntity<Mission>(mission, HttpStatus.OK);
	}

	@ApiOperation(value = "미션 등록")
	@PostMapping()
	public ResponseEntity<Mission> addMission(@RequestBody MissionDto mission) throws Exception {
		Mission ms = missionService.insertMission(mission);
		return new ResponseEntity<Mission>(ms, HttpStatus.OK);
	}

	@ApiOperation(value = "미션 완료 요청")
	@PutMapping(value = "/success")
	public ResponseEntity<Mission> succcess(@RequestBody MissionIdDto missionIdDto) throws Exception {
		Mission mission = missionService.updateStatusS(missionIdDto);
		return new ResponseEntity<Mission>(mission, HttpStatus.OK);
	}

	@ApiOperation(value = "미션 완료 승인")
	@PutMapping(value = "/approve")
	public ResponseEntity<Mission> approve(@RequestBody MissionIdDto missionIdDto) throws Exception {
		Mission mission = missionService.updateStatusA(missionIdDto);
		return new ResponseEntity<Mission>(mission, HttpStatus.OK);
	}

	@ApiOperation(value = "미션 완료 거절")
	@PutMapping(value = "/fail")
	public ResponseEntity<Mission> fail(@RequestBody RejectDto rejectDto) throws Exception {
		Mission mission = missionService.updateStatusF(rejectDto);
		return new ResponseEntity<Mission>(mission, HttpStatus.OK);
	}
}
