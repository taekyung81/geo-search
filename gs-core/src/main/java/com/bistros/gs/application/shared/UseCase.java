package com.bistros.gs.application.shared;

import java.util.function.Function;

public interface UseCase<T extends RequestUseCase, R extends ResponseUseCase> extends Function<T, R> {

}
