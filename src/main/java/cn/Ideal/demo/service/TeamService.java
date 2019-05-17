package cn.Ideal.demo.service;

import cn.Ideal.demo.dao.TeaminfoMapper;
import cn.Ideal.demo.entity.Teaminfo;
import cn.Ideal.demo.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class TeamService {
	@Autowired
	private TeaminfoMapper teaminfoMapper;
	public ResponseEntity createTeam(Teaminfo teaminfo, MultipartFile imgGo, HttpServletRequest request){
		ResponseEntity re=new ResponseEntity();
		Integer userid=(Integer) request.getSession().getAttribute("userid");
		Teaminfo team=teaminfoMapper.selectUid(userid);
		if (imgGo!=null){
			System.out.println("------------有图片---------");
			String profilesPath="F:\\eimg\\img\\";
			BufferedOutputStream out=null;
			UUID uuid=UUID.randomUUID();
			String uid=uuid.toString();
			uid=uid.replace("-","");
			String[] last=imgGo.getOriginalFilename().split("[.]");
			String path=profilesPath+uid+"."+last[1];
			teaminfo.setTlogo("img/"+uid+"."+last[1]);
			teaminfo.setUid(userid);
			if (team==null){
				teaminfoMapper.insert(teaminfo);
				re.setMsg("成功建立团队");
			}else {
				teaminfoMapper.updateByUid(teaminfo);
				re.setMsg("成功修改团队");
			}

			System.out.println("---11---");
			try {
				File folder=new File(profilesPath);
				if (!folder.exists())folder.mkdirs();
				out = new BufferedOutputStream(new FileOutputStream(path));
				out.write(imgGo.getBytes());
				out.flush();
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			teaminfo.setTlogo("img/timg.jpg");
			teaminfo.setUid(userid);
			if (team==null){
				teaminfoMapper.insert(teaminfo);
				re.setMsg("成功建立团队");
			}else {
				teaminfoMapper.updateByUidNotImg(teaminfo);
				re.setMsg("成功修改团队");
			}
		}
		return re;
	}
	public ModelAndView goCreateTeam(HttpServletRequest request){
		Teaminfo teaminfo=teaminfoMapper.selectUid((Integer)request.getSession().getAttribute("userid"));
		ModelAndView mav=new ModelAndView();
		if (teaminfo!=null) {
			mav.getModel().put("data", teaminfo);
			mav.getModel().put("price", teaminfo.getTprice());
		}else {
			mav.getModel().put("data", new Teaminfo());
		}
		mav.setViewName("/user/createTeam");
		return mav;
	}
}
