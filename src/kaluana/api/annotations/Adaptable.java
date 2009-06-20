package kaluana.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to declare a component as adaptable.
 * If the component is annotated, it can be replaced by another at runtime.
 * @author hubertfonseca
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Adaptable {

}
