package com.study.service.impl;

import com.study.dao.MemberDAO;
import com.study.dao.impl.MemberDAOImpl;
import com.study.entity.Member;
import com.study.service.MemberService;

public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    public boolean register(Member member) {
        if (memberDAO.saveMember(member) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistsUsername(String username) {
        Member member = memberDAO.queryByUserName(username);
        return member != null;
    }

    @Override
    public Member login(String username, String password) {
        return memberDAO.queryByUsernameAndPassword(username, password);
    }
}
