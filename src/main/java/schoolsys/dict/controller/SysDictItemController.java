package schoolsys.dict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import schoolsys.base.bean.RespBean;
import schoolsys.dict.bean.SysDictItemBean;
import schoolsys.dict.service.SysDictItemService;

@RestController
@RequestMapping("/sysDict")
public class SysDictItemController {
	
	@Autowired
	private SysDictItemService sysDictItemService;

	/**
	 * 一次性获取多个不同枚举值
	 * @param groupIds
	 * @return
	 */
	@RequestMapping("/getSysItemsBatch.do")
	public RespBean getSysItemsByGrpIdBatch(String[] groupIds){
		if(groupIds == null || groupIds.length == 0) {
			return RespBean.fail("参数不能为空");
		}
		
		Map<String, List<SysDictItemBean>> result = new HashMap<String, List<SysDictItemBean>>();
		for(String grpId : groupIds) {
			result.put(grpId, sysDictItemService.findSysDictItemBeansByGroupId(grpId));
		}
		
		return RespBean.success(result);
	}
}
