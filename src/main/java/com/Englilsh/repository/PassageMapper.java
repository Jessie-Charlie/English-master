package com.Englilsh.repository;

import com.Englilsh.domain.Passage;
import com.Englilsh.viewmodel.admin.passage.PassagePageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PassageMapper extends BaseMapper<Passage> {

    List<Passage> page(PassagePageRequestVM requestVM);

}
