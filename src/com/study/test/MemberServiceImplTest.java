package com.study.test;

import com.study.dao.MemberDAO;
import com.study.dao.impl.MemberDAOImpl;
import com.study.entity.Member;
import com.study.service.MemberService;
import com.study.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {
    private MemberService memberService = new MemberServiceImpl();

    @Test
    void register() {
        //构建一个Member对象
        Member member = new Member(null, "mary", "mary", "mary@qq.com");
        if(memberService.register(member)) {
            System.out.println("注册用户成功...");
        } else {
            System.out.println("注册用户失败...");
        }
    }

    @Test
    void isExistsUsername() {
        if(memberService.isExistsUsername("mary")) {
            System.out.println("用户名存在...");
        } else {
            System.out.println("用户名不存在...");
        }
    }

    @Test
    void login() {
        Member member = memberService.login("wufei", "wufei");
        if(member != null){
            System.out.println("登录成功...");
        }else {
            System.out.println("登录失败...");
        }
    }
}