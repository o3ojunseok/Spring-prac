package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 컨트롤러를 보고 스프링이 뜰때 컨트롤러의 객체를 생성해 스프링이 들고 있는다. -> 스프링 빈이 관리 된다.
public class MemberController {

//    private final MemberService memberService = new MemberService(); // new의 문제 다른곳에서 서비스를 다 가져다 쓸 수 있게 된다. -> 스프링 컨테이너에 등록한다.
    private  final MemberService memberService;


    // 생성사 만들어준다
    @Autowired // 연결해주는 역할 어노테이션 -> 스프링 컨테이너에서 해당하는걸 가져온다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
