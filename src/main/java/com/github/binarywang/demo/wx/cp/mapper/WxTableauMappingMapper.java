package com.github.binarywang.demo.wx.cp.mapper;

import com.github.binarywang.demo.wx.cp.model.WxTableauMapping;
import java.util.List;

public interface WxTableauMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxTableauMapping record);

    WxTableauMapping selectByPrimaryKey(Integer id);

    List<WxTableauMapping> selectAll();

    WxTableauMapping selectByUserId(String userId);

    int updateByPrimaryKey(WxTableauMapping record);
}
