package kaluana.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to declare a class as a Kaluana component.
 * Set its category by the field category.
 * Component's name will be the class name (except the package)
 * @author hubertfonseca
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {

	String category();
	
}
