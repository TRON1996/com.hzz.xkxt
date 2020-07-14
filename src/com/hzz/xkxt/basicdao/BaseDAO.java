package com.hzz.xkxt.basicdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * JDBC数据访问类
 * @author gudh 
 * 2019-4-9
 */
public class BaseDAO  extends DBConn {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	/**
	 * 关闭并释放连接资源
	 */
	public void close(){
		closeDB(conn, ps,rs);
	}
	
    /**
     * 执行添加操作的无参SQL语句
     * @param sql
     * @return 影响的行数
     */
	public int insert(String sql){	
		conn=getConnection();
		int cnt=0;
		try {
			ps=conn.prepareStatement(sql);
			cnt=ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return cnt;
	}
	
	/**
	 * 执行添加操作的有参SQL语句
	 * @param sql 
	 * @param parmas
	 * @return 影响的行数
	 */
	public int insert(String sql,Object[] parmas){	
		conn=getConnection();
		int cnt=0;
		try {
			ps=conn.prepareStatement(sql);
			for(int i=1;i<=parmas.length;i++){
				ps.setObject(i, parmas[i-1]);
			}
			cnt=ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return cnt;
	}
	
	
	 /**
     * 执行修改操作的无参SQL语句
     * @param sql
     * @return 影响的行数
     */
	public int update(String sql){	
		conn=getConnection();
		int cnt=0;
		try {
			ps=conn.prepareStatement(sql);
			cnt=ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return cnt;
	}
	
	/**
	 * 执行修改操作的有参SQL语句
	 * @param sql 
	 * @param parmas
	 * @return 影响的行数
	 */
	public int update(String sql,Object[] parmas){	
		conn=getConnection();
		int cnt=0;
		try {
			ps=conn.prepareStatement(sql);
			for(int i=1;i<=parmas.length;i++){
				ps.setObject(i, parmas[i-1]);
			}
			cnt=ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return cnt;
	}
	 /**
     * 执行删除操作的无参SQL语句
     * @param sql
     * @return 影响的行数
     */
	public int delete(String sql){	
		conn=getConnection();
		int cnt=0;
		try {
			ps=conn.prepareStatement(sql);
			cnt=ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return cnt;
	}
	
	/**
	 * 执行删除操作的有参SQL语句
	 * @param sql 
	 * @param parmas
	 * @return 影响的行数
	 */
	public int delete(String sql,Object[] parmas){	
		conn=getConnection();
		int cnt=0;
		try {
			ps=conn.prepareStatement(sql);
			for(int i=1;i<=parmas.length;i++){
				ps.setObject(i, parmas[i-1]);
			}
			cnt=ps.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return cnt;
	}
	
	/*--------------------------------------事务操作---------------------------------*/
	/**
	 * 开始事务
	 * @return 带手动提交的连接对象
	 */
	public Connection beginTrans(){
		conn=getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 执行带事务的带参数非查询操作
	 * @param sql 带参SQL指令
	 * @param parmas SQL参数
	 * @return 影响的行数
	 * @throws SQLException 
	 */	
	public int executeTrans(String sql,Object[] parmas) throws SQLException{
		int cnt=0;		
		ps=conn.prepareStatement(sql);
		for(int i=1;i<=parmas.length;i++){
			ps.setObject(i, parmas[i-1]);
		}
		cnt=ps.executeUpdate();				
		return cnt;
	}
	/**
	 * 执行带事务的非查询操作
	 * @param sql
	 * @return 影响的行数
	 * @throws SQLException
	 */
	public int executeTrans(String sql) throws SQLException{
		int cnt=0;		
		ps=conn.prepareStatement(sql);		
		cnt=ps.executeUpdate();			
		return cnt;
	}
	/**
	 * 提交事务
	 */
	public void commitTrans(){
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * 事务回滚
	 */
	public void rollbackTrans(){
		try {
			conn.rollback();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	/*--------------------------------------end---------------------------------*/
	
	/**
	 * 批量执行增删改操作
	 * @param sql   Sql指令
	 * @param parmas 参数集
	 * @return 执行指令的影响行数
	 */
	public int executeBatch(String sql,Object[][] parmas){
		
		conn=getConnection();
		int cnt=0;
		try {
			ps=conn.prepareStatement(sql);
			for(int i=0;i<parmas.length;i++){
				for(int j=1;j<=parmas[i].length;j++){
					ps.setObject(j, parmas[i][j-1]);
				}
				ps.addBatch();
			}
			
		    int[] num=ps.executeBatch();
		    for(int i=0;i<num.length;i++){
		    	cnt+=num[i];
		    }
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return cnt;
	}
	
	/**
	 * 执行无输出参数的存储过程
	 * @param procname
	 * @param parmas
	 * @return
	 */
	public int executePorcNoOutput(String procname,Object[] parmas){	
		conn=getConnection();
		int cnt=0;
		try {			
			CallableStatement cs=conn.prepareCall(procname);			
			for(int i=1;i<=parmas.length;i++){
				cs.setObject(i, parmas[i-1]);
			}
			cnt=cs.executeUpdate();
			close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return cnt;
	}
	
	/**
	 * 执行有输出参数的存储过程
	 * @param procname
	 * @param parmas
	 * @return
	 */
	public String executePorcOutput(String procname,Object[] parmas){	
		conn=getConnection();	
		String rezult="";
		try {			
			CallableStatement cs=conn.prepareCall(procname);
			int i=1;
			for(;i<=parmas.length;i++){
				cs.setObject(i, parmas[i-1]);
			}		
			cs.registerOutParameter(i, Types.VARCHAR);
			cs.execute();
			rezult= cs.getString(i);
			close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rezult;
	}
	
	/**
     * 执行查询操作的无参SQL语句
     * @param sql
     * @return ResultSet
     */
	public ResultSet select(String sql){	
		conn=getConnection();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			//close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rs;
	}
	
	/**
	 * 执行查询操作的有参SQL语句
	 * @param sql 
	 * @param parmas
	 * @return ResultSet
	 */
	public ResultSet select(String sql,Object[] parmas){	
		conn=getConnection();
		try {
			ps=conn.prepareStatement(sql);
			for(int i=1;i<=parmas.length;i++){
				ps.setObject(i, parmas[i-1]);
			}
			rs=ps.executeQuery();
			//close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rs;
	}
	
	/**
	 * 执行无输出参数的存储过程
	 * @param procname
	 * @param parmas
	 * @return
	 */
	public ResultSet selectPorc(String procname,Object[] parmas){	
		conn=getConnection();		
		try {			
			CallableStatement cs=conn.prepareCall(procname);			
			for(int i=1;i<=parmas.length;i++){
				cs.setObject(i, parmas[i-1]);
			}
			rs=cs.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rs;
	}
	
	
	/** 使用分页存储过程sp_page进行分页查询
	 * @param tablename 表名
	 * @param columns 查询到的字段名，多个字段用","隔开
	 * @param Where 查询条件
	 * @param pagesize 每页记录条数
	 * @param nowpage 当前页
	 * @param ordercolumn 排序字段
	 * @param ordercolumntype 排序字段类型：0-数字类型,1-字符类型,2-日期时间类型 
	 * @param order 排序方式,0-顺序,1-倒序 	 
	 * @param pagecount 总页数引用
	 * @return 当前页的结果集，使用完毕，关闭数据库对象
	 */
	public ResultSet selectPage(String tablename,String columns,String Where,
			int pagesize,int nowpage, String ordercolumn,int ordercolumntype,int order,int[] pagecount){	
		conn=getConnection();
		try {
			CallableStatement cs=conn.prepareCall("{call sp_page(?,?,?,?,?,?,?,?,?)}");
			cs.setObject(1, tablename);
			cs.setObject(2, ordercolumn);
			cs.setObject(3, ordercolumntype);
			cs.setObject(4, order);
			cs.setObject(5, columns);
			cs.setObject(6, pagesize);
			cs.setObject(7, nowpage);
			cs.setObject(8, Where);
			cs.registerOutParameter(9, Types.INTEGER);		
			cs.execute();			
			pagecount[0]=cs.getInt(9);
			rs=cs.executeQuery();
			
			//close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rs;
	}
	/**
	 * SQL分页存储过程代码：
	 * 
		CREATE PROCEDURE [dbo].[sp_page] 
		
		@strTable varchar(50), --表名 
		@strColumn varchar(50), --按该列来进行分页 
		@intColType int, --@strColumn列的类型,0-数字类型,1-字符类型,2-日期时间类型 
		@intOrder bit, --排序,0-顺序,1-倒序 
		@strColumnlist varchar(800), --要查询出的字段列表,*表示全部字段 
		@intPageSize int, --每页记录数 
		@intPageNum int, --指定页 
		@strWhere varchar(800), --查询条件 
		@intPageCount int OUTPUT --总页数 
		AS 
		DECLARE @sql nvarchar(4000) --用于构造SQL语句 
		DECLARE @where1 varchar(800) --构造条件语句 
		DECLARE @where2 varchar(800) --构造条件语句 
		IF @strWhere is null or rtrim(@strWhere)='' 
		-- 为了避免SQL关键字与字段、表名等连在一起，首先为传入的变量添加空格 
		BEGIN --没有查询条件 
		SET @where1=' WHERE ' 
		SET @where2=' ' 
		END 
		ELSE 
		BEGIN --有查询条件 
		SET @where1=' WHERE ('+@strWhere+') AND ' 
		SET @where2=' WHERE ('+@strWhere+') ' 
		END 
		
		set @strColumn = ' ' + @strColumn + ' ' 
		set @strColumnlist = ' ' + @strColumnlist + ' ' 
		--构造SQL语句，计算总页数。计算公式为 总页数 = Ceiling ( 记录个数 / 页大小 ) 
		
		SET @sql='SELECT @intPageCount=CEILING((COUNT(*)+0.0)/' 
		+ CAST(@intPageSize AS varchar) 
		+ ') FROM ' + @strTable + @where2 
		
		--执行SQL语句，计算总页数，并将其放入@intPageCount变量中 
		
		EXEC sp_executesql @sql,N'@intPageCount int OUTPUT',@intPageCount OUTPUT 
		
		--将总页数放到查询返回记录集的第一个字段前，此语句可省略 
		
		SET @strColumnlist= Cast(@intPageCount as varchar(30)) + ' as PageCount,' + 
		
		@strColumnlist 
		
		IF @intOrder=0 --构造升序的SQL 
		
		SET @sql='SELECT TOP '+ CAST(@intPageSize AS varchar) + ' ' + 
		@strColumnlist + 
		' FROM ' + @strTable + @where1 + 
		@strColumn + '>(SELECT MAX('+@strColumn+') '+ 
		' FROM (SELECT TOP '+ CAST(@intPageSize*(@intPageNum - 1) AS varchar) + 
		@strColumn + ' FROM '+ @strTable+@where2+'ORDER BY '+@strColumn+') t) ORDER BY '+ 
		
		@strColumn 
		
		ELSE --构造降序的SQL 
		
		SET @sql='SELECT TOP '+ CAST(@intPageSize AS varchar) + ' ' + 
		@strColumnlist+ 
		' FROM '+ @strTable + @where1 + 
		@strColumn + '<(SELECT MIN('+@strColumn+') '+ 
		' FROM (SELECT TOP '+ CAST(@intPageSize*(@intPageNum - 1) AS varchar) + 
		@strColumn + ' FROM '+ @strTable+@where2+'ORDER BY '+@strColumn+' DESC) t) ORDER BY '+ 
		@strColumn + ' DESC' 
		
		IF @intPageNum=1--第一页 
		
		SET @sql='SELECT TOP '+ CAST(@intPageSize AS varchar(10)) + ' ' + @strColumnlist + ' 
		
		FROM '+@strTable+ 
		@where2+' ORDER BY '+@strColumn + CASE @intOrder WHEN 0 THEN '' ELSE ' DESC' 
		
		END 
		--PRINT @sql 
		EXEC(@sql)
		GO
	 */
	
		
	public static void main(String[] args){
		//事务测试
		/*BaseDAO dao=new BaseDAO();
		dao.beginTrans();
		try{
			int a=dao.executeTrans("update BooksInfo set author='王五' where id='1000101'");
			int b=dao.executeTrans("delete from BooksInfo where id='1000102'");
			if(a>0&&b>0){
				dao.commitTrans();
			}else{
				dao.rollbackTrans();
			}
		}catch(SQLException e){
			dao.rollbackTrans();
		}*/
	}
}
