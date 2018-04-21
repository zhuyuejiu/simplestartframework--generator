package ${package_prefix}.mapper.provider;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * ${tableName}表Provider
 * @author ranger
 *
 */
public class ${className}Provider {
	
	/**
	 * 批量插入
	 * @param entitis -->${insertHeader}
	 * -->${providerBatchInsertValue}
	 * @return
	 */
	public String batchInsert(List<Map<String,Object>> entitis){
		
		String sql="INSERT INTO ${tableName}${insertHeader} VALUES";
		StringBuilder sb=new StringBuilder(sql);
		for(int i=0;i<entitis.size();i++){
			sb.append("(${providerBatchInsertValue})");
			if(i<(entitis.get("list").size()-1)){
				sb.append(",");
			}
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	

	
	/**
	 * 跟据ID的数组删量删除
	 * 
	 * @param ids
	 * @return
	 */
	public String deleteByIds(Map<String, Object[]> ids) {

		String sql = "DELETE FROM ${tableName} WHERE ${id} IN ";
		StringBuilder sb = new StringBuilder(sql);
		sb.append(Arrays.toString(ids.get("array")));

		sb.replace(sb.indexOf("["), sb.indexOf("[") + 1, "(");
		sb.replace(sb.indexOf("]"), sb.indexOf("]") + 1, ")");

		return sb.toString();
	}
	
	
	/**
	 * @Description 批量更新记录
	 * @param entitis
	 *            -->${batchUpdateValue}
	 * @return
	 */
	public String batchUpdate(List<Map<String,Object>> entitis){
		StringBuilder sql = new StringBuilder();
		for(int i=0;i<entitis.size();i++){
			sql.append("UPDATE ${tableName} SET ${batchUpdateValue}");
			if(i<(entitis.get("list").size()-1)){
				sql.append(",");
			}
			sql.append("WHERE ${id}=${r"#"}{${id}}");
		}
		
		return sql.toString();
	}

	
	/**
	 * 通过一组编号查询模块
	 * @param ids
	 * @return
	 */
	public String findByIds(Map<String,Object[] > ids){
		

		String sql = "SELECT * FROM ${tableName} WHERE MODULAR_ID IN ";
		StringBuilder sb = new StringBuilder(sql);
		sb.append(Arrays.toString(ids.get("array")));

		sb.replace(sb.indexOf("["), sb.indexOf("[") + 1, "(");
		sb.replace(sb.indexOf("]"), sb.indexOf("]") + 1, ")");

		return sb.toString();
	}
	
	/**
	 * 
	 * Desciption 根据传入的实体查询记录
	 * @author ranger
	 * @param entity
	 * @return
	 */
	public String findByEntity(Map<String, Object> entity){
		String base = "SElECT * FROM ${tableName} WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
	        ${where}
		}
		return sql.toString();
	}
	
	
	/**
	 * 
	 * Desciption  根据传入的实体获取到符合的记录数
	 * @author ranger

	 * @param entity
	 * @return
	 */
	public String count(Map<String, Object> entity){
		String base = "SELECT count(*) as total FROM ${tableName} a WHERE 1=1";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
			  ${where}
		}
		return sql.toString();
	}
	
	/**
	 * 
	 * Desciption 根据传入的实体模糊匹配获取对应分页的记录
	 * @author ranger
	 * @param entity
	 * @return
	 */
	public String findByEntityLikePage(Map<String, Object> entity){
		String base = "SELECT * FROM ${tableName}  WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
			${like}
			sql.append(" ORDER BY ${id} DESC");
			sql.append(" LIMIT ${r"#"}{pageIndex},${r"#"}{pageSize}");
		}
		return sql.toString();
	}
	
	
	/**
	 * 
	 * Desciption 根据传入的实体获取记录
	 * @author ranger
	 * @param entity
	 * @return
	 */
	public String findByEntityLike(Map<String, Object> entity){
		String base = "SElECT * FROM ${tableName}  WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
			${like}
		}
		return sql.toString();
	}

}
