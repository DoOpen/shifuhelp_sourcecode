package com.project.script;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import com.project.utils.JdbcUtil;

public class ConfirmWorkOrderLoss {
	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement statement=null;
		connection=JdbcUtil.getConnection();
		String sql="UPDATE work_order SET order_state=7 WHERE order_state=12 AND order_complete_time<(CURRENT_TIMESTAMP()+INTERVAL -24*3 HOUR)";
		try {
			statement=connection.prepareStatement(sql);
			Integer result=statement.executeUpdate();
			if(result==null||result==0){
				System.out.println("没有用户确认超时工单");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection,statement,null);
		}
	}
}
