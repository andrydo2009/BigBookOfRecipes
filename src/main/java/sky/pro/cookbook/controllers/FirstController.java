package sky.pro.cookbook.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Запрос успешно выполнен"
                    ) ,
                    @ApiResponse(
                            responseCode = "404",
                            description = "URL неверный или такого действия нет в веб-приложении."
                    ) ,
                    @ApiResponse(
                            responseCode = "500",
                            description = "Во время выполнения запроса произошла ошибка на сервере"
                    )

            }
    )
    public String startProject() {
        return "Приложение запущено";
    }

    @GetMapping("/info/")
    @Operation(
            summary = "Информация о проекте",
            description = "Общаяя инофрмация"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Достуна информация о проекте"
                    ) ,
                    @ApiResponse(
                            responseCode = "404",
                            description = "URL неверный или такого действия нет в веб-приложении."
                    ) ,
                    @ApiResponse(
                            responseCode = "500",
                            description = "Во время выполнения запроса произошла ошибка на сервере"
                    )
                    ,
                    @ApiResponse(
                            responseCode = "400",
                            description = "Есть ошибка в параметрах запроса."
                    )

            }
    )
    public String showInfo() {

        return " Имя студента - Андрей Овчинников " +
                " Название проекта - CookBook " +
                " Дата создания - 23.02.2023 " +
                " Описание проекта - Книга рецептов ";
    }
}
