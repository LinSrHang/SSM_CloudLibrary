package com.to404hanga.service;

import com.to404hanga.domain.Record;
import com.to404hanga.domain.User;
import com.to404hanga.entity.PageResult;

public interface RecordService {
    public boolean addRecord(Record record);
    //查询借阅记录
    public PageResult searchRecords(Record record, User user,Integer pageNum,Integer pageSize);
}
