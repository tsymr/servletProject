package com.study.dao.impl;

import com.study.dao.BasicDAO;
import com.study.dao.MemberDAO;
import com.study.entity.Member;

public class MemberDAOImpl extends BasicDAO<Member> implements MemberDAO {
    @Override
    public Member queryByUserName(String username) {
        String sql = "SELECT id, username, password, email from member WHERE username = ?";
        return querySingle(sql, Member.class, username);
    }

    @Override
    public int saveMember(Member member) {
        Member exist = queryByUserName(member.getUsername());
        if(exist != null){
            return -1;
        }
        String sql = "INSERT INTO member (username, password, email) VALUES (?, MD5(?), ?)";
        return update(sql, member.getUsername(), member.getPassword(), member.getEmail());
    }

    @Override
    public Member queryByUsernameAndPassword(String username, String password) {
        String sql = "SELECT id, username, email FROM member WHERE username = ? and password = MD5(?)";
        return querySingle(sql, Member.class, username, password);
    }
}
