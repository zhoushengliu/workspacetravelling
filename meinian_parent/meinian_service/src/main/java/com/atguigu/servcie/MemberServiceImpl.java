package com.atguigu.servcie;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.pojo.Member;
import com.atguigu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * MemberServiceImpl
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-02
 * @Description:
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCount(List<String> lists) {
        List<Integer> objects = new ArrayList<>();
        for (String list : lists) {
            // 2020-09-30 2020-10-31
            // 获取每个月的最后一天
            String lastDayOfMonth = DateUtils.getLastDayOfMonth(list);
            Integer memberCount =  memberDao.findMemberCount(lastDayOfMonth);
            objects.add(memberCount);
        }
        return objects;
    }
}