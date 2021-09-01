package com.wework.testcase;

import com.wework.page.ContactsPage;
import com.wework.page.MainPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 通讯录测试类
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactsTest {
    private static MainPage main;
    private static ContactsPage contacts;

    @BeforeAll
    static void setUp(){
        main = new MainPage();
        contacts = main.toContacts();
    }

    @Test
    @Order(1)
    @DisplayName("添加成员")
    void testAddMumber(){
        String username = contacts.addMumber("1","1","14400000001").search("1").getUsername();
        //todo:Assert
        assertEquals("1",username);
    }

    @Test
    @Order(2)
    @DisplayName("搜索并删除成员")
    void testSearch(){
        contacts.search("1").delete();
        //todo:Assert
        assertEquals("删除成功",contacts.getTips());
    }

    @Test
    @Order(3)
    @DisplayName("导入文件")
    void testUploadFile(){
        String username = contacts.uploadFile(this.getClass().getResource("/通讯录批量导入模板.xlsx")).search("张三（示例）").getUsername();
        assertEquals("张三（示例）",username);
        contacts.delete();
    }

    @Test
    @Order(4)
    @DisplayName("添加部门")
    void testAddDepartment(){
        contacts.addDept("测试","test");
        assertEquals("新建部门成功", contacts.getTips());
    }

    @Test
    @Order(5)
    @DisplayName("删除部门")
    void testDeleteDepartment(){
        contacts.deleteDept("测试");
        assertEquals("删除部门成功",contacts.getTips());
    }

    @Test
    @Order(6)
    @DisplayName("添加标签")
    void testAddTag(){
        contacts.addTag("CEO");
        assertEquals("创建成功", contacts.getTips());
    }

    @AfterAll
    static void tearDown(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contacts.qiut();
    }
}
