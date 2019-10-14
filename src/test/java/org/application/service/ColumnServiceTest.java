package org.application.service;

import org.application.container.ValuesContainer;
import org.application.date.UserDate;
import org.application.entity.Column;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;


import java.util.List;

import static org.application.ColumnTestData.*;


public class ColumnServiceTest extends AgileBoardApplicationTestRoot {
    @Autowired
    private ColumnService service;
    final Column user4_sColumn1 = new Column(ValuesContainer.TO_DO_COLUMN_NAME, UserDate.user4.getUsername());

   /* @PostConstruct
    public void setUp() {
        myUserDetailsService.loadUserByUsername(UserDate.user4.getUsername());
    }
*/

    @Test
    @WithMockUser(username = "user4",password = "123")
    public void addNewColumn() throws Exception {
        service.addNewColumn(user4_sColumn1);

        Column actual = service.getColumnByName(user4_sColumn1.getName(), UserDate.user4.getUsername());
        service.deliteColumnByName(user4_sColumn1.getId());
        assertMatch(actual, user4_sColumn1);
    }


    @Test
    @WithMockUser(username = "user4",password = "123")
    public void getAllColumns() throws Exception {
        List<Column> actual = service.getAllColumns(UserDate.user4.getUsername());
        System.out.println("actual = " + actual);
        assertMatch(actual, user4_sColumn1);
    }

}