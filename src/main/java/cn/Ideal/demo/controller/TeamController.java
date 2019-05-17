package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Teaminfo;
import cn.Ideal.demo.service.TeamService;
import cn.Ideal.demo.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TeamController {
	@Autowired
	private TeamService teamService;

	@PostMapping(value = "/user/goCreateTeam")
	public ResponseEntity createTeam(Teaminfo teaminfo, MultipartFile imgGo, HttpServletRequest request){
		return teamService.createTeam(teaminfo,imgGo,request);
	}
	@GetMapping(value = "/user/createTeam")
	public ModelAndView goCreateTeam(HttpServletRequest request){
		return teamService.goCreateTeam(request);
	}

}
