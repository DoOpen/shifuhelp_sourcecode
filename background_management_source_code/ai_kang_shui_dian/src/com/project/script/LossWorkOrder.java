package com.project.script;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import com.project.utils.JdbcUtil;

public class LossWorkOrder {
	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement statement=null;
		connection=JdbcUtil.getConnection();
		String sql="UPDATE work_order SET order_state=10 WHERE order_state=2 AND order_audit_pass_time<(CURRENT_TIMESTAMP()+INTERVAL -20 MINUTE)";
		try {
			statement=connection.prepareStatement(sql);
			Integer result=statement.executeUpdate();
			if(result==null||result==0){
				System.out.println("没有超时工单");
			}else {
				System.out.println("工单超时脚本执行结束");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection,statement,null);
		}
	}
}
