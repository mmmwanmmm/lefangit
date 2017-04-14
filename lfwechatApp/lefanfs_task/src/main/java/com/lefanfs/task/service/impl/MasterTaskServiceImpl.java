package com.lefanfs.task.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lefanfs.base.enums.AppApiMethodEnum;
import com.lefanfs.base.enums.BackendApiMethodEnum;
import com.lefanfs.base.utils.JsonUtil;
import com.lefanfs.task.dto.TaskTimingDto;
import com.lefanfs.task.service.MasterTaskService;

@Service("masterTaskService")
public class MasterTaskServiceImpl extends BaseServiceImpl implements MasterTaskService {
	private static final Logger log = Logger.getLogger(MasterTaskServiceImpl.class);

	@Autowired
	protected TaskExecutor myExecutor;

	@Override
	public void listenTask() {
		List<TaskTimingDto> tasks = this.getExecuteTask();
		if (tasks != null && !tasks.isEmpty()) {
			for (TaskTimingDto task : tasks) {
				this.execute(task);
			}
		}
	}

	private List<TaskTimingDto> getExecuteTask() {
		// String executeTime = DateTimeUtil.getNow(DateTimeUtil.DATETIME_PATTERN_M);
		// Map<String, Object> appendMap = new HashMap<String, Object>();
		// appendMap.put(Constant.JUST_NEED_RESULT, true);
		// appendMap.put("executeTime", executeTime);
		// String result = this.callApi(ApiMethodEnum.COMMON_TASKTIMING_GETEXECUTETASK, appendMap);
		// Type type = new TypeToken<Map<String, ApiFinalResponse<List<TaskTimingDto>>>>() {
		// }.getType();
		// Map<String, ApiFinalResponse<List<TaskTimingDto>>> retMap = JsonUtil.jsonToObject(result, type);
		// if (retMap != null) {
		// return retMap.get(ApiMethodEnum.COMMON_TASKTIMING_GETEXECUTETASK.getCode()).getResults();
		// }
		return null;
	}

	private void execute(final TaskTimingDto task) {
		myExecutor.execute(new Runnable() {
			@Override
			public void run() {
				Map<String, Object> appendMap = null;
				if (!StringUtils.isEmpty(task.getParameters())) {
					appendMap = JsonUtil.jsonToObject(task.getParameters(), Map.class);
				}
				if (AppApiMethodEnum.getApiMethodEnum(task.getInterfaceName()) != null) {
					callApi(AppApiMethodEnum.getApiMethodEnum(task.getInterfaceName()), appendMap);
				} else if (BackendApiMethodEnum.getApiMethodEnum(task.getInterfaceName()) != null) {
					callApi(BackendApiMethodEnum.getApiMethodEnum(task.getInterfaceName()), appendMap);
				} else {
					log.info(task.getInterfaceName() + " not found!");
				}
			}
		});
	}
}
