package com.repast.core.util;

import java.util.ArrayList;
import java.util.List;

import com.repast.core.system.BaseEntity;


public class JDBCHelper {

    public static final String EQ = " = ";

    public static final String NEQ = " != ";

    public static final String LT = " < ";

    public static final String LTQ = " <= ";

    public static final String GT = " > ";

    public static final String GTQ = " >= ";

    public static final String LIKE = " like ";

    public static final String IN = " IN ";

    public static final String AND = " AND ";

    public static final String OR = " OR ";

    public static final String ASC = " ASC ";

    public static final String DESC = " DESC ";

    public final static String QUERY_COUNT_LEFT = "SELECT COUNT(1) FROM ( ";

    public final static String QUERY_COUNT_RIGHT = " ) RES";
    
    public final static  String COMMA = " , ";

    private List paramList;

    private String sql;

    private String orderBy;
    
    private String defaultOrderBy;
    
    //动态排序标识串
    private String dynamicrOrderBy;

    public JDBCHelper(String sql)
    {
        this.sql = sql;
        paramList = new ArrayList();
        orderBy = "";
    }

    public void putOrder(String orderStr)
    {
    	if(orderStr!=null&&!"".equals(orderStr.trim()))
    	{
    			orderBy += orderStr;
    	}
    }
    
    /**
     * 增加统一实体对象排序处理
     * @param entity
     */
    public void putOrder(BaseEntity entity){
    	if(entity!=null){
    		String orderStr = entity.getBase_order_string();
    		if(orderStr!=null&&!orderStr.equals(""))
    			dynamicrOrderBy=orderStr;
    	}
    }

    public void putParam(String join, String columName, String ref, Object columValue)
    {
        if (columValue != null && !String.valueOf(columValue).equals(""))
        {
            if (ref.equals(LIKE))
            {
                String tmpStr = (String) columValue;
                tmpStr = tmpStr.replaceAll("'", "''").replaceAll("\\\\", "\\\\\\\\").replaceAll("_", "\\\\_")
                        .replaceAll("%", "\\\\%");
                String operator = " LIKE '%" + tmpStr + "%' ESCAPE '\\\\' ";

                sql = sql + join + columName + operator;
            }else if(ref.equals(IN)){
            	String str=(String)columValue;
            	String[] strArr = str.split(",");
            	if(strArr.length>0)
            	{
            		StringBuffer bf = new StringBuffer(5000);
            		bf.append(" ('',");
            		for(int i=0;i<strArr.length;i++)
            		{
            			/**bf.append("?").append(",");
            			paramList.add(columValue);*/
            			bf.append("'").append(strArr[i]).append("'").append(",");
            		}
            		bf.append("'') ");
            		sql = sql + join + columName + " IN "+ bf.toString();
            	}
            }else
            {
                sql = sql + join + columName + ref + " ? ";
                paramList.add(columValue);
            }

        }
    }
    /**
     * @description 
     * @author qijianjun
     * @updateAuthor qijianjun
     * @param join
     * @param columName
     * @param ref
     * @param columValue
     * @created 2014年9月26日 下午3:10:43
     */
    public void putParam(String join, String ref, Object columValue,String... columNames)
    {
    	
        if (columValue != null && !String.valueOf(columValue).equals(""))
        {
        	if(columNames!=null&&columNames.length>0)
        	{
        		String sqlTemp="( ";
        		for(int i=0;i<columNames.length;i++)
        		{
        			if (ref.equals(LIKE))
                    {
                        String tmpStr = (String) columValue;
                        tmpStr = tmpStr.replaceAll("'", "''").replaceAll("\\\\", "\\\\\\\\").replaceAll("_", "\\\\_")
                                .replaceAll("%", "\\\\%");
                        String operator = " LIKE '%" + tmpStr + "%' ESCAPE '\\\\' ";
                        sqlTemp += columNames[i] + operator;
                    }
                    else
                    {
                    	sqlTemp +=   columNames[i] + ref + " ? ";
                        paramList.add(columValue);
                    }
        			
        			if(i<columNames.length-1)
        			{
        				sqlTemp += " or ";
        			}
        		}
        		sqlTemp+=" ) ";
        		
        		sql+= join + sqlTemp;
        	}
            

        }
    }

    public String getSql()
    {
        String orderSql = "";
        
        if(dynamicrOrderBy!=null){
        	 orderSql = "ORDER BY " + dynamicrOrderBy ;
        	 if(defaultOrderBy!=null&&!"".equals(defaultOrderBy))
        		 orderSql = orderSql+","+defaultOrderBy;
        }else{
        
	        if (orderBy!=null&&!"".equals(orderBy)&orderBy.length() > 0)
	        {
	            orderSql = "ORDER BY " + orderBy;
	        }else{
	        	if(defaultOrderBy!=null&&!"".equals(defaultOrderBy))
	        		orderSql = "ORDER BY " +defaultOrderBy;
	        }
        }

        return sql + orderSql;
    }

    public Object[] getParam()
    {
        if(paramList!=null&&paramList.size()>0)
        {
        Object[] paramsArray = paramList.toArray(new Object[] {});
        return paramsArray;
        }else{
            return null;
        }
    }

	public String getDefaultOrderBy() {
		return defaultOrderBy;
	}

	public void setDefaultOrderBy(String defaultOrderBy) {
		this.defaultOrderBy = defaultOrderBy;
	}

    public String getUpdSql()
    {
        return sql.trim().replaceAll("^,*|,*$", "");
    }
	
}
