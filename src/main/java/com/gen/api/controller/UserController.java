package com.gen.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gen.api.exception.ServiceExc;
import com.gen.api.model.User;
import com.gen.api.model.entity.UserEntity;
import com.gen.api.model.mapper.UserMapper;
import com.gen.api.service.UserService;
import com.gen.api.util.Util;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        modelAndView.addObject("usuario", new User());
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        modelAndView.addObject("aluno", new User());
        return modelAndView;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", new User());
        modelAndView.setViewName("login/cadastro");
        return modelAndView;
    }

    @PostMapping("/salvarUsuario")
    public ModelAndView cadastrar(@Valid User usuario) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        service.userSave(UserMapper.INSTANCE.toEntity(usuario));
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid User usuario, BindingResult br,
                              HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", new User());
        if(br.hasErrors()) {
            modelAndView.setViewName("login/login");
        }
        
        UserEntity entity =  service.loginUser(usuario.getUser(), Util.md5(usuario.getPassword()));
        User userLogin = entity != null ? UserMapper.INSTANCE.toModel(entity) : null;
        if(userLogin == null) {
            modelAndView.addObject("msg","Usuario não encontrado. Tente novamente");
        } else {
            session.setAttribute("usuarioLogado", userLogin);
            return index();
        }

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }
}
