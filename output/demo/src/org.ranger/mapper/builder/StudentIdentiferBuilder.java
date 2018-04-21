package org.ranger.mapper.provider;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * tb_student_identifer表Provider
 * @author ranger
 *
 */
public class StudentIdentiferProvider {
	
	/**
	 * 批量插入
	 * @param entitis -->(stu_idcard,stu_code) 
	 * -->#{list["+i+"].stu_idcard},#{list["+i+"].stu_code}
	 * @return
	 */
	public String batchInsert(List<Map<String,Object>> entitis){
		
		String sql="INSERT INTO tb_student_identifer(stu_idcard,stu_code)  VALUES";
		StringBuilder sb=new StringBuilder(sql);
		for(int i=0;i<entitis.size();i++){
			sb.append("(#{list["+i+"].stu_idcard},#{list["+i+"].stu_code})");
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

		String sql = "DELETE FROM tb_student_identifer WHERE stu_id IN ";
		StringBuilder sb = new StringBuilder(sql);
		sb.append(Arrays.toString(ids.get("array")));

		sb.replace(sb.indexOf("["), sb.indexOf("[") + 1, "(");
		sb.replace(sb.indexOf("]"), sb.indexOf("]") + 1, ")");

		return sb.toString();
	}
	
	
	/**
	 * @Description 批量更新记录
	 * @param entitis
	 *            -->stu_idcard= #{list["+i+"].stu_idcard},stu_code= #{list["+i+"].stu_code}
	 * @return
	 */
	public String batchUpdate(List<Map<String,Object>> entitis){
		StringBuilder sql = new StringBuilder();
		for(int i=0;i<entitis.size();i++){
			sql.append("UPDATE tb_student_identifer SET stu_idcard= #{list["+i+"].stu_idcard},stu_code= #{list["+i+"].stu_code}");
			if(i<(entitis.get("list").size()-1)){
				sql.append(",");
			}
			sql.append("WHERE stu_id=#{stu_id}");
		}
		
		return sql.toString();
	}

	
	/**
	 * 通过一组编号查询模块
	 * @param ids
	 * @return
	 */
	public String findByIds(Map<String,Object[] > ids){
		

		String sql = "SELECT * FROM tb_student_identifer WHERE MODULAR_ID IN ";
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
		String base = "SElECT * FROM tb_student_identifer WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
	        if(entity.get("stu_id")!=null){
			 sql.append("stu_id=#{stu_id}");
		}
		if(entity.get("stu_idcard")!=null){
			sql.append(" and stu_idcard=#{stu_idcard}");
		}
		if(entity.get("stu_code")!=null){
			sql.append(" and stu_code=#{stu_code}");
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
		String base = "SELECT count(*) as total FROM tb_student_identifer a WHERE 1=1";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
			  if(entity.get("stu_id")!=null){
			 sql.append("stu_id=#{stu_id}");
		}
		if(entity.get("stu_idcard")!=null){
			sql.append(" and stu_idcard=#{stu_idcard}");
		}
		if(entity.get("stu_code")!=null){
			sql.append(" and stu_code=#{stu_code}");
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
		String base = "SELECT * FROM tb_student_identifer  WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
			if(entity.get("stu_id")!=null){
			 sql.append("stu_id=#{stu_id}");
		}
		if(entity.get("stu_idcard")!=null){
			sql.append(" and stu_idcard  like CONCAT('%',${stu_idcard},'%') ");
		}
		if(entity.get("stu_code")!=null){
			sql.append(" and stu_code  like CONCAT('%',${stu_code},'%') ");
		}

			sql.append(" ORDER BY stu_id DESC");
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
		String base = "SElECT * FROM tb_student_identifer  WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(base);
		if(entity != null){
			if(entity.get("stu_id")!=null){
			 sql.append("stu_id=#{stu_id}");
		}
		if(entity.get("stu_idcard")!=null){
			sql.append(" and stu_idcard  like CONCAT('%',${stu_idcard},'%') ");
		}
		if(entity.get("stu_code")!=null){
			sql.append(" and stu_code  like CONCAT('%',${stu_code},'%') ");
		}

		}
		return sql.toString();
	}

}
