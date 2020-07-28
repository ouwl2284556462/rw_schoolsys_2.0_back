package schoolsys.dict.service;

import java.util.List;

import schoolsys.dict.bean.SysDictItemBean;

/**
 * 字段相关服务 
 */
public interface SysDictItemService {
	//根据groupId查询
	public List<SysDictItemBean> findSysDictItemBeansByGroupId(String groupId);

	//根据groupId和itemId查询
	public SysDictItemBean getDictItem(String groupId, String itemId);
	
	public String getItemName(String groupId, String itemId);

	/**
	 * 插入字典
	 * @param dict
	 */
	public void insertDictItem(SysDictItemBean dict);

	/**
	 * 更新字典
	 * @param dict
	 */
	public void updateDictItem(SysDictItemBean dict);

	/**
	 * 删除字典
	 * @param string
	 * @param name
	 */
	public void deleteDictItem(String groupId, String itemId);
}
