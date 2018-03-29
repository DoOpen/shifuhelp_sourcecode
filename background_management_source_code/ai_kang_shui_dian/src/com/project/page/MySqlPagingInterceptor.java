package com.project.page;

import java.sql.Connection;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

@Intercepts(@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})) 
public class MySqlPagingInterceptor extends PageInterceptor {

    /**
     * 将sql改造成查询总数的sql
     */
    protected String getSelectTotalSql(String targetSql) {
        return "select count(*) from ("+targetSql.toLowerCase()+")as t";
    }

    /**
     * 将sql改造成支持分页的sql
     */
    protected String getSelectPagingSql(String targetSql, PageBean pageBean) {
    	String sql = targetSql.toLowerCase();
    	StringBuffer sb=new StringBuffer();
    	int order_index=sql.lastIndexOf("order by");
    	if(order_index!=-1){
    		sb.append("select * from (").append(sql.substring(0, order_index)).append(") as a ").append(sql.substring(order_index, sql.length())).append(" limit ").append((pageBean.getPage()-1)*pageBean.getLimit()+","+pageBean.getLimit());
    	}else{  
    		sb.append("select * from (").append(sql).append(") as a	limit ").append((pageBean.getPage()-1)*pageBean.getLimit()+","+pageBean.getLimit());
    	}
        return sb.toString();
    }
}
