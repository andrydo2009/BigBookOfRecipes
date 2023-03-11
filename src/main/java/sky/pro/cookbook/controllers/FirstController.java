package sky.pro.cookbook.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping("/")
    public String startProject() {
        return "Приложение запущено";
    }

    @GetMapping("/info/")
    public String showInfo() {

        return " Имя студента - Андрей Овчинников " +
                " Название проекта - CookBook " +
                " Дата создания - 23.02.2023 " +
                " Описание проекта - Книга рецептов ";
    }
}
