package org.ranger.mapper.provider;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * tb_result表Provider
 * @author ranger
 *
 */
public class ResultProvider {
	
	/**
	 * 批量插入
	 * @param entitis -->(res_subject,res_score,stu_id) 
	 * -->#{list["+i+"].res_subject},#{list["+i+"].res_score},#{list["+i+"].stu_id}
	 * @return
	 */
	public String batchInsert(List<Map<String,Object>> entitis){
		
		String sql="INSERT INTO tb_result(res_subject,res_score,stu_id)  VALUES";
		StringBuilder sb=new StringBuilder(sql);
		for(int i=0;i<entitis.size();i++){
			sb.append("(#{list["+i+"].res_subject},#{list["+i+"].res_score},#{list["+i+"].stu_id})");
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

		String sql = "DELETE FROM tb_result WHERE res_id IN ";
		StringBuilder sb = new StringBuilder(sql);
		sb.append(Arrays.toString(ids.get("array")));

		sb.replace(sb.indexOf("["), sb.indexOf("[") + 1, "(");
		sb.replace(sb.indexOf("]"), sb.indexOf("]") + 1, ")");

		return sb.toString();
	}
	
	
	/**
	 * @Description 批量更新记录
	 * @param entitis
	 *            -->res_subject= #{list["+i+"].res_subject},res_score= #{list["+i+"].res_score},stu_id= #{list["+i+"].stu_id}
	 * @return
	 */
	public String batchUpdate(List<Map<String,Object>> entitis){
		StringBuilder sql = new StringBuilder();
		for(int i=0;i<entitis.size();i++){
			sql.append("UPDATE tb_result SET res_subject= #{list["+i+"].res_subject},res_score= #{list["+i+"].res_score},stu_id= #{list["+i+"].stu_id}");
			if(i<(entitis.get("list").size()-1)){
				sql.append(",");
			}
			sql.append("WHERE res_id=#{res_id}");
		}
		
		return sql.toString();
	}

	
	/**
	 * 通过一组编号查询模块
	 * @param ids
	 * @return
	 */
	public String findByIds(Map<String,Object[] > ids){
		

		String sql = "SELECT * FROM tb_result WHERE MODULAR_ID IN ";
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
		String base = "SElECT * FROM tb_result WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
	        if(entity.get("res_id")!=null){
			 sql.append("res_id=#{res_id}");
		}
		if(entity.get("res_subject")!=null){
			sql.append(" and res_subject=#{res_subject}");
		}
		if(entity.get("res_score")!=null){
			sql.append(" and res_score=#{res_score}");
		}
		if(entity.get("stu_id")!=null){
			sql.append(" and stu_id=#{stu_id}");
		}

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
		String base = "SELECT count(*) as total FROM tb_result a WHERE 1=1";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
			  if(entity.get("res_id")!=null){
			 sql.append("res_id=#{res_id}");
		}
		if(entity.get("res_subject")!=null){
			sql.append(" and res_subject=#{res_subject}");
		}
		if(entity.get("res_score")!=null){
			sql.append(" and res_score=#{res_score}");
		}
		if(entity.get("stu_id")!=null){
			sql.append(" and stu_id=#{stu_id}");
		}

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
		String base = "SELECT * FROM tb_result  WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
			if(entity.get("res_id")!=null){
			 sql.append("res_id=#{res_id}");
		}
		if(entity.get("res_subject")!=null){
			sql.append(" and res_subject  like CONCAT('%',${res_subject},'%') ");
		}
		if(entity.get("res_score")!=null){
			sql.append(" and res_score=#{res_score}");
		}
		if(entity.get("stu_id")!=null){
			sql.append(" and stu_id=#{stu_id}");
		}

			sql.append(" ORDER BY res_id DESC");
			sql.append(" LIMIT #{pageIndex},#{pageSize}");
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
		String base = "SElECT * FROM tb_result  WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
			if(entity.get("res_id")!=null){
			 sql.append("res_id=#{res_id}");
		}
		if(entity.get("res_subject")!=null){
			sql.append(" and res_subject  like CONCAT('%',${res_subject},'%') ");
		}
		if(entity.get("res_score")!=null){
			sql.append(" and res_score=#{res_score}");
		}
		if(entity.get("stu_id")!=null){
			sql.append(" and stu_id=#{stu_id}");
		}

		}
		return sql.toString();
	}

}
