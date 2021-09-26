package com.weworkapp.testcase;

import com.weworkapp.page.MainPage;
import com.weworkapp.page.TodoPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    private static MainPage mainPage;
    private static TodoPage todoPage;

    @BeforeAll
    static void setUp() {
        todoPage = new MainPage().toTodo();
    }

    @ParameterizedTest
    @DisplayName("新建待办")
    @CsvSource({
            "新建待办, zhang"
    })
    void addTodoTest(String massage,String key){
        //todoPage.addTodo(massage,key);
        String name = todoPage.addTodo(massage,key).getTodoName();
        assertEquals("新建待办",name);
    }

    @AfterAll
    static void tearDown() {
        todoPage.qiut();
    }
}
