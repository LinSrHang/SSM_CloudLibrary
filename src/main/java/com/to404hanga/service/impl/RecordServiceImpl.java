package com.to404hanga.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.to404hanga.domain.Record;
import com.to404hanga.domain.User;
import com.to404hanga.entity.PageResult;
import com.to404hanga.mapper.RecordMapper;
import com.to404hanga.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public boolean addRecord(Record record) {
        return recordMapper.addRecord(record) > 0;
    }

    @Override
    public PageResult searchRecords(Record record, User user, Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 非管理员借阅人设置成当前用户
        if (!"ADMIN".equals(user.getRole())) {
            record.setBorrower(user.getName());
        }
        Page<Record> page = recordMapper.searchRecords(record);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
