package inf112.lilBro.app.utils;

@FunctionalInterface
public interface Function4<A, B, C, D, R> {

    /**
     * Applies function with the given parameters.
     *
     * @param a argument 1 of type {@link A}
     * @param b argument 2 of type {@link B}
     * @param c argument 3 of type {@link C}
     * @param d argument 4 of type {@link D}
     * @return result of function-call of type {@link R}
     */
    R apply(A a, B b, C c, D d);
}
