package com.kys.book.web;

import com.kys.book.config.auth.LoginUser;
import com.kys.book.config.auth.dto.SessionUser;
import com.kys.book.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService service;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){

        model.addAttribute("posts", service.findAllDesc());

        if(!Objects.isNull(user))
            model.addAttribute("userName",user.getName());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        model.addAttribute("post", service.findById(id));

        return "posts-update";
    }

}
