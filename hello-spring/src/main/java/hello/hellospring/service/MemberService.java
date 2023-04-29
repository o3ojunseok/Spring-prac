package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    // memberRepository를 외부에서 사용할 수 있게 해준다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        // 중복회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName()); // command option v
//
//        result.ifPresent(e -> {
//            throw new IllegalStateException("이미 존재 하는 회원");
//        });

        // optional말고 권장 방법
        validateDuplicateMember(member); // control option m 따로 메서드를 빼줌

        memberRepository.save(member);
       return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName((member.getName()))
                        .ifPresent(e -> {
                            throw new IllegalStateException("이미 존재하는 회원");
                        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 단일 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
