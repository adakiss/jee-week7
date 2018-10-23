package hu.adamkiss.hoeweek7.service.validator;

public abstract class BaseValidator {

    protected void validateParamIsNotNull(Object param) {
        if (param == null) {
            throw new NullPointerException();
        }
    }
}
