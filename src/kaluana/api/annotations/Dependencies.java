package kaluana.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to declare a component as dependent on other components.
 * Other components' name may be set at componentCategory.
 * @author hubertfonseca
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Dependencies {

	Dependency[] value();
	
}
