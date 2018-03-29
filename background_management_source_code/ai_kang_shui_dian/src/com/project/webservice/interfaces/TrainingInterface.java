package com.project.webservice.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.bean.training.TrainingBean;
import com.project.bean.training.TrainingClassBean;
import com.project.page.PageBean;
import com.project.service.interfaces.MemberServiceI;
import com.project.service.interfaces.TrainingServiceI;
import com.project.webservice.BaseController;

@Controller
@RequestMapping("/trainingInterfaces.api")
public class TrainingInterface extends BaseController {

	@Autowired
	MemberServiceI memberService;
	@Autowired
	TrainingServiceI trainingService;
	/**
	 * 获取培训分类列表
	 * @param request
	 * @param response
	 * @param pageBean
	 */
	@RequestMapping(params = "getTrainingClassList", method = RequestMethod.POST)
	public void getTrainingClassList(PageBean pageBean) {
		WriteObject(trainingService.getTrainingClassList(pageBean));
	}
	/**
	 * 获取培训列表
	 * @param memberBean
	 * @param trainingClassBean
	 * @param pageBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getTrainingList", method = RequestMethod.POST)
	public void getTrainingList(TrainingClassBean trainingClassBean,PageBean pageBean) throws Exception {
		WriteObject(trainingService.getTrainingList(pageBean,trainingClassBean));
	}
	/**
	 * 获取单个培训详情
	 * @param memberBean
	 * @param trainingBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getTraining", method = RequestMethod.POST)
	public void getTraining(TrainingBean trainingBean) throws Exception {
		WriteObject(trainingService.getTraining(trainingBean));
	}
}
