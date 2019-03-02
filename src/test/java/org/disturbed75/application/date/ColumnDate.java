package org.disturbed75.application.date;

import org.disturbed75.application.container.ValuesContainer;
import org.disturbed75.application.entity.Column;

public class ColumnDate {
    public static final Column user_sColumn1 = new Column(ValuesContainer.TO_DO_COLUMN_NAME, UserDate.user1.getUsername());
    public static final Column user_sColumn2 = new Column(ValuesContainer.IN_PROGRESS_COLUMN_NAME, UserDate.user1.getUsername());
    public static final Column user_sColumn3 = new Column(ValuesContainer.DONE_COLUMN_NAME, UserDate.user1.getUsername());

    public static final Column admin_sColumn1 = new Column(ValuesContainer.TO_DO_COLUMN_NAME, UserDate.admin.getUsername());
    public static final Column admin_sColumn2 = new Column(ValuesContainer.IN_PROGRESS_COLUMN_NAME, UserDate.admin.getUsername());
    public static final Column admin_sColumn3 = new Column(ValuesContainer.DONE_COLUMN_NAME, UserDate.admin.getUsername());

}
