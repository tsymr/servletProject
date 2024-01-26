package com.study.test;

import com.study.dao.MemberDAO;
import com.study.dao.impl.MemberDAOImpl;
import com.study.entity.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberDAOImplTest {

    private MemberDAO memberDAO = new MemberDAOImpl();

    @Test
    void queryByUserName() {
        Member member = memberDAO.queryByUserName("admin");
        if (member != null) {
            System.out.println("member 存在");
        } else {
            System.out.println("member 不存在");
        }
    }

    @Test
    void saveMember() {
        int i = memberDAO.saveMember(new Member(null, "admin1", "admin1", "admin1@qq.com"));
        if (i > 0) {
            System.out.println("保存成功");
        } else {
            System.out.println("保存失败");
        }
    }

    @Test
    void queryByUsernameAndPassword(){
        Member member = memberDAO.queryByUsernameAndPassword("wufei", "wufei");
        if(member != null){
            System.out.println("登录成功...");
        }else {
            System.out.println("登录失败...");
        }
    }
}