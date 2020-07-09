package com.repast.core.uiview;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.repast.core.system.BaseDao;

@Component("UIDAO")
public class UIDAO extends BaseDao {
	public List<Object> querySelectTable(SelectConfig config) {

		return null;
	}

	private class OptionRowMapper implements RowMapper {

		public Object mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {
			Option vo = new Option();
			vo.setText((String) rs.getString("TEXT"));
			vo.setValue((String) rs.getString("VALUE"));
			return vo;
		}

	}

	public List<Option> queryParameterListByTable(String tableName,String text, String value,String wheresql ) {
		String sql = "SELECT T." + value.trim().toUpperCase() + " VALUE,T."
				+ text.trim().toUpperCase() + " TEXT  FROM " + tableName
				+ " T WHERE 1=1 ";
		if(wheresql!=null&&!"".equals(wheresql)){
			sql += wheresql;
		}
		Object[] params = null;
		List res = this.getJdbcTemplate().query(sql, params,new OptionRowMapper());
		return res;
	}
}
