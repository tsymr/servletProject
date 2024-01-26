package com.study.dao;

import com.study.entity.Member;

public interface MemberDAO {

    public Member queryByUserName(String username);

    public int saveMember(Member member);

    public Member queryByUsernameAndPassword(String username, String password);
}
