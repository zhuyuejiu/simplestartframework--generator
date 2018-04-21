package ${package_prefix}.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteBuilder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertBuilder;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectBuilder;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateBuilder;
import org.apache.ibatis.session.RowBounds;
import org.simplestartframwork.context.annotation.component.Persistent;
import org.simplestartframwork.data.annotation.SQL;
import org.simplestartframwork.data.annotation.SQLBuilder;
import org.simplestartframwork.data.annotation.SQL.SQLType;
import org.springframework.stereotype.Repository;

import com.gb.mapper.Builder.ModularBuilder;

import ${package_prefix}.mapper.Builder.${className}Builder;

/**
 * ${tableName}表操作映射接口
 * @author ranger
 *
 */
@Persistent
public interface ${className}Mapper {
	
	/**
	 * @Description 插入记录
	 * @param entity -->${insertHeader}
	 * 
	 * @return
	 */
	@SQL(type=SQLType.INSERT,value="INSERT INTO ${tableName}${insertHeader} VALUES (${insertValue})")
	@Options(backfillParmaryKey=true,columnName="${id}",propteryName="${id}")
	int insert(Map<String,Object> entity);
	
	 /**
	  * @Description 批量插入记录 
	  * @param entitis -->${insertHeader}
	  * @return
	  */
	@SQLBuilder(type=${className}Builder.class,method="batchInsert")
	int batchInsert(List<Map<String,Object>> entitis);
	
	/**
	 * @Description 根据一组ID删除记录
	 * @param entity -->${insertHeader}
	 * @return
	 */
	@SQLBuilder(type=${className}Builder.class,method="deleteByIds")
	int deleteByIds(Object[] ids);
	
	/**
	 * @Description 根据ID删除记录
	 * @param id
	 * @return
	 */
	@SQL(type=SQLType.DELETE,value="DELETE FROM ${tableName} WHERE ${id}=${r"#"}{id}")
	int deleteById(Object id);
	
	 /**
	  * @Description 更新记录
	  * @param entity -->${insertHeader}
	  * @return
	  */
	@SQL(type=SQLType.UPDATE,value="UPDATE ${tableName} SET ${updateValue} WHERE ${id}=${r"#"}{${id}} ")
	int update(Map<String,Object> entity);
	
	 /**
	  * @Description 批量更新记录
	  * @param entitis -->${insertHeader}
	  * @return
	  */
	@SQLBuilder(type=${className}Builder.class,method="batchUpdate")
	int batchUpdate(List<Map<String,Object>> entitis);
	
	 /**
	  * @Description 查询所有记录 
	  * @return
	  */
	@SQL(type=SQLType.SELECT,value="SELECT * FROM ${tableName}")
	List<Map<String,Object>> findByAll();
	
	/**
	 * @Description 根据id查询数据记录
	 * 
	 * @param id
	 * @return
	 */
	@SQL(type=SQLType.SELECT,value="SELECT * FROM ${tableName} WHERE ${id}=${r"#"}{id}")
	Map<String,Object> findById(Object id);
	
	/**
	 *  @Description 通过一组Id查询记录
	 * @param ids
	 * @return
	 */
	@SQL(type=SQLType.SELECT,type=${className}Builder.class,method="findByIds")
	List<Map<String,Object>>  findByIds(Object... ids);
	

	/**
	 * @Description  根据传入的实体查询记录
	 * @param entity
	 * @return
	 */
	@SQLBuilder(type=${className}Builder.class,method="findByEntity")
	List<Map<String, Object>> findByEntity(Map<String, Object> entity);
	/**
	 * 
	 * @Description  根据传入的实体获取到符合的记录数
	 * @param entity
	 * @return
	 */
	@SQLBuilder(type=${className}Builder.class,method="count")
	int count(Map<String, Object> entity);
	/**
	 * 
	 *@Description  根据传入的实体模糊匹配获取对应分页的记录
	 * @param entity
	 * @return
	 */
	@SQLBuilder(type=${className}Builder.class,method="findByEntityLikePage")
	List<Map<String, Object>> findByEntityLikePage(Map<String, Object> entity);
	/**
	 * 
	 * @Description  根据传入的实体获取记录
	 * @param entity
	 * @return
	 */
	@SQLBuilder(type=${className}Builder.class,method="findByEntityLike")
	List<Map<String, Object>> findByEntityLike(Map<String, Object> entity);
	

}