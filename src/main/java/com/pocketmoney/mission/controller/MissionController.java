package com.pocketmoney.mission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pocketmoney.mission.Service.MissionService;
import com.pocketmoney.mission.model.Mission;
import com.pocketmoney.mission.model.StatusParam;

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
		StatusParam statusParam = new StatusParam();
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
	@GetMapping(value = "{id}")
	public ResponseEntity<Mission> getMissionById(@PathVariable int id) throws Exception {
		Mission mission = missionService.selectMission(id);
		return new ResponseEntity<Mission>(mission, HttpStatus.OK);
	}

	@ApiOperation(value = "미션 등록")
	@PostMapping()
	public ResponseEntity<Mission> addMission(@RequestBody Mission mission) throws Exception {
		Mission ms = missionService.insertMission(mission);
		return new ResponseEntity<Mission>(ms, HttpStatus.OK);
	}

	@ApiOperation(value = "미션 완료 요청")
	@PutMapping(value = "/success/{id}")
	public ResponseEntity<Integer> succcess(@PathVariable int id) throws Exception {
		int res = missionService.updateStatusS(id);
		if (res == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "미션 완료 승인")
	@PutMapping(value = "/approve/{id}")
	public ResponseEntity<Integer> approve(@PathVariable int id) throws Exception {
		int res = missionService.updateStatusA(id);
		if (res == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "미션 완료 거절")
	@PutMapping(value = "/fail/{id}")
	public ResponseEntity<Integer> fail(@PathVariable int id) throws Exception {
		int res = missionService.updateStatusF(id);
		if (res == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
