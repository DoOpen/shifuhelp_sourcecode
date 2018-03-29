package com.project.service.interfaces;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.training.TrainingBean;
import com.project.bean.training.TrainingClassBean;
import com.project.dao.interfaces.TrainingDaoI;
import com.project.page.PageBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class TrainingServiceI {
	@Autowired
	TrainingDaoI trainingDao;
	/**
	 * 获取培训列表
	 * @param pageBean
	 * @param trainingClassBean
	 * @return
	 */
	public List<TrainingBean> getTrainingList(PageBean pageBean, TrainingClassBean trainingClassBean) {
		return trainingDao.getTrainingList(pageBean,trainingClassBean);
	}
	/**
	 * 获取单个培训详情
	 * @param trainingBean
	 * @return
	 */
	public TrainingBean getTraining(TrainingBean trainingBean) {
		return trainingDao.getTraining(trainingBean);
	}
	/**
	 * 培训类别列表
	 * @param pageBean
	 * @return
	 */
	public List<TrainingClassBean> getTrainingClassList(PageBean pageBean) {
		return trainingDao.getTrainingClassList(pageBean);
	}
	
	
}
