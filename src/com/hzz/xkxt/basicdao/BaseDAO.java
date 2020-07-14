package com.hzz.xkxt.basicdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * JDBC���ݷ�����
 * @author gudh 
 * 2019-4-9
 */
public class BaseDAO  extends DBConn {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	/**
	 * �رղ��ͷ�������Դ
	 */
	public void close(){
		closeDB(conn, ps,rs);
	}
	
    /**
     * ִ����Ӳ������޲�SQL���
     * @param sql
     * @return Ӱ�������
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
	 * ִ����Ӳ������в�SQL���
	 * @param sql 
	 * @param parmas
	 * @return Ӱ�������
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
     * ִ���޸Ĳ������޲�SQL���
     * @param sql
     * @return Ӱ�������
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
	 * ִ���޸Ĳ������в�SQL���
	 * @param sql 
	 * @param parmas
	 * @return Ӱ�������
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
     * ִ��ɾ���������޲�SQL���
     * @param sql
     * @return Ӱ�������
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
	 * ִ��ɾ���������в�SQL���
	 * @param sql 
	 * @param parmas
	 * @return Ӱ�������
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
	
	/*--------------------------------------�������---------------------------------*/
	/**
	 * ��ʼ����
	 * @return ���ֶ��ύ�����Ӷ���
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
	 * ִ�д�����Ĵ������ǲ�ѯ����
	 * @param sql ����SQLָ��
	 * @param parmas SQL����
	 * @return Ӱ�������
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
	 * ִ�д�����ķǲ�ѯ����
	 * @param sql
	 * @return Ӱ�������
	 * @throws SQLException
	 */
	public int executeTrans(String sql) throws SQLException{
		int cnt=0;		
		ps=conn.prepareStatement(sql);		
		cnt=ps.executeUpdate();			
		return cnt;
	}
	/**
	 * �ύ����
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
	 * ����ع�
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
	 * ����ִ����ɾ�Ĳ���
	 * @param sql   Sqlָ��
	 * @param parmas ������
	 * @return ִ��ָ���Ӱ������
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
	 * ִ������������Ĵ洢����
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
	 * ִ������������Ĵ洢����
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
     * ִ�в�ѯ�������޲�SQL���
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
	 * ִ�в�ѯ�������в�SQL���
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
	 * ִ������������Ĵ洢����
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
	
	
	/** ʹ�÷�ҳ�洢����sp_page���з�ҳ��ѯ
	 * @param tablename ����
	 * @param columns ��ѯ�����ֶ���������ֶ���","����
	 * @param Where ��ѯ����
	 * @param pagesize ÿҳ��¼����
	 * @param nowpage ��ǰҳ
	 * @param ordercolumn �����ֶ�
	 * @param ordercolumntype �����ֶ����ͣ�0-��������,1-�ַ�����,2-����ʱ������ 
	 * @param order ����ʽ,0-˳��,1-���� 	 
	 * @param pagecount ��ҳ������
	 * @return ��ǰҳ�Ľ������ʹ����ϣ��ر����ݿ����
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
	 * SQL��ҳ�洢���̴��룺
	 * 
		CREATE PROCEDURE [dbo].[sp_page] 
		
		@strTable varchar(50), --���� 
		@strColumn varchar(50), --�����������з�ҳ 
		@intColType int, --@strColumn�е�����,0-��������,1-�ַ�����,2-����ʱ������ 
		@intOrder bit, --����,0-˳��,1-���� 
		@strColumnlist varchar(800), --Ҫ��ѯ�����ֶ��б�,*��ʾȫ���ֶ� 
		@intPageSize int, --ÿҳ��¼�� 
		@intPageNum int, --ָ��ҳ 
		@strWhere varchar(800), --��ѯ���� 
		@intPageCount int OUTPUT --��ҳ�� 
		AS 
		DECLARE @sql nvarchar(4000) --���ڹ���SQL��� 
		DECLARE @where1 varchar(800) --����������� 
		DECLARE @where2 varchar(800) --����������� 
		IF @strWhere is null or rtrim(@strWhere)='' 
		-- Ϊ�˱���SQL�ؼ������ֶΡ�����������һ������Ϊ����ı�����ӿո� 
		BEGIN --û�в�ѯ���� 
		SET @where1=' WHERE ' 
		SET @where2=' ' 
		END 
		ELSE 
		BEGIN --�в�ѯ���� 
		SET @where1=' WHERE ('+@strWhere+') AND ' 
		SET @where2=' WHERE ('+@strWhere+') ' 
		END 
		
		set @strColumn = ' ' + @strColumn + ' ' 
		set @strColumnlist = ' ' + @strColumnlist + ' ' 
		--����SQL��䣬������ҳ�������㹫ʽΪ ��ҳ�� = Ceiling ( ��¼���� / ҳ��С ) 
		
		SET @sql='SELECT @intPageCount=CEILING((COUNT(*)+0.0)/' 
		+ CAST(@intPageSize AS varchar) 
		+ ') FROM ' + @strTable + @where2 
		
		--ִ��SQL��䣬������ҳ�������������@intPageCount������ 
		
		EXEC sp_executesql @sql,N'@intPageCount int OUTPUT',@intPageCount OUTPUT 
		
		--����ҳ���ŵ���ѯ���ؼ�¼���ĵ�һ���ֶ�ǰ��������ʡ�� 
		
		SET @strColumnlist= Cast(@intPageCount as varchar(30)) + ' as PageCount,' + 
		
		@strColumnlist 
		
		IF @intOrder=0 --���������SQL 
		
		SET @sql='SELECT TOP '+ CAST(@intPageSize AS varchar) + ' ' + 
		@strColumnlist + 
		' FROM ' + @strTable + @where1 + 
		@strColumn + '>(SELECT MAX('+@strColumn+') '+ 
		' FROM (SELECT TOP '+ CAST(@intPageSize*(@intPageNum - 1) AS varchar) + 
		@strColumn + ' FROM '+ @strTable+@where2+'ORDER BY '+@strColumn+') t) ORDER BY '+ 
		
		@strColumn 
		
		ELSE --���콵���SQL 
		
		SET @sql='SELECT TOP '+ CAST(@intPageSize AS varchar) + ' ' + 
		@strColumnlist+ 
		' FROM '+ @strTable + @where1 + 
		@strColumn + '<(SELECT MIN('+@strColumn+') '+ 
		' FROM (SELECT TOP '+ CAST(@intPageSize*(@intPageNum - 1) AS varchar) + 
		@strColumn + ' FROM '+ @strTable+@where2+'ORDER BY '+@strColumn+' DESC) t) ORDER BY '+ 
		@strColumn + ' DESC' 
		
		IF @intPageNum=1--��һҳ 
		
		SET @sql='SELECT TOP '+ CAST(@intPageSize AS varchar(10)) + ' ' + @strColumnlist + ' 
		
		FROM '+@strTable+ 
		@where2+' ORDER BY '+@strColumn + CASE @intOrder WHEN 0 THEN '' ELSE ' DESC' 
		
		END 
		--PRINT @sql 
		EXEC(@sql)
		GO
	 */
	
		
	public static void main(String[] args){
		//�������
		/*BaseDAO dao=new BaseDAO();
		dao.beginTrans();
		try{
			int a=dao.executeTrans("update BooksInfo set author='����' where id='1000101'");
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
