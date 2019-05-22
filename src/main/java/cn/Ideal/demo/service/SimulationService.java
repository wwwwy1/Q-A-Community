package cn.Ideal.demo.service;

import cn.Ideal.demo.dao.*;
import cn.Ideal.demo.entity.*;
import cn.Ideal.demo.util.ResponseEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SimulationService {
	private Integer lunci=0;
	private List<Info> infos =null;
	private Boolean aBoolean=false;
	@Autowired
	private InfoMapper infoMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private WarehouseMapper warehouseMapper;
	@Autowired
	private TeaminfoMapper teaminfoMapper;
	@Autowired
	private MlogMapper mlogMapper;
	@Autowired
	private MrandomMapper mrandomMapper;
	@Autowired
	private AbilityMapper abilityMapper;
	@Autowired
	private QualityMapper qualityMapper;
	@Autowired
	private KnowledgeMapper knowledgeMapper;
	public ModelAndView goSimulation(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		Integer userid=(Integer)request.getSession().getAttribute("userid");
		mav.setViewName("/user/simulation");
		List<Warehouse> warehouses= warehouseMapper.selectByUid(userid);
		mav.getModel().put("warehouse",warehouses);
		List<Product> products= productMapper.selectAll();
		mav.getModel().put("product",products);


		//随机消息

		mav.getModel().put("infos",infos);
		Teaminfo teaminfo= teaminfoMapper.selectUid(userid);
		mav.getModel().put("balance",teaminfo.getTprice());
		mav.getModel().put("lunci",lunci+1);
		Page page=PageHelper.startPage(1, 6);
		List<Mlog>mlogs=mlogMapper.selectAll();
		mav.getModel().put("mlogs",mlogs);
		Mrandom mrandom=null;
		if (aBoolean==true){
			mrandom=new Mrandom();
			mrandom.setRcontent("您已经参与过本轮的测试");
			mrandom.setRan1("请期待");
			mrandom.setRan2("下一轮");
			mrandom.setRan3("突发事件");
		}else if ((lunci+1)==2){
			 mrandom= mrandomMapper.selectByPrimaryKey(1);
		}else if ((lunci+1)==3){
			mrandom=mrandomMapper.selectByPrimaryKey(2);
		}else if ((lunci+1)==7){
			mrandom=mrandomMapper.selectByPrimaryKey(3);
		}else if ((lunci+1)==13){
			mrandom=mrandomMapper.selectByPrimaryKey(4);
		}else {
			mrandom=new Mrandom();
			mrandom.setRcontent("突发事件暂未发生");
			mrandom.setRan1("暂无");
			mrandom.setRan2("暂无");
			mrandom.setRan3("暂无");
		}
		mav.getModel().put("mrand",mrandom);
		return mav;
	}
	public ResponseEntity payProduct(Warehouse warehouse,HttpServletRequest request){
		Integer uid=(Integer)request.getSession().getAttribute("userid");
		Warehouse warehouse1= warehouseMapper.selectByUidAndPid(warehouse.getPid(),uid);
		warehouse.setUid(uid);
		Float f =warehouse.getWnum()*warehouse.getWprice().floatValue();
		teaminfoMapper.updateAddPrice(uid,f*-1);
		if (warehouse1==null){
			warehouseMapper.insert(warehouse);
			System.out.println(1);
		}else {
			warehouseMapper.updateWnum(warehouse);
			System.out.println(2);
		}

		ResponseEntity re=new ResponseEntity();
		re.setMsg("购入成功");
	  	Product product= productMapper.selectByPrimaryKey(warehouse.getPid());
		Mlog mlog=new Mlog();
		mlog.setLtime(new Date());
		mlog.setLcontent("购入"+warehouse.getWnum()+"个"+product.getPname());
		mlog.setLoper("购买");
		mlogMapper.insert(mlog);
		return re;
	}
	public ResponseEntity sellProduct(String sellNum,String sellPrice,String sellName,HttpServletRequest request){

		//商品销售
		ResponseEntity re=new ResponseEntity();
		String[] sn= sellNum.split(",");
		String[] sp= sellPrice.split(",");
		String[] si= sellName.split(",");
		Integer uid=(Integer)request.getSession().getAttribute("userid");
		List<Warehouse> warehouses=new ArrayList<>();
		float zhanghu=0L;
		for (int i = 0; i <sn.length ; i++) {
			Warehouse warehouse=new Warehouse();
			warehouse.setUid(uid);
			warehouse.setPid(Integer.parseInt(si[i]));
			warehouse.setWnum(Integer.parseInt(sn[i]));
			warehouses.add(warehouse);
			zhanghu+=Integer.parseInt(sn[i])*Float.parseFloat(sp[i]);
		}
		teaminfoMapper.updateAddPrice(uid,zhanghu);
		for (Warehouse warehouse:
			 warehouses) {
			warehouseMapper.downWnum(warehouse);
			Product product= productMapper.selectByPrimaryKey(warehouse.getPid());
			Mlog mlog=new Mlog();
			mlog.setLcontent("出售"+warehouse.getWnum()+"个"+product.getPname());
			mlog.setLoper("出售");
			mlog.setLtime(new Date());
			mlogMapper.insert(mlog);
		}
		warehouseMapper.deleteWnum();
		/*System.out.println("-----------");
		System.out.println(warehouses);
		System.out.println(zhanghu);*/

		productMapper.updatePriceAndPchange();
		//随机
		long now = System.currentTimeMillis();
		System.out.println(now);
		Random random=new Random(now);
		Integer pchange= random.nextInt(8)+2;
		Integer zf=random.nextInt(2);
		if (zf==0){
			pchange=-pchange;
		}
		Integer pid=random.nextInt(5)+1;
		productMapper.updatePchange(pid,pchange);

		infos=new ArrayList<>();
		if (pchange>=0){
			infos.add(infoMapper.selectInfo(pid,1));
		}else {
			infos.add(infoMapper.selectInfo(pid,-1));
		}

		pchange= random.nextInt(20)+1;
		zf=random.nextInt(2);
		if (zf==0){
			pchange=-pchange;
		}
		Integer pid2=random.nextInt(5)+1;
		while (pid.equals(pid2)){
			pid2=random.nextInt(5)+1;
		}
		productMapper.updatePchange(pid2,pchange);
		if (pchange>=0){
			infos.add(infoMapper.selectInfo(pid2,1));
		}else {
			infos.add(infoMapper.selectInfo(pid2,-1));
		}

		System.out.println(infos);
		//随机无用信息
		Set<Integer> set=new HashSet<>();
		while (set.size()<3){
			set.add(random.nextInt(10)+11);
		}
		for (Integer iid: set) {
			infos.add(infoMapper.selectByPrimaryKey(iid));
		}
		Collections.shuffle(infos);
		System.out.println(lunci);
		if (lunci>=14){
			re.setMsg("测试结束");
			re.setStatus(3);
		}else {
			re.setMsg("销售成功");
		}
		lunci++;
		aBoolean=false;
		return re;
	}

	public void initProdutcAndwarehouse(HttpServletRequest request){
		productMapper.productInit();
		warehouseMapper.initWarehouse();
		productMapper.productPchangeInit();
		mlogMapper.mlogInit();
		String ss= (String)request.getSession().getAttribute("username");
		Mlog mlog=new Mlog();
		mlog.setLoper("登录");
		mlog.setLtime(new Date());
		mlog.setLcontent("用户:"+ss+" 进入模拟实训");
		//随机信息
		long now = System.currentTimeMillis();
		System.out.println(now);
		Random random=new Random(now);
		Integer pchange= random.nextInt(8)+2;
		Integer zf=random.nextInt(2);
		if (zf==0){
			pchange=-pchange;
		}
		Integer pid=random.nextInt(5)+1;
		productMapper.updatePchange(pid,pchange);

		infos=new ArrayList<>();
		if (pchange>=0){
			infos.add(infoMapper.selectInfo(pid,1));
		}else {
			infos.add(infoMapper.selectInfo(pid,-1));
		}

		pchange= random.nextInt(20)+1;
		zf=random.nextInt(2);
		if (zf==0){
			pchange=-pchange;
		}
		Integer pid2=random.nextInt(5)+1;
		while (pid.equals(pid2)){
			pid2=random.nextInt(5)+1;
		}
		productMapper.updatePchange(pid2,pchange);
		if (pchange>=0){
			infos.add(infoMapper.selectInfo(pid2,1));
		}else {
			infos.add(infoMapper.selectInfo(pid2,-1));
		}

		System.out.println(infos);
		//随机无用信息
		Set<Integer> set=new HashSet<>();
		while (set.size()<3){
			set.add(random.nextInt(10)+11);
		}
		for (Integer iid: set) {
			infos.add(infoMapper.selectByPrimaryKey(iid));
		}
		Collections.shuffle(infos);
		Integer uid=(Integer) request.getSession().getAttribute("userid");
		//初始化用户基础能力分
		Ability ability=new Ability(10);
		ability.setUid(uid);
		if (abilityMapper.selectByUid(uid)!=null){
			abilityMapper.initHaveAbility(uid);
		}else {
			abilityMapper.initNotHaveAbility(ability);
		}
		//初始化用户基础知识分
		Knowledge knowledge=new Knowledge(10);
		knowledge.setUid(uid);
		if (knowledgeMapper.selectByUid(uid)!=null){
			knowledgeMapper.initHaveKnowledge(uid);
		}else {
			knowledgeMapper.initNotHaveKnowledge(knowledge);
		}
		//初始化用户基础素质分
		Quality quality =new Quality(10);
		quality.setUid(uid);
		if (qualityMapper.selectByUid(uid)!=null){
			qualityMapper.initHaveQuality(uid);
		}else {
			qualityMapper.initNotHaveQuality(quality);
		}


		mlogMapper.insert(mlog);
	}
	public ModelAndView goProbability(){
		ModelAndView mav=new ModelAndView();
		System.out.println(lunci+"-------------");
		if (lunci>=14)
		{
			mav.setViewName("/user/probability2");
		}else {
			mav.setViewName("/user/probability");
		}
		return mav;
	}

	public ResponseEntity goRand(Integer rann,Integer rid){
		ResponseEntity re=new ResponseEntity();
		aBoolean=true;
		Mrandom mrandom= mrandomMapper.selectByPrimaryKey(rid);
		Mlog mlog=new Mlog();
		if (rann==1){
			mlog.setLcontent(mrandom.getRcontent()+"事件中，您选择了"+mrandom.getRan1());
		}else if (rann==2){
			mlog.setLcontent(mrandom.getRcontent()+"事件中，您选择了"+mrandom.getRan2());
		}else if (rann==3){
			mlog.setLcontent(mrandom.getRcontent()+"事件中，您选择了"+mrandom.getRan3());
		}
		mlog.setLoper("突发事件");
		mlog.setLtime(new Date());
		mlogMapper.insert(mlog);
		re.setMsg("测试完成");
		return re;
	}

}
