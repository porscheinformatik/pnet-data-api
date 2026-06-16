package at.porscheinformatik.happyrest.jackson;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;

/**
 * A {@link DeserializationProblemHandler} that maps unknown enum values to an {@code UNKNOWN} constant if one exists
 * on the target enum type. If no {@code UNKNOWN} constant is present, the problem is left unhandled and Jackson falls
 * back to its default behaviour (typically throwing an exception).
 */
public class UnknownEnumDeserializationHandler extends DeserializationProblemHandler {

    @Override
    public Object handleWeirdStringValue(
        DeserializationContext ctxt,
        Class<?> targetType,
        String valueToConvert,
        String failureMsg
    ) {
        if (targetType.isEnum()) {
            for (Object constant : targetType.getEnumConstants()) {
                if ("UNKNOWN".equals(((Enum<?>) constant).name())) {
                    return constant;
                }
            }
        }

        return DeserializationProblemHandler.NOT_HANDLED;
    }
}
