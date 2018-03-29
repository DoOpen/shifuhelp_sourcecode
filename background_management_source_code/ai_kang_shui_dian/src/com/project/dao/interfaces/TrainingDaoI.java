package com.project.dao.interfaces;

import java.util.List;

import com.project.bean.training.TrainingBean;
import com.project.bean.training.TrainingClassBean;
import com.project.page.PageBean;

public interface TrainingDaoI {
	/**
	 * 获取培训列表
	 * @param pageBean
	 * @param trainingClassBean
	 * @return
	 */
	List<TrainingBean> getTrainingList(PageBean pageBean, TrainingClassBean trainingClassBean);
	/**
	 * 单个培训详情
	 * @param trainingBean
	 * @return
	 */
	TrainingBean getTraining(TrainingBean trainingBean);
	/**
	 * 培训类别列表
	 * @param pageBean
	 * @return
	 */
	List<TrainingClassBean> getTrainingClassList(PageBean pageBean);
	
}