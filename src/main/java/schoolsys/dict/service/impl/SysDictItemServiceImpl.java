package schoolsys.dict.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import schoolsys.dict.bean.SysDictItemBean;
import schoolsys.dict.mapper.SysDictItemMapper;
import schoolsys.dict.service.SysDictItemService;

@Service("sysDictItemService")
/**
 * 字段相关服务
 */
public class SysDictItemServiceImpl implements SysDictItemService{

	@Autowired
	private SysDictItemMapper sysDictItemMapper;
	
	@Override
	//根据groupId查询
	public List<SysDictItemBean> findSysDictItemBeansByGroupId(String groupId) {
		return sysDictItemMapper.findSysDictItemBeansByGroupId(groupId);
	}

	@Override
	//根据groupId和itemId查询
	public SysDictItemBean getDictItem(String groupId, String itemId) {
		return sysDictItemMapper.getDictItem(groupId, itemId);
	}

	@Override
	public String getItemName(String groupId, String itemId) {
		if(StringUtils.isEmpty(itemId)) {
			return "";
		}
		
		SysDictItemBean item = sysDictItemMapper.getDictItem(groupId, itemId);
		if(null == item) {
			return "";
		}
		
		
		return item.getItemName();
	}

	@Override
	public void insertDictItem(SysDictItemBean dict) {
		sysDictItemMapper.insertDictItem(dict);
	}

	@Override
	public void updateDictItem(SysDictItemBean dict) {
		sysDictItemMapper.updateDictItem(dict);
	}

	@Override
	public void deleteDictItem(String groupId, String itemId) {
		sysDictItemMapper.delDictItem(groupId, itemId);
	}
}
