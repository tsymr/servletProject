package com.study.service;

import com.study.entity.Member;

public interface MemberService {

    public  boolean register(Member member);

    public boolean isExistsUsername(String username);

    public Member login(String username, String password);
}
