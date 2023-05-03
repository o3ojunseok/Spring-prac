package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

// 자바 코드로 직접 빈 작성
@Configuration
public class SpringConfig {
    private DataSource dataSource;


    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//         return new JdbcMemberRepository(dataSource);  // DB
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
//
//// Controller 어노테이션은 그대로 둔다. 단 서비스 레포지토리 애노테이션은 제거한 상태여야 함.
//
//// 빈등록 방법 차이
//// 자바코드로 설정하지 않고 과거에는 XML문서로 했지만, 지금은 거의 하지 않는다.
//// DI에는 필드 주입 setter주입, 생성자 주입 3가지 방법이 있는데, 필드 주입은 좋지 않다. 뭔가 바꿀수 있는 방법이 없음.
//// setter injection 방식의 단점은 누군가 컨트롤러를 호출하면 퍼블릭으로 열려 있어야 한다. -> 퍼블릭 노출 단점
//// 생성자를 통해 주입하면 처음부터 조립 및 세팅이되는 시점에 생성자가 처음 들어오고 끝난다. -> 권장 방식
