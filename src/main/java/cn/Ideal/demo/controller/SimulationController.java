package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Product;
import cn.Ideal.demo.entity.Warehouse;
import cn.Ideal.demo.service.SimulationService;
import cn.Ideal.demo.util.ResponseEntity;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SimulationController {
	@Autowired
	private SimulationService simulationService;

	@GetMapping(value = "/user/simulation")
	public ModelAndView goSimulation(HttpServletRequest request){

		return simulationService.goSimulation(request);
	}
	@PostMapping(value = "/user/payProduct")
	public ResponseEntity payProduct(Warehouse warehouse, HttpServletRequest request){
		System.out.println(warehouse);
		return simulationService.payProduct(warehouse,request);
	}
	@PostMapping(value = "/user/sellProduct")
	public ResponseEntity sellProduct(String sellNum,String sellPrice,String sellName,HttpServletRequest request){
		return simulationService.sellProduct(sellNum,sellPrice,sellName,request);
	}
	@GetMapping(value = "/user/probability")
	public ModelAndView goProbability(){
		return simulationService.goProbability();
	}
	/*@GetMapping(value = "/user/probability2")
	public ModelAndView goProbability2(){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/user/probability2");
		return mav;
	}*/
	@PostMapping(value = "/user/goRandom")
	public ResponseEntity goRandom(Integer rann,Integer rid){
		return simulationService.goRand(rann,rid);

	}
}
