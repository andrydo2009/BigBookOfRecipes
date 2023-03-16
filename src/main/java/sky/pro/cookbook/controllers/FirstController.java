package sky.pro.cookbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = " Кулинарная книга ", description = " Общая информация о проекте ")
public class FirstController {
    @GetMapping("/")
    @Operation(
            summary = "Информация о состоянии",
            description = "Статус приложения"
    )
    public String startProject() {
        return "Приложение запущено";
    }

    @GetMapping("/info/")
    @Operation(
            summary = "Информация о проекте",
            description = "Общаяя инофрмация"
    )
    public String showInfo() {

        return " Имя студента - Андрей Овчинников " +
                " Название проекта - CookBook " +
                " Дата создания - 23.02.2023 " +
                " Описание проекта - Книга рецептов ";
    }
}
