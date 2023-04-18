package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!");
        return "hello";
    }

    @GetMapping("hello- mvc")
    public String HelloMvc(@RequestParam(value="name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloSpring(@RequestParam(value="name", required = false) String name, Model model) {
        return "hello" + name; // hello+ spring html태그없이 그대로 문자
    }

    @GetMapping("hello-api")
    @ResponseBody // json으로 반환하는게 기본임
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체 만들기
        hello.setName(name);
        return hello; // json으로 결과
    }
    static class Hello {
        private String name;

        // Java Bean 규약 // 프로퍼티 접근방식
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }
}
