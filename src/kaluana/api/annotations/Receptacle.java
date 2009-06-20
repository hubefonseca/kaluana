package kaluana.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to declare a field as a receptacle to other components'
 * services. The component's receptacle name will be this field's name
 * @author hubertfonseca
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Receptacle {
	
}