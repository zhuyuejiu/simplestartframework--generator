package ${package_prefix}.action;

import org.simplestartframwork.context.annotation.Inject;
import org.simplestartframwork.context.annotation.component.Action;
import org.simplestartframwork.web.annotation.PathMapping;
import org.simplestartframwork.context.annotation.Scope;



@Action
@Scope("request")
@PathMapping(value="/${feildName}")
public class ${className}Action {

}