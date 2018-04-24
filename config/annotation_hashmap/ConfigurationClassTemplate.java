package ${package_prefix}.config;


import org.simplestartframwork.context.annotation.Config;
import org.simplestartframwork.context.annotation.PropertySource;
import org.simplestartframwork.context.annotation.Scan;
import org.simplestartframwork.data.annotation.EnableTransationProxy;


@Config
@Scan(basePackages = "org.ranger.platform")
@EnableTransationProxy
@PropertySource(value="classpath:resources/db.properties")
public class ContextConfig {
		

	

}