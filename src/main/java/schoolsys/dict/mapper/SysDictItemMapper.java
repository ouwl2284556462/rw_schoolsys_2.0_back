package schoolsys.dict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import schoolsys.dict.bean.SysDictItemBean;

/**
 * 字典Mapper
 *
 */
public interface SysDictItemMapper {
	//根据groupId查询
	public List<SysDictItemBean> findSysDictItemBeansByGroupId(String groupId);

	//根据groupId和itemId查询
	public SysDictItemBean getDictItem(@Param("groupId") String groupId, @Param("itemId") String itemId);

	/**
	 * 插入item
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
	 * @param groupId
	 * @param itemId
	 */
	public void delDictItem(String groupId, String itemId);
}
