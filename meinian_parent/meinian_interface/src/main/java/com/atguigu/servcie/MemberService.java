package com.atguigu.servcie;

import com.atguigu.pojo.Member;

import java.util.List;

/**
 * MemberService
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-02
 * @Description:
 */
public interface MemberService {
    Member findTelephone(String telephone);

    void add(Member member);

    List<Integer> findMemberCount(List<String> lists);
}

