package com.atguigu.dao;

import com.atguigu.pojo.Member;

/**
 * MemberDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-31
 * @Description:
 */
public interface MemberDao {
    Member findByTelephone(String telephone);

    void add(Member member);

    Integer findMemberCount(String lastDayOfMonth);

    int getTodayNewMember(String today);

    int getTotalMember();


    int getThisWeekAndMonthNewMember(String weekMonday);
}

